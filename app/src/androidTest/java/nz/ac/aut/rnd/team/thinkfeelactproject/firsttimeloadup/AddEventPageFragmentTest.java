package nz.ac.aut.rnd.team.thinkfeelactproject.firsttimeloadup;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertThat;


/**
 * Created by Robert on 29/09/2016.
 */
@RunWith(AndroidJUnit4.class)
public class AddEventPageFragmentTest extends AndroidJUnitRunner{

    private UiDevice uiDevice;
    private AddEventPageFragment addEventPageFragment;
    private static final int LAUNCH_TIMEOUT = 5000;

    @Rule
    public ActivityTestRule<AddEventPageFragment> mActivityRule = new ActivityTestRule<>(
            AddEventPageFragment.class);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testOnCreateView() throws Exception {

    }


}