/**
 * 
 */
package com.sivalabs.linkshare.web.jsf2.controllers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.sivalabs.linkshare.entities.Link;
import com.sivalabs.linkshare.services.LinkService;
import com.sivalabs.linkshare.utils.Constants;

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
	private LinkService linksService;
	
	//@ManagedProperty(value="#{userController}")
	@Autowired
	private UserController userController;
	
	private List<Link> links = null;
	private Link newLink = null;
	
	public Link getNewLink() {
		if(newLink == null){
			this.newLink = new Link();
		}
		return newLink;
	}

	public void setNewLink(Link newLink) {
		this.newLink = newLink;
	}

	public List<Link> getLinks()
	{
		Sort sort = new Sort(Sort.Direction.DESC, "createdOn");
		Pageable pageable = new PageRequest(0,Constants.DEFAULT_PAGE_SIZE, sort);
		
		Page<Link> linksResult = linksService.getLinks(pageable);
		links = linksResult.getContent();
		return links;
	}
	
	public String saveLink()
	{
		newLink.setPostedBy(userController.getLoginUser());
		linksService.createLink(newLink);
		return "home.jsf?faces-redirect=true";
	}
}
