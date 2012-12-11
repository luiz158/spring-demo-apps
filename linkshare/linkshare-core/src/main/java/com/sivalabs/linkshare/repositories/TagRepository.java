/**
 * 
 */
package com.sivalabs.linkshare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sivalabs.linkshare.entities.Tag;

/**
 * @author siva
 *
 */
public interface TagRepository extends JpaRepository<Tag, Integer>
{

	@Query("select t from Tag t where t.label like ?1")
	List<Tag> findByLabelStartingWith(String query);

}
