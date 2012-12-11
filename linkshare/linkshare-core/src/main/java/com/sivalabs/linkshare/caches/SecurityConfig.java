/**
 * 
 */
package com.sivalabs.linkshare.caches;

import java.util.Date;

/**
 * @author Siva
 *
 */
public class SecurityConfig implements Cacheable
{
	private String apiKey;
	private String secretKey;
	private Date lastLoadedTime;
	
	public String getApiKey()
	{
		return apiKey;
	}
	public void setApiKey(String apiKey)
	{
		this.apiKey = apiKey;
	}
	public String getSecretKey()
	{
		return secretKey;
	}
	public void setSecretKey(String secretKey)
	{
		this.secretKey = secretKey;
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
