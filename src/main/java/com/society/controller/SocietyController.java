package com.society.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.society.helper.BreadCrumbHelper;
import com.society.helper.model.BreadCrumb;
import com.society.model.domain.PersonDomain;
import com.society.model.domain.SocietyForm;
import com.society.service.SocietyService;

@Controller
public class SocietyController {
	
	@Autowired
	private SocietyService societyService;
	
	@Autowired
	private BreadCrumbHelper breadCrumbHelper;
	
	@RequestMapping(value = "societyRegistration", method = RequestMethod.GET)
	public ModelAndView getSociety() {
		
		String[] breadCrumbs = {"Society", "Registration"};
		List<BreadCrumb> breadCrumbList = breadCrumbHelper.getBreadCrumbList(breadCrumbs);
		
		ModelAndView modelAndView = new ModelAndView("societyRegistration");
		modelAndView.addObject(breadCrumbList);
		return modelAndView;
	}
	
	@RequestMapping(value = "getMemberRow", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ModelAndView getMemberRow(@RequestBody PersonDomain personDomain) {
		return new ModelAndView("memberRow", "personDomain", personDomain);
	}
	
	@RequestMapping(value = "societyRegistration", method = RequestMethod.POST)
	public String postSociety(@ModelAttribute SocietyForm societyForm, final RedirectAttributes redirectAttributes) {
		
		try{
			if(societyService.registerSociety(societyForm))
				redirectAttributes.addFlashAttribute("successMsg","Congrats!!! Your society account registered successfully.");
			else
				redirectAttributes.addFlashAttribute("successMsg","There is problem with creating new society account.");
		}
		catch(Exception e) {
			redirectAttributes.addFlashAttribute("successMsg","There is problem with creating new society account.");
		}
		return "redirect:/registrationStatus";
	}
	
	@RequestMapping(value = "registrationStatus", method = RequestMethod.GET)
	public ModelAndView registrationStatus() {
		return new ModelAndView("registrationStatus");
	}
	
}
