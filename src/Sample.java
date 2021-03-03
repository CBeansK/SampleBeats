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

	protected void getSampleBySong(String songName) {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();
		
		ResultSet result = null;
		Statement state = null;
		String query = String.format("SELECT sample.description, song.title, artist.name AS artist, album.name AS album\n" +
				"FROM ((song JOIN album) JOIN artist) JOIN sample\n" +
				"    ON sample.song_id = song.song_id AND\n" +
				"    sample.album_id = album.album_id AND\n" +
				"    sample.artist_id = artist.artist_id WHERE song.title = '%s'", songName);
				
		try {
            state = connect.createStatement();
            result = state.executeQuery(query);
			
			if(!result.isBeforeFirst()) {
				System.out.println("\nThere is no SAMPLE by that SONG NAME.");
				System.out.println("Returning to SAMPLE menu\n");
			} else {
				System.out.printf("\nSAMPLE DESCRIPTION            SONG NAME                     ARTIST NAME                   ALBUM NAME                    \n"
		                + "__________________________________________________________________________________________________________________________________\n");
        
				while (result.next()) {
						System.out.printf(" %-29s %-29s %-29s %-29s%n",
	                    		result.getString("description"),
	                    		result.getString("title"),
	                    		result.getString("artist"),
	                    		result.getString("album"));
				}
				System.out.println("\n");
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

	protected void getSampleByAlbum(String albumName) {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();
		
		ResultSet result = null;
		Statement state = null;
		String query = String.format("SELECT sample.description, song.title, artist.name AS artist, album.name AS album\n" +
				"FROM ((song JOIN album) JOIN artist) JOIN sample\n" +
				"    ON sample.song_id = song.song_id AND\n" +
				"    sample.album_id = album.album_id AND\n" +
				"    sample.artist_id = artist.artist_id WHERE album.name = '%s'", albumName);
				
		try {
            state = connect.createStatement();
            result = state.executeQuery(query);
			
			if(!result.isBeforeFirst()) {
				System.out.println("\nThere is no SAMPLE by that ALBUM NAME.");
				System.out.println("Returning to SAMPLE menu\n");
			} else {
				System.out.printf("\nSAMPLE DESCRIPTION            SONG NAME                     ARTIST NAME                   ALBUM NAME                    \n"
		                + "__________________________________________________________________________________________________________________________________\n");
        
				while (result.next()) {
						System.out.printf(" %-29s %-29s %-29s %-29s%n",
	                    		result.getString("description"),
	                    		result.getString("title"),
	                    		result.getString("artist"),
	                    		result.getString("album"));
				}
				System.out.println("\n");
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
	
	protected void getSampleByArtist(String artistName) {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();
		
		ResultSet result = null;
		Statement state = null;
		String query = String.format("SELECT sample.description, song.title, artist.name AS artist, album.name AS album\n" +
				"FROM ((song JOIN album) JOIN artist) JOIN sample\n" +
				"    ON sample.song_id = song.song_id AND\n" +
				"    sample.album_id = album.album_id AND\n" +
				"    sample.artist_id = artist.artist_id WHERE artist.name = '%s'", artistName);
				
		try {
            state = connect.createStatement();
            result = state.executeQuery(query);
			
			if(!result.isBeforeFirst()) {
				System.out.println("\nThere is no SAMPLE by that ALBUM NAME.");
				System.out.println("Returning to SAMPLE menu\n");
			} else {
				System.out.printf("\nSAMPLE DESCRIPTION            SONG NAME                     ARTIST NAME                   ALBUM NAME                    \n"
		                + "__________________________________________________________________________________________________________________________________\n");
        
				while (result.next()) {
						System.out.printf(" %-29s %-29s %-29s %-29s%n",
	                    		result.getString("description"),
	                    		result.getString("title"),
	                    		result.getString("artist"),
	                    		result.getString("album"));
				}
				System.out.println("\n");
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

	protected void insertSample() {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		
		DBC.closeDBConnection(connect);
	}

	protected void updateSampleName(String ogDesc, String desc) {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		String query = String.format("UPDATE sample SET description = '%s' WHERE description = '%s'", desc, ogDesc);

		try {
			PreparedStatement statement = connect.prepareStatement(query);
			int res = 0;
			res = statement.executeUpdate();

			if (res == 0) throw new SQLException();
			else System.out.println("Updated sample name successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		DBC.closeDBConnection(connect);
	}

	protected void updateSampleSong(String originalName, String newOriginal) {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		int song_id = 0;
		try {
			String q = "SELECT song_id FROM song WHERE name = " + originalName;
			song_id = connect.prepareStatement(q).executeQuery().getInt(1);
		} catch (SQLException e){
			e.printStackTrace();
		}

		if (song_id == 0){
			System.out.println("Original song not found.");
		}
		//TO DO (SQL QUERY CODES GOES HERE)
		String query = String.format("UPDATE sample SET song_id = %d WHERE description = '%s'", song_id, originalName);

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

	protected void removeSample(String desc) {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();

		//TO DO (SQL QUERY CODES GOES HERE)
		String query = String.format("DELETE FROM sample WHERE description = '%s'", desc);

		try {
			PreparedStatement statement = connect.prepareStatement(query);
			int res = 0;
			res = statement.executeUpdate();

			if (res == 0) throw new SQLException();
			else System.out.println("Removed sample successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
