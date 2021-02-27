import java.sql.*;
public class DatabaseConnection {
	protected Connection openDBConnection() {
		Connection connect = null;
		try {
			//PLEASE USER A DIFFERENT JDBC DRIVER IF NEEDED.
			//Class.forName("com.mysql.jdbc.Driver");
			
			//PLEASE USE YOUR URL HERE, YOUR USER, YOU PASSWORD
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample_beats", "root", "WaltDisney0721!");
        } catch (Exception error) {
            error.printStackTrace();
        }
		return connect;
	}
	
	protected void closeDBConnection(Connection connect) {
		try {
            connect.close();
        } catch (Exception error) {
        	System.out.println("DATABASE RESOURCE DID NOT CLOSE: LEAKED CONNECTION");
        }
	}
}