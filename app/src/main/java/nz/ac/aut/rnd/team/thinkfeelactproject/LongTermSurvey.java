package nz.ac.aut.rnd.team.thinkfeelactproject;

/**
 * Created by Aida on 20/05/2016.
 */
public class LongTermSurvey {
    private int ID;
    private String answerTF;
    private int rating;
    private String firstTimeUser;
    private String questionId;


    public LongTermSurvey(String answerTF, int rating, String firstTimeUser, String questionId) {
        this.answerTF = answerTF;
        this.rating = rating;
        this.firstTimeUser = firstTimeUser;
        this.questionId = questionId;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFirstTimeUser() {
        return firstTimeUser;
    }

    public void setFirstTimeUser(String firstTimeUser) {
        this.firstTimeUser = firstTimeUser;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
