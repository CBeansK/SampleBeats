import java.sql.*;
public class DatabaseConnection {
	protected Connection openDBConnection() {
		Connection connect = null;
		try {
			//PLEASE USER A DIFFERENT JDBC DRIVER IF NEEDED.
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//PLEASE USE YOUR URL HERE, YOUR USER, YOU PASSWORD
			//TODO: Change pw back when committing
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample_beats", "root", "rootSER322HW");
        } catch (Exception error) {
			System.out.println("FAILED TO CONNECT TO SERVER");
			System.out.println("ENSURE SERVER IS RUNNING AND CONFIGURATION IS CORRECT");
            error.printStackTrace();
        }
		return connect;
	}
	
	protected void closeDBConnection(Connection connect) {
		try {
            connect.close();
        } catch (Exception error) {
        	System.out.println("DATABASE RESOURSE DID NOT CLOSE: LEAKED CONNECTION");
        }
	}
}
