package com.sivalabs.jbb.services;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sivalabs.jbb.entities.Category;
import com.sivalabs.jbb.entities.Forum;
import com.sivalabs.jbb.services.AdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext.xml")
public class AdminServiceTest
{
	@Autowired
	private AdminService adminService;
	
	@Test
	@Ignore
	public void testCreateCategory() 
	{
		Category category = new Category();
		category.setCategoryName("ORM");
		category.setDescription("Object Relational Mapping");
		category.setDisplayOrder(4);
		
		Category createdCategory = adminService.createCategory(category);
		assertNotNull(createdCategory);
		assertNotNull(createdCategory.getCategoryId());
		
	}
	
	@Test
	@Ignore
	public void testCreateForum() {
		Forum forum = new Forum();
		forum.setForumName("MyBatis");
		forum.setDescription("MyBatis");
		forum.setDisplayOrder(6);
		Category category = new Category();
		category.setCategoryId(4);
		forum.setCategory(category);
		
		Forum createdForum = adminService.createForum(forum);
		assertNotNull(createdForum);
		assertNotNull(createdForum.getForumId());
	}
}
