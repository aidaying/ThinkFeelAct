package nz.ac.aut.rnd.team.thinkfeelactproject;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

public class FirstTimeLauncherSurveys extends FragmentActivity {

    private static final int LTsurvey = 1;
    private static final int STevent = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_launcher_surveys);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch(getIntent().getIntExtra("fragment_id",1)){
            case LTsurvey:
                FirstTimeSurveyFragment firstTimeSurveyFragment = new FirstTimeSurveyFragment();
                fragmentTransaction.add(R.id.fragment_container, firstTimeSurveyFragment);
                break;
            case STevent:
                AddEventPageFragment addEventPageFragment = new AddEventPageFragment();
                fragmentTransaction.add(R.id.fragment_container, addEventPageFragment);
                break;
        }
        fragmentTransaction.commit();
    }
}
