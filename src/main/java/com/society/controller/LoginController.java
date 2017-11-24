package com.society.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.society.model.domain.LoginDomain;
import com.society.model.domain.MenuRightsDomain;
import com.society.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginDomain loginDomain;
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getLogin() {
		return new ModelAndView("login", "loginDomain", loginDomain);
	}
	
	@RequestMapping(value = "validateLogin", method = RequestMethod.POST)
	public String postLogin(@ModelAttribute("loginDomain") LoginDomain loginDomain, HttpSession session) {
		
		if(loginService.validateLogin(loginDomain)){
			
			MenuRightsDomain menuRightsDomain = new MenuRightsDomain();
			menuRightsDomain.initMenuRights(loginDomain);
			
			session.setAttribute("USERID", loginDomain.getUserId());
			session.setAttribute("USERNAME", loginDomain.getUserName());
			session.setAttribute("ROLENAME", loginDomain.getRoleName());
			session.setAttribute("DISPLAYNAME", loginDomain.getDisplayName());
			session.setAttribute("SOCIETYID", loginDomain.getSocietyId());
			session.setAttribute("MENURIGHTS", menuRightsDomain);
			return "redirect:/home";
		}	
		else
			return "redirect:/";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
