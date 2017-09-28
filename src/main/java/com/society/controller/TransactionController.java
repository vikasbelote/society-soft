package com.society.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.society.helper.model.BreadCrumb;

@Controller
public class TransactionController extends BaseController {
	
	@RequestMapping(value = "recordTransaction")
	public ModelAndView getTransaction() {
		
		String[] breadCrumbs = {"Setting", "Record Transaction"};
		List<BreadCrumb> breadCrumbList = breadCrumbHelper.getBreadCrumbList(breadCrumbs);
		
		ModelAndView modelAndView = new ModelAndView("transaction");
		return modelAndView;
	}
}
