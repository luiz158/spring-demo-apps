/**
 * 
 */
package com.sivalabs.linkshare.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sivalabs.linkshare.entities.Comment;
import com.sivalabs.linkshare.entities.Link;
import com.sivalabs.linkshare.entities.UserAccount;
import com.sivalabs.linkshare.entities.Vote;

/**
 * @author siva
 *
 */
public interface LinkService 
{
	
	public Page<Link> getLinks(Pageable pageable);
	public Page<Link> getLinksByUser(Integer userId, Pageable pageable);
	public Link createLink(Link link);
	public void deleteLink(Integer linkId);
	public List<UserAccount> getLinkFeedSubscribedUsers();
	
	public Link getLink(Integer linkId);
	public Link addComment(Comment comment, Integer linkId);
	public Link voteLink(Vote vote);
	
	
}
