/**
 * 
 */
package com.sivalabs.jbb.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sivalabs.jbb.entities.User;
import com.sivalabs.jbb.services.UserService;

/**
 * @author Siva
 *
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	private List<User> users = null;
	
	public UserController() {
		
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	//@RequestMapping()
	public List<User> getUsers() {
		if(users == null){
			users = userService.findAllUsers();
		}
		return users;
	}
	
	/*@RequestMapping(value="/hello1", produces="text/html")
	public String hello() {
		System.out.println("---------------------");
		return "index";
	}*/
}
