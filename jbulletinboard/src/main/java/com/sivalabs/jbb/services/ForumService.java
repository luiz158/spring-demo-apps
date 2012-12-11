package com.sivalabs.jbb.services;

import java.util.List;

import com.sivalabs.jbb.entities.Category;
import com.sivalabs.jbb.entities.Forum;
import com.sivalabs.jbb.entities.Post;
import com.sivalabs.jbb.entities.Topic;

/**
 * @author Siva
 *
 */
public interface ForumService
{
	List<Category> findAllCategories();
	Category findCategoryById(Integer categoryId);
	
	List<Forum> findForumsByCategory(Integer categoryId);
	Forum findForumById(Integer forumId);
	
	List<Topic> findTopicsByForum(Integer forumId);
	Topic createTopic(Topic topic);
	Topic findTopicById(Integer topicId);
	
	List<Post> findPostsByTopic(Integer topicId);
	Post findPostById(Integer postId);
	Post createPost(Post post);
	Post updatePost(Post post);
	List<Forum> findAllForums();
}
