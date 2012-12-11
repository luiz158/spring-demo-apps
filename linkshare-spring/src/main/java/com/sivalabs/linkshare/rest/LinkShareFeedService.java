/**
 * 
 */
package com.sivalabs.linkshare.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sivalabs.linkshare.entities.Link;
import com.sivalabs.linkshare.service.LinkService;

/**
 * @author Siva
 *
 */
@Controller
public class LinkShareFeedService
{
	@Autowired
	private LinkService linkService;
	
	@RequestMapping(value="/linksFeed")
	@ResponseBody
	public LinksFeed getLinkFeed()
	{
		LinksFeed feed = new LinksFeed();
		List<Link> allLinks = linkService.getAllLinks();
		for (Link link : allLinks)
		{
			LinkFeedEntry entry = new LinkFeedEntry();
			entry.setTitle(link.getTitle());
			entry.setUrl(link.getUrl());
			entry.setDescription(link.getDescription());
			entry.setPostedBy(link.getPostedBy().getUserName());
			entry.setPostedOn(link.getPostedOn());		
			
			feed.addEntry(entry);
		}		
		return feed;
	}
	
	
	
	
}
