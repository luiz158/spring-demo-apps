/**
 * 
 */
package com.sivalabs.jbb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sivalabs.jbb.entities.User;
import com.sivalabs.jbb.repositories.UserRepository;
import com.sivalabs.jbb.services.UserService;

/**
 * @author Siva
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService
{
	@Autowired private UserRepository userRepository;
	
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User createUser(User user)
	{
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Integer userId)
	{
		return userRepository.findOne(userId);
	}
	
}
