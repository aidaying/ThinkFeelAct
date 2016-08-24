package nz.ac.aut.rnd.team.thinkfeelactproject;

/**
 * Created by Aida on 12/05/2016.
 */
public class Survey {

    private int id=0;
    private String question="";
    private String type="";
    private String description="";

    public Survey() {
    }

    public Survey(int id, String question,String description) {
        this.id = id;
        this.question = question;
        this.description = description;
    }

    public int getId(){
        return this.id;
    }

    public void setId (int id){

        this.id = id;
    }

    public String getQuestion(){

        return this.question;
    }

    public void setQuestion(String question){

        this.question = question;
    }
    public void setType (String type){

        this.type = type;
    }
    public String getType(){

        return this.type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
