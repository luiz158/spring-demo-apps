/**
 * 
 */
package com.sivalabs.linkshare.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sivalabs.linkshare.entities.Link;
import com.sivalabs.linkshare.entities.Vote;

/**
 * @author siva
 *
 */
public interface LinkRepository extends JpaRepository<Link, Integer>
{

	Page<Link> findAllByPostedByUserId(Integer userId, Pageable pageable);
	
	List<Link> findAllByPostedByUserId(Integer userId, Sort sort);
	
	@Query("select l from Link l left join fetch l.votes where l.linkId=?1")
	Link getLink(Integer linkId);

	@Query("select v from Vote v where v.voteId.linkId=?1 and v.voteId.userId=?2")
	Vote getLinkVoteByUser(Integer linkId, Integer userId);

}
