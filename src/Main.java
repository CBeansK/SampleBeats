import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        displayBaseMenu();
        Scanner input = new Scanner(System.in);

        // get menu choice
        char choice = 0;
        boolean choiceFound = false;
        while(!choiceFound){
            try{
                String in = input.next();
                if (in.length() > 1) throw new InputMismatchException();
                choice = in.charAt(0);
                if (choice < '1' || choice > '6') throw new InputMismatchException();
                choiceFound = true;
            } catch (InputMismatchException e){
                System.out.println("Please enter a valid number (1-6)");
            }
        }

        switch(choice){
            case 1:
                searchSongMenu(input);
                break;
            case 2:
                insertSongMenu(input);
                break;
            case 3:
                addSampleMenu(input);
                break;
            case 4:
                deleteSongMenu(input);
                break;
            case 5:
                getSongsByLengthMenu(input);
                break;
            case 6:
                findSamplesByArtistMenu(input);
                break;
        }
	    // display choices:
        /*
           1. Search for song
                - ask for song title
                - execute query
                - display results
           2. Insert new song
                - ask for title
                - ask for length
                - ask for album
                - if album already exists, use artist from album
                    - else, ask for artist
                - execute insert query
                - if successful, display song data
                    - else display error
           3. Add sample to song
                - pick song to add sample to
                - pick song to add as sample
                - TODO: flesh this out more
           4. Delete song
                - ask for song title
                - get song
                - display results and confirm deletion
                - delete song, display success or failure


           -------- Special Queries --------
           5. Get all songs longer than a certain length
                - ask for length
                - execute query
                - display results
           6. Get samples from songs by a certain artist
                - ask for artist
                - execute query
                - display results
         */
    }

    static void displayBaseMenu(){
        StringBuilder builder = new StringBuilder();

        builder.append("Enter a choice:\n")
            .append("1: Search a song\n")
            .append("2: Insert new song\n")
            .append("3: Add sample to song\n")
            .append("4. Delete song\n")
            .append("5. Get songs longer than specified length\n")
            .append("6. Find samples used by a specified artist\n");

        System.out.println(builder.toString());
    }

    // TODO: Implement
    static void searchSongMenu(Scanner input){

    }

    // TODO: Implement
    static void insertSongMenu(Scanner input){

    }

    // TODO: Implement
    static void addSampleMenu(Scanner input){

    }

    // TODO: Implement
    static void deleteSongMenu(Scanner input){

    }

    // TODO: Implement
    static void getSongsByLengthMenu(Scanner input){

    }

    // TODO: Implement
    static void findSamplesByArtistMenu(Scanner input){

    }
}
