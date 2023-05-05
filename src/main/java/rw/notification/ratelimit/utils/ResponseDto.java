package rw.notification.ratelimit.utils;

import org.springframework.http.HttpStatus;

/**
 * The Class ResponseDto.
 * 
 * @author richard.mazimpaka
 */
public class ResponseDto {

	private HttpStatus status;
	private Object response;
	private String message;

	public ResponseDto() {
		this.message = "Email was sent successfully";
	}

	public ResponseDto(Throwable throwable) {
		if (throwable instanceof RateLimitingException) {
			this.response = throwable.getMessage();
			this.status = HttpStatus.TOO_MANY_REQUESTS;
			this.message = "Rate limit exceeded";
		} else {
			this.response = throwable.getLocalizedMessage();
			this.status = HttpStatus.INTERNAL_SERVER_ERROR;
			this.message = "An error occurred";
		}
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
