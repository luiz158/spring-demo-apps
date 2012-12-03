/**
 * 
 */
package com.sivalabs.springdemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sivalabs.springdemo.entities.Link;

/**
 * @author Siva
 *
 */
public interface LinkRepository extends JpaRepository<Link, Integer>
{

	List<Link> findLinksByCreadtedByUserId(Integer userId);

	List<Link> findLinksByCreadtedByEmail(String email);

	

}
