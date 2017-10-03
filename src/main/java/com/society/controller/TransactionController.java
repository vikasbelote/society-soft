package com.society.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.society.helper.model.BreadCrumb;
import com.society.helper.model.DropDownHelper;
import com.society.model.domain.TransactionDomain;
import com.society.service.TransactionService;

@Controller
public class TransactionController extends BaseController {
	
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping(value = "recordTransaction")
	public ModelAndView getTransaction(HttpSession session) {
		
		String[] breadCrumbs = {"Setting", "Record Transaction"};
		List<BreadCrumb> breadCrumbList = breadCrumbHelper.getBreadCrumbList(breadCrumbs);
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		Map<String, List<DropDownHelper>> dropDownMap = transactionService.getMasterData(societyId);
		
		ModelAndView modelAndView = new ModelAndView("transaction", "transactionDomain", new TransactionDomain());
		modelAndView.addObject(breadCrumbList);
		
		for(Map.Entry<String, List<DropDownHelper>> dropDownEntry : dropDownMap.entrySet()){
			modelAndView.addObject(dropDownEntry.getKey(), dropDownEntry.getValue());
		}
		return modelAndView;
	}
}
