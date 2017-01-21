
import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
	
	public void printAverageRatings() {
		ThirdRatings tr = new ThirdRatings("data/ratings.csv");
		MovieDatabase.initialize("data/ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
	    int minimalRaters = 35;
	    ArrayList<Rating> list = tr.getAverageRatings(minimalRaters);
	    Collections.sort(list);
	    for(Rating r: list) {
	    	System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
	    }
	    System.out.println(list.size());
	}
	
	public void printAverageRatingsByYear() {
		ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
	    
	    MovieDatabase.initialize("data/ratedmovies_short.csv");
	    
	    int minimalRaters = 20;
	    int year = 2000;
	    Filter fc = new YearAfterFilter(year);
	    ArrayList<Rating> list = tr.getAverageRatingsByFilter(minimalRaters, fc);
	    
	    Collections.sort(list);
	    for(Rating r: list) {
	    	System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " +  MovieDatabase.getTitle(r.getItem()));
	    }		
	    System.out.println(list.size());
	}
	
	public void printAverageRatingsByGenre() {
		ThirdRatings tr = new ThirdRatings("data/ratings.csv");
	    
	    MovieDatabase.initialize("data/ratedmoviesfull.csv");
	    
	    int minimalRaters = 20;
	    String genre = "Comedy";
	    Filter fc = new GenreFilter(genre);
	    ArrayList<Rating> list = tr.getAverageRatingsByFilter(minimalRaters, fc);
	    
	    Collections.sort(list);
	    for(Rating r: list) {
	    	System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()) + "\n    " + MovieDatabase.getGenres(r.getItem()));
	    }	
	    System.out.println(list.size());
	}
	
	public void printAverageRatingsByMinutes() {
		ThirdRatings tr = new ThirdRatings("data/ratings.csv");
	    
	    MovieDatabase.initialize("data/ratedmoviesfull.csv");
	    
	    int minimalRaters = 5;
	    int min = 105;
	    int max = 135;
	    Filter fc = new MinutesFilter(min, max);
	    ArrayList<Rating> list = tr.getAverageRatingsByFilter(minimalRaters, fc);
	    
	    Collections.sort(list);
	    for(Rating r: list) {
	    	System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
	    }		
	    System.out.println(list.size());
	}
	
	public void printAverageRatingsByDirectors() {
		ThirdRatings tr = new ThirdRatings("data/ratings.csv");
	    
	    MovieDatabase.initialize("data/ratedmoviesfull.csv");
	    
	    int minimalRaters = 4;
	    String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"; 
	    Filter fc = new DirectorsFilter(directors);
	    ArrayList<Rating> list = tr.getAverageRatingsByFilter(minimalRaters, fc);
	    
	    Collections.sort(list);
	    for(Rating r: list) {
	    	System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()) + "\n    " + MovieDatabase.getDirector(r.getItem()));
	    }	
	    System.out.println(list.size());
	}
	
	public void printAverageRatingsByYearAfterAndGenre() {
		ThirdRatings tr = new ThirdRatings("data/ratings.csv");
	    
	    MovieDatabase.initialize("data/ratedmoviesfull.csv");
	    
	    int minimalRaters = 8;
	    int year = 1990;
	    String genre = "Drama";
	    AllFilters fc = new AllFilters();
	    fc.addFilter(new YearAfterFilter(year));
	    fc.addFilter(new GenreFilter(genre));
	    ArrayList<Rating> list = tr.getAverageRatingsByFilter(minimalRaters, fc);
	    
	    Collections.sort(list);
	    for(Rating r: list) {
	    	System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()) + "\n    " + MovieDatabase.getGenres(r.getItem()));
	    }		
	    System.out.println(list.size());
	}
	
	public void printAverageRatingsByDirectorsAndMinutes() {
		ThirdRatings tr = new ThirdRatings("data/ratings.csv");
	    
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    
	    int minimalRaters = 3;
	    int min = 90;
	    int max = 180;
	    String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
	    AllFilters fc= new AllFilters();
	    fc.addFilter(new DirectorsFilter(directors));
	    fc.addFilter(new MinutesFilter(min, max));
	    ArrayList<Rating> list = tr.getAverageRatingsByFilter(minimalRaters, fc);
	    
	    Collections.sort(list);
	    for(Rating r: list) {
	    	System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()) + "\n    " + MovieDatabase.getDirector(r.getItem()));
	    }	
	    System.out.println(list.size());
	}
}