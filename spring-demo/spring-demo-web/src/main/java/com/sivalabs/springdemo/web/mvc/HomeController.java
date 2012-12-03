package com.sivalabs.springdemo.web.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sivalabs.springdemo.entities.User;
import com.sivalabs.springdemo.services.JLinksService;

/**
 * @author Siva
 *
 */
@Controller
public class HomeController
{
	@Autowired
	private JLinksService jLinksService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginForm()
	{
		return "login";
	}
	
	@RequestMapping("welcome")
	public String welcome(Model model)
	{
		model.addAttribute("Welcome", "Welcome to AppStore!");
		return "welcome";
	}
	
	public String showUserProfile(Model model, @RequestParam("userId") Integer userId)
	{
		User user = this.jLinksService.findUserById(userId);
		model.addAttribute("USER_KEY", user);
		return "user_profile";
	}
	
	
}
