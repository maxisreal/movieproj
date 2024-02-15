import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class MovieCollection {
    private ArrayList<Movie> movies = new ArrayList<>();
    private final Scanner scan = new Scanner(System.in);
    public MovieCollection(){
        loadWordsInto(movies);
        start();
    }
    private void start(){
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scan.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                //searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }
    private String searchTitles(){
        System.out.print("Type any title: ");
        String title = scan.nextLine();
        String returnString = "";
        int a = 1;
        for (Movie movie : movies){
            if (movie.toString().contains(title)){
                returnString += a + ". " + movie.toString() + "\n";
                a++;
            }
        }
        return returnString;
    }

    private static void loadWordsInto(ArrayList<Movie> movies) {
        try {
            Scanner input = new Scanner(new File("src\\movies_data.csv"));
            input.nextLine();
            while (input.hasNext()) {
                String data = input.nextLine();
                String[] splitData = data.split(",");
                Movie m1 = new Movie(splitData[0], splitData[1], splitData[2], splitData[3], Double.parseDouble(splitData[5]), Integer.parseInt(splitData[4]));
                movies.add(m1);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
