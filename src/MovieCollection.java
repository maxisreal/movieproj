import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class MovieCollection {
    private ArrayList<Movie> movies = new ArrayList<>();
    public MovieCollection(){
        loadWordsInto(movies);
        System.out.println(movies);
    }
    public static void loadWordsInto(ArrayList<Movie> movies) {
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
