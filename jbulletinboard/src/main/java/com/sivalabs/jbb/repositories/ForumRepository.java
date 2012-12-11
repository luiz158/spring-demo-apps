/**
 * 
 */
package com.sivalabs.jbb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sivalabs.jbb.entities.Forum;

/**
 * @author Siva
 *
 */
public interface ForumRepository extends JpaRepository<Forum, Integer>
{

}
