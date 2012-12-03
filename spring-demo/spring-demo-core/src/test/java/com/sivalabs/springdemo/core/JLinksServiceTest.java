package com.sivalabs.springdemo.core;

import static org.junit.Assert.assertNotNull;

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

import com.sivalabs.springdemo.entities.Link;
import com.sivalabs.springdemo.entities.Preference;
import com.sivalabs.springdemo.entities.Tag;
import com.sivalabs.springdemo.entities.User;
import com.sivalabs.springdemo.services.JLinksService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext.xml")
public class JLinksServiceTest 
{

	@Autowired
	private JLinksService jLinksService;
	
	@Test @Ignore
	public void testFindAllUsers() 
	{
		List<User> allUsers = jLinksService.findAllUsers();
		assertNotNull(allUsers);
		for (User user : allUsers) {
			System.err.println(user);
		}
	}

	@Test 
	@Ignore
	public void testLogin() 
	{
		User user = this.jLinksService.login("sivaprasadreddy.k@gmail.com", "siva");
		System.out.println(user.getFirstName());
	}

	@Test 
	@Ignore
	public void testSaveUser() 
	{
		User user = new User();
		user.setEmail("admin@gmail.com");
		user.setPassword("admin");
		user.setFirstName("Administrator");
		user.setLastName("");
		Set<Preference> preferences = new HashSet<Preference>();
			Preference preference1 = new Preference();
			preference1.setName("daily_link_feed_notifications");
			preference1.setValue("yes");
			preference1.setUser(user);
			
			Preference preference2 = new Preference();
			preference2.setName("fav_tag_links_notifications");
			preference2.setValue("no");
			preference2.setUser(user);
			
		preferences.add(preference1);
		preferences.add(preference2);
			
		user.setPreferences(preferences );
		
		this.jLinksService.saveUser(user);
		
	}

	@Test 
	@Ignore
	public void testFindUserById() {
		User user = this.jLinksService.findUserById(1);
		assertNotNull(user);
		System.out.println(user.getEmail());
	}

	@Test 
	@Ignore
	public void testFindUserByEmail() {
		User user = this.jLinksService.findUserByEmail("sivaprasadreddy.k@gmail.com");
		assertNotNull(user);
		System.out.println(user.getFirstName());
	}

	@Test 
	@Ignore
	public void testCreateLink() 
	{
		//User user = this.jLinksService.findUserByEmail("sivaprasadreddy.k@gmail.com");
		User user = this.jLinksService.findUserByEmail("admin@gmail.com");
		Link link = new Link();
		link.setCreadtedBy(user);
		
		/*link.setTitle("SivaLabs Blog");
		link.setDescription("My Experiment with technology");
		link.setCreatedOn(new Date());
		link.setUrl("www.sivalabs.in");
		*/
		
		link.setTitle("TutorialsPoint");
		link.setDescription("TutorialsPoint WebSite");
		link.setCreatedOn(new Date());
		link.setUrl("www.TutorialsPoint.com");
		
		Set<Tag> tags = new HashSet<Tag>();
		Tag tag1 = new Tag();
		//tag1.setTagId(1);
		tag1.setTagName("java"); tag1.setDescription("java programming language");
		
		Tag tag2 = new Tag();
		//tag2.setTagId(2);
		tag2.setTagName("blog"); tag2.setDescription("Blogs");
		
		tags.add(tag1);
		tags.add(tag2);
		
		link.setTags(tags );
		
		this.jLinksService.createLink(link);
		
	}

	@Test 
	@Ignore
	public void testFindLinkById() {
		Link link = this.jLinksService.findLinkById(1);
		assertNotNull(link);
		System.out.println(link.getTitle());
	}

	@Test 
	@Ignore
	public void testFindLinksByUserId() {
		List<Link> linksByUser = this.jLinksService.findLinksByUserId(1);
		for (Link link : linksByUser) {
			System.out.println(link.getTitle());
		}
	}

	@Test 
	@Ignore
	public void testFindLinksByUserEmail() {
		List<Link> linksByUser = this.jLinksService.findLinksByUserEmail("sivaprasadreddy.k@gmail.com");
		for (Link link : linksByUser) {
			System.out.println(link.getTitle());
		}
	}

	@Test 
	@Ignore
	public void testFindLinksByTag() {
		List<Link> linksByTag = this.jLinksService.findLinksByTag(11);
		for (Link link : linksByTag) {
			System.out.println(link.getTitle());
		}
	}
	
	@Test 
	@Ignore
	public void testAddFavoriteTags() {
		
		Set<Tag> tags = new HashSet<Tag>();
		Tag tag1 = new Tag();
		tag1.setTagName("javaee6"); tag1.setDescription("JavaEE6");
		
		Tag tag2 = new Tag();
		tag2.setTagName("java"); tag2.setDescription("java programming language");
		
		Tag tag3 = new Tag();
		tag3.setTagName("spring"); tag3.setDescription("Spring framework");
		
		Tag tag4 = new Tag();
		tag4.setTagName("hibernate"); tag4.setDescription("Hibernate framework");
		
		Tag tag5 = new Tag();
		tag5.setTagName("junit"); tag5.setDescription("junit framework");
		
		tags.add(tag1);
		tags.add(tag2);
		tags.add(tag3);
		tags.add(tag4);
		tags.add(tag5);
		
		this.jLinksService.addFavoriteTags(1, tags);
	}

	@Test 
	@Ignore
	public void testRemoveFavoriteTags() {
		
		Set<Tag> tags = new HashSet<Tag>();
		Tag tag1 = new Tag();
		tag1.setTagId(5);
		//tag1.setTagName("javaee6"); tag1.setDescription("JavaEE6");
		
		tags.add(tag1);
		
		this.jLinksService.removeFavoriteTags(1, tags);
	}
}
