package io.javabrains.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableCaching
@EnableSwagger2
public class MovieCatalogServiceApplication extends CachingConfigurerSupport{

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		//		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		//		clientHttpRequestFactory.setConnectTimeout(5000);
		//		return new RestTemplate(clientHttpRequestFactory);
		return new RestTemplate();
	}

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

	//TODO: Swagger2 is not working
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("io.javabrains.moviecatalogservice.resources")).build();
	}

}

