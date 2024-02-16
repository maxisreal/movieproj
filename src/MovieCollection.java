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
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }
    private void searchCast(){
        System.out.print("Who are you looking for? -> ");
        String search = scan.nextLine().toLowerCase();
        ArrayList<Movie> movielist = new ArrayList<>();
        String returnString = "";
        for (Movie movie : movies){
            String[] names = movie.getCast().split(", ");
            for (String name : names){
                if (name.toLowerCase().contains(search)){
                    movielist.add(movie);
                    returnString += name + "\n";
                }
            }
        }
        String[] names = returnString.split("\n");
        insertionSortArray(names);
        returnString = "";
        int a = 1;
        for (String name : names) {
            if (!returnString.contains(name)){
                returnString += a + ". " + name + "\n";
                a++;
            }
        }
        if (returnString.equals("")){
            returnString = "No people found.";
        }
        System.out.println(returnString);
        insertionSort(movielist);
        if (!returnString.equals("No people found.")) {
            System.out.print("Which person do you want to see the movies of? -> ");
            a = scan.nextInt()-1;
            scan.nextLine();
            if (a < movielist.size() && movielist.get(a) != null) {
                int count = 1;
                for (Movie movie : movielist){
                    if (movie.getCast().toLowerCase().contains(names[a].toLowerCase()))
                    System.out.println(count + ". " + movie.getTitle());
                    count++;
                }
            }
            System.out.print("Which movie interests you?\nEnter number: ");
            a = scan.nextInt();
            scan.nextLine();
            if (a < movielist.size() && movielist.get(a - 1) != null) {
                System.out.println(movielist.get(a - 1).getInfo());
            } else {
                System.out.println("Movie not found.");
            }
        }
        System.out.println("Press ENTER ");
        scan.nextLine();
    }
    private void insertionSortArray(String[] words){
        for (int i = 1; i<words.length; i++){
            String temp = words[i];
            int a = i;
            while (a>0 && temp.compareTo(words[a-1]) < 0) {
                words[a] = words[a-1];
                a--;
            }
            words[a] = temp;
        }

    }
    private void searchTitles(){
        System.out.print("Type any title: ");
        String title = scan.nextLine().toLowerCase();
        String returnString = "";
        ArrayList<Movie> movielist = new ArrayList<>();
        for (Movie movie : movies){
            if (movie.getTitle().toLowerCase().contains(title)){
                movielist.add(movie);
            }
        }
        int a = 1;
        insertionSort(movielist);
        for (Movie movie : movielist){
            returnString += a + ". " + movie.getTitle() + "\n";
            a++;
        }
        if (returnString.equals("")){
            returnString = "No movies found.";
        }
        System.out.println(returnString);
        if (!returnString.equals("No movies found.")) {
                System.out.print("Which movie interests you?\nEnter number: ");
                a = scan.nextInt();
                scan.nextLine();
                if (a < movielist.size() && movielist.get(a - 1) != null) {
                    System.out.println(movielist.get(a - 1).getInfo());
                } else {
                    System.out.println("Movie not found.");
                }
        }
        System.out.println("Press ENTER ");
        scan.nextLine();
    }
    private void insertionSort(ArrayList<Movie> elements) {
        // TODO: Part A: implement me using an outer FOR loop and an inner WHILE loop
        for (int i = 0; i<elements.size(); i++){
            int a = 0;
            while (a<i) {
                if (elements.get(i).getTitle().compareTo(elements.get(a).getTitle())<0) {
                    Movie temp = elements.get(i);
                    elements.set(i, elements.get(a));
                    elements.set(a, temp);
                }
                a++;
            }
        }
    }


    private static void loadWordsInto(ArrayList<Movie> movies) {
        try {
            Scanner input = new Scanner(new File("src\\movies_data.csv"));
            input.nextLine();
            while (input.hasNext()) {
                String data = input.nextLine();
                String[] splitData = data.split(",");
                String cast = splitData[1];
                while (cast.contains("|")){
                    cast = cast.substring(0, cast.indexOf("|")) + ", " + cast.substring(cast.indexOf("|") + 1);
                }
                Movie m1 = new Movie(splitData[0], cast, splitData[2], splitData[3], Double.parseDouble(splitData[5]), Integer.parseInt(splitData[4]));
                movies.add(m1);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
