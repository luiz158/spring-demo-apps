package com.sivalabs.jbb.services;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sivalabs.jbb.entities.Category;
import com.sivalabs.jbb.entities.Forum;
import com.sivalabs.jbb.entities.Post;
import com.sivalabs.jbb.entities.Topic;
import com.sivalabs.jbb.services.ForumService;
import com.sivalabs.jbb.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext.xml")
public class ForumServiceTest
{
	@Autowired private UserService userService;
	@Autowired private ForumService forumService;
	

	@Test
	public void testFindAllCategories()
	{
		List<Category> categories = forumService.findAllCategories();
		assertNotNull(categories);
		assertTrue(categories.size()>0);
	}

	@Test
	public void testFindCategoryById()
	{
		Category category = forumService.findCategoryById(1);
		assertNotNull(category);
	}

	@Test
	public void testFindForumsByCategory()
	{
		List<Forum> forums = forumService.findForumsByCategory(1);
		assertNotNull(forums);
		assertTrue(forums.size()>0);
	}

	@Test
	public void testFindForumById()
	{
		Forum forum = forumService.findForumById(1);
		assertNotNull(forum);
	}
	
	@Test
	public void testFindTopicsByForum()
	{
		List<Topic> topics = forumService.findTopicsByForum(1);
		assertNotNull(topics);
		assertTrue(topics.size()> 0);
	}

	@Test
	public void testFindTopicById()
	{
		Topic topic = forumService.findTopicById(1);
		assertNotNull(topic);
	}

	@Test
	public void testFindPostsByTopic()
	{
		List<Post> posts = forumService.findPostsByTopic(1);
		assertNotNull(posts);
		assertTrue(posts.size()> 0);
	}

	@Test
	public void testFindPostById()
	{
		Post post = forumService.findPostById(1);
		assertNotNull(post);
	}
	
	@Test
	@Ignore
	public void testCreateTopic()
	{
		Topic topic = new Topic();
		topic.setCreatedBy(userService.getUserById(1));
		topic.setCreatedOn(new Date());
		topic.setForum(new Forum(1));
		topic.setTitle("Test Topic1");
		Set<Post> posts = new HashSet<Post>();
		Post post = new Post();
		post.setCreatedBy(userService.getUserById(1));
		post.setCreatedOn(new Date());
		post.setTitle("Test Post1");
		post.setTopic(topic);
		posts.add(post);
		topic.setPosts(posts );
		
		Topic createdTopic = forumService.createTopic(topic);
		assertNotNull(createdTopic);
		assertNotNull(createdTopic.getTopicId());
		assertNotNull(post.getPostId());
		
		
	}

	@Test
	@Ignore
	public void testCreatePost()
	{
		Post post = new Post();
		post.setCreatedBy(userService.getUserById(1));
		post.setCreatedOn(new Date());
		post.setTitle("Test Post1");
		post.setTopic(forumService.findTopicById(1));
		post.setMessage("This is a sample post");
		
		Post createdPost = forumService.createPost(post);
		assertNotNull(createdPost);
		assertNotNull(createdPost.getPostId());
	}

	@Test
	@Ignore
	public void testUpdatePost()
	{
		Post post = forumService.findPostById(1);
		post.setUpdatedBy(userService.getUserById(2));
		post.setUpdatedOn(new Date());
		Post updatedPost = forumService.updatePost(post);
		assertNotNull(updatedPost);
		assertTrue(updatedPost.getUpdatedBy().getUserId() == 2);
	}
	

}
