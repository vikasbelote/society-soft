package com.society.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.society.helper.BreadCrumbHelper;

@Controller
public class BaseController {
	
	@Autowired
	protected BreadCrumbHelper breadCrumbHelper;
}
