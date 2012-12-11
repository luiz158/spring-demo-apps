/**
 * 
 */
package com.sivalabs.linkshare.web.jsf2.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sivalabs.linkshare.LinkShareException;
import com.sivalabs.linkshare.entities.UserAccount;
import com.sivalabs.linkshare.services.UserAccountService;

/**
 * @author Siva
 *
 */
@Component
@ManagedBean
@SessionScoped
public class UserController {
	
	@Autowired
	private UserAccountService userAccountService;
	
	private UserAccount loginUser = null;
	private UserAccount registerUser = null;

	public UserAccount getLoginUser() {
		if(loginUser == null){
			this.loginUser = new UserAccount();
		}
		return loginUser;
	}

	public void setLoginUser(UserAccount loginUser) {
		this.loginUser = loginUser;
	}

	public UserAccount getRegisterUser() {
		if(registerUser == null){
			this.registerUser = new UserAccount();
		}
		return registerUser;
	}

	public void setRegisterUser(UserAccount registerUser) {
		this.registerUser = registerUser;
	}
	
	public String login() 
	{
		String view = "login";
		UserAccount userAccount = this.userAccountService.login(loginUser.getUserName(), loginUser.getPassword());
		if(userAccount != null){
			this.loginUser = userAccount;
		    view = "home";
		}else{
			FacesContext.getCurrentInstance()
			.addMessage(FacesMessage.FACES_MESSAGES, new FacesMessage("Invalid UserName & Password"));
		}
		return view;
	}
	
	public String register() 
	{
		String view = "login";
		UserAccount userAccount = null;
		try {
			userAccount = this.userAccountService.createUser(registerUser);
			if(userAccount != null){
				this.registerUser = null;
				FacesContext.getCurrentInstance()
				.addMessage(FacesMessage.FACES_MESSAGES, new FacesMessage("User Registration Successful.."));
			}else{
				FacesContext.getCurrentInstance()
				.addMessage(FacesMessage.FACES_MESSAGES, new FacesMessage("User Registration Failed."));
			}
		} catch (LinkShareException e) {
			FacesContext.getCurrentInstance()
			.addMessage(FacesMessage.FACES_MESSAGES, new FacesMessage(e.getMessage()));
		}
		
		return view;
	}
}
