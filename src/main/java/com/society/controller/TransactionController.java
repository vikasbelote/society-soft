package com.society.controller;

import java.util.ArrayList;
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
import com.society.model.domain.GeneralHeadDomain;
import com.society.model.domain.TransactionDescriptionDomain;
import com.society.model.domain.TransactionDomain;
import com.society.service.TransactionService;

@Controller
public class TransactionController extends BaseController {
	
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping(value = "recordTransaction", method = RequestMethod.GET)
	public ModelAndView getTransaction(HttpSession session) {
		
		String[] breadCrumbs = {"Setting", "Record Transaction"};
		List<BreadCrumb> breadCrumbList = breadCrumbHelper.getBreadCrumbList(breadCrumbs);
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		Map<String, List<DropDownHelper>> dropDownMap = transactionService.getMasterData(societyId);
		
		List<TransactionDomain> transactionDomainList = transactionService.getTransactionEntry(societyId);
		
		List<TransactionDescriptionDomain> tranDescDomainList = new ArrayList<TransactionDescriptionDomain>();
		
		TransactionDescriptionDomain tranDescDomain = new TransactionDescriptionDomain();
		tranDescDomain.setDescId(1);
		tranDescDomain.setLabel("Default 1");
		tranDescDomain.setGeneralHeadId(99999);
		tranDescDomain.setGeneralHeadName("");
		
		TransactionDescriptionDomain tranDescDomain1 = new TransactionDescriptionDomain();
		tranDescDomain1.setDescId(2);
		tranDescDomain1.setLabel("Default 2");
		tranDescDomain1.setGeneralHeadId(99999);
		tranDescDomain1.setGeneralHeadName("");
		
		TransactionDescriptionDomain tranDescDomain2 = new TransactionDescriptionDomain();
		tranDescDomain2.setDescId(3);
		tranDescDomain2.setLabel("Default 3");
		tranDescDomain2.setGeneralHeadId(99999);
		tranDescDomain2.setGeneralHeadName("");
		
		TransactionDescriptionDomain tranDescDomain3 = new TransactionDescriptionDomain();
		tranDescDomain3.setDescId(4);
		tranDescDomain3.setLabel("SH 1");
		tranDescDomain3.setGeneralHeadId(1);
		tranDescDomain3.setGeneralHeadName("Share Capital");
		
		TransactionDescriptionDomain tranDescDomain4 = new TransactionDescriptionDomain();
		tranDescDomain4.setDescId(5);
		tranDescDomain4.setLabel("SH 2");
		tranDescDomain4.setGeneralHeadId(1);
		tranDescDomain4.setGeneralHeadName("Share Capital");
		
		TransactionDescriptionDomain tranDescDomain5 = new TransactionDescriptionDomain();
		tranDescDomain5.setDescId(6);
		tranDescDomain5.setLabel("SH 3");
		tranDescDomain5.setGeneralHeadId(1);
		tranDescDomain5.setGeneralHeadName("Share Capital");
		
		tranDescDomainList.add(tranDescDomain);
		tranDescDomainList.add(tranDescDomain1);
		tranDescDomainList.add(tranDescDomain2);
		tranDescDomainList.add(tranDescDomain3);
		tranDescDomainList.add(tranDescDomain4);
		tranDescDomainList.add(tranDescDomain5);
		
		ModelAndView modelAndView = new ModelAndView("transaction", "transactionDomain", new TransactionDomain());
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
