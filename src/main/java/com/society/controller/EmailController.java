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

import com.society.model.domain.EmailDomain;
import com.society.service.EmailService;

@RestController
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value = "sendEmail", method = RequestMethod.POST)
	public ResponseEntity<Integer> sendEmail(@RequestBody EmailDomain email, HttpSession session) {
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		email.setSocietyId(societyId);
		email.setRootPath(session.getServletContext().getRealPath("/"));
		
		if(StringUtils.isNotEmpty(email.getRootPath()) && StringUtils.isNotBlank(email.getRootPath())){
			emailService.sendMail(email);
		}
		return new ResponseEntity<Integer>(1, HttpStatus.OK);
	}
	
	@RequestMapping(value = "sendEmailToFailedMember", method = RequestMethod.POST)
	public ResponseEntity<Integer> sendEmailToFailedMember(@RequestBody EmailDomain email, HttpSession session) {
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		email.setSocietyId(societyId);
		email.setRootPath(session.getServletContext().getRealPath("/"));
		
		if(StringUtils.isNotEmpty(email.getRootPath()) && StringUtils.isNotBlank(email.getRootPath())){
			emailService.sendMailToFailedMember(email);
		}
		return new ResponseEntity<Integer>(1, HttpStatus.OK);
	}
}
