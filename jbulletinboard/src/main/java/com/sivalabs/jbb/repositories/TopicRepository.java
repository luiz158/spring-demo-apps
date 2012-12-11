/**
 * 
 */
package com.sivalabs.jbb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sivalabs.jbb.entities.Topic;

/**
 * @author Siva
 *
 */
public interface TopicRepository extends JpaRepository<Topic, Integer>
{

}
