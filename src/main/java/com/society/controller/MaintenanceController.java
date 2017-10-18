package com.society.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.society.model.domain.GeneralHeadDomain;
import com.society.service.MaintenanceService;

@Controller
public class MaintenanceController extends BaseController {
	
	@Autowired
	private MaintenanceService maintenanceService;
	
	@RequestMapping(value = "maintaince", method = RequestMethod.GET)
	public ModelAndView getMaintenance(HttpSession session) {
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		List<List<GeneralHeadDomain>> generalHeadList = maintenanceService.getGeneralHeadList(societyId); 
				
		ModelAndView modelAndView = new ModelAndView("maintaince");
		modelAndView.addObject("generalHeadList", generalHeadList);
		return modelAndView;
	}
	
	@RequestMapping(value = "maintaince", method = RequestMethod.POST)
	public String postMaintenance(HttpSession session, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		Map<String, String[]> generalHeadIdChargeMap = request.getParameterMap();
		redirectAttributes.addFlashAttribute("generalHeadIdChargeMap", generalHeadIdChargeMap);
		
		return "redirect:/maintenanceTable";
	}
	
	@RequestMapping(value = "maintenanceTable", method = RequestMethod.GET)
	public ModelAndView getMaintainceTable(@ModelAttribute("generalHeadIdChargeMap") Map<String, String[]> generalHeadIdChargeMap, HttpServletRequest request) {
		
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		
		flashMap.get("generalHeadIdChargeMap");
		
		ModelAndView modelAndView = new ModelAndView("maintenanceTable");
		return modelAndView;
	}
	
}

