/**
 * 
 */
package com.sivalabs.jbb.services;

import java.util.List;

import com.sivalabs.jbb.entities.User;

/**
 * @author Siva
 *
 */
public interface UserService 
{
	List<User> findAllUsers();
	User createUser(User user);
	User getUserById(Integer userId);	
}
