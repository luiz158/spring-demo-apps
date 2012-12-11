/**
 * 
 */
package com.sivalabs.linkshare.caches;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/**
 * @author Siva
 *
 */
public class CacheManagerImpl implements CacheManager
{
	private LoadingCache<String, Cacheable> cache = null;
	
	public CacheManagerImpl()
	{
		initialize();
	}
	
	protected void initialize()
	{
		cache = CacheBuilder.newBuilder()
				//.expireAfterWrite(15, TimeUnit.SECONDS)
				.refreshAfterWrite(15, TimeUnit.SECONDS)
				.maximumSize(100)
			       .build(
			           new CacheLoader<String, Cacheable>() {
				             public Cacheable load(String key)  {
				            	 CacheEnum enuum = CacheEnum.getEnum(key);
				               return loadCache(enuum);
				             }
				             
				            public ListenableFuture<Cacheable> reload(final String key, Cacheable oldValue) throws Exception {
				            	System.out.println("Reloading "+key+" at "+new Date());
				            	return Futures.immediateFuture(load(key));
				            }
			           });
	}
	
	
	
	@SuppressWarnings("unchecked")
	private Cacheable loadCache(CacheEnum cacheEnum)
	{
		String cacheLoaderClassName = cacheEnum.getCacheLoader();
		CacheLoader<String, Cacheable> cacheLoader = null;
		try {
			cacheLoader = (CacheLoader<String, Cacheable>) Class.forName(cacheLoaderClassName).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 try {
			return cacheLoader.load(cacheEnum.getCacheId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCache(String cacheId, Class<T> clazz)
	{
		return (T)(cache.getUnchecked(cacheId));
	}

	@Override
	public <T> T reloadAndGetCache(String cacheId, Class<T> clazz)
	{
		cache.refresh(cacheId);
		return getCache(cacheId, clazz);
	}

	@Override
	public void reloadCache(String cacheId)
	{
		cache.refresh(cacheId);
	}

	@Override
	public void invalidateCache(String cacheId)
	{
		cache.invalidate(cacheId);
	}

	@Override
	public void invalidateAllCaches()
	{
		cache.invalidateAll();
	}

}
