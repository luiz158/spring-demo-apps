package com.sivalabs.jbb.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Siva
 *
 */
@Entity
@Table(name="posts")
public class Post
{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="post_id")
	private Integer postId;
	
	private String title;
	
	private String message;
	
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
	@JoinColumn(name="topic_id", nullable=false)
	private Topic topic;
	
	public Integer getPostId()
	{
		return postId;
	}
	public void setPostId(Integer postId)
	{
		this.postId = postId;
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
	public Topic getTopic()
	{
		return topic;
	}
	public void setTopic(Topic topic)
	{
		this.topic = topic;
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	
}
