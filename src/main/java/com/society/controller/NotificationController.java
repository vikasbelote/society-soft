package com.society.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.society.model.domain.EmailStatusDomain;
import com.society.model.domain.MaintenanceCycleReceiptDomain;
import com.society.service.NotificationService;

@Controller
public class NotificationController {
	
	@Autowired 
	private NotificationService notificationService;
	
	@RequestMapping(value = "emailNotification", method = RequestMethod.GET)
	public ModelAndView emailNotification(HttpSession session) {
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		List<MaintenanceCycleReceiptDomain> cycleList = notificationService.getMaintenacneCycleList(societyId);
		
		ModelAndView modelAndView = new ModelAndView("emailNotification");
		modelAndView.addObject("cycleList", cycleList);
		return modelAndView;
	}
	
	@RequestMapping(value = "msgNotification", method = RequestMethod.GET)
	public ModelAndView msgNotification() {
		
		ModelAndView modelAndView = new ModelAndView("msgNotification");
		return modelAndView;
	}
	
	@RequestMapping(value = "viewEmailStatus", method = RequestMethod.GET)
	public ModelAndView viewEmailStatus(@RequestParam(value="id", required=true)Integer cycleId) {
		
		List<EmailStatusDomain> emailStatusDomainList = notificationService.getEmailStatusList(cycleId);
		
		ModelAndView modelAndView = new ModelAndView("viewEmailStatus");
		modelAndView.addObject("emailStatusDomainList", emailStatusDomainList);
		modelAndView.addObject("cycleId", cycleId);
		return modelAndView;
	}
	
	
}
