import java.sql.*;
public class Album {
	protected ResultSet getAlbumByName(String name) {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		System.out.println("Albums Named " + name + ":");
		System.out.println("__________________________________________________________________");

		//TO DO (SQL QUERY CODES GOES HERE)
		String query = String.format("SELECT * FROM " +
				" (album JOIN artist ON artist.artist_id = album.artist_id) WHERE album.name = '%s'", name);

		try {
			PreparedStatement stmt = connect.prepareStatement(query);
			return stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		DBC.closeDBConnection(connect);
		return null;
	}

	protected ResultSet getAlbumByArtist(String artistName) {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		System.out.println("Albums by " + artistName);
		System.out.println("__________________________________________________________________");

		//TO DO (SQL QUERY CODES GOES HERE)
		String query = String.format("SELECT * FROM " +
				"(album JOIN artist ON album.artist_id = artist.artist_id) WHERE artist.name = '%s'", artistName);

		try{
			PreparedStatement stmt = connect.prepareStatement(query);
			return stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBC.closeDBConnection(connect);
		return null;
	}

	protected void insertAlbum(String name, String num_songs, String artist_id) {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		// get name
		// get number of songs
		// get artist
		// if artist not found: offer to add artist

		//TO DO (SQL QUERY CODES GOES HERE)
		
		DBC.closeDBConnection(connect);
	}

	protected void updateAlbumName(String albumName) {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		
		DBC.closeDBConnection(connect);
	}

	protected void updateAlbumSongs(String numSongs){

	}

	protected void updateAlbumArtist(String artist){

	}

	protected void removeAlbum(String name) {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		
		DBC.closeDBConnection(connect);
	}

	protected void displayAlbumData(ResultSet rs){
		try {
			// print query results
			if (!rs.isBeforeFirst() || rs == null) {
				System.out.println("No results found.");
			} else {
				//TODO: Make results print nicely
				System.out.println("NAME\t\t\t\tARTIST\t\t\t\tGENRE\t\t\tNUMBER OF SONGS");
				System.out.println("__________________________________________________________________");
				while(rs.next()){

					String tuple = String.format("%s\t%s\t%s\t%s",
							rs.getString("album.name"),
							rs.getString("artist.name"),
							rs.getString("artist.genre"),
							rs.getString("album.num_Songs"));
					System.out.println(tuple);
				}
			}
		} catch (SQLException e){
			System.out.println("Failed to finish query");
		}
	}
}
