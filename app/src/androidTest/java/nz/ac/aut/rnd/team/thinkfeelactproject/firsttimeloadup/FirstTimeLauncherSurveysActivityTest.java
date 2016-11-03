package nz.ac.aut.rnd.team.thinkfeelactproject.firsttimeloadup;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.SeekBar;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import nz.ac.aut.rnd.team.thinkfeelactproject.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Robert on 2/11/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class FirstTimeLauncherSurveysActivityTest {
    @Rule
    public ActivityTestRule<FirstTimeLauncherSurveysActivity> mActivityRule =
            new ActivityTestRule<>(FirstTimeLauncherSurveysActivity.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void onCreate() throws Exception {
        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        onView(withId(R.id.truebutton)).perform(click());
        //onView(withId(R.id.rateValue)).perform(typeText("5"));
        onView(withId(R.id.longtermRateBar)).perform(setProgress(5));
        onView(withId(R.id.nextbtn)).perform(click());

        //onView(withId(R.id.textView3)).check(matches(withText("Add Event")));

        onView(withId(R.id.eventEntry)).perform(typeText("Test Event 1"), closeSoftKeyboard());
        onView(withId(R.id.eventRateBar)).perform(setProgress(8));
        onView(withId(R.id.dayEntry)).perform(typeText("1"));
        onView(withId(R.id.monthEntry)).perform(typeText("08"));
        onView(withId(R.id.yearEntry)).perform(typeText("2016"), closeSoftKeyboard());
        onView(withId(R.id.addButton)).perform(click());

        onView(withId(R.id.eventEntry)).perform(typeText("Test Event 2"), closeSoftKeyboard());
        onView(withId(R.id.eventRateBar)).perform(setProgress(8));
        onView(withId(R.id.dayEntry)).perform(typeText("2"));
        onView(withId(R.id.monthEntry)).perform(typeText("08"));
        onView(withId(R.id.yearEntry)).perform(typeText("2016"), closeSoftKeyboard());
        onView(withId(R.id.addButton)).perform(click());

        onView(withId(R.id.eventEntry)).perform(typeText("Test Event 3"), closeSoftKeyboard());
        onView(withId(R.id.eventRateBar)).perform(setProgress(8));
        onView(withId(R.id.dayEntry)).perform(typeText("3"));
        onView(withId(R.id.monthEntry)).perform(typeText("08"));
        onView(withId(R.id.yearEntry)).perform(typeText("2016"), closeSoftKeyboard());
        onView(withId(R.id.addButton)).perform(click());
        onView(withId(R.id.calculateButton)).perform(click());

        onView(withId(R.id.bucketSeek)).check(matches(withProgress(6)));

    }

    public static ViewAction setProgress(final int progress) {
        return new ViewAction() {
            @Override
            public void perform(UiController uiController, View view) {
                SeekBar seekBar = (SeekBar) view;
                seekBar.setProgress(progress);
            }
            @Override
            public String getDescription() {
                return "Set a progress on a SeekBar";
            }
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(SeekBar.class);
            }
        };
    }

    public static Matcher<View> withProgress(final int expectedProgress) {
        return new BoundedMatcher<View, SeekBar>(SeekBar.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("expected: ");
                description.appendText(""+expectedProgress);
            }

            @Override
            public boolean matchesSafely(SeekBar seekBar) {
                return seekBar.getProgress() == expectedProgress;
            }
        };
    }


}