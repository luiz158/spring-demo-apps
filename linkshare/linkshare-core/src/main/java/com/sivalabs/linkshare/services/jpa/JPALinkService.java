/**
 * 
 */
package com.sivalabs.linkshare.services.jpa;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sivalabs.core.logging.Slf4JLogger;
import com.sivalabs.linkshare.LinkShareException;
import com.sivalabs.linkshare.entities.Comment;
import com.sivalabs.linkshare.entities.Link;
import com.sivalabs.linkshare.entities.UserAccount;
import com.sivalabs.linkshare.entities.Vote;
import com.sivalabs.linkshare.repositories.LinkRepository;
import com.sivalabs.linkshare.repositories.UserRepository;
import com.sivalabs.linkshare.services.LinkService;

/**
 * @author siva
 *
 */
@Service
@Transactional
public class JPALinkService implements LinkService 
{
	@Slf4JLogger
	private Logger logger;
	
	private UserRepository userRepository;
	private LinkRepository linkRepository;
	
	@Autowired
	public JPALinkService(UserRepository userRepository, 
							LinkRepository linkRepository) {
		this.userRepository = userRepository;
		this.linkRepository = linkRepository;
	}
		
	@Override
	public Page<Link> getLinks(Pageable pageable) {
		Page<Link> links = linkRepository.findAll(pageable);
		return links;
	}

	@Override
	public Page<Link> getLinksByUser(Integer userId, Pageable pageable) {
		Page<Link> links = this.linkRepository.findAllByPostedByUserId(userId,pageable);
		return links; 
	}

	
	@Override
	public Link createLink(Link link) {
		return linkRepository.save(link);
	}

	
	@Override
	public void deleteLink(Integer linkId) {
		linkRepository.delete(linkId);
	}

	@Override
	public List<UserAccount> getLinkFeedSubscribedUsers() {
		return this.userRepository.findBySubscribeToLinkFeed(true);
	}
	
	@Override
	public Link getLink(Integer linkId) {
		 Link link = this.linkRepository.getLink(linkId);
		 link.getComments();
		// link.getVoteList();
		 return link;
	}

	@Override
	public Link addComment(Comment comment, Integer linkId) {
		Link link = getLink(linkId);
		comment.setLink(link);
		link.getComments().add(comment);
		return link;
	}
	@Override
	public Link voteLink(Vote vote) {
		Link link = null;
		Vote oldVote = this.linkRepository.
					getLinkVoteByUser(vote.getVoteId().getLinkId(), 
									vote.getVoteId().getUserId());
		logger.debug("Old Vote:"+oldVote);
		if(oldVote != null)
		{
			if(vote.getVoteType()==oldVote.getVoteType()){
				logger.error("You have already voted this link!!");
				throw new LinkShareException("You have already voted this link!!");
			} else{
				logger.debug("Updating the vote type to:"+vote.getVoteType());
				oldVote.setVoteType(vote.getVoteType());
				link = getLink(vote.getLink().getLinkId());
			}
			
		}
		else
		{
			logger.debug("Inserting the vote.");
			link = getLink(vote.getLink().getLinkId());
			link.getVotes().add(vote);
		}
		
		return link;
	}

}
