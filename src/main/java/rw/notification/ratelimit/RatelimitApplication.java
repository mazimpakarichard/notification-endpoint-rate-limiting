package rw.notification.ratelimit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = { "rw.notification.ratelimit" })
@EnableCaching
@EnableScheduling
public class RatelimitApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatelimitApplication.class, args);
	}

}
