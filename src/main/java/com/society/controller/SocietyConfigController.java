package com.society.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.society.constant.ReportEnum;
import com.society.model.domain.GeneralHeadDomain;
import com.society.model.domain.SocietyConfigDomain;
import com.society.service.SocietyConfigService;

@Controller
public class SocietyConfigController {
	
	@Autowired
	private SocietyConfigService societyConfigService;
	
	@RequestMapping(value = "societyConfig", method = RequestMethod.GET)
	public ModelAndView getSocietyConfig(HttpSession session) {
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		SocietyConfigDomain societyConfigDomian = societyConfigService.getSocietyConfig(societyId);
		List<GeneralHeadDomain> balanceSheetGeneralHeadList = societyConfigService.getGeneralHeadDomain(ReportEnum.BS.value());
		List<GeneralHeadDomain> incomeAndExpenseGeneralHeadList = societyConfigService.getGeneralHeadDomain(ReportEnum.IE.value());
		
		ModelAndView modelAndView = new ModelAndView("societyConfig", "societyConfigDomain" , societyConfigDomian);
		modelAndView.addObject("balanceSheetGeneralHeadList", balanceSheetGeneralHeadList);
		modelAndView.addObject("incomeAndExpenseGeneralHeadList", incomeAndExpenseGeneralHeadList);
		return modelAndView;
	}
	
	@RequestMapping(value = "societyConfig", method = RequestMethod.POST)
	public String postSocietyConfig(@ModelAttribute("societyConfigDomain") SocietyConfigDomain societyConfigDomain, HttpSession session, RedirectAttributes redirectAttribute) {
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		societyConfigDomain.setSocietyId(societyId);
		if(societyConfigService.insertSocietyConfig(societyConfigDomain)) {
			redirectAttribute.addFlashAttribute("successMsg", "Congrats!!! Configuration information save successfuly.");
		}
		else {
			redirectAttribute.addFlashAttribute("successMsg", "There is error while saving configuration information.");
		}
		return "redirect:/societyConfigStatus";
	}
	
	@RequestMapping(value = "societyConfigStatus", method = RequestMethod.GET)
	public ModelAndView statusSocietyConfig() {
		ModelAndView modelAndView = new ModelAndView("societyConfigStatus");
		return modelAndView;
	}
}
