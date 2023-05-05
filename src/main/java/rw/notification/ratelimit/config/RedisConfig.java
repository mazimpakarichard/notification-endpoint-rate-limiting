package rw.notification.ratelimit.config;

import javax.cache.CacheManager;
import javax.cache.Caching;

import org.redisson.config.Config;
import org.redisson.jcache.configuration.RedissonConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.grid.jcache.JCacheProxyManager;

// TODO: Auto-generated Javadoc
/**
 * The Class RedisConfig.
 * @author richard.mazimpaka
 */
@Configuration
public class RedisConfig {

	/**
	 * Config.
	 *
	 * @return the config
	 */
	@Bean
	public Config config() {
		Config config = new Config();
		config.useSingleServer().setAddress("redis://localhost:6379");
		return config;
	}

	/**
	 * Cache manager.
	 *
	 * @param config the config
	 * @return the cache manager
	 */
	@Bean(name = "springCM")
	public CacheManager cacheManager(Config config) {
		CacheManager manager = Caching.getCachingProvider().getCacheManager();
		manager.createCache("cache", RedissonConfiguration.fromConfig(config));
		manager.createCache("rateLimitList", RedissonConfiguration.fromConfig(config));
		manager.createCache("throttlingList", RedissonConfiguration.fromConfig(config));
		return manager;
	}

	/**
	 * Proxy manager.
	 *
	 * @param cacheManager the cache manager
	 * @return the proxy manager
	 */
	@Bean
	ProxyManager<String> proxyManager(CacheManager cacheManager) {
		return new JCacheProxyManager<>(cacheManager.getCache("cache"));
	}
}
