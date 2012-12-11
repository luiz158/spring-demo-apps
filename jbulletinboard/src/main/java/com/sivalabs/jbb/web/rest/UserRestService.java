/**
 * 
 */
package com.sivalabs.jbb.web.rest;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sivalabs.jbb.entities.User;
import com.sivalabs.jbb.services.UserService;

/**
 * @author Siva
 *
 */
@Controller
@RequestMapping(value="/users", produces="application/json")
public class UserRestService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private Mapper dozerMapper;
	
	@RequestMapping(value="/**", produces="text/plain")
	@ResponseBody
	public String welcome() 
	{
		return "Welcome to JBB";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUsers() 
	{
		 List<User> users = userService.findAllUsers();
		 return users;
	}
	
	
	@RequestMapping(value="/{userId}", method=RequestMethod.GET)
	@ResponseBody
	public User getUser(@PathVariable("userId") Integer userId) 
	{
		 User user = userService.getUserById(userId);
		 User userPojo = dozerMapper.map(user, User.class);
		 
		 return userPojo;
	}
	
}
