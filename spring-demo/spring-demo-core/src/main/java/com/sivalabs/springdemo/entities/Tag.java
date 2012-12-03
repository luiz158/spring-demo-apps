package com.sivalabs.springdemo.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Siva
 *
 */
@Entity
@Table(name = "tags")
@XmlRootElement
public class Tag implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Integer tagId;
    @Column(name="tag_name", nullable=false, unique=true)
    private String tagName;
    private String description;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "links_tags",
	    joinColumns = {@JoinColumn(name="tag_id")},
	    inverseJoinColumns = {@JoinColumn(name="link_id")}
    )
    private Set<Link> links = new HashSet<Link>();
    
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "users_tags",
	    joinColumns = {@JoinColumn(name="tag_id")},
	    inverseJoinColumns = {@JoinColumn(name="user_id")}
    )
    private Set<User> subscribedUsers = new HashSet<User>();
    
	public Integer getTagId()
	{
		return tagId;
	}
	public void setTagId(Integer tagId)
	{
		this.tagId = tagId;
	}
	public String getTagName()
	{
		return tagName;
	}
	public void setTagName(String tagName)
	{
		this.tagName = tagName;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public Set<Link> getLinks()
	{
		return links;
	}
	public void setLinks(Set<Link> links)
	{
		this.links = links;
	}
	public Set<User> getSubscribedUsers()
	{
		return subscribedUsers;
	}
	public void setSubscribedUsers(Set<User> subscribedUsers)
	{
		this.subscribedUsers = subscribedUsers;
	}
    
    
}
