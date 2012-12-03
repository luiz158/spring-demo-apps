package com.sivalabs.springdemo.web.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sivalabs.springdemo.entities.User;
import com.sivalabs.springdemo.services.UserService;

/**
 * @author Siva
 *
 */
@Controller
public class HomeController
{
	@Autowired
	private UserService userService;
	
	@RequestMapping("/welcome")
	public String welcome(Model model, HttpServletRequest request)
	{
		List<User> users = userService.findAllUsers();
		model.addAttribute("USERS", users);		
		return "welcome";
	}
}
