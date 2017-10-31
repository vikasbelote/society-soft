package com.society.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.society.helper.model.BreadCrumb;
import com.society.model.domain.GeneralHeadDomain;
import com.society.model.domain.MaintenanceCycleReceiptDomain;
import com.society.model.domain.MaintenanceDomain;
import com.society.model.domain.MaintenanceTableDomain;
import com.society.service.MaintenanceService;

@Controller
public class MaintenanceController extends BaseController {
	
	@Autowired
	private MaintenanceService maintenanceService;
	
	@RequestMapping(value = "maintaince", method = RequestMethod.GET)
	public ModelAndView getMaintenance(@ModelAttribute("maintenanceDomain")MaintenanceDomain maintenanceDomain, HttpSession session) {
		
		String[] breadCrumbs = {"Report", "Member Mainenacne", "Create"};
		List<BreadCrumb> breadCrumbList = breadCrumbHelper.getBreadCrumbList(breadCrumbs);
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		List<List<GeneralHeadDomain>> generalHeadList = maintenanceService.getGeneralHeadList(societyId); 
		
		List<String> cycleDateList = maintenanceService.getCycleDateList(maintenanceDomain,societyId);
				
		ModelAndView modelAndView = new ModelAndView("maintaince", "maintenanceDomain", maintenanceDomain);
		modelAndView.addObject(breadCrumbList);
		modelAndView.addObject("generalHeadList", generalHeadList);
		modelAndView.addObject("cycleDateList", cycleDateList);
		return modelAndView;
	}
	
	@RequestMapping(value = {"maintaince"}, method = RequestMethod.POST)
	public String postMaintenance(@ModelAttribute("maintenanceDomain")MaintenanceDomain maintenanceDomain, 
			RedirectAttributes redirectAttributes, HttpSession session) {
		
		//check the cycle is already exit
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		maintenanceDomain.setSocietyId(societyId);
		if(maintenanceService.checkPaymentCycleExist(maintenanceDomain)) {
			redirectAttributes.addFlashAttribute("maintenanceDomain", maintenanceDomain);
			redirectAttributes.addFlashAttribute("cycleExist", true);
			return "redirect:/maintaince";
		}
			
		
		redirectAttributes.addFlashAttribute("maintenanceDomain", maintenanceDomain);
		return "redirect:/maintenanceTable";
	}
	
	@RequestMapping(value = "maintenanceTable", method = RequestMethod.GET)
	public ModelAndView getMaintainceTable(@ModelAttribute("maintenanceDomain")MaintenanceDomain maintenanceDomain, HttpSession session) {
		
		String[] breadCrumbs = {"Report", "Member Mainenacne", "Create"};
		List<BreadCrumb> breadCrumbList = breadCrumbHelper.getBreadCrumbList(breadCrumbs);
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		if(maintenanceDomain.getPaymentDueDate() == null && maintenanceDomain.getGeneralHeadChargeMap() == null) {
			List<List<GeneralHeadDomain>> generalHeadList = maintenanceService.getGeneralHeadList(societyId); 
			
			maintenanceDomain.setSocietyId(societyId);
			List<String> cycleDateList = maintenanceService.getCycleDateList(maintenanceDomain, societyId);
			
			ModelAndView modelAndView = new ModelAndView("maintaince", "maintenanceDomain", maintenanceDomain);
			modelAndView.addObject(breadCrumbList);
			modelAndView.addObject("generalHeadList", generalHeadList);
			modelAndView.addObject("cycleDateList", cycleDateList);
			return modelAndView;
		}
		
		MaintenanceTableDomain maintenanceTable = maintenanceService.getMaintenanceTableList(maintenanceDomain, societyId);
		
		ModelAndView modelAndView = new ModelAndView("maintenanceTable");
		modelAndView.addObject(breadCrumbList);
		modelAndView.addObject("maintenanceTable", maintenanceTable);
		return modelAndView;
	}
	
	@RequestMapping(value = "saveMaintenanceData", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ModelAndView getMemberRow(@RequestBody MaintenanceCycleReceiptDomain maintenanceCycleReceiptDomain, HttpSession session) {
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		maintenanceCycleReceiptDomain.setSocietyId(societyId);
		if(maintenanceService.saveMaintenanceData(maintenanceCycleReceiptDomain))
			maintenanceService.updateCycle(maintenanceCycleReceiptDomain);
		else
			throw new RuntimeException("exception");

		ModelAndView modelAndView = new ModelAndView("maintenanceReceipt");
		modelAndView.addObject("maintenanceCycleReceiptDomain", maintenanceCycleReceiptDomain);
		return modelAndView;
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
	
	@RequestMapping(value = "viewCycleDetails")
	public ModelAndView viewCycleDetails(@RequestParam(value="id", required=true)Integer cycleId) {
		
		String[] breadCrumbs = {"Report", "Member Mainenacne", "View"};
		List<BreadCrumb> breadCrumbList = breadCrumbHelper.getBreadCrumbList(breadCrumbs);
		
		MaintenanceCycleReceiptDomain cycle = maintenanceService.getCycleDetails(cycleId);
		
		ModelAndView modelAndView = new ModelAndView("viewMaintenanceTable");
		modelAndView.addObject(breadCrumbList);
		modelAndView.addObject("cycle", cycle);
		return modelAndView;
		
	}
	
}

