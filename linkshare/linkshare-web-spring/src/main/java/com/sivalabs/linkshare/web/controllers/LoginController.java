/**
 * 
 */
package com.sivalabs.linkshare.web.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sivalabs.core.logging.Slf4JLogger;
import com.sivalabs.linkshare.entities.UserAccount;
import com.sivalabs.linkshare.services.UserAccountService;
import com.sivalabs.linkshare.utils.Validators;
import com.sivalabs.linkshare.web.WebUtils;

/**
 * @author siva
 *
 */
@Controller
public class LoginController 
{
	@Slf4JLogger
	private Logger logger;
	
	@Autowired
	private UserAccountService userAccountService;
	
	
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String showLoginForm(Model model) 
	{
		model.addAttribute("user", new UserAccount());
		return "user/login";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(@Validated(Validators.LoginValidation.class) @ModelAttribute UserAccount userAccount, 
								BindingResult result, 
								Model model, 
								HttpSession session) 
	{
		if(result.hasErrors())
		{
			logger.debug("Found Errors in Login parameters");
			return "user/login";
		}
		
		UserAccount loginUser = userAccountService.login(userAccount.getUserName(), userAccount.getPassword());
		if(loginUser !=null)
		{
			WebUtils.setLoggedInUser(session, loginUser);
			return "redirect:home.htm";
		}
		else
		{
			result.reject("login.failed", "Login Failed. Please try again");
			return "user/login";
		}
	}

	
	
}
