package com.society.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.society.helper.model.BreadCrumb;
import com.society.model.domain.GenaralHeadDomain;
import com.society.service.GeneralHeadService;

@Controller
public class GeneralHeadController extends BaseController {
	
	@Autowired
	private GeneralHeadService generalHeadService;
	
	@RequestMapping(value = "generalHead", method = RequestMethod.GET)
	public ModelAndView getGeneralHead(){
		
		String[] breadCrumbs = {"Setting", "General Head"};
		List<BreadCrumb> breadCrumbList = breadCrumbHelper.getBreadCrumbList(breadCrumbs);
		
		ModelAndView modelAndView = new ModelAndView("generalHead", "genaralHeadDomain", new GenaralHeadDomain());
		modelAndView.addObject(breadCrumbList);
		return modelAndView;
	}
	
	@RequestMapping(value = "generalHead", method = RequestMethod.POST)
	public String postGeneralHead(@ModelAttribute("genaralHeadDomain") GenaralHeadDomain genaralHeadDomain){
		
		return "redirect:/generalHead";
	}

}
