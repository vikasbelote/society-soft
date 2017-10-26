package com.society.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.society.model.domain.GeneralHeadDomain;
import com.society.model.domain.MaintenanceDomain;
import com.society.model.domain.MaintenanceTableDomain;
import com.society.service.MaintenanceService;

@Controller
public class MaintenanceController extends BaseController {
	
	@Autowired
	private MaintenanceService maintenanceService;
	
	@RequestMapping(value = "maintaince", method = RequestMethod.GET)
	public ModelAndView getMaintenance(HttpSession session) {
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		List<List<GeneralHeadDomain>> generalHeadList = maintenanceService.getGeneralHeadList(societyId); 
				
		ModelAndView modelAndView = new ModelAndView("maintaince", "maintenanceDomain", new MaintenanceDomain());
		modelAndView.addObject("generalHeadList", generalHeadList);
		return modelAndView;
	}
	
	@RequestMapping(value = "maintaince", method = RequestMethod.POST)
	public String postMaintenance(@ModelAttribute("maintenanceDomain")MaintenanceDomain maintenanceDomain, RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addFlashAttribute("maintenanceDomain", maintenanceDomain);
		return "redirect:/maintenanceTable";
	}
	
	@RequestMapping(value = "maintenanceTable", method = RequestMethod.GET)
	public ModelAndView getMaintainceTable(@ModelAttribute("maintenanceDomain")MaintenanceDomain maintenanceDomain, HttpSession session) {
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		if(maintenanceDomain.getPaymentDueDate() == null && maintenanceDomain.getGeneralHeadChargeMap() == null) {
			List<List<GeneralHeadDomain>> generalHeadList = maintenanceService.getGeneralHeadList(societyId); 
			ModelAndView modelAndView = new ModelAndView("maintaince", "maintenanceDomain", new MaintenanceDomain());
			modelAndView.addObject("generalHeadList", generalHeadList);
			return modelAndView;
		}
		
		MaintenanceTableDomain maintenanceTable = maintenanceService.getMaintenanceTableList(maintenanceDomain, societyId);
		
		ModelAndView modelAndView = new ModelAndView("maintenanceTable");
		modelAndView.addObject("maintenanceTable", maintenanceTable);
		return modelAndView;
	}
	
}

