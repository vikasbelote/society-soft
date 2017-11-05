package com.society.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.society.helper.model.BreadCrumb;
import com.society.helper.model.DropDownHelper;
import com.society.model.domain.TransactionDescriptionDomain;
import com.society.model.domain.TransactionDomain;
import com.society.service.TransactionService;

@Controller
public class TransactionController extends BaseController {
	
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping(value = "recordTransaction", method = RequestMethod.GET)
	public ModelAndView getTransaction(@ModelAttribute("transactionDomain")TransactionDomain transactionDomain, HttpSession session) {
		
		String[] breadCrumbs = {"Setting", "Record Transaction"};
		List<BreadCrumb> breadCrumbList = breadCrumbHelper.getBreadCrumbList(breadCrumbs);
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		Map<String, List<DropDownHelper>> dropDownMap = transactionService.getMasterData(societyId);
		
		List<TransactionDomain> transactionDomainList = transactionService.getTransactionEntry(societyId);
		List<TransactionDescriptionDomain> tranDescDomainList = transactionService.getTransactionDescriptionDomainList(societyId);
		
		if(transactionDomain == null)
			transactionDomain = new TransactionDomain();
			
		ModelAndView modelAndView = new ModelAndView("transaction", "transactionDomain", transactionDomain);
		modelAndView.addObject(breadCrumbList);
		modelAndView.addObject("transactionDomainList", transactionDomainList);
		modelAndView.addObject("tranDescDomainList", tranDescDomainList);
		for(Map.Entry<String, List<DropDownHelper>> dropDownEntry : dropDownMap.entrySet()){
			modelAndView.addObject(dropDownEntry.getKey(), dropDownEntry.getValue());
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "recordTransaction", method = RequestMethod.POST)
	public String postTransaction(@ModelAttribute("transactionDomain")TransactionDomain transactionDomain, HttpSession session, RedirectAttributes redirectAttributes){
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		transactionDomain.setSocietyId(societyId);
		
		if(transactionService.checkTransactionExist(transactionDomain)) {
			redirectAttributes.addFlashAttribute("transactionDomain", transactionDomain);
			redirectAttributes.addFlashAttribute("transactionExist", true);
			return "redirect:/recordTransaction";
		}
		
		if(transactionService.insertTransactionEntry(transactionDomain))
			redirectAttributes.addFlashAttribute("successMsg", "Congrats!!! you have inserted tranasaction entry successfuly.");
		else
			redirectAttributes.addFlashAttribute("successMsg", "There is problem with inserting transaction entry.");
		return "redirect:/recordTransactionStatus";
	}
	
	@RequestMapping(value = "recordTransactionStatus", method = RequestMethod.GET)
	public ModelAndView statusTransaction() {
		return new ModelAndView("recordTransactionStatus");
	}
	
	@RequestMapping(value = "deleteTransaction", method = RequestMethod.POST)
	public String deleteTransactionEntry(@ModelAttribute("genaralHeadDomain") TransactionDomain transactionDomain, RedirectAttributes redirectAttributes) {
		
		if(transactionService.deleteTransactionEntry(transactionDomain))
			redirectAttributes.addFlashAttribute("successMsg", "Congrats!!! Transaction entry deleted successfully.");
		else
			redirectAttributes.addFlashAttribute("successMsg", "There is problem with deleting information.");
		
		return "redirect:/recordTransactionStatus";
	}
}
