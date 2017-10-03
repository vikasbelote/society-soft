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

import com.society.helper.model.BreadCrumb;
import com.society.model.domain.GeneralHeadDomain;
import com.society.service.GeneralHeadService;

@Controller
public class GeneralHeadController extends BaseController {
	
	@Autowired
	private GeneralHeadService generalHeadService;
	
	@RequestMapping(value = "generalHead", method = RequestMethod.GET)
	public ModelAndView getGeneralHead(HttpSession session){
		
		String[] breadCrumbs = {"Setting", "General Head"};
		List<BreadCrumb> breadCrumbList = breadCrumbHelper.getBreadCrumbList(breadCrumbs);
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		List<GeneralHeadDomain> generalHeadList = generalHeadService.getGeneralHeadList(societyId);
		
		ModelAndView modelAndView = new ModelAndView("generalHead", "genaralHeadDomain", new GeneralHeadDomain());
		modelAndView.addObject(breadCrumbList);
		modelAndView.addObject("generalHeadList", generalHeadList);
		return modelAndView;
	}
	
	@RequestMapping(value = "generalHead", method = RequestMethod.POST)
	public String postGeneralHead(@ModelAttribute("genaralHeadDomain") GeneralHeadDomain generalHeadDomain, HttpSession session, RedirectAttributes redirectAttributes){
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		generalHeadDomain.setSocietyId(societyId);
		
		if(generalHeadService.insertGeneralHead(generalHeadDomain))
			redirectAttributes.addFlashAttribute("successMsg", "Congrats!!! General head information save successfully.");
		else
			redirectAttributes.addFlashAttribute("successMsg", "There is problem with saving information.");	
		return "redirect:/generalHeadStatus";
	}
	
	@RequestMapping(value = "generalHeadStatus", method = RequestMethod.GET)
	public ModelAndView registrationStatus() {
		return new ModelAndView("generalHeadStatus");
	}
	
	@RequestMapping(value = "deleteGeneralHead", method = RequestMethod.POST)
	public String deleteGeneralHead(@ModelAttribute("genaralHeadDomain") GeneralHeadDomain generalHeadDomain, RedirectAttributes redirectAttributes) {
		
		if(generalHeadService.deleteGeneralHead(generalHeadDomain))
			redirectAttributes.addFlashAttribute("successMsg", "Congrats!!! General head information deeleted successfully.");
		else
			redirectAttributes.addFlashAttribute("successMsg", "There is problem with saving information.");
		return "redirect:/generalHeadStatus";
	}
}
