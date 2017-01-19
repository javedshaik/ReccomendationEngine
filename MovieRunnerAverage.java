import java.util.ArrayList;
import java.io.*;
import java.util.*;
public class MovieRunnerAverage {
    public void printAverageRatings(){
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        sr.getMovieSize();
        sr.getRaterSize();
        int minimalRaters = 19;
        ArrayList<Rating> list = sr.getAverageRatings(minimalRaters);
        Collections.sort(list);
        for(Rating r: list) {
            System.out.println(r.getValue() + " " + sr.getTitle(r.getItem()));
       }
    }
    public void getAverageRatingOneMovie(){
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        int minimalRaters = 0;
        ArrayList<Rating> list = sr.getAverageRatings(minimalRaters);
        //String title = "Vacation";
        String title = "The Maze Runner";
        //String title = "Moneyball";
        String ID = sr.getID(title);
        for(Rating r: list) {
            if(r.getItem().equals(ID))
                System.out.println("The average rating for the movie: " + title + "is " + r.getValue());
        }
    }
}
    
    
