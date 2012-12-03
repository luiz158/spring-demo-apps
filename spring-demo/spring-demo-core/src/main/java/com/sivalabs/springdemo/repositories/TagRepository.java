/**
 * 
 */
package com.sivalabs.springdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sivalabs.springdemo.entities.Tag;

/**
 * @author Siva
 *
 */
public interface TagRepository extends JpaRepository<Tag, Integer>
{

	Tag findTagByTagName(String tagName);

}
