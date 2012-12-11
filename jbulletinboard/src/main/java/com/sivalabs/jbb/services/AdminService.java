package com.sivalabs.jbb.services;

import com.sivalabs.jbb.entities.Category;
import com.sivalabs.jbb.entities.Forum;

/**
 * @author Siva
 *
 */
public interface AdminService
{
	Category createCategory(Category category);
	Forum createForum(Forum forum);
	
}
