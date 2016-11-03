package nz.ac.aut.rnd.team.thinkfeelactproject.bucketmodel;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
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
import nz.ac.aut.rnd.team.thinkfeelactproject.firsttimeloadup.FirstTimeInitialPageActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Robert on 3/11/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class BucketModelActivityTest {

    @Rule
    public ActivityTestRule<BucketModelActivity> mActivityRule =
            new ActivityTestRule<>(BucketModelActivity.class);

    @Test
    public void onCreate() throws Exception {
        onView(withId(R.id.sosBtn)).perform(click());
        onView(withId(R.id.textView8)).check(matches(withText("Same Old Stuff")));
        pressBack();
        onView(withId(R.id.selfEvalBtn)).perform(click());
        onView(withId(R.id.SE_OV_evView)).check(matches(withText("Event Name:")));
        pressBack();
        //onView(withId(R.id.timeoutBtn)).perform(click());
        //onView(withId(R.id.SE_OV_evView)).check(matches(withText("Event Name:")));
        //ViewActions.pressBack();
        //onView(withId(R.id.timelineBtn)).perform(click());
        //onView(withId(R.id.SE_OV_evView)).check(matches(withText("Event Name:")));
    }

    @Test
    public void displayStressValue() throws Exception {
        onView(withId(R.id.bucketSeek)).perform(setProgress(8));
        onView(withId(R.id.bucketSeek)).check(matches(withProgress(8)));
    }

    @Test
    public void onCreateOptionsMenu() throws Exception {

    }

    @Test
    public void onOptionsItemSelected() throws Exception {

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