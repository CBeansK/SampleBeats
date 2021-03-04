import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	static String currentUser[] = new String[3];
	public static void main(String[] args) {
		startup();
	}
	
	static void startup() {
		User user = new User();
		printTitle();
		printLoginMenu();

		Scanner input = new Scanner(System.in);
		char choice = 0;
		boolean choiceFound = false;
		while (!choiceFound) {
			try {
				String in = input.next();
				if (in.length() > 1)
					throw new InputMismatchException();
				choice = in.charAt(0);
				if (choice < '0' || choice > '3')
					throw new InputMismatchException();
				choiceFound = true;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid number (0-3)");
			}

			if (choice == '1') {
				userLogin(user);
			} else if (choice == '2') {
				lookUpUser(user);
				startup();
			} else if (choice == '3') {
				addNewUser(user);
				startup();
			} else if (choice == '0') {
				System.out.println("\nGood Bye! From Sample Beats");
				System.exit(0);
			}
		}
	}

	static void printTitle() {
		System.out.print("\r\n"
				+ " _______  _______  _______  _______  _        _______ \r\n"
				+ "(  ____ \\(  ___  )(       )(  ____ )( \\      (  ____ \\\r\n"
				+ "| (    \\/| (   ) || () () || (    )|| (      | (    \\/\r\n"
				+ "| (_____ | (___) || || || || (____)|| |      | (__    \r\n"
				+ "(_____  )|  ___  || |(_)| ||  _____)| |      |  __)   \r\n"
				+ "      ) || (   ) || |   | || (      | |      | (      \r\n"
				+ "/\\____) || )   ( || )   ( || )      | (____/\\| (____/\\\r\n"
				+ "\\_______)|/     \\||/     \\||/       (_______/(_______/\r\n"
				+ "                                                      \r\n"
				+ " ______   _______  _______ _________ _______ \r\n"
				+ "(  ___ \\ (  ____ \\(  ___  )\\__   __/(  ____ \\\r\n"
				+ "| (   ) )| (    \\/| (   ) |   ) (   | (    \\/\r\n"
				+ "| (__/ / | (__    | (___) |   | |   | (_____ \r\n"
				+ "|  __ (  |  __)   |  ___  |   | |   (_____  )\r\n"
				+ "| (  \\ \\ | (      | (   ) |   | |         ) |\r\n"
				+ "| )___) )| (____/\\| )   ( |   | |   /\\____) |\r\n"
				+ "|/ \\___/ (_______/|/     \\|   )_(   \\_______)\r\n"
				+ "                                             ");
	}

	static void printLoginMenu() {
		StringBuilder builder = new StringBuilder();

		builder.append("\nWELCOME TO SAMPLE BEATS\n")
				.append("1: Login\n")
				.append("2: Recover User's password\n")
				.append("3: Add a User\n")
				.append("0: CLOSE SAMPLE BEATS\n");

		System.out.println(builder.toString());	
	}
	
	static void userLogin(User user) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your Username and Password.\nUSERNAME:");
		String username = input.next();
		System.out.println("\nPASSWORD:");
		String password = input.next();

		if (user.getUsername(username, password) == true) {
			MenuController menu = new MenuController();
			menu.mainMenu();
		} else {
			System.out.println("\nYOUR USERNAME OR PASSWORD WAS INCORRECT!");
			System.out.println("RETURNING TO STARTUP AND LOGIN MENU");
			startup();
		}
	}

	static void lookUpUser(User user) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your Username and full name so we find recover your password.\n"
				+ "\nFirst we will need your username:");
		
		String username = input.next();

		System.out.println("\nNext, please enter your full name:");
		String fullname = input.nextLine();
		
		System.out.println("\nUsername entered was: " + username + " "
						 + "\nFullname entered was: " + fullname);
		System.out.println("\nPassword: " + user.recoverPassword(username, fullname));
	}

	static void addNewUser(User user) {
		Scanner input = new Scanner(System.in);
		System.out.println("To add a new we will need you to enter in a Username, fullname, and a password");
		System.out.println("\nEnter your username: ");
		
		String username;
		do {
			username = input.next();
		} while(user.checkExistingUsers(username));
		
		System.out.println("\nEnter your fullname name: ");
		String fullname = input.nextLine();
		System.out.println("\nEnter your password: ");
		String password = input.next();
		
		System.out.println(user.insertUser(username, fullname, password));
	}
	
	static void setCurrentUser(String[] current) {
		currentUser = current;
	}
	static String[] getUser() {
		return currentUser;
	}
}