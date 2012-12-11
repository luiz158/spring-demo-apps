/**
 * 
 */
package com.sivalabs.linkshare.jobs;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sivalabs.core.email.Email;
import com.sivalabs.core.email.EmailService;
import com.sivalabs.core.logging.Slf4JLogger;
import com.sivalabs.linkshare.entities.Link;
import com.sivalabs.linkshare.entities.UserAccount;
import com.sivalabs.linkshare.services.LinkService;

/**
 * @author siva
 *
 */
@Service
public class LinkFeedJob 
{

	@Slf4JLogger
	private Logger logger;
	
	@Autowired
	private EmailService emailService;
	@Autowired
	private LinkService linkService;
	
	@Scheduled(cron="0 30 12 * * *")
	public void sendLinkFeed() 
	{
		logger.debug("Sending Link Feed at :"+new Date());
		StringBuilder sb = new StringBuilder();
		int page =0;
		int pageSize=25;
		Sort sort = new Sort(Sort.Direction.DESC, "createdOn");
		Pageable pageable = new PageRequest(page,pageSize, sort);
		
		
		Page<Link> linksResult = linkService.getLinks(pageable);
		List<Link> links = linksResult.getContent();
		for (Link link : links) 
		{
			sb.append("<p><font size='4'><a href='"+link.getUrl()+"'>"+link.getTitle()+"</a></font><br/>");
			sb.append("Sumitted By : "+link.getPostedBy().getEmailId()+"<br/>");
			sb.append("Submitted On : "+link.getCreatedOn()+"</p>");			
		}
		
		List<UserAccount> subscribedUsers = this.linkService.getLinkFeedSubscribedUsers();
		for (UserAccount userAccount : subscribedUsers) 
		{
			Email email = new Email();
			email.setFrom("sivaprasadreddy.k@gmail.com");
			email.setTo(userAccount.getEmailId());
			email.setSubject("Link Feed from LinkShare on CloudFoundry");
			email.setText(sb.toString());
			try {
				logger.debug("Sending Link Feed  to :"+userAccount.getEmailId());
				emailService.sendEmail(email);
				logger.debug("Sent.");
			} catch (Exception e) {
				//e.printStackTrace();
				logger.error("Error in sending Link Feed",e);
			}
		}
		
		
	}
	
}
