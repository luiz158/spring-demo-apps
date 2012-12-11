/**
 * 
 */
package com.sivalabs.linkshare.caches;

import java.util.Date;

/**
 * @author Siva
 *
 */
public class GlobalConfig implements Cacheable
{
	private String version;
	private String authorName;
	private String url;
	private Date lastLoadedTime;
	
	public String getVersion()
	{
		return version;
	}
	public void setVersion(String version)
	{
		this.version = version;
	}
	public String getAuthorName()
	{
		return authorName;
	}
	public void setAuthorName(String authorName)
	{
		this.authorName = authorName;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public Date getLastLoadedTime()
	{
		return lastLoadedTime;
	}
	public void setLastLoadedTime(Date lastLoadedTime)
	{
		this.lastLoadedTime = lastLoadedTime;
	}
	
}
