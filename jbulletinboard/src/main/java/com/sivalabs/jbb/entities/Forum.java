package com.sivalabs.jbb.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="forums")
public class Forum
{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="forum_id")
	private Integer forumId;
	@Column(name="forum_name", nullable=false, unique=true)
	private String forumName;
	private String description;
	@Column(name="display_order")
	private int displayOrder;
	@ManyToOne
	@JoinColumn(name="category_id", nullable=false)
	private Category category;
	@OneToMany(mappedBy="forum")
	private Set<Topic> topics = new HashSet<Topic>();
	
	public Forum()
	{
	}
	public Forum(int forumId)
	{
		this.forumId = forumId;
	}
	public Integer getForumId()
	{
		return forumId;
	}
	public void setForumId(Integer forumId)
	{
		this.forumId = forumId;
	}
	public String getForumName()
	{
		return forumName;
	}
	public void setForumName(String forumName)
	{
		this.forumName = forumName;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public Category getCategory()
	{
		return category;
	}
	public void setCategory(Category category)
	{
		this.category = category;
	}
	public int getDisplayOrder()
	{
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder)
	{
		this.displayOrder = displayOrder;
	}
	public Set<Topic> getTopics()
	{
		return topics;
	}
	public void setTopics(Set<Topic> topics)
	{
		this.topics = topics;
	}
	
}
