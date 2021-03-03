import java.sql.*;
public class Sample {
	protected ResultSet getSampleByDescription(String songName) {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		String query = String.format("SELECT sample.description, song.title, artist.name, album.name\n" +
				"FROM ((song JOIN album) JOIN artist) JOIN sample\n" +
				"    ON sample.song_id = song.song_id AND\n" +
				"    sample.album_id = album.album_id AND\n" +
				"    sample.artist_id = artist.artist_id WHERE sample.description = '%s'", songName);

		try {
			PreparedStatement statement = connect.prepareStatement(query);
			return statement.executeQuery();
		} catch (SQLException e){
			e.printStackTrace();
		}
		DBC.closeDBConnection(connect);
		return null;
	}

	protected void getSampleBySong() {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		
		DBC.closeDBConnection(connect);
	}

	protected void getSampleByAlbum() {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		
		DBC.closeDBConnection(connect);
	}

	protected void getSampleByArtist() {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		
		DBC.closeDBConnection(connect);
	}

	protected void insertSample() {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		
		DBC.closeDBConnection(connect);
	}

	protected void updateSample() {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		
		DBC.closeDBConnection(connect);
	}

	protected void removeSample() {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		
		DBC.closeDBConnection(connect);
	}

	public void printSampleData(ResultSet rs){
		try {
			// print query results
			if (!rs.isBeforeFirst() || rs == null) {
				System.out.println("No results found.");
			} else {
				//TODO: Make results print nicely
				System.out.println("SAMPLE IN\t\t\tORIGINAL SONG\t\t\t\t\tARTIST\t\t\tALBUM");
				System.out.println("__________________________________________________________________");
				while(rs.next()){

					String tuple = String.format("%s\t%s\t%s\t%s",
							rs.getString("sample.description"),
							rs.getString("song.title"),
							rs.getString("artist.name"),
							rs.getString("album.name"));
					System.out.println(tuple);
				}
			}

			rs.getStatement().close();
		} catch (SQLException e){
			System.out.println("Failed to finish query");
		}
	}
}
