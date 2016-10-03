package nz.ac.aut.rnd.team.thinkfeelactproject.bucketmodel;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;


import nz.ac.aut.rnd.team.thinkfeelactproject.R;
import nz.ac.aut.rnd.team.thinkfeelactproject.sosmodel.SOSModelActivity;
import nz.ac.aut.rnd.team.thinkfeelactproject.timeoutmodel.TimeOutModelAcivity;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.DatabaseHandler;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.StressCalculator;


/**
 * A simple {@link Fragment} subclass.
 */
public class BucketModelActivity extends Activity {


    DatabaseHandler mydb;
    List<Double> longTermSurveyRating;
    List<Double> eventRating;
    StressCalculator stressCalculator;
    TextView ratingResult;
    Button sosBtn;
    Button timeoutBtn;

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
        String aR = new DecimalFormat("##").format(a);
        String bR = new DecimalFormat("##").format(b);
        double c = a + b;
        String cR = new DecimalFormat("##").format(c);
        ratingResult = (TextView) findViewById(R.id.bucketModelPercentage);
        ratingResult.setText(aR + " + " + bR + " = " + cR +" %");

        sosBtn = (Button) findViewById(R.id.sosBtn);
        timeoutBtn = (Button) findViewById(R.id.timeoutBtn);

        sosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),SOSModelActivity.class);
                startActivity(intent);
            }
        });

        timeoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TimeOutModelAcivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onDestroy();
    }
}
