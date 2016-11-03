package nz.ac.aut.rnd.team.thinkfeelactproject.java;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

/**
 * Created by Robert on 3/11/2016.
 */
public class ExerciseItemTest {

    private ExerciseItem exerciseItem;

    @Before
    public void setUp() throws Exception {
        this.exerciseItem = new ExerciseItem();
    }

    @After
    public void tearDown() throws Exception {
        this.exerciseItem = null;
    }

    @Test
    public void getName() throws Exception {
        this.exerciseItem.setName("a");
        assertEquals("a", this.exerciseItem.getName());
    }

    @Test
    public void setName() throws Exception {
        this.exerciseItem.setName("b");
        assertEquals("b", this.exerciseItem.getName());
    }

    @Test
    public void getImage() throws Exception {
        this.exerciseItem.setImage("c");
        assertEquals("c", this.exerciseItem.getImage());
    }

    @Test
    public void setImage() throws Exception {
        this.exerciseItem.setImage("d");
        assertEquals("d", this.exerciseItem.getImage());
    }

}