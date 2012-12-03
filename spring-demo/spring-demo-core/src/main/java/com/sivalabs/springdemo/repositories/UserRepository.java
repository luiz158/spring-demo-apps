package com.sivalabs.springdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sivalabs.springdemo.entities.User;

/**
 * @author Siva
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>
{

}
