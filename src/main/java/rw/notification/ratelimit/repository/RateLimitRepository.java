package rw.notification.ratelimit.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.notification.ratelimit.model.RateLimits;

// TODO: Auto-generated Javadoc
/**
 * The Interface RateLimitRepository.
 * 
 * @author richard.mazimpaka
 */
@Repository
public interface RateLimitRepository extends JpaRepository<RateLimits, UUID> {

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	Optional<RateLimits> findById(UUID id);

	/**
	 * Find byclient code.
	 *
	 * @param clientCode the client code
	 * @return the optional
	 */
	Optional<RateLimits> findByclientCode(String clientCode);
}
