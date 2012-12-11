/**
 * 
 */
package com.sivalabs.linkshare.web.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sivalabs.core.logging.Slf4JLogger;
import com.sivalabs.linkshare.entities.Link;
import com.sivalabs.linkshare.entities.UserAccount;
import com.sivalabs.linkshare.services.LinkService;
import com.sivalabs.linkshare.services.UserAccountService;
import com.sivalabs.linkshare.web.WebUtils;

/**
 * @author siva
 *
 */
@Controller
public class UserController 
{
	@Slf4JLogger
	private Logger logger;
	
	@Autowired
	private LinkService linkService;
	
	@Autowired
	private UserAccountService userAccountService;
	
	
	@RequestMapping(value="showUserProfile")
	public String showUserProfile(Model model, 
			@RequestParam(value="0", required=false, defaultValue="0") int page, 
			HttpSession session) 
	{
		UserAccount loggedInUser = WebUtils.getLoggedInUser(session);
		model.addAttribute("userProfile", loggedInUser);
		
		int pageSize=5;
		Sort sort = new Sort(Sort.Direction.DESC, "createdOn");
		Pageable pageable = new PageRequest(page,pageSize, sort);
		
		Page<Link> linksPage = linkService.getLinksByUser(1, pageable);
		List<Link> links = linksPage.getContent();
		
		model.addAttribute("USER_LINKS", links);
		
		return "user/userProfile";
	}
	
}
