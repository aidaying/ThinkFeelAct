package nz.ac.aut.rnd.team.thinkfeelactproject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Robert on 9/08/2016.
 */
public class surveyTest {

    private survey survey;

    @Before
    public void setUp() throws Exception {
        survey = new survey();
        survey.setId(1);
        survey.setQuestion("www");
        survey.setType("aaa");
    }

    @After
    public void tearDown() throws Exception {
        survey = null;
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals(1, survey.getId());
    }

    @Test
    public void testSetId() throws Exception {
        survey.setId(2);
        assertEquals(2, survey.getId());
    }

    @Test
    public void testGetQuestion() throws Exception {
        assertEquals("www", survey.getQuestion());
    }

    @Test
    public void testSetQuestion() throws Exception {
        survey.setQuestion("vvv");
        assertEquals("vvv", survey.getQuestion());
    }

    @Test
    public void testSetType() throws Exception {
        assertEquals("aaa", survey.getType());
    }

    @Test
    public void testGetType() throws Exception {
        survey.setType("bbb");
        assertEquals("bbb", survey.getType());
    }
}