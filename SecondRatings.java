import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies("data/ratedmoviesfull.csv");
        myRaters = fr.loadRaters("data/ratings.csv");
    }
    public int getMovieSize(){
        int num = myMovies.size();
        System.out.println("The number of movies that were read in and stored in the ArrayList of type Movie: " + num);
        return num;
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
    public String getTitle(String id){
        
        for(Movie m: myMovies){
            if(m.getID().equals(id)){
                return m.getTitle(); 
            }
                   
           
        }
        return "The" + id + "ID was not found";
    }
    public String getID(String title){
        for(Movie m: myMovies){
            if(m.getTitle().equals(title)){
                return m.getID();
            }
                    
        
        }
         return "NO SUCH TITLE";
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
    ArrayList<Rating> list = new ArrayList<Rating>();
    for(Movie m: myMovies){
        double id = getAverageID(m.getID(), minimalRaters);
        if(id!=0){
            Rating a = new Rating(m.getID(),id);
            list.add(a);
        }
    }
    return list;
    }
    
}

