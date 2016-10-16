package ua.kiev.unicyb.tcct;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author Denys Storozhenko.
 */
public class Server {
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("context.xml");
	}
}
