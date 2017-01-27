import java.util.*;
public class FourthRatings {
    private double getAverageID(String Id, int minimalRaters){
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        double avg = 0;
        double total = 0;
        int count = 0;
        for(Rater r: myRaters){
            if(r.hasRating(Id)){
                count++;
                total += r.getRating(Id);
            }
        }
        if(count >= minimalRaters){
            avg = total/count;
            return avg;
        }
       return 0;
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
    public double dotProduct(Rater me, Rater r){
        double product = 0.0;
        ArrayList<String> rt = me.getItemsRated();
        for(String id: rt){
            if(r.hasRating(id)){
                double ratingMe = me.getRating(id);
                double ratingR = me.getRating(id);
                ratingMe -= 5;
                ratingR -= 5; 
                product += ratingMe * ratingR;
            }
        }
        return product;
    }
     public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
    	ArrayList<Rating> aL = new ArrayList<Rating>();
    	ArrayList<Rating> list = getSimilarities(id);	
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());	
    	
	    for (String mID : movies) {
        	double weightAvg = 0;
        	double sum = 0;
        	int count= 0;
        	
	    	for (int i = 0; i < numSimilarRaters; i++) {
	    		Rating r = list.get(i);
	    		double weight = r.getValue();
	    		String Rid = r.getItem();
	    		Rater myRater = RaterDatabase.getRater(Rid);
	    		
	    		if(myRater.hasRating(mID)) {
	    			count++;
	    			sum += weight * myRater.getRating(mID);
	    		}
	    		
	    	}
	    	
	    	if (count >= minimalRaters) {
	    		weightAvg = sum / count;
	    		aL.add(new Rating(mID, weightAvg));
			}			
	    }
		Collections.sort(aL, Collections.reverseOrder());
		return aL;		
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
    	ArrayList<Rating> aL = new ArrayList<Rating>();
    	ArrayList<Rating> list = getSimilarities(id);	
    	ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
	    for (String mID : movies) {
        	double weightAvg = 0;
        	double sum = 0;
        	int count = 0;
	    	for (int i = 0; i < numSimilarRaters; i++) {
	    		Rating r = list.get(i);
	    		double weight = r.getValue();
	    		String Rid = r.getItem();
	    		Rater myRater = RaterDatabase.getRater(Rid);
	    		if(myRater.hasRating(mID)) {
	    			count++;
	    			sum += weight * myRater.getRating(mID);
	    		}
	    		
	    	}
	    	if (count >= minimalRaters) {
	    		weightAvg = sum / count;
	    		aL.add(new Rating(mID, weightAvg));
			}			
	    }
		Collections.sort(aL, Collections.reverseOrder());
		return aL;	
    }

     private ArrayList<Rating> getSimilarities(String id) {
    	ArrayList<Rating> list = new ArrayList<Rating>();
    	Rater n = RaterDatabase.getRater(id);
    	
    	for(Rater r: RaterDatabase.getRaters()) {
    		if(!r.equals(n)) {
    			double total = dotProduct(n, r);
    			if (total > 0)
    				list.add(new Rating(r.getID(), total));
    		}
    	}
    	
    	Collections.sort(list, Collections.reverseOrder());
    	return list;
    }
    
}
