package nz.ac.aut.rnd.team.thinkfeelactproject.java;

/**
 * Created by Aida on 20/05/2016.
 */
public class LongTermSurvey {
    private int ID;
    private String answerTF;
    private String question;
    private Double rating;
    private String description;



    public LongTermSurvey() {

    }

    public LongTermSurvey(int id,String answerTF, Double rating) {
        this.ID = id;
        this.answerTF = answerTF;
        this.rating = rating;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAnswerTF() {
        return answerTF;
    }

    public void setAnswerTF(String answerTF) {
        this.answerTF = answerTF;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
