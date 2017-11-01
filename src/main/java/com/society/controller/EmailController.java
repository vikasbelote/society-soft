package com.society.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.society.model.domain.MaintenanceCycleReceiptDomain;
import com.society.service.EmailService;

@RestController
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value = "sendEmail", method = RequestMethod.POST)
	public ResponseEntity<String> sendEmail(@RequestBody MaintenanceCycleReceiptDomain cycle, HttpSession session) {
		
		String rootPath = session.getServletContext().getRealPath("/");
		if(StringUtils.isNotEmpty(rootPath) & StringUtils.isNotBlank(rootPath)){
			emailService.sendMail(rootPath);
		}
		return new ResponseEntity<String>("Sending email...", HttpStatus.OK);
	}

}
