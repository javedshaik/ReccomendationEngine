import java.util.*;
public class MovieRunnerSimilarRatings {
    public void printSimilarRatings() {
        RaterDatabase.initialize("data/ratings.csv");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        
        FourthRatings fr = new FourthRatings();
        int minimalRaters = 35;
        String raterID = "25";
        int numSimilarRaters = 5;
        ArrayList<Rating> list = fr.getSimilarRatings(raterID, numSimilarRaters, minimalRaters);
      
        for(Rating r: list) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println(list.size());
    }
    public void printSimilarRatingsByGenre() {
        RaterDatabase.initialize("ratings.csv ");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        FourthRatings fr = new FourthRatings();
        String raterID = "65";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        String genre = "Action";
        
        Filter filterCriteria = new GenreFilter(genre);
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
        System.out.println(ratings.size());
    }
    
    public void printSimilarRatingsByDirector() {
        RaterDatabase.initialize("ratings.csv ");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        FourthRatings fr = new FourthRatings();
        String raterID = "1034";
        int numSimilarRaters = 10;
        int minimalRaters = 3;
        String directors = "Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone"; 
        Filter filterCriteria = new DirectorsFilter(directors);
        
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
        System.out.println(ratings.size());
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        RaterDatabase.initialize("ratings.csv ");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        FourthRatings fr = new FourthRatings();
        String raterID = "65";
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        String genre = "Adventure";
        int min = 100;
        int max = 200;
        
        AllFilters fc = new AllFilters();
        fc.addFilter(new GenreFilter(genre));
        fc.addFilter(new MinutesFilter(min, max));
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, fc);
        
        System.out.println(ratings.size());
        
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes() {
        RaterDatabase.initialize("ratings.csv ");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        FourthRatings fr = new FourthRatings();
        String raterID = "65";
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        int year = 2000;    
        int min = 80;
        int max = 100;
        
        AllFilters fc = new AllFilters();
        fc.addFilter(new YearAfterFilter(year));
        fc.addFilter(new MinutesFilter(min, max));
        
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, fc);
        System.out.println(ratings.size());
        
    }
}
