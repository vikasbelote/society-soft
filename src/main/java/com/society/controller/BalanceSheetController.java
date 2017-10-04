package com.society.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BalanceSheetController extends BaseController {
	
	@RequestMapping(value = "balanceSheet", method = RequestMethod.GET)
	public ModelAndView getBalanceSheet() {
		return new ModelAndView("balanceSheet");
	}

}
