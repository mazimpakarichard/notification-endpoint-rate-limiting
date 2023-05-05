package rw.notification.ratelimit.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.micrometer.core.instrument.util.StringUtils;
import rw.notification.ratelimit.model.Throttling;
import rw.notification.ratelimit.service.RateLimiter;
import rw.notification.ratelimit.utils.RateLimitingException;

@Component
public class RequestFilter extends OncePerRequestFilter {

	@Autowired
	private RateLimiter rateLimiter;

//	private static final int SOFT_LIMIT = 10; // Number of tokens below which soft throttling is applied
//	private static final int HARD_LIMIT = 0; // Number of tokens below which hard throttling is applied
//	private static final int DELAY_MS = 500; // Delay (in milliseconds) before retrying when soft/hard throttling is

	@Value("${server.name}")
	private String serverName;

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (request.getRequestURI().startsWith("/notifications")) {
			String clientCode = request.getHeader("clientCode");
			if (StringUtils.isNotBlank(clientCode)) {
				Throttling throttling = rateLimiter.getThrottlingConfiguration(serverName);
				Bucket bucket = rateLimiter.resolveBucketForClient(clientCode);

				// do the soft throttling
				if (bucket.tryConsume(1)) {
					System.out.println("the bucket is remaining with " + bucket.getAvailableTokens() + " tokens ");
					filterChain.doFilter(request, response);
				} else {
					Bucket bucketForGlobal = rateLimiter.resolveBucketForClientGlobally(clientCode);
					if (bucketForGlobal.tryConsume(1)) {
						System.out.println(
								"the bucket is remaining with " + bucketForGlobal.getAvailableTokens() + " tokens ");
						filterChain.doFilter(request, response);
					} else {
						Bucket bucketForMonthtly = rateLimiter.resolveBucketForClientMonthly(clientCode);
						if (bucketForMonthtly.tryConsume(1)) {
							System.out.println("the bucket is remaining with " + bucketForMonthtly.getAvailableTokens()
									+ " tokens ");
							filterChain.doFilter(request, response);
						} else {
							sendErrorReponse(response, HttpStatus.TOO_MANY_REQUESTS.value(), "too many request");
						}
					}

				}
			} else {
				sendErrorReponse(response, HttpStatus.FORBIDDEN.value(), "client code is null");
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}

	private void sendErrorReponse(HttpServletResponse response, int value, String message) {
		HttpServletResponse resp = (HttpServletResponse) response;
		try {
			resp.setStatus(value);
			resp.getWriter().write(message);
			resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
