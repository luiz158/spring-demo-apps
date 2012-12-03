package com.sivalabs.springdemo.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Siva
 *
 */
@Entity
@Table(name = "links")
@XmlRootElement
public class Link implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id")
    private Integer linkId;
    @Column(nullable=false)
    private String title;
    @Column(nullable=false)
    private String url;
    @Column(nullable=false)
    private String description;
    @ManyToOne
    @JoinColumn(name="created_by", nullable=false)
    private User creadtedBy;
    @Column(name="created_on", nullable=false)
    private Date createdOn;
    
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "links_tags",
	    joinColumns = {@JoinColumn(name="link_id")},
	    inverseJoinColumns = {@JoinColumn(name="tag_id")}
    )
    private Set<Tag> tags = new HashSet<Tag>();

	public Integer getLinkId()
	{
		return linkId;
	}

	public void setLinkId(Integer linkId)
	{
		this.linkId = linkId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public User getCreadtedBy()
	{
		return creadtedBy;
	}

	public void setCreadtedBy(User creadtedBy)
	{
		this.creadtedBy = creadtedBy;
	}

	public Date getCreatedOn()
	{
		return createdOn;
	}

	public void setCreatedOn(Date createdOn)
	{
		this.createdOn = createdOn;
	}

	public Set<Tag> getTags()
	{
		return tags;
	}

	public void setTags(Set<Tag> tags)
	{
		this.tags = tags;
	}

	public void addTag(Tag tag) {
		this.getTags().add(tag);
	}
    
    

}
