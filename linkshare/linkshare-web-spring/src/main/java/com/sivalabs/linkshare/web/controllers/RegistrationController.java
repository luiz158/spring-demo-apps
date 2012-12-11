/**
 * 
 */
package com.sivalabs.linkshare.web.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sivalabs.core.logging.Slf4JLogger;
import com.sivalabs.linkshare.LinkShareException;
import com.sivalabs.linkshare.entities.UserAccount;
import com.sivalabs.linkshare.services.UserAccountService;
import com.sivalabs.linkshare.utils.Validators;

/**
 * @author siva
 *
 */
@Controller
public class RegistrationController 
{
	@Slf4JLogger
	private Logger logger;
	
	@Autowired
	private UserAccountService userAccountService;
	
	
	
	@RequestMapping(value="registration", method=RequestMethod.GET)
	public String showRegistrationForm(Model model) 
	{
		model.addAttribute("user", new UserAccount());
		return "user/registration";
	}
	
	@RequestMapping(value="registration", method=RequestMethod.POST)
	public String registerUser(@Validated(Validators.RegistrationValidation.class) @ModelAttribute UserAccount userAccount,
								BindingResult result, 
								Model model,
								RedirectAttributes redirectAttributes) 
	{
		if(result.hasErrors())
		{
			return "user/registration";
		}
		Integer userId = null;
		try {
			userId = userAccountService.createUser(userAccount).getUserId();
			logger.debug("New UserId : "+userId);
		} catch (LinkShareException e) {
			logger.error(null, e);
			result.addError(new ObjectError("user",  e.getMessage()));
			return "user/registration";
		}
		redirectAttributes.addFlashAttribute("message", "Registered successfully.");
		return "redirect:login.htm";
	}

	
}
