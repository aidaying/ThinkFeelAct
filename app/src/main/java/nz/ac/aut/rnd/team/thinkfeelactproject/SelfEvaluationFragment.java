package nz.ac.aut.rnd.team.thinkfeelactproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelfEvaluationFragment extends Fragment {


    DatabaseHandler mydb;
    List<Double> longTermSurveyRating;
    List<Double> eventRating;
    StressCalculator stressCalculator;
    TextView ratingResult;
    ProgressBar bucketModelProgress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View selfEvaluationView =  inflater.inflate(R.layout.fragment_self_evaluation, container, false);
        mydb = new DatabaseHandler(getActivity());
        longTermSurveyRating = mydb.getAlltheRateFromLTSurvey();
        eventRating = mydb.getAllTheRateFromEvent();
       stressCalculator = new StressCalculator();
      double a = stressCalculator.standardDeviationResult(longTermSurveyRating);
        double b = stressCalculator.standardDeviationResult(eventRating);

        double c = a+b;

        ratingResult = (TextView) selfEvaluationView.findViewById(R.id.bucketModelPercentage);
        ratingResult.setText(c +" %");
       // bucketModelProgress = (ProgressBar) selfEvaluationView.findViewById(R.id.bucketModelProgress);
        //bucketModelProgress.setProgress(c);

        return selfEvaluationView;
    }

}
