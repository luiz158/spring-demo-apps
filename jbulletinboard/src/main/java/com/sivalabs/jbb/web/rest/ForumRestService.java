/**
 * 
 */
package com.sivalabs.jbb.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sivalabs.jbb.entities.Forum;
import com.sivalabs.jbb.services.ForumService;

/**
 * @author Siva
 *
 */
@Controller
@RequestMapping(value="/forums", produces="application/json")
public class ForumRestService 
{

	@Autowired private ForumService forumService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	@ResponseBody
	public List<Forum> getForums() {
		return forumService.findAllForums();
	}
	
	@RequestMapping(value="/{forumId}", method=RequestMethod.GET)
	@ResponseBody
	public Forum getForumById(@PathVariable("forumId")Integer forumId) {
		return forumService.findForumById(forumId);
	}
}
