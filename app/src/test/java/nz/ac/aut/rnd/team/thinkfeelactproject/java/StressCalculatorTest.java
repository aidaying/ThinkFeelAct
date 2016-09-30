package nz.ac.aut.rnd.team.thinkfeelactproject.java;

import android.test.AndroidTestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 9/08/2016.
 */
public class StressCalculatorTest extends AndroidTestCase {

    private StressCalculator stressCalculator;
    private List<Double> rateList;

    @Before
    public void setUp() throws Exception {
        stressCalculator = new StressCalculator();
    }

    @After
    public void tearDown() throws Exception {
        stressCalculator = null;
    }

    @Test
    public void testStandardDeviationResult() throws Exception {

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
        assertEquals(5.0, stressCalculator.standardDeviationResult(rateList), 0);
    }

}