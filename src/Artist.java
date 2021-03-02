import java.sql.*;
public class Artist {
	protected ResultSet getArtistInfoByName(String name) {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		System.out.println("Artist Named " + name + ":");
		System.out.println("__________________________________________________________________");

		//TO DO (SQL QUERY CODES GOES HERE)
		String query = String.format("SELECT * FROM artist WHERE name = '%s'", name);

		try {
			PreparedStatement stmt = connect.prepareStatement(query);
			return stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBC.closeDBConnection(connect);
		return null;
	}

	protected void insertArtist(String name, String genre) {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		if (getArtistId(name) > 0){
			System.out.println("Artist already exists in database.");
			displayArtistData(getArtistInfoByName(name));
			return;
		}

		String query = String.format("INSERT INTO artist (name, genre) VALUES ('%s','%s')", name, genre);

		try {
			PreparedStatement stmt = connect.prepareStatement(query);
			int res = stmt.executeUpdate();

			if(res == 0) throw new SQLException();
			else System.out.println("Added new artist successfully.");

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//TO DO (SQL QUERY CODES GOES HERE)
		
		DBC.closeDBConnection(connect);
	}

	protected void updateArtistName(String startingArtistName, String artistName) {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		String query = String.format("UPDATE artist SET name = '%s' WHERE name = '%s'", artistName, startingArtistName);

		try{
			PreparedStatement statement = connect.prepareStatement(query);
			int res = 0;
			res = statement.executeUpdate();

			if(res == 0) throw new SQLException();
			else System.out.println("Updated Artist name.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DBC.closeDBConnection(connect);
	}

	protected void updateArtistGenre(String startingGenreName, String artistGenre) {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		String query = String.format("UPDATE artist SET genre = '%s' WHERE genre = '%s'", artistGenre, startingGenreName);

		try{
			PreparedStatement statement = connect.prepareStatement(query);
			int res = 0;
			res = statement.executeUpdate();

			if(res == 0) throw new SQLException();
			else System.out.println("Updated Artist genre.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBC.closeDBConnection(connect);
	}

	protected void removeArtist(String name, String artist) {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		int artist_id = getArtistId(artist);

		String query = String.format("DELETE FROM artist WHERE name = '%s' AND artist_id = '%d'", name, artist_id);
		//TO DO (SQL QUERY CODES GOES HERE)
		try {
			PreparedStatement statement = connect.prepareStatement(query);
			int res = 0;
			res = statement.executeUpdate();

			if (res == 0) throw new SQLException();
			else System.out.println("Removed artist successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBC.closeDBConnection(connect);
	}

	//Created by Benjiman Keen
	//Aids in checking if an Artist is already in the database
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

	protected void displayArtistData(ResultSet rs){
		try {
			// print query results
			if (!rs.isBeforeFirst() || rs == null) {
				System.out.println("No results found.");
			} else {
				//TODO: Make results print nicely
				System.out.println("ARTIST\t\t\t\tGENRE\t\t\t");
				System.out.println("__________________________________________________________________");
				while(rs.next()){

					String tuple = String.format("%s\t%s",
							rs.getString("artist.name"),
							rs.getString("artist.genre"));
					System.out.println(tuple);
				}
			}

			rs.getStatement().close();
		} catch (SQLException e){
			System.out.println("Failed to finish query");
		}
	}
}
