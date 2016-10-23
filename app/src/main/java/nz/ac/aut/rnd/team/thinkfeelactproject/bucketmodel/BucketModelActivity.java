package nz.ac.aut.rnd.team.thinkfeelactproject.bucketmodel;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;


import nz.ac.aut.rnd.team.thinkfeelactproject.R;
import nz.ac.aut.rnd.team.thinkfeelactproject.selfevalmodel.SelfEvaluationActivity;
import nz.ac.aut.rnd.team.thinkfeelactproject.sosmodel.SOSModelActivity;
import nz.ac.aut.rnd.team.thinkfeelactproject.timelinemodel.TimelineActivity;
import nz.ac.aut.rnd.team.thinkfeelactproject.timeoutmodel.TimeOutModelAcivity;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.DatabaseHandler;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.StressCalculator;


/**
 * A simple {@link Fragment} subclass.
 */
public class BucketModelActivity extends AppCompatActivity {


    DatabaseHandler mydb;
    List<Double> longTermSurveyRating;
    List<Double> eventRating;
    StressCalculator stressCalculator;
    TextView ratingResult, rateDesc;
    ImageView bImg;
    Button sosBtn, timeoutBtn, selfEvalBtn, realRateButton,timelineBtn;
    SeekBar seekTest;
    RatingBar ratingBar;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket_model);
        setTitle("Bucket Model");
        mydb = new DatabaseHandler(this);
        longTermSurveyRating = mydb.getAlltheRateFromLTSurvey();
        eventRating = mydb.getAllTheRateFromEvent();

        stressCalculator = new StressCalculator();
        double a =stressCalculator.calculationResult(longTermSurveyRating);
        double b = stressCalculator.calculationResult(eventRating);
        final double c = stressCalculator.getWeightedMean(a, b);

        ratingResult = (TextView) findViewById(R.id.bucketModelPercentage);
        rateDesc = (TextView) findViewById(R.id.rateDesc);
        timelineBtn = (Button) findViewById(R.id.timelineBtn);
        sosBtn = (Button) findViewById(R.id.sosBtn);
        timeoutBtn = (Button) findViewById(R.id.timeoutBtn);
        selfEvalBtn = (Button) findViewById(R.id.selfEvalBtn);
        bImg = (ImageView) findViewById(R.id.bucketImg);
        displayStressValue(bImg,(int)c);
        realRateButton = (Button) findViewById(R.id.realRateButton);

        realRateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayStressValue(bImg,(int)c);
            }
        });

        seekTest = (SeekBar) findViewById(R.id.bucketSeek);
        seekTest.setProgress((int)c);
        seekTest.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int result = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                result = progress;
                ratingResult.setText("Your Stress Rating: "+ result + "/10");
                displayStressValue(bImg, result);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                seekTest.setProgress((int)c);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

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
        selfEvalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SelfEvaluationActivity.class);
                startActivity(intent);
            }
        });
        timelineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TimelineActivity.class);
                startActivity(intent);
            }
        });
    }

    public void displayStressValue(ImageView bImg, int result){
        ratingResult.setText("Rating: "+ result + "/10");
        if(seekTest!=null){
            seekTest.setProgress(result);
        }
        if (result >= 8) {
            ratingResult.setTextColor(Color.parseColor("#ff484b"));
            rateDesc.setText("Danger Zone: You are over-stressing");
            rateDesc.setTextColor(Color.parseColor("#ff484b"));
        }
        else if (result < 8 && result >= 6) {
            ratingResult.setTextColor(Color.parseColor("#ffa14a"));
            rateDesc.setText("Warning Zone: You are on the verge of over-stressing");
            rateDesc.setTextColor(Color.parseColor("#ffa14a"));
        }
        else if(result<6 && result > 3){
            ratingResult.setTextColor(Color.parseColor("#40d973"));
            rateDesc.setText("Fairly Relaxed: Everyday stress");
            rateDesc.setTextColor(Color.parseColor("#40d973"));
        }else{
            ratingResult.setTextColor(Color.parseColor("#40d973"));
            rateDesc.setText("Relaxed: Stress free");
            rateDesc.setTextColor(Color.parseColor("#40d973"));
        }
        switch((int)result){
            case (0):{
                bImg.setBackgroundResource(R.drawable.b0);
            }break;
            case (1):{
                bImg.setBackgroundResource(R.drawable.b1);
            }break;
            case (2):{
                bImg.setBackgroundResource(R.drawable.b2);
            }break;
            case (3):{
                bImg.setBackgroundResource(R.drawable.b3);
            }break;
            case (4):{
                bImg.setBackgroundResource(R.drawable.b4);
            }break;
            case (5):{
                bImg.setBackgroundResource(R.drawable.b5);
            }break;
            case (6):{
                bImg.setBackgroundResource(R.drawable.b6);
            }break;
            case (7):{
                bImg.setBackgroundResource(R.drawable.b7);
            }break;
            case (8):{
                bImg.setBackgroundResource(R.drawable.b8);
            }break;
            case (9):{
                bImg.setBackgroundResource(R.drawable.b9);
            }break;
            case (10):{
                bImg.setBackgroundResource(R.drawable.b10);
            }break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bucket_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        android.app.FragmentManager fm = getFragmentManager();
        switch(item.getItemId()) {
            case R.id.infoIcon:
                AboutDialog aboutDialog = new AboutDialog();
                aboutDialog.show(fm, "about");
                return true;
            case R.id.credits:
                Credits credits = new Credits();
                credits.show(fm, "credits");
                return true;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //super.onDestroy();
        super.finish();
    }
}
