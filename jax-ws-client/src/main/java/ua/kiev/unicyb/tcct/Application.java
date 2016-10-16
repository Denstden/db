package ua.kiev.unicyb.tcct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author Denys Storozhenko.
 */
@SpringBootApplication
public class Application {
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class);
		ClientConsoleRunner runner = context.getBean(ClientConsoleRunner.class);
		runner.run(args);
	}
}