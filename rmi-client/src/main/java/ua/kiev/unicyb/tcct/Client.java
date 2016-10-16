package ua.kiev.unicyb.tcct;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author Denys Storozhenko.
 */
public class Client {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		ConsoleApp consoleApp = context.getBean(ConsoleApp.class);
		consoleApp.run();
	}
}
