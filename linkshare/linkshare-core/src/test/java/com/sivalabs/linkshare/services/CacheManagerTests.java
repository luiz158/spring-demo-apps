/**
 * 
 */
package com.sivalabs.linkshare.services;

import java.util.Date;

import com.sivalabs.linkshare.caches.CacheEnum;
import com.sivalabs.linkshare.caches.CacheManager;
import com.sivalabs.linkshare.caches.CacheManagerImpl;
import com.sivalabs.linkshare.caches.GlobalConfig;

/**
 * @author Siva
 *
 */
public class CacheManagerTests
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		CacheManager cacheManager = new CacheManagerImpl();
		GlobalConfig configCache = cacheManager.getCache(CacheEnum.GLOBAL_CONFIG.getCacheId(), 
				GlobalConfig.class);

		System.out.println(configCache.getAuthorName()+" @ "+new Date());
		for (int i = 0; i < 25; i++) {
			/*GlobalConfig configCache = cacheManager.getCache(CacheEnum.GLOBAL_CONFIG.getCacheId(), 
					GlobalConfig.class);

			System.out.println(i+" "+configCache.getAuthorName()+" @ "+new Date());*/
			try {
				Thread.sleep(1000*5);
				System.out.println(new Date());
				if(i==10){
					cacheManager.getCache(CacheEnum.GLOBAL_CONFIG.getCacheId(), GlobalConfig.class);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
