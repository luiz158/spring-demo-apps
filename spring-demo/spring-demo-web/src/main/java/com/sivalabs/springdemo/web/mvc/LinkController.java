/**
 * 
 */
package com.sivalabs.springdemo.web.mvc;

import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sivalabs.springdemo.entities.Link;
import com.sivalabs.springdemo.entities.Tag;
import com.sivalabs.springdemo.entities.User;
import com.sivalabs.springdemo.services.JLinksService;

/**
 * @author Siva
 *
 */
@Controller
public class LinkController {

	@Autowired
	private JLinksService jLinksService;
	
		
	@RequestMapping(value="/createLink", method=RequestMethod.GET)
	public String showCreateLinkForm() 
	{
		return "createLink";
	}
	
	@RequestMapping(value="/createLink", method=RequestMethod.POST)
	public String createLink(Link link, HttpServletRequest request) 
	{
		String view = "redirect:welcome.htm";
		User loginUser = (User) request.getSession().getAttribute("LOGIN_USER");
		link.setCreadtedBy(loginUser);
		link.setCreatedOn(new Date());
		String tagsList = request.getParameter("tagsList");
		StringTokenizer tokenizer = new StringTokenizer(tagsList,",");
		while(tokenizer.hasMoreTokens()){
			String tagName = tokenizer.nextToken().trim();
			Tag tag = new Tag();
			tag.setTagName(tagName);
			tag.setDescription(tagName);
			link.addTag(tag);
		}
		Link createdLink = jLinksService.createLink(link);
		if(createdLink != null){
			
			request.setAttribute("INFO", "Link created successfully");			
		}else{
			request.setAttribute("ERROR", "Link creation failed. Please try again.");
		}
		
		return view;
	}
}
