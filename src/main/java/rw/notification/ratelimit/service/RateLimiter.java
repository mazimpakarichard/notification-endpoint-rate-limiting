package rw.notification.ratelimit.service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.Refill;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import rw.notification.ratelimit.model.RateLimits;
import rw.notification.ratelimit.model.Throttling;

// TODO: Auto-generated Javadoc
/**
 * The Class RateLimiter.
 * 
 * @author richard.mazimpaka
 */
@Service
public class RateLimiter {

	/** The user service. */
	@Autowired
	private RateLimitService userService;

	/** The throttling service. */
	@Autowired
	private ThrottlingService throttlingService;

	/** The proxy manager. */
	@Autowired
	ProxyManager<String> proxyManager;

	private static final String GLOBAL_BUCKET = "global";

	private static final String MONTHLY_BUCKET = "monthly";

	/**
	 * Resolve bucket for client.
	 *
	 * @param key the key
	 * @return the bucket
	 */
	public Bucket resolveBucketForClient(String key) {
		try {
			Supplier<BucketConfiguration> configSupplier = getConfigSupplierForClient(key);
			return proxyManager.builder().build(key, configSupplier);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Resolve bucket for client globally.
	 *
	 * @param key the key
	 * @return the bucket
	 */
	public Bucket resolveBucketForClientGlobally(String key) {
		try {
			Supplier<BucketConfiguration> configSupplier = getConfigSupplierForClientGlobally(key);
			return proxyManager.builder().build(GLOBAL_BUCKET, configSupplier);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Resolve bucket for client monthly.
	 *
	 * @param key the key
	 * @return the bucket
	 */
	public Bucket resolveBucketForClientMonthly(String key) {
		try {
			Supplier<BucketConfiguration> configSupplier = getConfigSupplierForClientMontlly(key);
			return proxyManager.builder().build(key + MONTHLY_BUCKET, configSupplier);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Gets the config supplier for client.
	 *
	 * @param clientCode the client code
	 * @return the config supplier for client
	 */
	private Supplier<BucketConfiguration> getConfigSupplierForClient(String clientCode) {
		try {
			RateLimits rateLimits = userService.getRateLimit(clientCode);
			Bandwidth perClientLimit = Bandwidth.simple(rateLimits.getMaxRequestsPerTimeWindow(),
					Duration.ofSeconds(rateLimits.getTimeWindowSeconds()));

			BucketConfiguration config = BucketConfiguration.builder().addLimit(perClientLimit).build();

			return () -> (config);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Gets the config supplier for client globally.
	 *
	 * @param clientCode the client code
	 * @return the config supplier for client globally
	 */
	private Supplier<BucketConfiguration> getConfigSupplierForClientGlobally(String clientCode) {
		try {
			RateLimits rateLimits = userService.getRateLimit(clientCode);
			Bandwidth globalLimit = Bandwidth.simple(rateLimits.getMaxglobalLimit(),
					Duration.ofHours(rateLimits.getTimeWindowGloballyHour()));

			BucketConfiguration config = BucketConfiguration.builder().addLimit(globalLimit).build();

			return () -> (config);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Gets the config supplier for client montlly.
	 *
	 * @param clientCode the client code
	 * @return the config supplier for client montlly
	 */
	private Supplier<BucketConfiguration> getConfigSupplierForClientMontlly(String clientCode) {
		try {
			RateLimits rateLimits = userService.getRateLimit(clientCode);
			long secondsInMonth = Duration.ofDays(30).getSeconds();
			Refill refill = Refill.intervally(rateLimits.getMaxRequestsPerTimeWindow(),
					Duration.ofSeconds(secondsInMonth));
			// create bandwidth object for monthly rate limiting
			Bandwidth monthlyLimit = Bandwidth.classic(rateLimits.getMaxRequestsPerMonth(), refill);
			BucketConfiguration config = BucketConfiguration.builder().addLimit(monthlyLimit).build();

			return () -> (config);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Gets the throttling configuration.
	 *
	 * @param ServerName the server name
	 * @return the throttling configuration
	 */
	public Throttling getThrottlingConfiguration(String ServerName) {
		try {
			Throttling throttling = throttlingService.getThrottlingConfigurations(ServerName);
			return throttling;
		} catch (Exception e) {
			throw e;
		}
	}

}
