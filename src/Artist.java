import java.sql.*;
public class Artist {
	protected void getArtistInfoByName() {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		
		DBC.closeDBConnection(connect);
	}

	protected void insertArtist() {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		
		DBC.closeDBConnection(connect);
	}

	protected void updateArtist() {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		
		DBC.closeDBConnection(connect);
	}

	protected void removeArtist() {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		
		DBC.closeDBConnection(connect);
	}
}
