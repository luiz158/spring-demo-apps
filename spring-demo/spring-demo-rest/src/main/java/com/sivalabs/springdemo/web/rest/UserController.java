/**
 * 
 */
package com.sivalabs.springdemo.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sivalabs.springdemo.entities.User;
import com.sivalabs.springdemo.services.UserService;

/**
 * @author skatam
 *
 */
@Controller
@RequestMapping("/users")
public class UserController 
{

	@Autowired
	private UserService userService;
	
	@Autowired
	Mapper dozerMapper;
	
	@RequestMapping(value={"","/list"}, method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<User> listUsers(Model model, HttpServletRequest request) 
	{
		List<User> users = userService.findAllUsers();
		List<User> cleanUsers = new ArrayList<User>();
		dozerMapper.map(users, cleanUsers);
		return cleanUsers;
	}
	
}
