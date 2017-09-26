package com.society.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.society.helper.BreadCrumbHelper;
import com.society.helper.model.BreadCrumb;

@Controller
public class HomeController {
	
	@Autowired
	private BreadCrumbHelper breadCrumbHelper;

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	/*@RequestMapping("/home")
	public String home(Map<String, Object> model) {

		//LOGGER.debug("This is a debug message");
		//LOGGER.info("This is an info message");
		//LOGGER.warn("This is a warn message");
		//LOGGER.error("This is an error message");

		model.put("message", this.message);
		return "home";
	}*/
	
	@RequestMapping("home")
	public ModelAndView home() {
		
		String[] breadCrumbs = {"Dashboard"};
		List<BreadCrumb> breadCrumbList = breadCrumbHelper.getBreadCrumbList(breadCrumbs);
		
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject(breadCrumbList);
		return modelAndView;
	}
	

	@RequestMapping("/about")
	public String about() {
		return "about";
	}
}
