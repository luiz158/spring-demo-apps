package com.sivalabs.jbb.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Siva
 *
 */
@Entity
@Table(name="topics")
public class Topic
{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="topic_id")
	private Integer topicId;
	private String title;
	@ManyToOne
	@JoinColumn(name = "created_by", nullable=false)
	private User createdBy;
	@Column(name = "created_on")
	private Date createdOn;
	@ManyToOne
	@JoinColumn(name = "updated_by")
	private User updatedBy;
	@Column(name = "updated_on")
	private Date updatedOn;
	@ManyToOne
	@JoinColumn(name="forum_id", nullable=false)
	private Forum forum;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="topic")
	private Set<Post> posts = new HashSet<Post>();
	
	public Integer getTopicId()
	{
		return topicId;
	}
	public void setTopicId(Integer topicId)
	{
		this.topicId = topicId;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public User getCreatedBy()
	{
		return createdBy;
	}
	public void setCreatedBy(User createdBy)
	{
		this.createdBy = createdBy;
	}
	public Date getCreatedOn()
	{
		return createdOn;
	}
	public void setCreatedOn(Date createdOn)
	{
		this.createdOn = createdOn;
	}
	public User getUpdatedBy()
	{
		return updatedBy;
	}
	public void setUpdatedBy(User updatedBy)
	{
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedOn()
	{
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn)
	{
		this.updatedOn = updatedOn;
	}
	public Forum getForum()
	{
		return forum;
	}
	public void setForum(Forum forum)
	{
		this.forum = forum;
	}
	public Set<Post> getPosts()
	{
		return posts;
	}
	public void setPosts(Set<Post> posts)
	{
		this.posts = posts;
	}
	
	
}
