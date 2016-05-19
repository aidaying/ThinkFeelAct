package nz.ac.aut.rnd.team.thinkfeelactproject;

/**
 * Created by Aida on 12/05/2016.
 */
public class Survey {

    private int id=0;
    private String question="";
    private String type="";

    public Survey() {
    }

    public Survey(String question,String type) {
        this.question = question;
        this.type = type;
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
    public void setType (String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
}
