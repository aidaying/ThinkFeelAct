package nz.ac.aut.rnd.team.thinkfeelactproject.java;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import nz.ac.aut.rnd.team.thinkfeelactproject.java.Event;

import static org.junit.Assert.*;

/**
 * Created by Robert on 9/08/2016.
 */
public class EventTest {

    private Event event;

    @Before
    public void setUp() throws Exception {
        event = new Event();
        event.setDate("111");
        event.setID(1);
        event.setName("aaa");
        event.setRating(1.0);
    }

    @After
    public void tearDown() throws Exception {
        event = null;
    }

    @Test
    public void testGetID() throws Exception {
        assertEquals(1, event.getID());
    }

    @Test
    public void testSetID() throws Exception {
        event.setID(2);
        assertEquals(2, event.getID());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("aaa", event.getName());
    }

    @Test
    public void testSetName() throws Exception {
        event.setName("bbb");
        assertEquals("bbb", event.getName());
    }

    @Test
    public void testGetDate() throws Exception {
        assertEquals("111", event.getDate());
    }

    @Test
    public void testSetDate() throws Exception {
        event.setDate("222");
        assertEquals("222", event.getDate());
    }

    @Test
    public void testGetRating() throws Exception {
        assertEquals(1.0, event.getRating());
    }

    @Test
    public void testSetRating() throws Exception {
        event.setRating(2.0);
        assertEquals(2.0, event.getRating());
    }
}