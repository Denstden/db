package ua.kiev.unicyb.tcct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * @Author Denys Storozhenko.
 */
@Component
public class ClientConsoleRunner implements CommandLineRunner {
	private static final Scanner scanner = new Scanner(System.in);

	@Autowired
	private WSClient wsClient;

	@Override
	public void run(String... strings) throws Exception {
		label:
		while (true) {
			printCommands();
			Integer command = scanner.nextInt();
			switch (command) {
			case 1: {
				getDatabaseByName();
				continue label;
			}
			case 2: {
				intersectTables();
				continue label;
			}
			case 0: {
				System.exit(0);
			}
			default: {
				break;
			}
			}
		}
	}

	private void getDatabaseByName() {
		System.out.println("Enter database name");
		try {
			System.out.println(wsClient.getDatabaseByName(scanner.next()));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private void intersectTables() {
		System.out.println("Enter database1 name");
		String db1Name = scanner.next();
		System.out.println("Enter table1 name");
		String table1Name = scanner.next();
		System.out.println("Enter database2 name");
		String db2Name = scanner.next();
		System.out.println("Enter table2 name");
		String table2Name = scanner.next();

		try {
			System.out.println("Table intersection:");
			System.out.println(wsClient.intersect(db1Name, db2Name, table1Name, table2Name));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private void printCommands() {
		System.out.println("===============AVAILABLE COMMANDS==============");
		System.out.println("Get database by name - 1");
		System.out.println("Get table intersection - 2");
		System.out.println("Exit - 0");
		System.out.println("===============AVAILABLE COMMANDS==============");
	}
}
