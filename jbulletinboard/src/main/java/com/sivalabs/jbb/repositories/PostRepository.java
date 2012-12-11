/**
 * 
 */
package com.sivalabs.jbb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sivalabs.jbb.entities.Post;

/**
 * @author Siva
 *
 */
public interface PostRepository extends JpaRepository<Post, Integer>
{

}
