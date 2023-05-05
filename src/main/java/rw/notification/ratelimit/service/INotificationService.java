package rw.notification.ratelimit.service;

import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Interface INotificationService.
 * @author richard.mazimpaka
 */
public interface INotificationService {
	
	/** The name. */
	String NAME = "NotificationService";

	/**
	 * Send notification.
	 *
	 * @param message the message
	 * @param recipient the recipient
	 */
	public void sendNotification(String message, String recipient);
}
