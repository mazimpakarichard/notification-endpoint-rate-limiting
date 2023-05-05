package rw.notification.ratelimit.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * The Class RateLimits.
 * 
 * @author richard.mazimpaka
 */
@Data
@Entity
@Table(name = "RATE_LIMITS")
public class RateLimits implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private UUID id;

	/** The max requests per month. */
	@Column(name = "max_requests_per_month")
	private Integer maxRequestsPerMonth;

	/** The max requests per time window. */
	@Column(name = "max_requests_per_time_window")
	private Integer maxRequestsPerTimeWindow;

	@Column(name = "MAX_GLOBAL_LIMIT")
	private Integer maxglobalLimit;

	/** The time window seconds. */
	@Column(name = "time_window_seconds")
	private long timeWindowSeconds;

	@Column(name = "time_window_globally_hour")
	private long timeWindowGloballyHour;

	/** The client code. */
	@Column(name = "CLIENT_CODE")
	private String clientCode;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * Gets the max requests per month.
	 *
	 * @return the max requests per month
	 */
	public int getMaxRequestsPerMonth() {
		return maxRequestsPerMonth;
	}

	/**
	 * Sets the max requests per month.
	 *
	 * @param maxRequestsPerMonth the new max requests per month
	 */
	public void setMaxRequestsPerMonth(int maxRequestsPerMonth) {
		this.maxRequestsPerMonth = maxRequestsPerMonth;
	}

	/**
	 * Gets the max requests per time window.
	 *
	 * @return the max requests per time window
	 */
	public int getMaxRequestsPerTimeWindow() {
		return maxRequestsPerTimeWindow;
	}

	/**
	 * Sets the max requests per time window.
	 *
	 * @param maxRequestsPerTimeWindow the new max requests per time window
	 */
	public void setMaxRequestsPerTimeWindow(int maxRequestsPerTimeWindow) {
		this.maxRequestsPerTimeWindow = maxRequestsPerTimeWindow;
	}

	/**
	 * Gets the time window seconds.
	 *
	 * @return the time window seconds
	 */
	public long getTimeWindowSeconds() {
		return timeWindowSeconds;
	}

	/**
	 * Sets the time window seconds.
	 *
	 * @param timeWindowSeconds the new time window seconds
	 */
	public void setTimeWindowSeconds(long timeWindowSeconds) {
		this.timeWindowSeconds = timeWindowSeconds;
	}

	/**
	 * Gets the client code.
	 *
	 * @return the client code
	 */
	public String getClientCode() {
		return clientCode;
	}

	public void setMaxRequestsPerMonth(Integer maxRequestsPerMonth) {
		this.maxRequestsPerMonth = maxRequestsPerMonth;
	}

	/**
	 * Sets the max requests per time window.
	 *
	 * @param maxRequestsPerTimeWindow the new max requests per time window
	 */
	public void setMaxRequestsPerTimeWindow(Integer maxRequestsPerTimeWindow) {
		this.maxRequestsPerTimeWindow = maxRequestsPerTimeWindow;
	}

}
