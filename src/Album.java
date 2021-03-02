import java.sql.*;
import java.util.Scanner;

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

	protected void insertAlbum(String name, int num_songs, String artistName) {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		int artist_id = 0;


		artist_id = getArtistId(artistName);
		// return if artist not found
		// TODO:  if artist not found: offer to add artist
		if (artist_id == 0){
			System.out.println("Artist not found.");
			return;
		}


		String query = String.format("INSERT INTO album (name, num_Songs, artist_id) VALUES ('%s', %d, %d)", name, num_songs, artist_id);
		try {
			PreparedStatement stmt = connect.prepareStatement(query);
			int res = stmt.executeUpdate();

			if (res == 0) throw new SQLException();
			else System.out.println("Added new album successfully.");

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//TO DO (SQL QUERY CODES GOES HERE)
		
		DBC.closeDBConnection(connect);
	}

	protected boolean updateAlbumName(String originalName, String albumName) {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		String query = String.format("UPDATE album SET name = '%s' WHERE name = '%s'", albumName, originalName);

		try {
			PreparedStatement statement = connect.prepareStatement(query);
			int res = 0;
			res = statement.executeUpdate();

			if (res == 0) throw new SQLException();
			else System.out.println("Updated album name successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		
		DBC.closeDBConnection(connect);
		return true;
	}

	protected boolean updateAlbumSongs(String songName, int numSongs){
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		String query = String.format("UPDATE album SET num_Songs = '%d' WHERE name = '%s'", numSongs, songName);

		try {
			PreparedStatement statement = connect.prepareStatement(query);
			int res = 0;
			res = statement.executeUpdate();

			if (res == 0) throw new SQLException();
			else System.out.println("Updated number of songs successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		DBC.closeDBConnection(connect);
		return true;
	}

	protected void updateAlbumArtist(String albumName, String artist){
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		int artist_id = getArtistId(artist);

		// TODO: Implement add artist here
		if (artist_id == 0){
			System.out.println("Artist not found.");
			System.out.println("Would you like to add the artist? (y/n)");

			// bad for single threading I think ://
			Scanner input = new Scanner(System.in);

			boolean found = false;
			while(!found){
				String response = input.nextLine();
				switch (response.toLowerCase()){
					case "y":
						System.out.println("Enter artist's genre:");
						String genre = input.nextLine();
						Artist artistClass = new Artist();
						artistClass.insertArtist(artist, genre);
						found = true;
						break;
					case "n":
						System.out.println("Cancelling update.");
						found = true;
						return;
					default:
						System.out.println("Invalid response. Please enter (y/n)");

				}
			}

			artist_id = getArtistId(artist);


		}
		//TO DO (SQL QUERY CODES GOES HERE)
		String query = String.format("UPDATE album SET artist_id = '%d' WHERE name = '%s'", artist_id, albumName);
		try {
			PreparedStatement statement = connect.prepareStatement(query);
			int res = 0;
			res = statement.executeUpdate();

			if (res == 0) throw new SQLException();
			else System.out.println("Updated artist successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBC.closeDBConnection(connect);
	}

	protected void removeAlbum(String name, String artist) {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		int artist_id = getArtistId(artist);


		String query = String.format("DELETE FROM album WHERE name = '%s' AND artist_id = '%d'", name, artist_id);
		//TO DO (SQL QUERY CODES GOES HERE)
		try {
			PreparedStatement statement = connect.prepareStatement(query);
			int res = 0;
			res = statement.executeUpdate();

			if (res == 0) throw new SQLException();
			else System.out.println("Removed album successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBC.closeDBConnection(connect);
	}

	private int getArtistId(String artistName){

		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		String artistQuery = String.format("SELECT artist_id FROM artist WHERE artist.name = '%s'", artistName);
		int artist_id = 0;
		// get artist id from artist name
		try{
			PreparedStatement stmt = connect.prepareStatement(artistQuery);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()){
				System.out.println("No results found");
			} else {
				rs.next();
				artist_id = rs.getInt(1);
				rs.close();
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBC.closeDBConnection(connect);
		return artist_id;
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

			rs.getStatement().close();
		} catch (SQLException e){
			System.out.println("Failed to finish query");
		}
	}
}
