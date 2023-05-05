package rw.notification.ratelimit.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class Throttling.
 * 
 * @author richard.mazimpaka
 */
@Data
@Entity
@Table(name = "THROTTLING")
public class Throttling implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private UUID id;
	/** The soft limit. */
	@Column(name = "SOFT_LIMIT")
	private Integer softLimit;

	/** The delays MS. */
	@Column(name = "DELAYS_MS")
	private Integer delaysMS;

	/** The server name. */
	@Column(name = "SERVER_NAME")
	private String serverName;

	public Throttling() {
	}

	/**
	 * Gets the soft limit.
	 *
	 * @return the soft limit
	 */
	public Integer getSoftLimit() {
		return softLimit;
	}

	/**
	 * Sets the soft limit.
	 *
	 * @param softLimit the new soft limit
	 */
	public void setSoftLimit(Integer softLimit) {
		this.softLimit = softLimit;
	}

	/**
	 * Gets the delays MS.
	 *
	 * @return the delays MS
	 */
	public Integer getDelaysMS() {
		return delaysMS;
	}

	/**
	 * Sets the delays MS.
	 *
	 * @param delaysMS the new delays MS
	 */
	public void setDelaysMS(Integer delaysMS) {
		this.delaysMS = delaysMS;
	}

	/**
	 * Gets the server name.
	 *
	 * @return the server name
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * Sets the server name.
	 *
	 * @param serverName the new server name
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

}
