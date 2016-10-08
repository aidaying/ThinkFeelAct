package nz.ac.aut.rnd.team.thinkfeelactproject.java;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Robert on 29/09/2016.
 */
public class LongTermSurveyTest {

    private LongTermSurvey longTermSurvey;

    @Before
    public void setup(){
        longTermSurvey = new LongTermSurvey("T", 5.0, 1);
        longTermSurvey.setID(1);
    }

    @Test
    public void testSetID() throws Exception {
        longTermSurvey.setID(2);
        assertEquals(2, longTermSurvey.getID());
    }

    @Test
    public void testSetAnswerTF() throws Exception {
        longTermSurvey.setAnswerTF("F");
        assertEquals("F", longTermSurvey.getAnswerTF());
    }

    @Test
    public void testSetRating() throws Exception {
        longTermSurvey.setRating(5.1);
        assertEquals(5.1, longTermSurvey.getRating(), 0);
    }

    @Test
    public void testSetQuestionId() throws Exception {
        longTermSurvey.setQuestionId(2);
        assertEquals(2, longTermSurvey.getQuestionId());
    }

    @Test
    public void testGetID() throws Exception {
        assertEquals(1, longTermSurvey.getID());
    }

    @Test
    public void testGetAnswerTF() throws Exception {
        assertEquals("T", longTermSurvey.getAnswerTF());
    }

    @Test
    public void testGetRating() throws Exception {
        assertEquals(5.0, longTermSurvey.getRating(), 0);
    }

    @Test
    public void testGetQuestionId() throws Exception {
        assertEquals(1, longTermSurvey.getQuestionId());
    }
}