package nz.ac.aut.rnd.team.thinkfeelactproject;

import android.test.AndroidTestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

import nz.ac.aut.rnd.team.thinkfeelactproject.java.StressCalculator;

/**
 * Created by Robert on 9/08/2016.
 */
public class StressCalculatorTest extends AndroidTestCase {

    private StressCalculator stressCalculator;
    private List<Double> rateList;

    @Before
    public void setUp() throws Exception {
        stressCalculator = new StressCalculator();
        rateList = new ArrayList<Double>();
        rateList.add(1.0);
        rateList.add(2.0);
        rateList.add(3.0);
        rateList.add(4.0);
        rateList.add(5.0);
        rateList.add(6.0);
        rateList.add(7.0);
        rateList.add(8.0);
        rateList.add(9.0);
    }

    @After
    public void tearDown() throws Exception {
        stressCalculator = null;
    }

    @Test
    public void testStandardDeviationResult() throws Exception {
        assertEquals("It is not equal.", stressCalculator.standardDeviationResult(rateList), 5);
    }

}