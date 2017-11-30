package com.society.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.society.model.domain.AdminAssetTrackerDomain;
import com.society.service.AdminAssetTrackerService;

@Controller
public class AdminAssetTrackerController {
	
	@Autowired
	private AdminAssetTrackerService adminAssetTrackerService;
	
	@RequestMapping(value="createAsset", method = RequestMethod.GET)
	public ModelAndView getAssetTracker(@ModelAttribute("adminAssetTrackerDomain")AdminAssetTrackerDomain adminAssetTrackerDomain) {
		
		ModelAndView modelAndView = new ModelAndView("createAsset", "adminAssetTrackerDomain", adminAssetTrackerDomain);
		modelAndView.addObject("categoryList", adminAssetTrackerService.getCategoryList());
		return modelAndView;
	}
	
	@RequestMapping(value="createAsset", method = RequestMethod.POST)
	public String postAssetTracker(@RequestParam("scanFile") MultipartFile[] files, RedirectAttributes redirectAttributes,
			@RequestParam("scanFileName") String[] scanFileNames, @ModelAttribute("adminAssetTrackerDomain")AdminAssetTrackerDomain adminAssetTrackerDomain, HttpSession session) {
		
		if (files.length != scanFileNames.length) {
			redirectAttributes.addFlashAttribute("save", false);
			return "redirect:/createAsset";
		}
		
		String societyName = (String)session.getAttribute("DISPLAYNAME");
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		Integer userId = (Integer)session.getAttribute("USERID");
		String rootPath = session.getServletContext().getRealPath("/");
		
		adminAssetTrackerDomain.setSocietyName(societyName);
		adminAssetTrackerDomain.setSocietyId(societyId);
		adminAssetTrackerDomain.setUserId(userId);
		adminAssetTrackerDomain.setRootPath(rootPath);
		
		if(adminAssetTrackerService.saveAssetTrackerEntry(adminAssetTrackerDomain, files, scanFileNames))
			redirectAttributes.addFlashAttribute("save", true);
		else
			redirectAttributes.addFlashAttribute("save", false);
		
		return "redirect:/createAsset";
	}
	
	@RequestMapping(value = "viewAsset", method = RequestMethod.GET)
	public ModelAndView viewAssetTracker(HttpSession session) {
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		
		ModelAndView modelAndView = new ModelAndView("viewAsset");
		modelAndView.addObject("assetList", adminAssetTrackerService.getAssetList(societyId));
		return modelAndView;
	}
	
	@RequestMapping(value = "editAssetDetail", method = RequestMethod.GET)
	public ModelAndView editAssetDetail(@RequestParam("id")Integer assetId) {
		
		AdminAssetTrackerDomain adminAssetTrackerDomain = adminAssetTrackerService.getAssetDomain(assetId);
		
		ModelAndView modelAndView = new ModelAndView("editAsset", "adminAssetTrackerDomain", adminAssetTrackerDomain);
		modelAndView.addObject("categoryList", adminAssetTrackerService.getCategoryList());
		return modelAndView;
	}
}
