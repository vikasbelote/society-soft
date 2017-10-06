package com.society.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.society.model.report.BalanceSheet;
import com.society.service.BalanceSheetService;

@Controller
public class BalanceSheetController extends BaseController {
	
	@Autowired
	private BalanceSheetService balanceSheetService;
	
	
	@RequestMapping(value = "balanceSheet", method = RequestMethod.GET)
	public ModelAndView getBalanceSheet() {
		
		BalanceSheet balanceSheet = balanceSheetService.getBalanceSheetData();
		
		ModelAndView modelAndView = new ModelAndView("balanceSheet");
		modelAndView.addObject("balanceSheetData", balanceSheet);
		return modelAndView;
	}

}
