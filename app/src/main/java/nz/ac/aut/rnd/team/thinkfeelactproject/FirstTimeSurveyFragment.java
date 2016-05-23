package nz.ac.aut.rnd.team.thinkfeelactproject;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;


import java.util.List;


public class FirstTimeSurveyFragment extends Fragment implements View.OnClickListener{

    DatabaseHandler mydb;
    TextView question;
    TextView rateValue;
    ProgressBar surveyProgress;
    Button next;
    int i = 0;
    List<Survey> arrayList;
    Survey survey;
    LongTermSurvey longTermSurvey;
    RadioGroup radio;
    private RadioButton trueRB;
    private RadioButton falseRB;
    private SeekBar rateBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View surveyView = inflater.inflate(R.layout.fragment_first_time_survey, container, false);
        mydb = new DatabaseHandler(getActivity());
        arrayList = mydb.getAllQuestion();
        survey = arrayList.get(i);
        question = (TextView) surveyView.findViewById(R.id.question);
        next = (Button) surveyView.findViewById(R.id.nextbtn);
        radio = (RadioGroup) surveyView.findViewById(R.id.radio);
        trueRB = (RadioButton) surveyView.findViewById(R.id.truebutton);
        falseRB = (RadioButton) surveyView.findViewById(R.id.falseButton);
        surveyProgress = (ProgressBar) surveyView.findViewById(R.id.surveyProgress);
        surveyProgress.setMax(arrayList.size());
        surveyProgress.setProgress(i);
        rateBar = (SeekBar) surveyView.findViewById(R.id.stressRateBar);
        rateBar.setProgress(0);
        rateBar.incrementProgressBy(1);
        rateBar.setMax(10);
        rateBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rateValue = (TextView) surveyView.findViewById(R.id.rateValue);
                rateValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        setQuestionView();
        next.setOnClickListener(this);
        return surveyView;
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        String TF = getTFValue();
        if(survey.getId()==61){
            longTermSurvey = new LongTermSurvey(TF,getRating(),survey.getId());
            mydb.addLongTermSurvey(longTermSurvey);
            Intent intent = new Intent(getActivity(),MainActivity.class);
                intent.putExtra("fragment_id",3);
                startActivity(intent);
        }else {
            survey = arrayList.get(i);
            setQuestionView();

            longTermSurvey = new LongTermSurvey(TF, getRating(), survey.getId());
            mydb.addLongTermSurvey(longTermSurvey);
        }
    }

    private String getTFValue(){
        String isTF="";

        int radioId = radio.getCheckedRadioButtonId();

        switch(radioId){
            case R.id.truebutton:
                    isTF = trueRB.getText().toString();
                break;
            case R.id.falseButton:
                    isTF = falseRB.getText().toString();
                break;
        }
        return isTF;
    }

    private int getRating(){
        int rate=0;
        rate = rateBar.getProgress();
        return rate;
    }

    private void setQuestionView(){
        question.setText(arrayList.get(i).getQuestion());
        i++;

    }
}
