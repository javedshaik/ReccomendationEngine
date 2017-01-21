import java.util.*;

public class ThirdRatings {
    
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    public ThirdRatings(String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters("data/ratings.csv");
    }
    public int getRaterSize(){
        int num = myRaters.size();
        System.out.println("The number of raters that were read in and stored in the ArrayList of type Rater: " + num);
        return num;
    }
    private double getAverageID(String movieId, int minimalRaters){
        double avg = 0;
        double total = 0;
        int count = 0;
        for(Rater r: myRaters){
            if(r.hasRating(movieId)){
                count++;
                total += r.getRating(movieId);
            }
        }
        if(count >= minimalRaters){
            avg = total/count;
            return avg;
        }
        else{
            return 0.0;
        }
    }
      
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
    ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    ArrayList<Rating> list = new ArrayList<Rating>();
    for(String m: movies){
        double id = getAverageID(m, minimalRaters);
        if(id!=0){
            Rating a = new Rating(m,id);
            list.add(a);
        }
    }
    return list;
    }
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter fc){
        ArrayList<String> movies = MovieDatabase.filterBy(fc);
        ArrayList<Rating> r = new ArrayList<Rating>();
        for(String id: movies) {
    		double av = getAverageID(id,minimalRaters);
    		if(av!=0){
    			Rating ral = new Rating(id, av);
    			r.add(ral);
    		}
    	}
    	return r;
    }
    }
    


