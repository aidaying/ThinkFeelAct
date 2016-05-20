package nz.ac.aut.rnd.team.thinkfeelactproject;

/**
 * Created by Aida on 20/05/2016.
 */
public class Event {

    private int ID;
    private String name;
    private String date;
    private int rating;

    public Event(String name, String date, int rating) {
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
