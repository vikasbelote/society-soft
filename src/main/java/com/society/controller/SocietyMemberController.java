package com.society.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.society.model.domain.SocietyMemberDomain;
import com.society.service.SocietyMemberService;

@Controller
public class SocietyMemberController {
	
	@Autowired
	private SocietyMemberService memberService;
	
	@RequestMapping(value = "member", method = RequestMethod.GET)
	public ModelAndView getMember(@ModelAttribute("societyMemberDomain") SocietyMemberDomain societyMemberDomain, HttpSession session) {
		
		Integer societyId = (Integer) session.getAttribute("SOCIETYID");
		List<SocietyMemberDomain> memberList = memberService.getSocietyMemberList(societyId);
		
		if(societyMemberDomain == null)
			societyMemberDomain = new SocietyMemberDomain();
		
		ModelAndView modelAndView = new ModelAndView("member", "societyMemberDomain", societyMemberDomain);
		modelAndView.addObject("memberList", memberList);
		return modelAndView;
	}
	
	@RequestMapping(value = "member", method = RequestMethod.POST)
	public String postMember(@ModelAttribute("societyMemberDomain") SocietyMemberDomain societyMemberDomain, RedirectAttributes redirectAttributes, HttpSession session) {
		
		Integer societyId = (Integer) session.getAttribute("SOCIETYID");
		societyMemberDomain.setSocietyId(societyId);
		
		if(memberService.checkMemberExist(societyMemberDomain)) {
			redirectAttributes.addFlashAttribute("memberExist", true);
			redirectAttributes.addFlashAttribute("societyMemberDomain", societyMemberDomain);
			return "redirect:/member";
		}
		
		if(memberService.saveOrUpdateSocietyMemberDetails(societyMemberDomain)) 
			redirectAttributes.addFlashAttribute("saveOrUpdate", true);
		else {
			redirectAttributes.addFlashAttribute("saveOrUpdate", false);
			redirectAttributes.addFlashAttribute("societyMemberDomain", societyMemberDomain);
		}
		return "redirect:/member";
	}
	
	@RequestMapping(value = "editMember", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SocietyMemberDomain> editMember(@RequestBody SocietyMemberDomain societyMemberDomain, HttpSession session) {
		
		Integer societyId = (Integer) session.getAttribute("SOCIETYID");
		societyMemberDomain.setSocietyId(societyId);
		
		if(societyMemberDomain.getMemberId() == null)
			return new ResponseEntity<SocietyMemberDomain>(societyMemberDomain, HttpStatus.INTERNAL_SERVER_ERROR);
		
		if(memberService.getSocietyMemberDetails(societyMemberDomain))
			return new ResponseEntity<SocietyMemberDomain>(societyMemberDomain, HttpStatus.OK);
		else
			return new ResponseEntity<SocietyMemberDomain>(societyMemberDomain, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "deleteMember", method = RequestMethod.POST)
	public String deleteMemberDetails(@ModelAttribute("societyMemberDomain") SocietyMemberDomain societyMemberDomain, RedirectAttributes redirectAttributes) {
		
		if(societyMemberDomain.getMemberId() != null && memberService.deleteMemberDetails(societyMemberDomain.getMemberId())) {
			redirectAttributes.addFlashAttribute("deleted", true);
		}
		else {
			redirectAttributes.addFlashAttribute("deleted", false);
		}
		return "redirect:/member";
	}
}
