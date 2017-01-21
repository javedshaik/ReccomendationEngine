import java.util.*;
public class DirectorsFilter implements Filter {
   private String[] myDirector;
   public DirectorsFilter(String directors){
       this.myDirector = directors.split(",");
   }
   public boolean satisfies(String id){
       for(String s: myDirector){
           if(MovieDatabase.getDirector(id).contains(s)){
               return true;
            }
        }
        return false;
   }
}
