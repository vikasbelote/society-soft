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

import com.society.constant.ReportEnum;
import com.society.constant.SectionEnum;
import com.society.model.domain.CompareSectionReportDomain;
import com.society.model.report.CompareSectionReport;
import com.society.service.CompareSectionReportService;

@Controller
public class IncomeAndExpenseController extends BaseController {
	
	@Autowired
	private CompareSectionReportService incomeAndExpenseService;
	
	@RequestMapping(value = "incomeAndExpense", method = RequestMethod.GET)
	public ModelAndView getBalanceSheet() {
		
		CompareSectionReportDomain compareSectionReportDomain = new CompareSectionReportDomain();
		compareSectionReportDomain.setLastYearStartDate(Date.valueOf("2016-04-01"));
		compareSectionReportDomain.setLastYearEndDate(Date.valueOf("2017-03-31"));
		compareSectionReportDomain.setCurrentYearStartDate(Date.valueOf("2017-04-01"));
		compareSectionReportDomain.setCurrentYearEndDate(Date.valueOf("2018-03-31"));
		
		return new ModelAndView("incomeAndExpense","incomeAndExpenseDomain", compareSectionReportDomain);
	}
	
	@RequestMapping(value = "incomeAndExpense", method = RequestMethod.POST)
	public String getBalanceSheet(@ModelAttribute("incomeAndExpenseDomain")CompareSectionReportDomain compareSectionReportDomain,  HttpSession session, RedirectAttributes redirectAttribute) {
		
		Integer societyId = (Integer) session.getAttribute("SOCIETYID");
		compareSectionReportDomain.setSocietyId(societyId);
		redirectAttribute.addFlashAttribute("incomeAndExpenseDomainObject", compareSectionReportDomain);
		
		return "redirect:/incomeAndExpenseData";
	}
	
	@RequestMapping(value = "incomeAndExpenseData", method = RequestMethod.GET)
	public ModelAndView getBalanceSheet(@ModelAttribute("incomeAndExpenseDomainObject")CompareSectionReportDomain compareSectionReportDomain) {
		
		if(compareSectionReportDomain.getCurrentYearEndDate() == null && compareSectionReportDomain.getCurrentYearStartDate() == null
				&& compareSectionReportDomain.getLastYearEndDate() == null && compareSectionReportDomain.getLastYearStartDate() == null){
			compareSectionReportDomain = new CompareSectionReportDomain();
			compareSectionReportDomain.setLastYearStartDate(Date.valueOf("2016-04-01"));
			compareSectionReportDomain.setLastYearEndDate(Date.valueOf("2017-03-31"));
			compareSectionReportDomain.setCurrentYearStartDate(Date.valueOf("2017-04-01"));
			compareSectionReportDomain.setCurrentYearEndDate(Date.valueOf("2018-03-31"));
			return new ModelAndView("incomeAndExpense","incomeAndExpenseDomain", compareSectionReportDomain);
		}
			
		compareSectionReportDomain.setReportName(ReportEnum.IE.value());
		compareSectionReportDomain.setLeftSectionName(SectionEnum.EXPENSES.value());
		compareSectionReportDomain.setRightSectionName(SectionEnum.INCOME.value());
		CompareSectionReport balanceSheet = incomeAndExpenseService.getCompareSectionReportData(compareSectionReportDomain);
		
		ModelAndView modelAndView = new ModelAndView("incomeAndExpenseData");
		modelAndView.addObject("incomeAndExpenseData", balanceSheet);
		return modelAndView;
	}

}

