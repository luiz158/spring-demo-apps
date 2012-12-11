/**
 * 
 */
package com.sivalabs.linkshare.caches;

import java.util.Date;

import com.google.common.cache.CacheLoader;

/**
 * @author Siva
 *
 */
public class SecurityConfigCacheLoader extends CacheLoader<String, Cacheable>
{
	
	@Override
	public Cacheable load(String key) throws Exception 
	{
		System.out.println("loading SecurityConfig at "+new Date());
		SecurityConfig securityConfig = new SecurityConfig();
		securityConfig.setLastLoadedTime(new Date());		            	 
		return securityConfig;
	}

}
