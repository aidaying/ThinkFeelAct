package nz.ac.aut.rnd.team.thinkfeelactproject.java;

/**
 * Created by Aida on 20/05/2016.
 */
public class Event {

    private int ID;
    private String name;
    private String date;
    private double rating;

    public Event(){


    }

    public Event(String name, String date, double rating) {
        this.name = name;
        this.date = date;
        this.rating = rating;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
