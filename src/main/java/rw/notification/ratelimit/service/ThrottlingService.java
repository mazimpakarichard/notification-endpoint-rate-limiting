package rw.notification.ratelimit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import rw.notification.ratelimit.exception.ConfigurationNotFoundException;
import rw.notification.ratelimit.model.Throttling;
import rw.notification.ratelimit.repository.ThrottlingRepository;

/**
 * The Class ThrottlingService.
 */
@Service
public class ThrottlingService {

	/** The throttling repository. */
	@Autowired
	private ThrottlingRepository throttlingRepository;

	/**
	 * Gets the throttling configurations.
	 *
	 * @param ServerName the server name
	 * @return the throttling configurations
	 */
	@Cacheable(value = "throttlingList", key = "#serverName")
	public Throttling getThrottlingConfigurations(String serverName) {
		try {
			Optional<Throttling> throttling = throttlingRepository.findByServerName(serverName);
			if (throttling.isPresent()) {
				return throttling.get();
			} else {
				throw new ConfigurationNotFoundException("throttling configurations not found");
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
