/**
 * 
 */
package com.sivalabs.jbb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sivalabs.jbb.entities.User;

/**
 * @author Siva
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>
{

}
