package io.javabrains.moviecatalogservice;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;

@EnableCaching
@org.springframework.context.annotation.Configuration
public class EhCacheConfiguration extends CachingConfigurerSupport{
	@Bean
	public CacheManager ehCacheManager() {

		CacheConfiguration tenSecondCache = new CacheConfiguration();
		tenSecondCache.setName("ten-second-cache");
		tenSecondCache.setMemoryStoreEvictionPolicy("LRU");
		tenSecondCache.maxEntriesLocalHeap(1000);
		tenSecondCache.timeToLiveSeconds(10);

		CacheConfiguration thirtySecondCache = new CacheConfiguration();
		thirtySecondCache.setName("thirty-second-cache");
		thirtySecondCache.setMemoryStoreEvictionPolicy("LRU");
		thirtySecondCache.maxEntriesLocalHeap(1000);
		thirtySecondCache.timeToLiveSeconds(30);

		CacheConfiguration sixtySecondCache = new CacheConfiguration();
		sixtySecondCache.setName("sixty-second-cache");
		sixtySecondCache.setMemoryStoreEvictionPolicy("LRU");
		sixtySecondCache.maxEntriesLocalHeap(1000);
		sixtySecondCache.timeToLiveSeconds(60);

		Configuration configuration = new Configuration();
		configuration.addCache(sixtySecondCache);
		configuration.addCache(thirtySecondCache);
		configuration.addCache(tenSecondCache);

		return CacheManager.newInstance(configuration);

	}

	@Bean
	@Override
	public org.springframework.cache.CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManager());
	}
}
