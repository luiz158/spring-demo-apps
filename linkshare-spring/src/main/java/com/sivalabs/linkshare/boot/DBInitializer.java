/**
 * 
 */
package com.sivalabs.linkshare.boot;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sivalabs.linkshare.entities.Link;
import com.sivalabs.linkshare.entities.User;
import com.sivalabs.linkshare.service.LinkService;
import com.sivalabs.linkshare.service.UserService;

/**
 * @author Siva
 *
 */
@Component
public class DBInitializer
{
	private static Logger logger = LoggerFactory.getLogger(DBInitializer.class);
	
	@Value("${InitDB}")
	private boolean executeDBInitScript;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LinkService linkService;
	
	@PostConstruct
	void init()
	{
		if(!executeDBInitScript){
			return;
		}
		User admin = new User();
		admin.setUserName("admin");
		admin.setPassword("admin");
		admin.setEmailId("admin@gmail.com");
		
		createUserQuietly(admin);
		
		User test = new User();
		test.setUserName("test");
		test.setPassword("test");
		test.setEmailId("test@gmail.com");
		
		createUserQuietly(test);
		
		Link link1 = new Link();
		link1.setTitle("JavaLobby");
		link1.setUrl("www.javalobby.com");
		link1.setDescription("JavaLobby is a Java Community Site");
		link1.setPostedBy(userService.getUserByName("admin"));
		link1.setPostedOn(new Date());
		
		createLinkQuietly(link1);
		
		Link link2 = new Link();
		link2.setTitle("JavaCodeGeeks");
		link2.setUrl("www.JavaCodeGeeks.com");
		link2.setDescription("JavaCodeGeeks is a Java Community Site");
		link2.setPostedBy(userService.getUserByName("test"));
		link2.setPostedOn(new Date());
		
		createLinkQuietly(link2);
		
		Link link3 = new Link();
		link3.setTitle("SivaLabs");
		link3.setUrl("www.SivaLabs.in");
		link3.setDescription("SivaLabs is My Blog");
		link3.setPostedBy(userService.getUserByName("admin"));
		link3.setPostedOn(new Date());
		
		createLinkQuietly(link3);
		
	}
	
	private void createUserQuietly(User user){
		try
		{
			userService.createUser(user);
		} catch (Exception e)
		{
			logger.error(e.getMessage());
		}
	}
	
	private void createLinkQuietly(Link link){
		try
		{
			linkService.saveLink(link);
		} catch (Exception e)
		{
			logger.error(e.getMessage());
		}
	}
}
