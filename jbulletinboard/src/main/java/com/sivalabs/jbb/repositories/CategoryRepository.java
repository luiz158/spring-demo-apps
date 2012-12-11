/**
 * 
 */
package com.sivalabs.jbb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sivalabs.jbb.entities.Category;

/**
 * @author Siva
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Integer>
{

}
