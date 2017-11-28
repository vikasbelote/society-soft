package com.society.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.society.model.domain.AdminAssetTrackerDomain;

@Controller
public class AdminAssetTrackerController {
	
	@RequestMapping(value="createAsset", method = RequestMethod.GET)
	public ModelAndView getAssetTracker(@ModelAttribute("adminAssetTrackerDomain")AdminAssetTrackerDomain adminAssetTrackerDomain) {
		
		ModelAndView modelAndView = new ModelAndView("createAsset", "adminAssetTrackerDomain", adminAssetTrackerDomain);
		modelAndView.addObject("categoryList", new ArrayList<String>());
		return modelAndView;
	}
}
