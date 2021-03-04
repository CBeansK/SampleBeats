import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Song {
	protected void getSongByName() {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();
		Scanner input = new Scanner(System.in);
		System.out.println("Please input the NAME of the SONG your looking for");
		String title = input.nextLine();
		
		ResultSet result = null;
		Statement state = null;
		String query = "SELECT song.song_id, song.title, song.length, album.name AS album, artist.name AS artist "
				+ "FROM song "
				+ "INNER JOIN album ON song.album_id = album.album_id "
				+ "INNER JOIN artist ON song.artist_id = artist.artist_id "
				+ "WHERE title = '" + title + "';";
				
		try {
            state = connect.createStatement();
            result = state.executeQuery(query);
			
			if(!result.isBeforeFirst()) {
				System.out.println("\nThere is no SONGS by that NAME.");
				System.out.println("Returning to Song menu\n");
			} else {
				System.out.printf("\nSONG ID          SONG NAME          SONG LENGTH          ALBUM NAME          ARTIST NAME          \n"
		                + "_____________________________________________________________________________________________________________________\n");
        
				while (result.next()) {
						System.out.printf(" %-16s %-18s %-20s %-19s %-21s%n",
	                    		result.getInt("song_id"),
	                    		result.getString("title"),
	                    		result.getInt("length"),
	                    		result.getString("album"),
	                    		result.getString("artist"));
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

	protected void getSongByArtist() {
		//SQL QUERIES NEEDS THESE OBJ
				DatabaseConnection DBC = new DatabaseConnection();
				Connection connect = DBC.openDBConnection();
				Scanner input = new Scanner(System.in);
				System.out.println("Please input the NAME of the ARTIST of the SONG your looking for");
				String artist = input.nextLine();
				
				ResultSet result = null;
				Statement state = null;
				String query = "SELECT song.song_id, song.title, song.length, album.name AS album, artist.name AS artist "
						+ "FROM song "
						+ "INNER JOIN album ON song.album_id = album.album_id "
						+ "INNER JOIN artist ON song.artist_id = artist.artist_id "
						+ "WHERE artist.name = '" + artist + "';";
						
				try {
		            state = connect.createStatement();
		            result = state.executeQuery(query);
					
					if(!result.isBeforeFirst()) {
						System.out.println("\nThere is no SONGS by that ARTIST NAME.");
						System.out.println("Returning to Song menu\n");
					} else {
						System.out.printf("\nSONG ID          SONG NAME          SONG LENGTH          ALBUM NAME          ARTIST NAME          \n"
				                + "_____________________________________________________________________________________________________________________\n");
		        
						while (result.next()) {
								System.out.printf(" %-16s %-18s %-20s %-19s %-21s%n",
			                    		result.getInt("song_id"),
			                    		result.getString("title"),
			                    		result.getInt("length"),
			                    		result.getString("album"),
			                    		result.getString("artist"));
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

	protected void getSongByAlbum() {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();
		Scanner input = new Scanner(System.in);
		System.out.println("Please input the NAME of the ALBUM of the SONG your looking for");
		String album = input.nextLine();
		
		ResultSet result = null;
		Statement state = null;
		String query = "SELECT song.song_id, song.title, song.length, album.name AS album, artist.name AS artist "
				+ "FROM song "
				+ "INNER JOIN album ON song.album_id = album.album_id "
				+ "INNER JOIN artist ON song.artist_id = artist.artist_id "
				+ "WHERE album.name = '" + album + "';";
				
		try {
            state = connect.createStatement();
            result = state.executeQuery(query);
			
			if(!result.isBeforeFirst()) {
				System.out.println("\nThere is no SONGS by that ALBUM NAME.");
				System.out.println("Returning to Song menu\n");
			} else {
				System.out.printf("\nSONG ID          SONG NAME          SONG LENGTH          ALBUM NAME          ARTIST NAME          \n"
		                + "_____________________________________________________________________________________________________________________\n");
        
				while (result.next()) {
						System.out.printf(" %-16s %-18s %-20s %-19s %-21s%n",
	                    		result.getInt("song_id"),
	                    		result.getString("title"),
	                    		result.getInt("length"),
	                    		result.getString("album"),
	                    		result.getString("artist"));
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

	protected void insertSong() {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();
		ResultSet result = null;
		PreparedStatement state = null;
		
		Scanner input = new Scanner(System.in);
		System.out.println("To insert a song we will need your song's name, album (if any), and the artist.");
		System.out.println("This will be used to ensure the song does not exist already.");

		System.out.println("Please input the SONG'S NAME: ");
		String songName = input.nextLine();
		System.out.println("Please input the SONG'S ALBUM NAME (if none put 'NULL'): ");
		String songAlbum = input.nextLine();
		System.out.println("Please input the SONG'S ARTIST NAME: ");
		String songArtist = input.nextLine();

		if(checkSongInfo(songName, songAlbum, songArtist) == false) {
			System.out.println("THE SONG YOU SEARCHED FOR DOES NOT EXIST. LETS ADD IT NOW\n\n");
			System.out.println("Please input the SONG'S NAME: ");
			String newSongName = input.nextLine();
			
			boolean formatInt = false;
			int newSongAlbum = 0;
			int newSongArtist = 0;
			int newSongLength = 0;
			formatInt = false;
			while (formatInt == false) {
				try {
					System.out.println("Please input the SONG'S NEW ALBUM ID (if none put 'NULL'): ");
					String tempNewSongAlbum = input.nextLine();
					tempNewSongAlbum = tempNewSongAlbum.toUpperCase();
					
					if (tempNewSongAlbum.equals("NULL")) {
						break;
					} else {
						newSongAlbum = Integer.parseInt(tempNewSongAlbum);
						formatInt = true;
					}
				} catch (NumberFormatException ex) {
					System.out.println("\nInvalid Input: not a number. Try again.");
					formatInt = false;
				}
			 }
			formatInt = false;
			while (formatInt == false) {
				try {
					System.out.println("Please input the SONG'S ARTIST ID: ");
					String tempNewSongArtist = input.nextLine();
					
					newSongArtist = Integer.parseInt(tempNewSongArtist);
					formatInt = true;
				} catch (NumberFormatException ex) {
					System.out.println("\nInvalid Input: not a number. Try again.");
					formatInt = false;
				}
			 }
			formatInt = false;
			while (formatInt == false) {
				try {
					System.out.println("Please input the SONG'S LENGTH: ");
					String tempNewSongLength = input.nextLine();

					newSongLength = Integer.parseInt(tempNewSongLength);
					formatInt = true;
				} catch (NumberFormatException ex) {
					System.out.println("\nInvalid Input: not a number. Try again.");
					formatInt = false;
				}
			 }

			String query = "INSERT INTO song (title, length, album_id, artist_id) "
						 + "VALUES ('" + newSongName + "'," + newSongLength + "," + newSongAlbum + "," + newSongArtist + ");";
			
			try {
				state = connect.prepareStatement(query);
				
				if(state.executeUpdate() > 0) {
					System.out.println("SUCCESS! \nSONG: " + songName + " WAS ADDED!");
				} else {
					System.out.println("SONG FAILED TO UPDATE");
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
		} else {
			System.out.println("\nThat song already exists\nReturn you to the Song Menu.\n");
			DBC.closeDBConnection(connect);
			return;
		}
		DBC.closeDBConnection(connect);
	}

	protected void updateSong() {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();
		ResultSet result = null;
		PreparedStatement state = null;
		
		Scanner input = new Scanner(System.in);
		System.out.println("To UPDATE a song we will need your song's name, album (if any), and the artist.");
		System.out.println("This will be used to ensure we UPDATE the correct song.");

		System.out.println("Please input the SONG'S NAME: ");
		String songName = input.nextLine();
		System.out.println("Please input the SONG'S ALBUM NAME (if none put 'NULL'): ");
		String songAlbum = input.nextLine();
		System.out.println("Please input the SONG'S ARTIST NAME: ");
		String songArtist = input.nextLine();

		if(checkSongInfo(songName, songAlbum, songArtist) == true) {
			System.out.println("If this is the correct song you want to UPDATE please input: "
					+ "\n1: TO UPDATE "
					+ "\n0: TO GO BACK TO MENU");
			
			char choice = 0;
			boolean choiceFound = false;
			while (!choiceFound) {
				try {
					String in = input.nextLine();
					if (in.length() > 1)
						throw new InputMismatchException();
					choice = in.charAt(0);
					if (choice < '0' || choice > '3')
						throw new InputMismatchException();
					choiceFound = true;
				} catch (InputMismatchException e) {
					System.out.println("Please enter a valid number (0-3)");
				}

				if (choice == '1') {
					boolean formatInt = false;
					int newSongAlbum = 0;
					int newSongArtist = 0;
					int newSongLength = 0;
					int songID = 0;
					
					while (formatInt == false) {
						try {
							System.out.println("Please input the SONG'S ID (LISTED ABOVE): ");
							String tempSongID = input.nextLine();
							songID = Integer.parseInt(tempSongID);
							formatInt = true;
						} catch (NumberFormatException ex) {
							System.out.println("\nInvalid Input: not a number. Try again.");
							formatInt = false;
						}
					 }
					
					System.out.println("Please input the SONG'S NEW NAME: ");
					String newSongName = input.nextLine();
					formatInt = false;
					while (formatInt == false) {
						try {
							System.out.println("Please input the SONG'S NEW ALBUM ID (IF SAME ID USE WHAT IS LISTED ABOVE) (if none put 'NULL'): ");
							String tempNewSongAlbum = input.nextLine();
							tempNewSongAlbum = tempNewSongAlbum.toUpperCase();
							
							if (tempNewSongAlbum.equals("NULL")) {
								break;
							} else {
								newSongAlbum = Integer.parseInt(tempNewSongAlbum);
								formatInt = true;
							}
						} catch (NumberFormatException ex) {
							System.out.println("\nInvalid Input: not a number. Try again.");
							formatInt = false;
						}
					 }
					formatInt = false;
					while (formatInt == false) {
						try {
							System.out.println("Please input the SONG'S NEW ARTIST ID (IF SAME ID USE WHAT IS LISTED ABOVE): ");
							String tempNewSongArtist = input.nextLine();
							
							newSongArtist = Integer.parseInt(tempNewSongArtist);
							formatInt = true;
						} catch (NumberFormatException ex) {
							System.out.println("\nInvalid Input: not a number. Try again.");
							formatInt = false;
						}
					 }
					formatInt = false;
					while (formatInt == false) {
						try {
							System.out.println("Please input the SONG'S NEW LENGTH: ");
							String tempNewSongLength = input.nextLine();
		
							newSongLength = Integer.parseInt(tempNewSongLength);
							formatInt = true;
						} catch (NumberFormatException ex) {
							System.out.println("\nInvalid Input: not a number. Try again.");
							formatInt = false;
						}
					 }
		
					String query = "UPDATE song "
								 + "SET title = '" + newSongName + "', length = " + newSongLength + ", album_id = " + newSongAlbum + ", artist_id = " + newSongArtist + " "
								 + "WHERE song_id = " + songID + ";";
					
					try {
						state = connect.prepareStatement(query);
						
						if(state.executeUpdate() > 0) {
							System.out.println("\nSUCCESS! \nSONG: " + songName + " WAS UPDATED!");
						} else {
							System.out.println("SONG FAILED TO UPDATE");
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
				} else if (choice == '0') {
					DBC.closeDBConnection(connect);
					return;
				}
			}
		} else {
			System.out.println("\nSorry but we could not find that song!\nReturn you to the Song Menu.\n");
			DBC.closeDBConnection(connect);
			return;
		}
		DBC.closeDBConnection(connect);
	}

	protected void removeSong() {
		//SQL QUERIES NEEDS THESE OBJ
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();
		ResultSet result = null;
		PreparedStatement state = null;
		
		Scanner input = new Scanner(System.in);
		System.out.println("To remove a song we will need your song's name, album (if any), and the artist.");
		System.out.println("This will be used to find the correct song.");

		System.out.println("Please input the SONG'S NAME: ");
		String songName = input.nextLine();
		System.out.println("Please input the SONG'S ALBUM NAME (if none put 'NULL'): ");
		String songAlbum = input.nextLine();
		System.out.println("Please input the SONG'S ARTIST NAME: ");
		String songArtist = input.nextLine();

		if(checkSongInfo(songName, songAlbum, songArtist) == true) {
			System.out.println("If this is the correct song you want to REMOVE please input: "
					+ "\n1: TO REMOVE "
					+ "\n0: TO GO BACK TO MENU");
			
			char choice = 0;
			boolean choiceFound = false;
			while (!choiceFound) {
				try {
					String in = input.nextLine();
					if (in.length() > 1)
						throw new InputMismatchException();
					choice = in.charAt(0);
					if (choice < '0' || choice > '3')
						throw new InputMismatchException();
					choiceFound = true;
				} catch (InputMismatchException e) {
					System.out.println("Please enter a valid number (0-3)");
				}

				if (choice == '1') {
					int songID = 0;
					boolean formatInt = false;
					while (formatInt == false) {
						try {
							System.out.println("Please input the SONG'S ID (LISTED ABOVE): ");
							String tempSongID = input.nextLine();
							songID = Integer.parseInt(tempSongID);
							formatInt = true;
						} catch (NumberFormatException ex) {
							System.out.println("\nInvalid Input: not a number. Try again.");
							formatInt = false;
						}
					 }
					
					String query = "DELETE FROM song "
								 + "WHERE song_id = " + songID + ";";
					
					try {
						state = connect.prepareStatement(query);
						
						if(state.executeUpdate() > 0) {
							System.out.println("\nSUCCESS! \nSONG: " + songName + "WAS REMOVED!");
						} else {
							System.out.println("SONG FAILED TO UPDATE");
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
				} else if (choice == '0') {
					DBC.closeDBConnection(connect);
					return;
				}
			}
		} else {
			System.out.println("\nSorry but we could not find that song!\nReturn you to the Song Menu.\n");
			DBC.closeDBConnection(connect);
			return;
		}
		DBC.closeDBConnection(connect);
	}
	
	private boolean checkSongInfo(String songName, String songAlbum, String songArtist) {
		DatabaseConnection DBC = new DatabaseConnection();
		Connection connect = DBC.openDBConnection();
		
		ResultSet result = null;
		Statement state = null;
		boolean check = false;
		songAlbum = songAlbum.toUpperCase();
		String query;
		
		if (songAlbum.equals("NULL")) {
			 query = "SELECT song.song_id, song.title, song.length, album.album_id AS albumID, album.name AS album, artist.artist_id AS artistID, artist.name AS artist "
				   + "FROM song LEFT OUTER JOIN album ON song.album_id = album.album_id "
				   + "LEFT OUTER JOIN artist ON song.artist_id = artist.artist_id "
				   + "WHERE title = '" + songName + "' AND artist.name = '" + songArtist + "';";
		} else {
			 query = "SELECT song.song_id, song.title, song.length, album.album_id AS albumID, album.name AS album, artist.artist_id AS artistID, artist.name AS artist "
				   + "FROM song LEFT OUTER JOIN album ON song.album_id = album.album_id "
				   + "LEFT OUTER JOIN artist ON song.artist_id = artist.artist_id "
				   + "WHERE title = '" + songName + "' AND album.name = '" + songAlbum + "' AND artist.name = '" + songArtist + "';";
		}
	
		try {
            state = connect.createStatement();
            result = state.executeQuery(query);
			
			if(!result.isBeforeFirst()) {
				System.out.println("\nThere is no SONGS by that NAME.");
				check = false;
			} else {
				System.out.printf("\nSONG ID          SONG NAME          SONG LENGTH          ALBUM ID          ALBUM NAME                     ARTIST ID            ARTIST NAME           \n"
		                + "_______________________________________________________________________________________________________________________________________________________________\n");
        
				while (result.next()) {
						System.out.printf(" %-16s %-18s %-20s %-17s %-30s %-20s %-20s%n",
	                    		result.getInt("song_id"),
	                    		result.getString("title"),
	                    		result.getInt("length"),
	                    		result.getInt("albumID"),
	                    		result.getString("album"),
	                    		result.getInt("artistID"),
	                    		result.getString("artist"));
				}
				System.out.println("\n");
				check = true;
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
		return check;
	}
}