package nz.ac.aut.rnd.team.thinkfeelactproject;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BucketModelActivity extends Activity {


    DatabaseHandler mydb;
    List<Double> longTermSurveyRating;
    List<Double> eventRating;
    StressCalculator stressCalculator;
    TextView ratingResult;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket_model);
        mydb = new DatabaseHandler(this);
        longTermSurveyRating = mydb.getAlltheRateFromLTSurvey();
        eventRating = mydb.getAllTheRateFromEvent();

        stressCalculator = new StressCalculator();

        double a = stressCalculator.standardDeviationResult(longTermSurveyRating);
        stressCalculator = new StressCalculator();
        double b = stressCalculator.standardDeviationResult(eventRating);
        String aR = new DecimalFormat("##.##").format(a);
        String bR = new DecimalFormat("##.##").format(b);
        double c = a + b;
        String cR = new DecimalFormat("##.##").format(c);
        ratingResult = (TextView) findViewById(R.id.bucketModelPercentage);
        ratingResult.setText(aR + " + " + bR + " = " + cR +" %");


    }

}
