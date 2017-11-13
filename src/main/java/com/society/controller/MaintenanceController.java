package com.society.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import com.society.model.domain.GeneralHeadDomain;
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
	
	@RequestMapping(value = "saveMaintenanceData", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_PDF_VALUE })
	public ResponseEntity<?> getMemberRow(@RequestBody MaintenanceCycleReceiptDomain maintenanceCycleReceiptDomain, HttpSession session, HttpServletResponse response) {
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		maintenanceCycleReceiptDomain.setSocietyId(societyId);
		
//		if(maintenanceService.saveMaintenanceData(maintenanceCycleReceiptDomain))
//			maintenanceService.updateCycle(maintenanceCycleReceiptDomain);
//		else
//			throw new RuntimeException("exception");
		
		String path = session.getServletContext().getRealPath("/") + "/maintenance_receipt.pdf";
		File receiptFile = new File(path);
		try {
			FileOutputStream fs = new FileOutputStream(receiptFile);
			fs.write(1);
			fs.close();
			
			byte[] b = new byte[(int) receiptFile.length()];
			FileInputStream fi = new FileInputStream(receiptFile);
			fi.read(b);
			
			fi.close();
			
			response.setContentType("application/pdf");
	        response.setHeader("Content-disposition", "attachment; filename=receipt.pdf");
	        response.setContentLength(b.length);

	        response.getOutputStream().write(b);
	        response.getOutputStream().flush();
	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.parseMediaType("application/pdf"));
	        headers.setContentDispositionFormData("inline", "Report.pdf");
	        
	        return new ResponseEntity<byte[]>(b, headers, HttpStatus.OK);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Hello", HttpStatus.OK);
		

//
//		ModelAndView modelAndView = new ModelAndView("maintenanceReceipt");
//		modelAndView.addObject("maintenanceCycleReceiptDomain", maintenanceCycleReceiptDomain);
//		return modelAndView;
	}
	
	@RequestMapping(value = "viewMaintenanceReport")
	public ModelAndView viewMaintenanceReport(HttpSession session) {
		
		String[] breadCrumbs = {"Report", "Member Mainenacne", "View"};
		List<BreadCrumb> breadCrumbList = breadCrumbHelper.getBreadCrumbList(breadCrumbs);
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		List<MaintenanceCycleReceiptDomain> cycleList = maintenanceService.getMaintenacneCycleList(societyId);
		
		ModelAndView modelAndView = new ModelAndView("viewMaintenanceReport");
		modelAndView.addObject(breadCrumbList);
		modelAndView.addObject("cycleList", cycleList);
		return modelAndView;
	}
	
	/*@RequestMapping(value = "viewCycleDetails")
	public ModelAndView viewCycleDetails(@RequestParam(value="id", required=true)Integer cycleId) {
		
		String[] breadCrumbs = {"Report", "Member Mainenacne", "View"};
		List<BreadCrumb> breadCrumbList = breadCrumbHelper.getBreadCrumbList(breadCrumbs);
		
		MaintenanceCycleReceiptDomain cycle = maintenanceService.getCycleDetails(cycleId);
		
		ModelAndView modelAndView = new ModelAndView("viewMaintenanceTable");
		modelAndView.addObject(breadCrumbList);
		modelAndView.addObject("cycle", cycle);
		return modelAndView;
		
	}*/
	
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

