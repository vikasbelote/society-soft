package com.society.controller;

import java.sql.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.society.model.domain.BalanceSheetDomain;
import com.society.model.report.BalanceSheet;
import com.society.service.BalanceSheetService;

@Controller
public class BalanceSheetController extends BaseController {
	
	@Autowired
	private BalanceSheetService balanceSheetService;
	
	@RequestMapping(value = "balanceSheet", method = RequestMethod.GET)
	public ModelAndView getBalanceSheet() {
		
		BalanceSheetDomain balceshettDomain = new BalanceSheetDomain();
		balceshettDomain.setLastYearStartDate(Date.valueOf("2016-04-01"));
		balceshettDomain.setLastYearEndDate(Date.valueOf("2017-03-31"));
		balceshettDomain.setCurrentYearStartDate(Date.valueOf("2017-04-01"));
		balceshettDomain.setCurrentYearEndDate(Date.valueOf("2018-03-31"));
		
		return new ModelAndView("balanceSheet","balanceSheetDomain", balceshettDomain);
	}
	
	@RequestMapping(value = "balanceSheet", method = RequestMethod.POST)
	public String getBalanceSheet(@ModelAttribute("balanceSheetDomain")BalanceSheetDomain balanceSheetDomain,  HttpSession session, RedirectAttributes redirectAttribute) {
		
		Integer societyId = (Integer) session.getAttribute("SOCIETYID");
		balanceSheetDomain.setSocietyId(societyId);
		redirectAttribute.addFlashAttribute("balanceSheetDomainObject", balanceSheetDomain);
		
		return "redirect:/balanceSheetData";
	}
	
	@RequestMapping(value = "balanceSheetData", method = RequestMethod.GET)
	public ModelAndView getBalanceSheet(@ModelAttribute("balanceSheetDomainObject") BalanceSheetDomain balanceSheetDomain) {
		
		if(balanceSheetDomain.getCurrentYearEndDate() == null && balanceSheetDomain.getCurrentYearStartDate() == null
				&& balanceSheetDomain.getLastYearEndDate() == null && balanceSheetDomain.getLastYearStartDate() == null){
			BalanceSheetDomain balceshettDomain = new BalanceSheetDomain();
			balceshettDomain.setLastYearStartDate(Date.valueOf("2016-04-01"));
			balceshettDomain.setLastYearEndDate(Date.valueOf("2017-03-31"));
			balceshettDomain.setCurrentYearStartDate(Date.valueOf("2017-04-01"));
			balceshettDomain.setCurrentYearEndDate(Date.valueOf("2018-03-31"));
			return new ModelAndView("balanceSheet","balanceSheetDomain", balceshettDomain);
		}
			
		
		BalanceSheet balanceSheet = balanceSheetService.getBalanceSheetData(balanceSheetDomain);
		
		ModelAndView modelAndView = new ModelAndView("balanceSheetData");
		modelAndView.addObject("balanceSheetData", balanceSheet);
		return modelAndView;
	}
}
