/**
 * 
 */
package com.sivalabs.linkshare.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sivalabs.linkshare.entities.Link;

/**
 * @author Siva
 *
 */
@Service
@Transactional
public class LinkService
{
	@PersistenceContext
	private EntityManager em;
	
	public List<Link> getAllLinks()
	{
		return em.createQuery("select l from Link l order by l.postedOn desc", Link.class).getResultList();
	}
	
	public List<Link> getLinksByUser(Long userId)
	{
		TypedQuery<Link> query = em.createQuery("select l from Link l where l.postedBy.userId=:userId order by l.postedOn desc", Link.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}
	
	public void saveLink(Link link)
	{
		em.persist(link);
	}

	public List<Link> searchLinks(String linkSearchKeyword)
	{
		TypedQuery<Link> query = em.createQuery("select l from Link l where l.title like :keyword or l.description like :keyword order by l.postedOn desc", Link.class);
		query.setParameter("keyword", "%"+linkSearchKeyword+"%");
		return query.getResultList();
	}

}
