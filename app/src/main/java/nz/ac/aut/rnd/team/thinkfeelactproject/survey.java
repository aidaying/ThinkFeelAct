package nz.ac.aut.rnd.team.thinkfeelactproject;

/**
 * Created by Aida on 12/05/2016.
 */
public class survey {

    private int id;
    private String question;

    public survey() {
    }

    public survey(int id, String question) {
        this.id = id;
        this.question = question;
    }

    public int getId(){
        return this.id;
    }

    public void setId (int ID){
        this.id = id;
    }

    public String getQuestion(){
        return this.question;
    }

    public void setQuestion (String Question){
        this.question = question;
    }
}
