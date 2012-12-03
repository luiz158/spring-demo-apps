/**
 * 
 */
package com.sivalabs.springdemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sivalabs.springdemo.entities.User;
import com.sivalabs.springdemo.repositories.UserRepository;


/**
 * @author skatam
 *
 */
@Service
@Transactional
public class UserService 
{
	@Autowired private UserRepository userRepository;
	
	public List<User> findAllUsers()
	{
		return userRepository.findAll();
	}

	public User findUserById(Integer userId)
	{
		return userRepository.findOne(userId);
	}
	
}
