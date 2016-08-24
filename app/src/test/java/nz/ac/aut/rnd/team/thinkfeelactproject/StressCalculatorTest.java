package nz.ac.aut.rnd.team.thinkfeelactproject;

import android.test.AndroidTestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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
        rateList.add(4.0);
        rateList.add(5.0);
        rateList.add(6.0);
    }

    @After
    public void tearDown() throws Exception {
        stressCalculator = null;
    }

    @Test
    public void testStandardDeviationResult() throws Exception {
        assertEquals(2, stressCalculator.standardDeviationResult(rateList), 0);
    }

}