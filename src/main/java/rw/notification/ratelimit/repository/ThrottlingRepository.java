package rw.notification.ratelimit.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.notification.ratelimit.model.Throttling;

/**
 * The Interface ThrottlingRepository.
 * 
 * @author richard.mazimpaka
 */
public interface ThrottlingRepository extends JpaRepository<Throttling, UUID> {

	/**
	 * Find by server name.
	 *
	 * @param serverName the server name
	 * @return the optional
	 */
	Optional<Throttling> findByServerName(String serverName);

}
