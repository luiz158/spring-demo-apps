/**
 * 
 */
package com.sivalabs.linkshare.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sivalabs.linkshare.entities.Link;
import com.sivalabs.linkshare.entities.UserAccount;

/**
 * @author siva
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext.xml")
@ActiveProfiles("dev")
public class LinkShareServiceTests 
{
	@Autowired
	private LinkService linkService;
	
	@Autowired
	private UserAccountService userAccountService;
	
	public static void main(String[] args) 
	{

	}
	
	//@Test(expected=LinkShareException.class)
	public void testCreateUser() {
		UserAccount userAccount = new UserAccount(null, "admin", "admin", "admin@gmail.com");
		UserAccount savedUser = userAccountService.createUser(userAccount);
		Assert.assertNotNull(savedUser);
		Assert.assertNotNull(savedUser.getUserId());
	}
	
	//@Test()
	public void testLogin() {
		
		
		UserAccount userAccount = userAccountService.login("siva@gmail.com", "siva");
		Assert.assertNotNull(userAccount);
		Assert.assertNotNull(userAccount.getUserId());
		
		userAccount = userAccountService.login("admin@gmail.com", "admin");
		Assert.assertNotNull(userAccount);
		Assert.assertNotNull(userAccount.getUserId());
	}

	//@Test
	public void testUpdateUser() {
		UserAccount userAccount = userAccountService.login("siva@gmail.com", "siva");
		//user.setUserName("Siva Prasad Reddy");
		UserAccount savedUser = userAccountService.updateUser(userAccount);
		Assert.assertNotNull(savedUser);
		Assert.assertNotNull(savedUser.getUserId());
		Assert.assertEquals("siva@gmail.com", savedUser.getEmailId());
	}
	
	//@Test
	public void testCreateLink() {
		UserAccount userAccount = userAccountService.login("admin@gmail.com", "admin");
		Link link = new Link(null, "reddit", "www.reddit.com");
		link.setPostedBy(userAccount);
		Link createdLink = linkService.createLink(link);
		Assert.assertNotNull(createdLink);
		Assert.assertNotNull(createdLink.getLinkId());
		Assert.assertEquals(userAccount.getEmailId(), createdLink.getPostedBy().getEmailId());
	}
	
	//@Test
	public void testGetLinks() {
		int page =0;
		int pageSize=5;
		Sort sort = new Sort(Sort.Direction.DESC, "createdOn");
		Pageable pageable = new PageRequest(page,pageSize, sort);
		
		Page<Link> linksPage = linkService.getLinks(pageable);
		List<Link> links = linksPage.getContent();
		Assert.assertNotNull(links);
		Assert.assertTrue(links.size()!=0);
	}
	 
	@Test
	public void testGetLinksByUser() {
		
		int page =0;
		int pageSize=5;
		Sort sort = new Sort(Sort.Direction.DESC, "createdOn");
		Pageable pageable = new PageRequest(page,pageSize, sort);
		
		Page<Link> linksPage = linkService.getLinksByUser(1, pageable);
		List<Link> links = linksPage.getContent();
		
		System.err.println("Current Page:"+linksPage.getNumber());
		System.err.println("Records Per Page:"+linksPage.getNumberOfElements());
		System.err.println("Total Pages:"+linksPage.getTotalPages());
		System.err.println("Total Records:"+linksPage.getTotalElements());
		
		Assert.assertNotNull(links);
		Assert.assertTrue(links.size()!=0);
		for (Link link : links) {
			System.out.println(link.getTitle());
		}
		/*links = linkShareService.getLinksByUser(4);
		Assert.assertNotNull(links);
		Assert.assertTrue(links.size()!=0);
		
		links = linkShareService.getLinksByUser(5);
		Assert.assertNotNull(links);
		Assert.assertTrue(links.size()==0);*/
	}
}
