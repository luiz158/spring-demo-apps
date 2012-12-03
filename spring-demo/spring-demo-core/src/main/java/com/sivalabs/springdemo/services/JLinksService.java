/**
 * 
 */
package com.sivalabs.springdemo.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sivalabs.springdemo.entities.Link;
import com.sivalabs.springdemo.entities.Tag;
import com.sivalabs.springdemo.entities.User;
import com.sivalabs.springdemo.repositories.LinkRepository;
import com.sivalabs.springdemo.repositories.TagRepository;
import com.sivalabs.springdemo.repositories.UserRepository;

/**
 * @author Siva
 *
 */
@Service
@Transactional
public class JLinksService 
{
	@Autowired private UserRepository userRepository;
	@Autowired private LinkRepository linkRepository;
	@Autowired private TagRepository tagRepository;
	
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	
	
	public User login(String email, String password) {
		return this.userRepository.login(email, password);
	}
	
	public User saveUser(User user) {
		return this.userRepository.save(user);
	}
	
	public User findUserById(Integer userId) {
		return this.userRepository.findOne(userId);
	}
	
	public User findUserByEmail(String email) {
		return this.userRepository.findUserByEmail(email);
	}
	
	public Link createLink(Link link) {
		Set<Tag> linkTags = new HashSet<Tag>();
		Set<Tag> tags = link.getTags();
		for (Tag tag : tags) {
			Tag existingTag = this.tagRepository.findTagByTagName(tag.getTagName());
			if(existingTag != null){
				linkTags.add(existingTag);
			}else{
				linkTags.add(tag);
			}
		}
		link.setTags(linkTags);
		return this.linkRepository.save(link);
	}
	
	public Link findLinkById(Integer linkId) {
		return this.linkRepository.findOne(linkId);
	}
	
	public List<Link> findLinksByUserId(Integer userId) {
		return this.linkRepository.findLinksByCreadtedByUserId(userId);
	}
	
	public List<Link> findLinksByUserEmail(String email) {
		return this.linkRepository.findLinksByCreadtedByEmail(email);
	}
	
	public List<Link> findLinksByTag(Integer tagId) {
		Tag tag = tagRepository.findOne(tagId);
		if(tag != null){
			return new ArrayList<Link>(tag.getLinks());
		}
		return new ArrayList<Link>();
	}
	
	public void addFavoriteTags(Integer userId, Set<Tag> tags) {
		User user = this.findUserById(userId);
		Set<Tag> favoriteTags = user.getFavoriteTags();
		for (Tag tag : tags) 
		{
			boolean exists = false;
			for (Tag favoriteTag : favoriteTags) 
			{
				if(tag.getTagName().equalsIgnoreCase(favoriteTag.getTagName()))
				{
					System.err.println("already in favorites..");
					exists = true;
					break;
				}
			}
			if(!exists)
			{
				Tag newFavorite = this.tagRepository.findTagByTagName(tag.getTagName());
				user.getFavoriteTags().add(newFavorite);
			}
			
		}
		
	}
	
	public void removeFavoriteTags(Integer userId, Set<Tag> tags) 
	{
		User user = this.findUserById(userId);
		Set<Tag> favoriteTags = user.getFavoriteTags();
		for (Tag tag : tags) 
		{
			for (Tag favoriteTag : favoriteTags) 
			{
				if(tag.getTagId()== favoriteTag.getTagId())
				{
					System.err.println("removing...");
					user.getFavoriteTags().remove(favoriteTag);
					break;
				}
			}
			
		}
		//this.userRepository.save(user);
	}
}
