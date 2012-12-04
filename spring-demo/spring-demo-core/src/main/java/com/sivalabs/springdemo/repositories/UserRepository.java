/**
 * 
 */
package com.sivalabs.springdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sivalabs.springdemo.entities.User;

/**
 * @author Siva
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>
{

	@Query("select u from User u where u.userName=?1 and u.password=?2")
	User login(String userName, String password);

	User findUserByEmail(String email);

	User findUserByUserName(String username);

}
