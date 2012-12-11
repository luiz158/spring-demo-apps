/**
 * 
 */
package com.sivalabs.linkshare.rest;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.sivalabs.linkshare.service.BaseIT;

/**
 * @author Siva
 *
 */
public class LinkShareFeedServiceIT extends BaseIT
{
	//@Ignore
	@Test
	public void testGetAllLinks()
	{
		RestTemplate restTemplate = new RestTemplate();
		LinksFeed feed = restTemplate.getForObject("http://localhost:8080/linkshare-spring/services/linksFeed", LinksFeed.class);
		assertNotNull(feed);
		List<LinkFeedEntry> entries = feed.getEntries();
		for (LinkFeedEntry linkFeedEntry : entries)
		{
			System.err.println(ToStringBuilder.reflectionToString(linkFeedEntry)); 
		}
	}
}
