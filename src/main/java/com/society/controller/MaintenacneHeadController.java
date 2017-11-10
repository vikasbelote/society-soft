package com.society.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.society.model.domain.MaintenanceHeadChargeCalcTypeDomain;
import com.society.model.domain.MaintenanceHeadDomain;
import com.society.service.MaintenanceHeadService;

@Controller
public class MaintenacneHeadController {
	
	@Autowired
	private MaintenanceHeadService maintenanceHeadService;
	
	@RequestMapping(value = "maintenanceHead", method = RequestMethod.GET)
	public ModelAndView getMaintenanceHead(@ModelAttribute("maintenanceHeadDomain")MaintenanceHeadDomain maintenanceHeadDomain, HttpSession session) {
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		List<MaintenanceHeadChargeCalcTypeDomain> chargeTypeDomainList = maintenanceHeadService.getMaintenanceHeadChargeCalcType();
		List<MaintenanceHeadDomain> maintenanceHeadList = maintenanceHeadService.getMaintenanceHeadDomainList(societyId);
		
		if(maintenanceHeadDomain == null)
			maintenanceHeadDomain = new MaintenanceHeadDomain();
		
		ModelAndView modelAndView = new ModelAndView("maintenanceHead", "maintenanceHeadDomain", maintenanceHeadDomain);
		modelAndView.addObject("chargeTypeDomainList", chargeTypeDomainList);
		modelAndView.addObject("maintenanceHeadList", maintenanceHeadList);
		return modelAndView;
	}
	
	@RequestMapping(value = "maintenanceHead", method = RequestMethod.POST)
	public String postMaintenanceHead(@ModelAttribute("maintenanceHeadDomain")MaintenanceHeadDomain maintenanceHeadDomain, HttpSession session, RedirectAttributes rediredtAttribute) {
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		maintenanceHeadDomain.setSocietyId(societyId);
		
		if(maintenanceHeadService.checkMaintenanceHeadExist(maintenanceHeadDomain)) {
			rediredtAttribute.addFlashAttribute("maintenanceHeadExist", true);
			rediredtAttribute.addFlashAttribute("maintenanceHeadDomain", maintenanceHeadDomain);
			return "redirect:/maintenanceHead";
		}
		
		if(maintenanceHeadService.saveMaintenanceHead(maintenanceHeadDomain)){
			rediredtAttribute.addFlashAttribute("success", true);
		}
		else {
			rediredtAttribute.addFlashAttribute("maintenanceHeadDomain", maintenanceHeadDomain);
			rediredtAttribute.addFlashAttribute("success", false);
		}
		return "redirect:/maintenanceHead";
	}
	
	@RequestMapping(value = "deleteMaintenanceHead", method = RequestMethod.POST)
	public String deleteMaintenanceHead(@ModelAttribute("maintenanceHeadDomain")MaintenanceHeadDomain maintenanceHeadDomain, RedirectAttributes rediredtAttribute) {
		
		if(maintenanceHeadService.deleteMaintenanceHead(maintenanceHeadDomain)){
			rediredtAttribute.addFlashAttribute("success", true);
		}
		else {
			rediredtAttribute.addFlashAttribute("maintenanceHeadDomain", maintenanceHeadDomain);
			rediredtAttribute.addFlashAttribute("success", false);
		}
		return "redirect:/maintenanceHead";
	}

}
