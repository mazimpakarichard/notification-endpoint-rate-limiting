package rw.notification.ratelimit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rw.notification.ratelimit.model.EmailNotificationRequest;
import rw.notification.ratelimit.service.INotificationService;
import rw.notification.ratelimit.utils.ResponseDto;

// TODO: Auto-generated Javadoc
/**
 * The Class NotificationController.
 */
@RestController
@RequestMapping("/notifications")
public class NotificationController {

	/** The notification service. */
	@Autowired
	private INotificationService notificationService;

	@PostMapping("/sms")
	public ResponseDto sendSmsNotification() {
		try {
			return new ResponseDto();
		} catch (Exception e) {
			return new ResponseDto(e);
		}
	}

	/**
	 * Send email notification.
	 *
	 * @param request the request
	 * @return the response dto
	 */
	@PostMapping("/email")
	public ResponseDto sendEmailNotification(@RequestBody EmailNotificationRequest request) {
		try {
			notificationService.sendNotification(request.getMessage(), request.getRecipient());
			return new ResponseDto();
		} catch (Exception e) {
			return new ResponseDto(e);
		}

	}
}
