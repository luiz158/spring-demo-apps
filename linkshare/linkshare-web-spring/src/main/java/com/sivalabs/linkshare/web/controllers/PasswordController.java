/**
 * 
 */
package com.sivalabs.linkshare.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sivalabs.core.logging.Slf4JLogger;
import com.sivalabs.linkshare.services.UserAccountService;
import com.sivalabs.linkshare.web.WebUtils;

/**
 * @author siva
 *
 */
@Controller
public class PasswordController 
{
	@Slf4JLogger
	private Logger logger;
	
	@Autowired
	private UserAccountService userAccountService;
	
	

	
	@RequestMapping(value="forgotPwd", method=RequestMethod.GET)
	public String showForgotPwdForm(Model model) 
	{
		//model.addAttribute("user", new UserAccount());
		return "user/forgotPwd";
	}
	
	@RequestMapping(value="forgotPwd", method=RequestMethod.POST)
	public String forgotPwd(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) 
	{
		String emailId = request.getParameter("emailId");
		
		boolean sentEmail = userAccountService.forgotPwd(emailId);
		if(sentEmail)
		{
			redirectAttributes.addFlashAttribute("message", "Password Reset Email sent to your Email.");
			return "redirect:login.htm";
		}
		else
		{
			//result.reject("forgotPwd.failed", "Password recovery failed. Please try again");
			model.addAttribute("message", "Password recovery failed. Please try again.");
			return "user/forgotPwd";
		}
	}
	
	@RequestMapping(value="resetPwd", method=RequestMethod.GET)
	public String showResetPwdForm(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) 
	{
		String emailId = request.getParameter("emailId");
		String pwdRestToken = request.getParameter("pwdRestToken");
		boolean validToken = userAccountService.validateResetPwdToken(emailId, pwdRestToken);
		if(validToken)
		{
			model.addAttribute("pwdRestToken", pwdRestToken);
			return "user/resetPwd";
		}
		else
		{
			model.addAttribute("message", "Invalid Reset Token or Token was Expired.");
			return "user/resetPwd";
		}
	}
	
	@RequestMapping(value="resetPwd", method=RequestMethod.POST)
	public String resetPwd(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) 
	{
		String emailId = request.getParameter("emailId");
		String newPwd = request.getParameter("newPwd");
		
		boolean updated = this.userAccountService.resetPwd(emailId, newPwd);
		if(updated)
		{
			redirectAttributes.addFlashAttribute("message", "Password Updated Successfully.");
			return "redirect:login.htm";
		}
		else
		{
			redirectAttributes.addFlashAttribute("message", "Password Updation Failed. Please try later.");
			return "redirect:login.htm";
		}
	}
	
	
	@RequestMapping(value="changePwd", method=RequestMethod.POST)
	@ResponseBody
	public String changePwd(HttpServletRequest request) {
		String response = "Password updation failed.";
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		//String confPwd = request.getParameter("confPwd");
		Integer userId = WebUtils.getLoggedInUser(request.getSession(false)).getUserId();
		boolean changed = this.userAccountService.changePwd(userId, oldPwd, newPwd);
		if(changed){
			response = "Password updated successfully.";
		}
		return response;
	}
	
	
}
