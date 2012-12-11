package com.sivalabs.jbb.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sivalabs.jbb.entities.Category;
import com.sivalabs.jbb.entities.Forum;
import com.sivalabs.jbb.entities.Post;
import com.sivalabs.jbb.entities.Topic;
import com.sivalabs.jbb.repositories.CategoryRepository;
import com.sivalabs.jbb.repositories.ForumRepository;
import com.sivalabs.jbb.repositories.PostRepository;
import com.sivalabs.jbb.repositories.TopicRepository;
import com.sivalabs.jbb.services.ForumService;

/**
 * @author Siva
 *
 */
@Service
@Transactional
public class ForumServiceImpl implements ForumService
{
	@Autowired private CategoryRepository categoryRepository;
	@Autowired private ForumRepository forumRepository;
	@Autowired private TopicRepository topicRepository;
	@Autowired private PostRepository postRepository;
	
	
	@Override
	public Topic createTopic(Topic topic)
	{
		return topicRepository.save(topic);
	}

	@Override
	public Post createPost(Post post)
	{
		return postRepository.save(post);
	}

	@Override
	public Post updatePost(Post post)
	{
		return postRepository.save(post);
	}

	@Override
	public List<Category> findAllCategories()
	{
		return categoryRepository.findAll();
	}

	@Override
	public Category findCategoryById(Integer categoryId)
	{
		return categoryRepository.findOne(categoryId);
	}

	@Override
	public List<Forum> findForumsByCategory(Integer categoryId)
	{
		return new ArrayList<Forum>(findCategoryById(categoryId).getForums());
	}

	@Override
	public List<Forum> findAllForums() {
		return forumRepository.findAll();
	}
	
	@Override
	public Forum findForumById(Integer forumId)
	{
		return forumRepository.findOne(forumId);
	}

	@Override
	public List<Topic> findTopicsByForum(Integer forumId)
	{
		return new ArrayList<Topic>(findForumById(forumId).getTopics());
	}

	@Override
	public Topic findTopicById(Integer topicId)
	{
		return topicRepository.findOne(topicId);
	}

	@Override
	public List<Post> findPostsByTopic(Integer topicId)
	{
		return new ArrayList<Post>(topicRepository.findOne(topicId).getPosts());
	}

	@Override
	public Post findPostById(Integer postId)
	{
		return postRepository.findOne(postId);
	}


}
