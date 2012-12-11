package com.sivalabs.jbb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sivalabs.jbb.entities.Category;
import com.sivalabs.jbb.entities.Forum;
import com.sivalabs.jbb.repositories.CategoryRepository;
import com.sivalabs.jbb.repositories.ForumRepository;
import com.sivalabs.jbb.services.AdminService;

/**
 * @author Siva
 *
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService
{
	@Autowired private CategoryRepository categoryRepository;
	@Autowired private ForumRepository forumRepository;
	
	
	@Override
	public Category createCategory(Category category)
	{
		return categoryRepository.save(category);
	}

	@Override
	public Forum createForum(Forum forum)
	{
		return forumRepository.save(forum);
	}

}
