/**
 * 
 */
package com.sivalabs.linkshare.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sivalabs.linkshare.entities.Link;
import com.sivalabs.linkshare.entities.User;
import com.sivalabs.linkshare.service.LinkService;
import com.sivalabs.linkshare.service.TwitterService;
import com.sivalabs.linkshare.web.FacesUtils;

/**
 * @author Siva
 *
 */
@Component
@ManagedBean
@RequestScoped
public class LinksController
{
	
	@Autowired
	private LinkService linkService;
	
	@Autowired
	private TwitterService twitterService;
	
	private List<Link> links = null;
	private List<Link> searchLinksResults = null;
	private String linkSearchKeyword;
	private List<Link> userLinks = null;
	private Link newLink = null;
	
	public Link getNewLink()
	{
		if(newLink == null){
			newLink = new Link();
		}
		return newLink;
	}

	public void setNewLink(Link newLink)
	{
		this.newLink = newLink;
	}
	
	public String getLinkSearchKeyword()
	{
		if(linkSearchKeyword == null){
			linkSearchKeyword = "";
		}
		return linkSearchKeyword;
	}

	public void setLinkSearchKeyword(String linkSearchKeyword)
	{
		this.linkSearchKeyword = linkSearchKeyword;
	}

	public List<Link> getLinks()
	{
		links = linkService.getAllLinks();
		return links;
	}
	
	public List<Link> getUserLinks(Long userId)
	{
		userLinks = linkService.getLinksByUser(userId);
		return userLinks;
	}
	
	public List<Link> getSearchLinksResults()
	{
		if(searchLinksResults == null)
		{
			searchLinksResults = new ArrayList<Link>();
		}
		return searchLinksResults;
	}
	public void searchLinks()
	{
		searchLinksResults = linkService.searchLinks(linkSearchKeyword);
	}
	public String saveLink()
	{
		User user = FacesUtils.getUserContext().getUser();
		newLink.setPostedBy(user);
		if(!newLink.getUrl().toLowerCase().startsWith("http://") || !newLink.getUrl().toLowerCase().startsWith("https://")){
			newLink.setUrl("http://"+newLink.getUrl());
		}
		this.linkService.saveLink(newLink);
		twitterService.sendMessage(newLink.getUrl());
		newLink = null;
		return "home.jsf?faces-redirect=true";
	}

}
