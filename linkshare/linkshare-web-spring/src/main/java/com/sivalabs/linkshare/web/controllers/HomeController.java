/**
 * 
 */
package com.sivalabs.linkshare.web.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sivalabs.core.logging.Slf4JLogger;
import com.sivalabs.linkshare.entities.Link;
import com.sivalabs.linkshare.entities.UserAccount;
import com.sivalabs.linkshare.services.LinkService;
import com.sivalabs.linkshare.services.UserAccountService;
import com.sivalabs.linkshare.utils.Constants;
import com.sivalabs.linkshare.web.UserNotLoggedinException;
import com.sivalabs.linkshare.web.WebUtils;

/**
 * @author siva
 *
 */
@Controller
public class HomeController 
{
	@Slf4JLogger
	private Logger logger;
	
	@Autowired
	private LinkService linkService;
	@Autowired
	private UserAccountService userAccountService;
	
	@RequestMapping(value={"","welcome"})
	public String welcome() {
		System.err.println(this.getClass()+":"+this.hashCode()+":"+this.toString());
		logger.debug("Some one is visiting LinkShare");
		return "welcome";
	}
	
	
	
	@RequestMapping("logout")
	public String logout(HttpSession session) 
	{
		session.invalidate();
		return "redirect:login.htm";
	}
	
	@RequestMapping(value="home")
	public String home(Model model, 
						@RequestParam(value="page", required=false, defaultValue="0") Integer page, 
						HttpSession session) 
	{
		if(page == null){
			page = 0;
		}
		String userName = WebUtils.getCurrentUserName();
		UserAccount loggedinUser = this.userAccountService.getUserByUserName(userName);
		WebUtils.setLoggedInUser(session, loggedinUser);
		
		//int pageSize=25;
		System.err.println("page--------------->"+page);
		Sort sort = new Sort(Sort.Direction.DESC, "createdOn");
		Pageable pageable = new PageRequest(page,Constants.DEFAULT_PAGE_SIZE, sort);
		
		Page<Link> linksResult = linkService.getLinks(pageable);
		//List<Link> links = linksResult.getContent();
		
		//model.addAttribute("ALL_LINKS", links);
		model.addAttribute("ALL_LINKS", linksResult);
		return "home";
	}
	
	@ExceptionHandler(UserNotLoggedinException.class)
	public String redirectToLogin() {
		return "redirect:login.htm";
	}
}
