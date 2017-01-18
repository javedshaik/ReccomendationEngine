
/**
 * Write a description of FirstRatings here.
 * 
 * @author (Javed Shaik) 
 * @version (1/15/2017)
 */
import edu.duke.*;
import java.util.*;
import java.util.Map;
import java.util.ArrayList;
import org.apache.commons.csv.*;

public class FirstRatings {
	public ArrayList<Movie> loadMovies(String filename) {
		ArrayList<Movie> info = new ArrayList<Movie>();
		FileResource csvData = new FileResource(filename);
		CSVParser parser = csvData.getCSVParser();
		for (CSVRecord record : parser) {
			Movie movie = new Movie(record.get("id"), record.get("title"), record.get("year"), record.get("genre"),
					record.get("director"), record.get("country"), record.get("poster"),
					Integer.parseInt(record.get("minutes")));
			;
			info.add(movie);
		}
		return info;
	}

	public void testLoadMovies() {
		ArrayList<Movie> al = loadMovies("data/ratedmovies_short.csv");
		;
		// System.out.print(al+"\n");
		// System.out.println("There are a total of: " + al.size() + " in this
		// file");
		genre(al, "Comedy");
		movieLength(al, 150);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (Movie list : al) {
			if (map.containsKey(list.getDirector())) {
				map.put(list.getDirector(), map.get(list.getDirector() + 1));
			} else {
				map.put(list.getDirector(), 1);
			}
		}
		int max = Collections.max(map.values());
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (pair.getValue().equals(max))
				System.out.println(
						pair.getKey() + " directed the most movies. The number of movies directed are : " + max);
		}

	}

	public void genre(ArrayList<Movie> list, String genre) {
		int count = 0;
		for (Movie movie : list) {
			if (movie.getGenres().contains(genre)) {
				count++;
			}
		}
		System.out.println("Number of movies in: " + genre + " are: " + count);
	}

	public void movieLength(ArrayList<Movie> list, Integer minutes) {
		int count = 0;
		for (Movie movie : list) {
			if (movie.getMinutes() > 150) {
				count++;
			}
		}
		System.out.println("Number of movies greater than 150 minutes: " + count);
	}

	public ArrayList<Rater> loadRaters(String filename) {
		ArrayList<Rater> info = new ArrayList<Rater>();
		FileResource csvData = new FileResource(filename);
		CSVParser parser = csvData.getCSVParser();
		for (CSVRecord record : parser) {
			if (!record.get(0).equals("rater_id")) {
				String i = record.get(1);
				String id = record.get(0);
				double val = Double.parseDouble(record.get(2));

				if (info.size() == 0) {
					Rater rate = new Rater(id);
					rate.addRating(i, val);
					info.add(rate);
				} else {
					List<Rater> value = new ArrayList<Rater>(info);
					Iterator<Rater> iter = value.iterator();
					while (iter.hasNext()) {
						Rater j = iter.next();

						if (j.getID().equals(id)) {
							j.addRating(i, val);
							break;
						} else if (!iter.hasNext()) {
							Rater rate = new Rater(id);
							rate.addRating(i, val);
							info.add(rate);
							break;
						}
					}
				}
			}

		}
		return info;
	}

	public void testLoadRaters() {
		ArrayList<Rater> placeHolder = loadRaters("data/ratings_short.csv");
		System.out.println(Arrays.deepToString(placeHolder.toArray()));
		System.out.println("The total # of raters: " + placeHolder.size());

		findNumOfRatingsPartMovie(placeHolder, "1798709");

		String movieId = "2";
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Set<String> setM = new HashSet<String>();

		for (Rater rt : placeHolder) {
			ArrayList<String> list = rt.getItemsRated();
			for (String s : list) {
				if (!setM.contains(s)) {
					setM.add(s);
				}

			}
			if (rt.getID().equals(movieId))
				System.out.println("Rater ID " + movieId + " has " + rt.numRatings() + " ratings");
			map.put(rt.getID(), rt.numRatings());
		}
		System.out.println(setM.size() + " movies have been rated by a total of: " + placeHolder.size() + " raters");

		int max = Collections.max(map.values());
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (pair.getValue().equals(max))
				System.out.println("This ID: " + pair.getKey() + " has the most # of ratings which is: " + max);
		}
	}

	public void findNumOfRatingsPartMovie(ArrayList<Rater> r, String s) {
		int count = 0;
		for (Rater list : r) {
			if (list.hasRating(s)) {
				count++;
			}
		}
		System.out.println("The number of ratings for a particular movie: " + count);

	}
}
