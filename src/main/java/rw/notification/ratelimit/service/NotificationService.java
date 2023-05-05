package rw.notification.ratelimit.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import rw.notification.ratelimit.utils.RateLimitingException;

// TODO: Auto-generated Javadoc
/**
 * The Class NotificationService.
 */
@Service(INotificationService.NAME)
public class NotificationService implements INotificationService {

	/** The email sender. */
	@Autowired
	private JavaMailSender emailSender;

	/**
	 * Send notification.
	 *
	 * @param message the message
	 * @param recipient the recipient
	 */
	@Override
	public void sendNotification(String message, String recipient) {
		try {
			sendHtmlEmail(message, recipient);
		} catch (Exception e) {
			throw new RateLimitingException(e.getMessage(), e);
		}
	}

	/**
	 * Send html email.
	 *
	 * @param content the content
	 * @param to the to
	 * @throws MessagingException the messaging exception
	 */
	public void sendHtmlEmail(String content, String to) throws MessagingException {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom("mazimpaka51@gmail.com");
			helper.setTo(to);
			helper.setSubject("Test");
			helper.setText(content);
			emailSender.send(message);
		} catch (MessagingException e) {
			throw e;
		}
	}

}
