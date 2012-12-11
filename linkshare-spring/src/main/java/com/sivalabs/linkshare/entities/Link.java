/**
 * 
 */
package com.sivalabs.linkshare.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Siva
 *
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Link
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="link_id")
	private Long linkId;
	@Column(nullable=false)
	private String title;
	@Column(nullable=false)
	private String url;
	private String description;
	@JoinColumn(name = "posted_by", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
	private User postedBy;
	@Column(name="posted_on")
	private Date postedOn = new Date();
	
	public Long getLinkId()
	{
		return linkId;
	}
	public void setLinkId(Long linkId)
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
	public User getPostedBy()
	{
		return postedBy;
	}
	public void setPostedBy(User postedBy)
	{
		this.postedBy = postedBy;
	}
	public Date getPostedOn()
	{
		return postedOn;
	}
	public void setPostedOn(Date postedOn)
	{
		this.postedOn = postedOn;
	}
	
	
}
