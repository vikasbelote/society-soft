package com.society.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminAssetTrackerController {
	
	@RequestMapping(value="createAsset", method = RequestMethod.GET)
	public ModelAndView getAssetTracker() {
		ModelAndView modelAndView = new ModelAndView("createAsset");
		return modelAndView;
	}
}
