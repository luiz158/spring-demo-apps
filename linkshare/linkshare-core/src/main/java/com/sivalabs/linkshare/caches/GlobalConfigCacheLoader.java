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
class GlobalConfigCacheLoader extends CacheLoader<String, Cacheable>
{
	@Override
	public Cacheable load(String key) throws Exception {
		System.out.println("loading GlobalConfig at "+new Date());
	   	 GlobalConfig linkShareConfig = new GlobalConfig();
	   	 linkShareConfig.setVersion("1.0");
	   	 linkShareConfig.setAuthorName("K.Siva Prasad Reddy");
	   	 linkShareConfig.setUrl("http://linkshare.cloudfoundry.com");
	   	 linkShareConfig.setLastLoadedTime(new Date());		            	 
	     return linkShareConfig;
	}
	
}
