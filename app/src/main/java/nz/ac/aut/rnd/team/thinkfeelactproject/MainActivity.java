package nz.ac.aut.rnd.team.thinkfeelactproject;



import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity {

    private final int fragment_first_time_initial_page=1;
    private final int fragment_first_time_survey=2;
    private final int fragement_add_event_page=3;
    private final int fragment_self_evaluation=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch(getIntent().getIntExtra("fragment_id",1)){
            case fragment_first_time_initial_page:
                FirstTimeInitialPageFragment firstTimeInitialPageFragment = new FirstTimeInitialPageFragment();
                fragmentTransaction.add(R.id.fragment_container, firstTimeInitialPageFragment);
                break;
            case fragment_first_time_survey:
                FirstTimeSurveyFragment firstTimeSurveyFragment = new FirstTimeSurveyFragment();
                fragmentTransaction.add(R.id.fragment_container, firstTimeSurveyFragment);
                break;
            case fragement_add_event_page:
                AddEventPageFragment addEventPageFragment = new AddEventPageFragment();
                fragmentTransaction.add(R.id.fragment_container, addEventPageFragment);
                break;
            case fragment_self_evaluation:
                SelfEvaluationFragment selfEvaluationFragment = new SelfEvaluationFragment();
                fragmentTransaction.add(R.id.fragment_container, selfEvaluationFragment);
                break;

        }
        fragmentTransaction.commit();
    }

}
