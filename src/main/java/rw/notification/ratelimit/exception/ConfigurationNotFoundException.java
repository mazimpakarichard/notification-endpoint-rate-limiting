package rw.notification.ratelimit.exception;

public class ConfigurationNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConfigurationNotFoundException(String string) {
		super(string);
	}
}
