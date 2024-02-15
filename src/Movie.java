public class Movie {
    private String title;
    private String cast;
    private String director;
    private String blurb;
    private double rating;
    private int runtime;

    public Movie(String a, String b, String c, String d, double e, int f){
        title = a;
        cast = b;
        director = c;
        blurb = d;
        rating  = e;
        runtime = f;
    }
    public String getTitle(){
        return title;
    }
    public String getCast(){
        return cast;
    }
    public String getDirector(){
        return director;
    }
    public String getOverview(){
        return blurb;
    }
    public double getRating(){
        return rating;
    }
    public int getRuntime(){
        return runtime;
    }

}
