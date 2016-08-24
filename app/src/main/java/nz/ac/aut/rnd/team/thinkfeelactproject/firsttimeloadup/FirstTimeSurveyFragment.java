package nz.ac.aut.rnd.team.thinkfeelactproject.firsttimeloadup;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import nz.ac.aut.rnd.team.thinkfeelactproject.java.DatabaseHandler;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.LongTermSurvey;
import nz.ac.aut.rnd.team.thinkfeelactproject.R;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.Survey;

public class FirstTimeSurveyFragment extends Fragment implements View.OnClickListener{

    DatabaseHandler mydb;
    TextView question;
    TextView desc;
    TextView rateValue;
    Button next;
    int i = 0;
    List<Survey> arrayList;
    Survey survey;
    LongTermSurvey longTermSurvey;
    RadioGroup radio;
    private RadioButton trueRB;
    private RadioButton falseRB;
    private SeekBar rateBar;
    private String isTF="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View surveyView = inflater.inflate(R.layout.fragment_first_time_survey, container, false);
        mydb = new DatabaseHandler(getActivity());

        XmlPullParserFactory pullParserFactory;
        try{
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();
            InputStream inputStream = getResources().openRawResource(R.raw.surveys);
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            parser.setInput(inputStream,null);
            parserXML(parser);
        }catch(XmlPullParserException | IOException e){
            e.printStackTrace();
        }

        survey = arrayList.get(i);
        question = (TextView) surveyView.findViewById(R.id.question);
        desc = (TextView) surveyView.findViewById(R.id.desc_survey);
        next = (Button) surveyView.findViewById(R.id.nextbtn);
        radio = (RadioGroup) surveyView.findViewById(R.id.radio);
        trueRB = (RadioButton) surveyView.findViewById(R.id.truebutton);
        falseRB = (RadioButton) surveyView.findViewById(R.id.falseButton);

        rateValue = (TextView) surveyView.findViewById(R.id.rateValue);
        rateValue.setText("1");
        rateBar = (SeekBar) surveyView.findViewById(R.id.stressRateBar);
        rateBar.setProgress(1);
        rateBar.incrementProgressBy(1);
        rateBar.setMax(10);
        rateBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                rateValue.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButtons = radio.findViewById(i);
                int index = radio.indexOfChild(radioButtons);
                switch (index){
                    case 0:
                        isTF = trueRB.getText().toString();
                        rateBar.setEnabled(true);
                        rateBar.setProgress(1);
                        break;
                    case 1:
                        isTF = falseRB.getText().toString();
                        rateBar.setEnabled(false);
                        rateBar.setProgress(0);
                        break;
                }
            }
        });
        setSurveyViews();
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
        String TF = isTF;
        if(survey.getId()==40){
            longTermSurvey = new LongTermSurvey(TF,getRating(),survey.getId());
            mydb.addLongTermSurvey(longTermSurvey);
            Intent intent = new Intent(getActivity(),FirstTimeLauncherSurveys.class);
            intent.putExtra("fragment_id",2);
            startActivity(intent);
        }else {
            setSurveyViews();
            survey = arrayList.get(i);
            longTermSurvey = new LongTermSurvey(TF, getRating(), survey.getId());
            mydb.addLongTermSurvey(longTermSurvey);
        }
    }



    private int getRating(){
        int rate = 0;
        rate = rateBar.getProgress();
        return rate;
    }

    private void setSurveyViews(){
        question.setText(arrayList.get(i).getQuestion());
        desc.setText(arrayList.get(i).getDescription());
        i++;
    }
    private void parserXML(XmlPullParser parser) throws XmlPullParserException, IOException{

        int eventType = parser.getEventType();
        Survey currentSurvey = null;

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    arrayList = new ArrayList<>();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if(name.equals("survey")){
                        currentSurvey = new Survey();
                    }else if(currentSurvey != null){
                        switch (name) {
                            case "id":
                                currentSurvey.setId(Integer.valueOf(parser.nextText()));
                                break;
                            case "question":
                                currentSurvey.setQuestion(parser.nextText());
                                break;
                            case "description":
                                currentSurvey.setDescription(parser.nextText());
                                break;
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if(name.equalsIgnoreCase("survey")&& currentSurvey != null) {
                        arrayList.add(currentSurvey);
                    }

            }
            eventType = parser.next();
        }

    }
}
