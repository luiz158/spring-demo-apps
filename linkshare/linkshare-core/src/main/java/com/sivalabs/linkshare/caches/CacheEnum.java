/**
 * 
 */
package com.sivalabs.linkshare.caches;


/**
 * @author siva
 *
 */
public enum CacheEnum 
{

	GLOBAL_CONFIG("GLOBAL_CONFIG",GlobalConfigCacheLoader.class.getName()),
	SECURITY_CONFIG("SECURITY_CONFIG",SecurityConfigCacheLoader.class.getName());
	
	private String cacheId;
	private String cacheLoader;
	
	private CacheEnum(String cacheId, String cacheLoader) {
		this.cacheId = cacheId;
		this.cacheLoader = cacheLoader;
	}

	public String getCacheId() {
		return cacheId;
	}

	public String getCacheLoader() {
		return cacheLoader;
	}

	public static CacheEnum getEnum(String key) {
		CacheEnum[] enums = CacheEnum.values();
		for (CacheEnum cacheEnum : enums) {
			if(cacheEnum.cacheId.equals(key)){
				return cacheEnum;
			}
		}
		return null;
	}
	
}
