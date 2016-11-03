package nz.ac.aut.rnd.team.thinkfeelactproject.java;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

/**
 * Created by Robert on 2/11/2016.
 */
public class DescTest{

    private Desc desc;

    @Before
    public void setUp() throws Exception {
        this.desc = new Desc("a",  "b", 1);
    }

    @After
    public void tearDown() throws Exception {
        this.desc = null;
    }

    @Test
    public void setId() throws Exception {
        this.desc.setId(2);
        assertEquals(2, this.desc.getId());
    }

    @Test
    public void getId() throws Exception {
        this.desc.setId(1);
        assertEquals(1, this.desc.getId());
    }

    @Test
    public void getDesc() throws Exception {
        assertEquals("a", this.desc.getDesc());
    }

    @Test
    public void setDesc() throws Exception {
        this.desc.setDesc("b");
        assertEquals("b", this.desc.getDesc());
    }

    @Test
    public void getType() throws Exception {
        assertEquals("b", this.desc.getType());
    }

    @Test
    public void setType() throws Exception {
        this.desc.setType("a");
        assertEquals("a", this.desc.getType());
    }

    @Test
    public void getNum() throws Exception {

        assertEquals(1, this.desc.getNum());
    }

    @Test
    public void setNum() throws Exception {
        this.desc.setNum(2);
        assertEquals(2, this.desc.getNum());
    }

}