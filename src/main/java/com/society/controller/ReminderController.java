package com.society.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.society.helper.BreadCrumbHelper;
import com.society.helper.model.BreadCrumb;

@Controller
public class ReminderController {
	
	@Autowired
	private BreadCrumbHelper breadCrumbHelper;
	
	@RequestMapping(value = "reminder", method = RequestMethod.GET)
	public ModelAndView getReminder() {
		
		String[] breadCrumbs = {"Society", "Reminder"};
		List<BreadCrumb> breadCrumbList = breadCrumbHelper.getBreadCrumbList(breadCrumbs);
		
		ModelAndView modelAndView = new ModelAndView("reminder");
		modelAndView.addObject(breadCrumbList);
		return modelAndView;
	}
}
