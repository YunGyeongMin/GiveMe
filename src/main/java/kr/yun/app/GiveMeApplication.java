package kr.yun.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:${JDBC.Config}"})
public class GiveMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiveMeApplication.class, args);
	}

}
