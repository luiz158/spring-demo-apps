/**
 * 
 */
package com.sivalabs.linkshare.caches;

/**
 * @author Siva
 *
 */
public interface CacheManager
{
	public <T> T getCache(String cacheId, Class<T> clazz);
	public <T> T reloadAndGetCache(String cacheId, Class<T> clazz);
	public void reloadCache(String cacheId);
	public void invalidateCache(String cacheId);
	public void invalidateAllCaches();	
}
