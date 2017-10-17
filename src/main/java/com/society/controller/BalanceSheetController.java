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
import com.society.model.domain.BalanceSheetDomain;
import com.society.model.domain.CompareSectionReportDomain;
import com.society.model.report.BalanceSheet;
import com.society.model.report.CompareSectionReport;
import com.society.service.BalanceSheetService;
import com.society.service.CompareSectionReportService;

@Controller
public class BalanceSheetController extends BaseController {
	
	@Autowired
	private CompareSectionReportService balanceSheetService;
	
	@RequestMapping(value = "balanceSheet", method = RequestMethod.GET)
	public ModelAndView getBalanceSheet() {
		
		CompareSectionReportDomain compareSectionReportDomain = new CompareSectionReportDomain();
		compareSectionReportDomain.setLastYearStartDate(Date.valueOf("2016-04-01"));
		compareSectionReportDomain.setLastYearEndDate(Date.valueOf("2017-03-31"));
		compareSectionReportDomain.setCurrentYearStartDate(Date.valueOf("2017-04-01"));
		compareSectionReportDomain.setCurrentYearEndDate(Date.valueOf("2018-03-31"));
		
		return new ModelAndView("balanceSheet","balanceSheetDomain", compareSectionReportDomain);
	}
	
	@RequestMapping(value = "balanceSheet", method = RequestMethod.POST)
	public String getBalanceSheet(@ModelAttribute("balanceSheetDomain")CompareSectionReportDomain compareSectionReportDomain,  HttpSession session, RedirectAttributes redirectAttribute) {
		
		Integer societyId = (Integer) session.getAttribute("SOCIETYID");
		compareSectionReportDomain.setSocietyId(societyId);
		redirectAttribute.addFlashAttribute("balanceSheetDomainObject", compareSectionReportDomain);
		
		return "redirect:/balanceSheetData";
	}
	
	@RequestMapping(value = "balanceSheetData", method = RequestMethod.GET)
	public ModelAndView getBalanceSheet(@ModelAttribute("balanceSheetDomainObject")CompareSectionReportDomain compareSectionReportDomain) {
		
		if(compareSectionReportDomain.getCurrentYearEndDate() == null && compareSectionReportDomain.getCurrentYearStartDate() == null
				&& compareSectionReportDomain.getLastYearEndDate() == null && compareSectionReportDomain.getLastYearStartDate() == null){
			compareSectionReportDomain = new CompareSectionReportDomain();
			compareSectionReportDomain.setLastYearStartDate(Date.valueOf("2016-04-01"));
			compareSectionReportDomain.setLastYearEndDate(Date.valueOf("2017-03-31"));
			compareSectionReportDomain.setCurrentYearStartDate(Date.valueOf("2017-04-01"));
			compareSectionReportDomain.setCurrentYearEndDate(Date.valueOf("2018-03-31"));
			return new ModelAndView("balanceSheet","balanceSheetDomain", compareSectionReportDomain);
		}
			
		compareSectionReportDomain.setReportName(ReportEnum.BS.value());
		compareSectionReportDomain.setLeftSectionName(SectionEnum.LC.value());
		compareSectionReportDomain.setRightSectionName(SectionEnum.PA.value());
		CompareSectionReport balanceSheet = balanceSheetService.getCompareSectionReportData(compareSectionReportDomain);
		
		ModelAndView modelAndView = new ModelAndView("balanceSheetData");
		modelAndView.addObject("balanceSheetData", balanceSheet);
		return modelAndView;
	}
}
