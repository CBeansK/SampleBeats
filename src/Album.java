import java.sql.*;
public class Album {
	protected void getAlbumByName() {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		
		DBC.closeDBConnection(connect);
	}

	protected void getAlbumByArtist() {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		
		DBC.closeDBConnection(connect);
	}

	protected void insertAlbum() {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		
		DBC.closeDBConnection(connect);
	}

	protected void updateAlbum() {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		
		DBC.closeDBConnection(connect);
	}

	protected void removeAlbum() {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		
		DBC.closeDBConnection(connect);
	}
}
