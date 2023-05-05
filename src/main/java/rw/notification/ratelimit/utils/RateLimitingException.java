package rw.notification.ratelimit.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RateLimitingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(RateLimitingException.class);

	public RateLimitingException(String string) {
		super(string);

	}

	public RateLimitingException(String message, Throwable cause) {
		super(message, cause);
	}

	public RateLimitingException(Exception ex) {
		super(ex);
	}

}
