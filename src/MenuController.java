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
		.append("3: Update an Artist's Information\n")
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

			input.nextLine();

			try {
				if (choice == '1') {
					//SELECT input
					System.out.println("Please input an Artist's name:");
					String name = input.nextLine();

					if (name.length() < 1) throw new InputMismatchException();

					artist.displayArtistData(artist.getArtistInfoByName(name));
					artistMenu();
				} else if (choice == '2') {
					//INSERT input

					//get artist name
					System.out.println("Enter Artist name: ");
					String artistName = input.nextLine();
					//get artist's genre
					System.out.println("Enter Artist's genre: ");
					String genre = input.nextLine();

					artist.insertArtist(artistName, genre);
					artist.insertArtist(artistName, genre);
					artistMenu();
				} else if (choice == '3') {
					//UPDATE input
					System.out.println("Enter the name of the Artist you want to update: ");
					String originalName = input.nextLine();

					try {
						ResultSet rs = artist.getArtistInfoByName(originalName);
						if (!rs.isBeforeFirst()) {
							//If result is not found return to Menu
							System.out.println("Artist not found.");
							artistMenu();
						} else {
							artist.displayArtistData(rs);

							System.out.println("Enter updated name or leave blank if no change");
							String artistName = input.nextLine();

							System.out.println("Enter new genre or leave blank if no change");
							String artistGenre = input.nextLine();

							if (!artistName.trim().equals("")) artist.updateArtistName(originalName, artistName);
							if (!artistGenre.trim().equals("")) artist.updateArtistGenre(originalName, artistGenre);
							artistMenu();
						}
						rs.close();
					} catch (SQLException e) {
						System.out.println("Error while trying to make query.");
						e.printStackTrace();
					}
				} else if (choice == '4') {
					//DELETE input
					System.out.println("Enter Artist name for data:");
					String name = input.nextLine();

					//Display search for clarity
					artist.displayArtistData(artist.getArtistInfoByName(name));

					//get name to ensure it doesn't delete other artists
					System.out.println("Enter name of artist to DELETE:");
					String artistName = input.nextLine();

					artist.removeArtist(name, artistName);
					artistMenu();
				} else if (choice == '0') {
					mainMenu();
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid choice entered.");
				artistMenu();
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

			if (choice == '1') {
				album.getAlbumByName();
				albumMenu();
			} else if (choice == '2') {
				album.getAlbumByArtist();
				albumMenu();
			} else if (choice == '3') {
				album.insertAlbum();
				albumMenu();
			} else if (choice == '4') {
				album.updateAlbum();
				albumMenu();
			} else if (choice == '5') {
				album.removeAlbum();
				albumMenu();
			} else if (choice == '0') {
				mainMenu();
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