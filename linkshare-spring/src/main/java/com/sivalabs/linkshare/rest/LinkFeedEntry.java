/**
 * 
 */
package com.sivalabs.linkshare.rest;

import java.util.Date;

/**
 * @author Siva
 *
 */
public class LinkFeedEntry
{
	private String title;
	private String url;
	private String description;
	private String postedBy;
	private Date postedOn;
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
	public String getPostedBy()
	{
		return postedBy;
	}
	public void setPostedBy(String postedBy)
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
