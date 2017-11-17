package com.society.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.society.model.domain.MaintenanceCycleReceiptDomain;
import com.society.service.MaintenanceBillStatusService;
import com.society.service.MaintenanceService;

@Controller
public class MaintenanceBillStatusController {
	
	@Autowired
	private MaintenanceService maintenanceService;
	
	@Autowired
	private MaintenanceBillStatusService billStatusService;
	
	@RequestMapping(value = "viewBillStatus", method = RequestMethod.GET)
	public ModelAndView getBillStatus(HttpSession session) {
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		List<MaintenanceCycleReceiptDomain> cycleList = maintenanceService.getMaintenacneCycleList(societyId);
		
		ModelAndView modelAndView = new ModelAndView("viewBillStatus");
		modelAndView.addObject("cycleList", cycleList);
		return modelAndView;
	}
	
	@RequestMapping(value = "viewCycleBillStatus", method = RequestMethod.GET)
	public ModelAndView getCycleBillStatus(@RequestParam("id")Integer cycleId) {
		
		MaintenanceCycleReceiptDomain cycle = billStatusService.getCycleDetails(cycleId);
		
		ModelAndView modelAndView = new ModelAndView("viewCycleBillStatus");
		modelAndView.addObject("cycle", cycle);
		return modelAndView;
	}
	
	@RequestMapping(value = "updateBillStatus", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Integer> updateBillStatus(@RequestBody MaintenanceCycleReceiptDomain maintenanceCycleReceiptDomain) {
		
		if(billStatusService.updateBillStatus(maintenanceCycleReceiptDomain))
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		else
			return new ResponseEntity<Integer>(0, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
