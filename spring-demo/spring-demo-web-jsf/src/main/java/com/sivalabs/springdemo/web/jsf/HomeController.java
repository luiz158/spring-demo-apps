package com.sivalabs.springdemo.web.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sivalabs.springdemo.entities.User;
import com.sivalabs.springdemo.services.UserService;
import com.sivalabs.springdemo.web.utils.JSFUtils;

/**
 * @author Siva
 *
 */
@ManagedBean
//@RequestScoped
@Component
@Scope("request")
public class HomeController
{
	@Autowired
	private UserService userService;
	
	private String loginEmail;
	private String loginPwd;
	
	private List<User> users = null;
	
	public HomeController()
	{
	}
	
	@PostConstruct
	public void init()
	{
		
	}
	
	public List<User> getUsers()
	{
		if(users == null)
		{
			System.out.println("Loading from DB...");
			users = userService.findAllUsers();	
		}
		return users;
	}
	
	public String login()
	{
		boolean success = "admin@gmail.com".equals(loginEmail) && "admin".equals(loginPwd);
		if(!success){
			JSFUtils.addErrorMessage(null, "Invalid EmailId and Password.");
		}
		System.err.println(success);
		return (success)? "welcome.jsf?faces-redirect=true" : "login";
	}
	public String getLoginEmail()
	{
		return loginEmail;
	}

	public void setLoginEmail(String loginEmail)
	{
		this.loginEmail = loginEmail;
	}

	public String getLoginPwd()
	{
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd)
	{
		this.loginPwd = loginPwd;
	}
	
	
}
