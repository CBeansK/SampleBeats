import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class User {	
	protected Boolean getUsername(String username, String password) {
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();
		
		ResultSet result = null;
		Statement state = null;
		String query = "SELECT username, fullname, password "
					 + "FROM user "
					 + "WHERE username = '" + username + "' AND password = '" + password + "';";
		boolean found = false;
				
		try {
			state = connect.createStatement();
			result = state.executeQuery(query);
			
			if(!result.isBeforeFirst()) {
				found = false;
			} else {
				found = true;
				String[] currentUser = new String[3];
				while(result.next()) {
					currentUser[0] = result.getString("username");
					currentUser[1] = result.getString("fullname");
					currentUser[2] = result.getString("password");
				}
				Main.setCurrentUser(currentUser);
			}
		} catch (Exception error) {
			error.printStackTrace();
		} finally {
			try {
                if (result != null) {
                	result.close();
                }
                if (state != null) {
                	state.close();
                }
            } catch (Exception error) {
                error.printStackTrace();
            }
		}
		DBC.closeDBConnection(connect);
		return found;
	}
	
	protected String recoverPassword(String username, String fullname) {
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();
		
		ResultSet result = null;
		Statement state = null;
		String query = "SELECT password "
					 + "FROM user "
					 + "WHERE username = '" + username + "' AND fullname = '" + fullname + "';";
		String password = "";
				
		try {
			state = connect.createStatement();
			result = state.executeQuery(query);
			
			if(!result.isBeforeFirst()) {
				password = "NO USER FOUND";
			} else {
				while(result.next()) {
					password = result.getString("password");
				}
			}
		} catch (Exception error) {
			error.printStackTrace();
		} finally {
			try {
                if (result != null) {
                	result.close();
                }
                if (state != null) {
                	state.close();
                }
            } catch (Exception error) {
                error.printStackTrace();
            }
		}
		DBC.closeDBConnection(connect);
		return password;
	}

	protected String insertUser(String username, String fullname, String password) {
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();
		
		ResultSet result = null;
		PreparedStatement state = null;
		String query = "INSERT INTO user (username, fullname, password) "
					 + "VALUES('" + username + "','" + fullname + "','" + password + "');";
		String message = "";
				
		try {
			state = connect.prepareStatement(query);
			
			if(state.executeUpdate() > 0) {
				message = "SUCCESS! \n";
			} else {
				message = "NEW USER FAILED TO BE ADDED";
			}
		} catch (Exception error) {
			error.printStackTrace();
		} finally {
			try {
                if (result != null) {
                	result.close();
                }
                if (state != null) {
                	state.close();
                }
            } catch (Exception error) {
                error.printStackTrace();
            }
		}
		DBC.closeDBConnection(connect);
		return message;
	}

	protected void updateUser() {
		String[] currentUser = Main.getUser();
		System.out.println("\nWant to update your user information?");
		System.out.println("Current User Information: "
				+ "\nUsername: " + currentUser[0]
				+ "\nFullname: " + currentUser[1]
				+ "\nPassword: " + currentUser[2]);
		
		System.out.print("__________________________________________________________________"
				+ "\nEnter a designed option: "
				+ "\n1: Username"
				+ "\n2: Fullname"
				+ "\n3: Password");
		
		Scanner input = new Scanner(System.in);
		char choice = 0;
		boolean choiceFound = false;
		String query = "";
		String username = "";
		String fullname = "";
		String password = "";
		
		while (!choiceFound) {
			try {
				String in = input.next();
				if (in.length() > 1)
					throw new InputMismatchException();
				choice = in.charAt(0);
				if (choice < '1' || choice > '3')
					throw new InputMismatchException();
				choiceFound = true;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid number (1-3)");
			}

			if (choice == '1') {
				System.out.println("Please enter your new username: ");
				username = input.next();
				query = "INSERT INTO user (username) "
				      + "VALUES('" + username + "');";
				
			} else if (choice == '2') {
				System.out.println("Please enter your new fullname: ");
				fullname = input.next();
				query = "INSERT INTO user (fullname) "
					      + "VALUES('" + fullname + "');";
				
			} else if (choice == '3') {
				System.out.println("Please enter your new password: ");
				password = input.next();
				query = "INSERT INTO user (password) "
					  + "VALUES('" + password + "');";
			} else if (choice == '0') {
				break;
			}
		}
		
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();
		
		ResultSet result = null;
		PreparedStatement state = null;
		try {
			state = connect.prepareStatement(query);
			
			if(state.executeUpdate() > 0) {
				if(!(username.equals(""))) {
					System.out.println("SUCCESS! \n Updated user's username to: " + username);
					currentUser[0] = username;
				} else if (!(fullname.equals(""))) {
					System.out.println("SUCCESS! \n Updated user's fullname to: " + fullname);
					currentUser[1] = fullname;
				} else if (!(password.equals(""))) {
					System.out.println("SUCCESS! \n Updated user's password to: " + password);
					currentUser[2] = password;
				}
			} else {
				System.out.println("FAILED TO UPDATE");
			}
		} catch (Exception error) {
			error.printStackTrace();
		} finally {
			try {
                if (result != null) {
                	result.close();
                }
                if (state != null) {
                	state.close();
                }
            } catch (Exception error) {
                error.printStackTrace();
            }
		}
		DBC.closeDBConnection(connect);
	}

	protected boolean removeUser() {
		String[] currentUser = Main.getUser();
		
		System.out.println("\nARE YOU SURE YOU WANT TO DELETE YOUR USER?");
		System.out.println("Current User Information: "
				+ "\nUsername: " + currentUser[0]
				+ "\nFullname: " + currentUser[1]
				+ "\nPassword: " + currentUser[2]);
		
		System.out.print("__________________________________________________________________"
				+ "\nEnter a designed option: "
				+ "\n1: Yes"
				+ "\n2: No");
		
		boolean success = false;
		Scanner input = new Scanner(System.in);
		char choice = 0;
		boolean choiceFound = false;
		
		while (!choiceFound) {
			try {
				String in = input.next();
				if (in.length() > 1)
					throw new InputMismatchException();
				choice = in.charAt(0);
				if (choice < '1' || choice > '2')
					throw new InputMismatchException();
				choiceFound = true;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid number (1-2)");
			}

			if (choice == '1') {
				DatabaseConnection DBC = new DatabaseConnection();
				Connection connect = DBC.openDBConnection();
				
				ResultSet result = null;
				Statement state = null;
				String query = "DELETE FROM user "
						+ "WHERE username = '" + currentUser[0] + "' AND fullname = '" + currentUser[1] + "';";
				
				try {
					state = connect.createStatement();
					
					if(state.executeUpdate(query) > 0) {
						System.out.println("SUCCESS! \n Removed User: " + currentUser[0]);
						success = true;
					} else {
						System.out.println("FAILED TO REMOVE");
						success = false;
					}
				} catch (Exception error) {
					error.printStackTrace();
				} finally {
					try {
		                if (result != null) {
		                	result.close();
		                }
		                if (state != null) {
		                	state.close();
		                }
		            } catch (Exception error) {
		                error.printStackTrace();
		            }
				}
				DBC.closeDBConnection(connect);
			} else if (choice == '2') {
				success = false;
				break;
			}
		}
		return success;
	}
}