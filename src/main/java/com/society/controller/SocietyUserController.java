package com.society.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.society.helper.BreadCrumbHelper;
import com.society.helper.model.BreadCrumb;
import com.society.model.domain.SocietyUserDomain;
import com.society.model.domain.UserDomain;
import com.society.service.SocietyUserService;

@Controller
public class SocietyUserController {
	
	@Autowired
	private BreadCrumbHelper breadCrumbHelper;
	
	@Autowired
	private SocietyUserService societyUserService;
	
	@RequestMapping(value = "createUser", method = RequestMethod.GET)
	public ModelAndView getUser(@ModelAttribute("societyUserDomain")SocietyUserDomain societyUserDomain) {
		
		String[] breadCrumbs = {"Society", "Create User"};
		List<BreadCrumb> breadCrumbList = breadCrumbHelper.getBreadCrumbList(breadCrumbs);
		
		if(societyUserDomain == null)
			societyUserDomain = new SocietyUserDomain();
		
		ModelAndView modelAndView = new ModelAndView("createUser", "societyUserDomain", societyUserDomain);
		modelAndView.addObject(breadCrumbList);
		return modelAndView;
	}
	
	@RequestMapping(value = "createUser", method = RequestMethod.POST)
	public String postUser(@ModelAttribute SocietyUserDomain societyUserDomain, RedirectAttributes redirectAttributes, HttpSession session) {
		
		societyUserDomain.setSocietyId((Integer)session.getAttribute("SOCIETYID"));
		
		if(societyUserService.checkUserNameExist(societyUserDomain)) {
			redirectAttributes.addFlashAttribute("societyUserDomain", societyUserDomain);
			redirectAttributes.addFlashAttribute("userExist", true);
			return "redirect:/createUser";
		}
			
			
		if(societyUserService.insertSocietyUserDetails(societyUserDomain))
			redirectAttributes.addFlashAttribute("successMsg", "Congrats!!! Your society new user account created successfully.");
		else
			redirectAttributes.addFlashAttribute("successMsg", "There is problem with creating new society user account.");
		
		return "redirect:/createUserStatus";
	}
	
	@RequestMapping(value = "createUserStatus", method = RequestMethod.GET)
	public ModelAndView getUserStatus() {
		return new ModelAndView("createUserStatus");
	}
	
	@RequestMapping(value = "userList", method = RequestMethod.GET)
	public ModelAndView getUserList(HttpSession session) {
		
		String[] breadCrumbs = {"Society", "User List"};
		List<BreadCrumb> breadCrumbList = breadCrumbHelper.getBreadCrumbList(breadCrumbs);
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		List<UserDomain> userDomainList = societyUserService.getUserList(societyId);
		
		ModelAndView modelAndView = new ModelAndView("userList");
		modelAndView.addObject("userDomainList", userDomainList);
		modelAndView.addObject(breadCrumbList);
		return modelAndView;
	}
	
	@RequestMapping(value = "viewUser", method = RequestMethod.GET)
	public ModelAndView viewUserDetail(@RequestParam("id") Integer userId) {
		
		String[] breadCrumbs = {"Society", "View User Detail"};
		List<BreadCrumb> breadCrumbList = breadCrumbHelper.getBreadCrumbList(breadCrumbs);
		
		SocietyUserDomain societyUserDomain = societyUserService.getUser(userId);
		
		ModelAndView modelAndView = new ModelAndView("createUser","societyUserDomain", societyUserDomain);
		modelAndView.addObject(breadCrumbList);
		return modelAndView;
	}
	
}
