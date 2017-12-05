package com.society.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.society.model.domain.AdminParkingManagerDomain;

@Controller
public class AdminParkingController {
	
	@RequestMapping(value="parkingManager", method = RequestMethod.GET)
	public ModelAndView getParking() {
		
		ModelAndView modelAndView = new ModelAndView("parkingManager","adminParkingManagerDomain",new AdminParkingManagerDomain());
		return modelAndView;
	}
}
