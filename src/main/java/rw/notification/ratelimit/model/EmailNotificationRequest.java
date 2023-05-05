package rw.notification.ratelimit.model;

/**
 * The Class EmailNotificationRequest.
 * 
 * @author richard.mazimpaka
 */
public class EmailNotificationRequest {

	/** The message. */
	private String message;

	/** The recipient. */
	private String recipient;

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the recipient.
	 *
	 * @return the recipient
	 */
	public String getRecipient() {
		return recipient;
	}

	/**
	 * Sets the recipient.
	 *
	 * @param recipient the new recipient
	 */
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

}
