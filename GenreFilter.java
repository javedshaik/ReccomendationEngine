public class GenreFilter implements Filter {
    private String myGenre;
    public GenreFilter(String genre){
        this.myGenre = genre; 
    }
    @Override 
    public boolean satisfies(String id) {
		if (MovieDatabase.getGenres(id).contains(myGenre)){
		    return true;
		}
		
		    return false;
	}
}
