import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuController {
	protected void mainMenu() {
		StringBuilder builder = new StringBuilder();

		builder.append("\nWELCOME TO THE MAIN MENU\n")
				.append("__________________________________________________________________")
				.append("\nEnter the menu's number, which you want navigate to:\n")
				.append("1: Artist Menu\n")
				.append("2: Album Menu\n")
				.append("3: Song Menu\n")
				.append("4: Sample Menu\n")
				.append("5: User Menu\n")
				.append("0: TO EXIT");

		System.out.println(builder.toString());
		
		Scanner input = new Scanner(System.in);
		char choice = 0;
		boolean choiceFound = false;
		while (!choiceFound) {
			try {
				String in = input.next();
				if (in.length() > 1)
					throw new InputMismatchException();
				choice = in.charAt(0);
				if (choice < '0' || choice > '5')
					throw new InputMismatchException();
				choiceFound = true;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid number (0-5)");
			}

			if (choice == '1') {
				artistMenu();
			} else if (choice == '2') {
				albumMenu();
			} else if (choice == '3') {
				songMenu();
			} else if (choice == '4') {
				sampleMenu();
			} else if (choice == '5') {
				userMenu();
			} else if (choice == '0') {
				System.out.println("\nGood Bye! From Sample Beats");
				System.exit(0);
			}
		}
	}
	
	protected void artistMenu() {
		Artist artist = new Artist();
		StringBuilder builder = new StringBuilder();
		
		builder.append("\nWELCOME TO THE ARTIST MENU\n")
		.append("__________________________________________________________________")
		.append("\nEnter a designed option: \n")
		.append("1: Search for Artist Information\n")
		.append("2: Add new Artist\n")
		.append("3: Update an Artist's information\n")
		.append("4: Remove an Artist\n")
		.append("0: GO BACK");

		System.out.println(builder.toString());
		
		Scanner input = new Scanner(System.in);
		char choice = 0;
		boolean choiceFound = false;
		while (!choiceFound) {
			try {
				String in = input.next();
				if (in.length() > 1)
					throw new InputMismatchException();
				choice = in.charAt(0);
				if (choice < '0' || choice > '4')
					throw new InputMismatchException();
				choiceFound = true;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid number (0-4)");
			}

			if (choice == '1') {
				artist.getArtistInfoByName();
				artistMenu();
			} else if (choice == '2') {
				artist.insertArtist();
				artistMenu();
			} else if (choice == '3') {
				artist.updateArtist();
				artistMenu();
			} else if (choice == '4') {
				artist.removeArtist();
				artistMenu();
			} else if (choice == '0') {
				mainMenu();
			}
		}
	}
	
	protected void albumMenu() {

		Album album = new Album();
		StringBuilder builder = new StringBuilder();

		builder.append("\nWELCOME TO THE ALBUM MENU\n")
		.append("__________________________________________________________________")
		.append("\nEnter a designed option: \n")
		.append("1: Search for Album by NAME\n")
		.append("2: Search for Album by ARTIST\n")
		.append("3: Add new Album\n")
		.append("4: Update an Album's information\n")
		.append("5: Remove an Album\n")
		.append("0: GO BACK");
		
		System.out.println(builder.toString());
		
		Scanner input = new Scanner(System.in);
		char choice = 0;
		boolean choiceFound = false;
		while (!choiceFound) {
			try {
				String in = input.next();
				if (in.length() > 1)
					throw new InputMismatchException();
				choice = in.charAt(0);
				if (choice < '0' || choice > '5')
					throw new InputMismatchException();
				choiceFound = true;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid number (0-5)");
			}

			// reset buffer
			input.nextLine();

			try {
				if (choice == '1') {
					// get input
					System.out.println("Please enter an album name:");
					String name = input.nextLine();

					// quick validation
					if (name.length() < 1) throw new InputMismatchException();

					album.displayAlbumData(album.getAlbumByName(name));
					albumMenu();
				} else if (choice == '2') {

					// input
					System.out.println("Please enter an artist's name:");
					String artistName = input.nextLine();

					// validation
					if (artistName.length() < 1) throw new InputMismatchException();

					// make query, display results
					album.displayAlbumData(album.getAlbumByArtist(artistName));

					albumMenu();
				} else if (choice == '3') {

					// get album name
					System.out.println("Enter album name:");
					String albumName = input.nextLine();
					// get num_songs
					System.out.println("Enter number of songs:");
					String numSongs = input.nextLine();
					// get artist
					System.out.println("Enter the artist:");
					String artistName = input.nextLine();
					// assuming for now we are entering a valid artist
					// if artist not found, offer to add artist
					album.insertAlbum(albumName, numSongs, artistName);
					albumMenu();
				} else if (choice == '4') {

					// get album to be altered
					System.out.println("Enter the name of the album you want to alter:");
					String title = input.nextLine();

					try {
						ResultSet rs = album.getAlbumByName(title);
						if (!rs.isBeforeFirst()){
							System.out.println("Song not found.");
							albumMenu();
						} else {
							album.displayAlbumData(rs);
							System.out.println("Enter new name for album or leave blank if no change");
							String albumName = input.nextLine();
							System.out.println("Enter new number of songs or leave blank if no change");
							String numSongs = input.nextLine();
							System.out.println("Enter new artist or leave blank if no change");
							String artist = input.nextLine();
							if (!albumName.trim().equals("")) album.updateAlbumName(albumName);
							if (!numSongs.trim().equals("")) album.updateAlbumSongs(numSongs);
							if (!artist.trim().equals("")) album.updateAlbumArtist(artist);
							albumMenu();
						}
					} catch (SQLException e){
						System.out.println("Error while trying to make query.");
						e.printStackTrace();
					}
				} else if (choice == '5') {
					System.out.println("Enter album name:");
					String name = input.nextLine();
					album.removeAlbum(name);
					albumMenu();
				} else if (choice == '0') {
					mainMenu();
				}
			} catch (InputMismatchException e){
				System.out.println("Invalid choice entered.");
				albumMenu();
			}

		}
	}
	
	protected void songMenu() {
		Song song = new Song();
		StringBuilder builder = new StringBuilder();

		builder.append("\nWELCOME TO THE SONG MENU\n")
		.append("__________________________________________________________________")
		.append("\nEnter a designed option: \n")
		.append("1: Search for Song by NAME\n")
		.append("2: Search for Song by ALBUM\n")
		.append("3: Search for Song by ARTIST\n")
		.append("4: Add new Song\n")
		.append("5: Update an Song's information\n")
		.append("6: Remove an Song\n")
		.append("0: GO BACK");

		System.out.println(builder.toString());
		
		Scanner input = new Scanner(System.in);
		char choice = 0;
		boolean choiceFound = false;
		while (!choiceFound) {
			try {
				String in = input.next();
				if (in.length() > 1)
					throw new InputMismatchException();
				choice = in.charAt(0);
				if (choice < '0' || choice > '6')
					throw new InputMismatchException();
				choiceFound = true;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid number (0-6)");
			}

			if (choice == '1') {
				song.getSongByName();
				songMenu();
			} else if (choice == '2') {
				song.getSongByAlbum();
				songMenu();
			} else if (choice == '3') {
				song.getSongByArtist();
				songMenu();
			} else if (choice == '4') {
				song.insertSong();
				songMenu();
			} else if (choice == '5') {
				song.updateSong();
				songMenu();
			} else if (choice == '6') {
				song.removeSong();
				songMenu();
			} else if (choice == '0') {
				mainMenu();
			}
		}
	}
	
	protected void sampleMenu() {
		Sample sample = new Sample();
		StringBuilder builder = new StringBuilder();

		builder.append("\nWELCOME TO THE SAMPLE MENU\n")
		.append("__________________________________________________________________")
		.append("\nEnter a designed option: \n")
		.append("1: Search for SAMPLES' INFOMATION by Sample's description\n")
		.append("2: Search for SAMPLE'S SONG'S NAME by Sample's description\n")
		.append("3: Search for SAMPLE'S ALBUM (if one) by Sample's description\n")
		.append("4: Search for SAMPLE'S ARTIST'S NAME by Sample's description\n")
		.append("5: Add new Sample\n")
		.append("6: Update an Sample's information\n")
		.append("7: Remove an Sample\n")
		.append("0: GO BACK");

		System.out.println(builder.toString());
		
				Scanner input = new Scanner(System.in);
		char choice = 0;
		boolean choiceFound = false;
		while (!choiceFound) {
			try {
				String in = input.next();
				if (in.length() > 1)
					throw new InputMismatchException();
				choice = in.charAt(0);
				if (choice < '0' || choice > '7')
					throw new InputMismatchException();
				choiceFound = true;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid number (0-7)");
			}

			if (choice == '1') {
				sample.getSampleByDescription();
				sampleMenu();
			} else if (choice == '2') {
				sample.getSampleBySong();
				sampleMenu();
			} else if (choice == '3') {
				sample.getSampleByAlbum();
				sampleMenu();
			} else if (choice == '4') {
				sample.getSampleByArtist();
				sampleMenu();
			} else if (choice == '5') {
				sample.insertSample();
				sampleMenu();
			} else if (choice == '6') {
				sample.updateSample();
				sampleMenu();
			} else if (choice == '7') {
				sample.removeSample();
				sampleMenu();
			} else if (choice == '0') {
				mainMenu();
			}
		}
	}
	
	protected void userMenu() {
		User user = new User();
		StringBuilder builder = new StringBuilder();

		builder.append("\nWELCOME TO THE USER MENU\n")
		.append("__________________________________________________________________")
		.append("\nEnter a designed option: \n")
		.append("1: Update your USER information\n")
		.append("2: Remove your USER\n")
		.append("0: GO BACK");

		System.out.println(builder.toString());
		
		Scanner input = new Scanner(System.in);
		char choice = 0;
		boolean choiceFound = false;
		while (!choiceFound) {
			try {
				String in = input.next();
				if (in.length() > 1)
					throw new InputMismatchException();
				choice = in.charAt(0);
				if (choice < '0' || choice > '2')
					throw new InputMismatchException();
				choiceFound = true;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid number (0-2)");
			}

			if (choice == '1') {
				user.updateUser();
				userMenu();
			} else if (choice == '2') {
				if (user.removeUser() == true) {
					System.out.println("\n\nReturning to start up and login menu");
					Main.startup();
				} else {
					userMenu();
				}
			} else if (choice == '0') {
				mainMenu();
			}
		}
	}
}