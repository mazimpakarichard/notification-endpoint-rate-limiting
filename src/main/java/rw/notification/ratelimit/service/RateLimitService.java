package rw.notification.ratelimit.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import rw.notification.ratelimit.exception.ConfigurationNotFoundException;
import rw.notification.ratelimit.model.RateLimits;
import rw.notification.ratelimit.repository.RateLimitRepository;

/**
 * The Class UserService.
 */
@Service
public class RateLimitService {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(RateLimitService.class);

	/** The rate limit repository. */
	@Autowired
	private RateLimitRepository rateLimitRepository;

	/**
	 * Gets the rate limit.
	 *
	 * @param clientCode the client code
	 * @return the rate limit
	 */
	@Cacheable(value = "rateLimitList", key = "#clientCode")
	public RateLimits getRateLimit(String clientCode) {
		Optional<RateLimits> limits = rateLimitRepository.findByclientCode(clientCode);
		if (limits.isPresent()) {
			return limits.get();
		} else {
			throw new ConfigurationNotFoundException("rate limit not found");
		}
	}

	/**
	 * Delete rate limit list.
	 */
	@CacheEvict(value = { "rateLimitList" }, allEntries = true)
	@Scheduled(fixedDelayString = "${caching.spring.rateLimitListTTL}", initialDelay = 50000)
	public void deleteRateLimitList() {
		LOG.info("Evict rate limit List");
	}

}
