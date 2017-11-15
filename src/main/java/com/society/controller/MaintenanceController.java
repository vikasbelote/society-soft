package com.society.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.society.SocietyApp;
import com.society.helper.model.BreadCrumb;
import com.society.model.domain.MaintenanceCycleReceiptDomain;
import com.society.model.domain.MaintenanceDomain;
import com.society.model.domain.MaintenanceTableDomain;
import com.society.service.MaintenanceService;

@Controller
public class MaintenanceController extends BaseController {
	
	private static final Logger logger = LogManager.getLogger(SocietyApp.class);
	
	@Autowired
	private MaintenanceService maintenanceService;
	
	@RequestMapping(value = "maintaince", method = RequestMethod.GET)
	public ModelAndView getMaintenance(@ModelAttribute("maintenanceDomain")MaintenanceDomain maintenanceDomain, HttpSession session) {
		
		String[] breadCrumbs = {"Report", "Member Mainenacne", "Create"};
		List<BreadCrumb> breadCrumbList = breadCrumbHelper.getBreadCrumbList(breadCrumbs);
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		MaintenanceTableDomain maintenanceTable = null;
		if(BooleanUtils.isTrue(maintenanceDomain.getGetMaintenanceTable())) {
			logger.info("creating maintenance table");
			maintenanceTable = maintenanceService.getMaintenanceTableList(maintenanceDomain, societyId);
		}
		
		List<String> cycleDateList = maintenanceService.getCycleDateList(maintenanceDomain,societyId);
		ModelAndView modelAndView = new ModelAndView("maintenanceCreate", "maintenanceDomain", maintenanceDomain);
		modelAndView.addObject(breadCrumbList);
		modelAndView.addObject("cycleDateList", cycleDateList);
		modelAndView.addObject("maintenanceTable", maintenanceTable);
		return modelAndView;
	}
	
	@RequestMapping(value = {"maintaince"}, method = RequestMethod.POST)
	public String postMaintenance(@ModelAttribute("maintenanceDomain")MaintenanceDomain maintenanceDomain, 
			RedirectAttributes redirectAttributes, HttpSession session) {
		
		//check the cycle is already exit
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		maintenanceDomain.setSocietyId(societyId);
		
		redirectAttributes.addFlashAttribute("maintenanceDomain", maintenanceDomain);
		if(maintenanceService.checkPaymentCycleExist(maintenanceDomain))
			maintenanceDomain.setCycleExist(true);
		else
			maintenanceDomain.setGetMaintenanceTable(true);
		return "redirect:/maintaince";
	}
	
	@RequestMapping(value = "downloadReceipt", method = RequestMethod.GET)
	public void downloadMaintenanceRceipt(@RequestParam(value="id", required=true)Integer cycleId, HttpSession session, HttpServletResponse response) {
		
		InputStream fileInputStream = null;
		File receiptFile = null;
		try {
			String path = session.getServletContext().getRealPath("/") + "/maintenance_receipt.pdf";
		    receiptFile = maintenanceService.getDownloadReceipt(cycleId, path);
			
			if(receiptFile != null) {
			    fileInputStream = new FileInputStream(receiptFile);
				OutputStream responseOutputStream = response.getOutputStream();
				
				byte[] buf = new byte[8192];
				int c = 0;
		        while ((c = fileInputStream.read(buf, 0, buf.length)) > 0) {
		        	responseOutputStream.write(buf, 0, c);
		        	responseOutputStream.flush();
		        }
				
				response.setContentType("application/pdf");
		        response.setHeader("Content-disposition", "attachment; filename=receipt.pdf");
		        response.getOutputStream().flush();
			}
		} 
		catch (FileNotFoundException e) {
			logger.error("File Not Found " + e.getMessage());
		}
		catch (IOException e) {
			logger.error("IO Exception is occurred " + e.getMessage());
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
		finally{
			if(fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			if(receiptFile != null)
				receiptFile.delete();
		}
	}

	@RequestMapping(value = "viewMaintenanceReport")
	public ModelAndView viewMaintenanceReport(HttpSession session) {
		
		String[] breadCrumbs = {"Report", "Member Maintenance", "View"};
		List<BreadCrumb> breadCrumbList = breadCrumbHelper.getBreadCrumbList(breadCrumbs);
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		List<MaintenanceCycleReceiptDomain> cycleList = maintenanceService.getMaintenacneCycleList(societyId);
		
		ModelAndView modelAndView = new ModelAndView("maintenanceView");
		modelAndView.addObject(breadCrumbList);
		modelAndView.addObject("cycleList", cycleList);
		return modelAndView;
	}
	
	@RequestMapping(value = "viewCycleDetails")
	public ModelAndView viewCycleDetails(@RequestParam(value="id", required=true)Integer cycleId) {
		
		String[] breadCrumbs = {"Report", "Member Mainenacne", "View"};
		List<BreadCrumb> breadCrumbList = breadCrumbHelper.getBreadCrumbList(breadCrumbs);
		
		MaintenanceCycleReceiptDomain cycle = maintenanceService.getCycleDetails(cycleId);
		
		ModelAndView modelAndView = new ModelAndView("maintenanceViewTable");
		modelAndView.addObject(breadCrumbList);
		modelAndView.addObject("cycle", cycle);
		return modelAndView;
		
	}
	
	@RequestMapping(value = "saveMaintenanceData", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Integer> saveMaintenanceData(@RequestBody MaintenanceCycleReceiptDomain maintenanceCycleReceiptDomain, HttpSession session, HttpServletResponse response) {
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		maintenanceCycleReceiptDomain.setSocietyId(societyId);
		
		if(!(maintenanceService.saveMaintenanceData(maintenanceCycleReceiptDomain)))
			throw new RuntimeException("exception");

		return new ResponseEntity<Integer>(1, HttpStatus.OK);
	}
	
	@RequestMapping(value = "deleteCycleDetais")
	public String deleteCycleDetails(@RequestParam(value="id", required=true)Integer cycleId, RedirectAttributes redirectAttributes) {
		
		if(maintenanceService.deleteCycle(cycleId)) {
			redirectAttributes.addFlashAttribute("deleted", true);
		}
		else {
			redirectAttributes.addFlashAttribute("deleted", false);
		}
		return "redirect:/viewMaintenanceReport";
	}
}

