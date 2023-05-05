package rw.notification.ratelimit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import rw.notification.ratelimit.repository.RateLimitRepository;
import rw.notification.ratelimit.repository.ThrottlingRepository;

import org.mockito.junit.jupiter.MockitoExtension;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class BucketConfigurationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private RateLimitRepository limitRepository;

	@Autowired
	private ThrottlingRepository throttlingRepository;

	@AfterEach
	void clean() {
		limitRepository.deleteAll();
		throttlingRepository.deleteAll();
	}

	@Test
	@Sql("classpath:/sql/data.sql")
	public void givenClient_whenSendingNotofication_thenStatus200() throws Exception {

		MvcResult result = mvc.perform(post("/notifications/sms").header("clientCode", "00440")).andReturn();

		int statusCode = result.getResponse().getStatus();
		assertEquals(200, statusCode);

	}

	@Test
	@Sql("classpath:/sql/data.sql")
	public void givenClient_whenSendingNotofication_thenStatus429() throws Exception {

		int numRequests = 110; // number of requests to make
		long timeIntervalMs = 1000; // time interval between requests in milliseconds

		for (int i = 0; i < numRequests; i++) {
			MvcResult result = mvc
					.perform(post("/notifications/sms").contentType(MediaType.APPLICATION_JSON)
							.header("clientCode", "00440")
							.content("{\"message\": \"test\", \"recipient\": \"mazimpaka51@gmail.com\"}"))
					.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andReturn();

			int statusCode = result.getResponse().getStatus();
			if (statusCode == HttpStatus.TOO_MANY_REQUESTS.value()) {
				System.out.println("message from the response " + result.getResponse().getErrorMessage());
				System.out.println("Too many requests: " + result.getResponse().getContentAsString());
				assertEquals(429, statusCode);
			} else {
				assertEquals(200, statusCode);
				String content = result.getResponse().getContentAsString();
				System.out.println("Response: " + content);
			}

			Thread.sleep(timeIntervalMs);
		}

	}
}
