/**
 * 
 */
package com.sivalabs.linkshare.web.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sivalabs.core.logging.Slf4JLogger;
import com.sivalabs.linkshare.LinkShareException;
import com.sivalabs.linkshare.entities.Comment;
import com.sivalabs.linkshare.entities.Link;
import com.sivalabs.linkshare.entities.Tag;
import com.sivalabs.linkshare.entities.UserAccount;
import com.sivalabs.linkshare.entities.Vote;
import com.sivalabs.linkshare.entities.VoteId;
import com.sivalabs.linkshare.services.LinkService;
import com.sivalabs.linkshare.web.WebUtils;

/**
 * @author siva
 *
 */
@Controller
public class LinksControlers 
{
	@Slf4JLogger
	private Logger logger;
	
	@Autowired
	private LinkService linkService;
	
	
	@RequestMapping(value="createLink", method=RequestMethod.GET)
	public String submitNewLink(Model model) 
	{
		model.addAttribute("link", new Link());
		return "links/newLink";
	}
	
	@RequestMapping(value="createLink", method=RequestMethod.POST)
	public String submitNewLink(@Validated @ModelAttribute Link link, 
								BindingResult result, 
								Model model, 
								HttpServletRequest request,
								RedirectAttributes redirectAttributes) 
	{
		if(result.hasErrors())
		{
			return "links/newLink";
		}
		String url = link.getUrl();
		if(!url.startsWith("http://")){
			url = "http://"+url;
		}
		link.setUrl(url);
		UserAccount loginUser = WebUtils.getLoggedInUser(request.getSession());
		link.setPostedBy(loginUser);
		link.setCreatedOn(new Date());
		
		List<Tag> tags = new ArrayList<Tag>();
		
		String tagIdsList = request.getParameter("tagIdsList");
		StringTokenizer st = new StringTokenizer(tagIdsList, ",");
		while(st.hasMoreElements())
		{
			String tagIdVal = st.nextToken();
			if(StringUtils.trimToNull(tagIdVal) != null){
				Integer tagId = new Integer(tagIdVal);
				tags.add(new Tag(tagId));
			}
		}
		
		link.setTags(tags);
		this.linkService.createLink(link);
		logger.debug("Link posted successfully.");
		redirectAttributes.addFlashAttribute("message", "Link posted successfully.");
		return "redirect:/home.htm";
	}
	
	@RequestMapping(value="showLink")
	public String showLink(@RequestParam("linkId") Integer linkId, Model model)
	{
		Link link = this.linkService.getLink(linkId);
		model.addAttribute("link", link);
		return "links/link";
	}
	
	@RequestMapping(value="addComment", method=RequestMethod.POST)
	@ResponseBody
	public String addComment(Comment  comment,
							@RequestParam("linkId") Integer linkId, 
							Model model, HttpServletRequest request)
	{
		comment.setCreatedOn(new Date());
		UserAccount postedBy = WebUtils.getLoggedInUser(request.getSession());
		comment.setPostedBy(postedBy);
		/*Link link = */this.linkService.addComment(comment, linkId);
		//model.addAttribute("link", link);
		//return "links/link";
		return "success";
	}
	
	
	@RequestMapping(value="voteLink", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public String voteLink(@RequestParam("linkId") Integer linkId, 
							@RequestParam("voteType") char voteType, 
							
							Model model, HttpServletRequest request)
	{
		UserAccount postedBy = WebUtils.getLoggedInUser(request.getSession());
		VoteId votePK = new VoteId(linkId, postedBy.getUserId());
		Vote vote = new Vote(votePK);
		vote.setLink(new Link(linkId));
		vote.setUser(postedBy);
		vote.setVoteType(voteType);
		
		Link link = null;
		String response = null;
		try {
			link = this.linkService.voteLink(vote);
			response = "{\"status\": \"success\", \"up\": \""+link.getUpVotesCount()+
					"\", \"down\": \""+link.getDownVotesCount()+"\"}";
		} catch (LinkShareException e) {
			logger.error(e.getMessage());
			response = "{\"status\": \"failure\", \"message\": \""+e.getMessage()+"\"}";
		}
		
		return response;
	}
	
	
}
