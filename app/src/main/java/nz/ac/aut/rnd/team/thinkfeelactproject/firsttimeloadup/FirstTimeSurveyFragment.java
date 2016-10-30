package nz.ac.aut.rnd.team.thinkfeelactproject.firsttimeloadup;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.DatabaseHandler;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.LongTermSurvey;
import nz.ac.aut.rnd.team.thinkfeelactproject.R;


public class FirstTimeSurveyFragment extends Fragment {

    private DatabaseHandler mydb;
    private TextView question, qNum;
    private TextView desc;
    private TextView rateValue;
    private Button next;
    private int i = 0;
    private double rate = 0.0;
    private ArrayList<LongTermSurvey> arrayList;
    private LongTermSurvey survey;
    private LongTermSurvey longTermSurvey;
    private RadioGroup radio;
    private RadioButton radioButton;
    private RadioButton trueButton;
    private SeekBar rateBar;
    private String isTF= "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View surveyView = inflater.inflate(R.layout.fragment_first_time_survey, container, false);
        mydb = new DatabaseHandler(getActivity());
        xmlInit();
        question = (TextView) surveyView.findViewById(R.id.question);
        qNum = (TextView) surveyView.findViewById(R.id.qNum);
        desc = (TextView) surveyView.findViewById(R.id.desc_survey);
        radio = (RadioGroup) surveyView.findViewById(R.id.radioGroup);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.truebutton:
                        rateBar.setEnabled(true);
                        rateBar.setProgress(1);
                        rate = ((double) rateBar.getProgress());
                        break;
                    case R.id.falseButton:
                        rateBar.setEnabled(false);
                        rateBar.setProgress(0);
                        rate = 0.0;
                        break;

                }
            }
        });
        trueButton = (RadioButton) surveyView.findViewById(R.id.truebutton);
        int selectedId = radio.getCheckedRadioButtonId();
        radioButton = (RadioButton) surveyView.findViewById(selectedId);

        isTF = (String) radioButton.getText();
        rateValue = (TextView) surveyView.findViewById(R.id.rateValue);
        rateValue.setText("1");
        rateBar = (SeekBar) surveyView.findViewById(R.id.longtermRateBar);
        rateBar.incrementProgressBy(1);
        if(isTF.equals("False")){
            rate = 0.0;
        }else{
            rate = ((double) rateBar.getProgress());
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
                    rate = ((double) seekBar.getProgress());
                }
            });

        }

        setSurveyViews();
        next = (Button) surveyView.findViewById(R.id.nextbtn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeSurvey(v.getRootView());
                i++;
                if(i==41){
                    Intent intent = new Intent(getActivity(),FirstTimeLauncherSurveysActivity.class);
                    intent.putExtra("fragment_id",2);
                    startActivity(intent);
                }
                setSurveyViews();
                rateBar.setProgress(1);
                trueButton.setChecked(true);
            }
        });
        return surveyView;
    }

    public void onBackPressed() {
        //handle back press event
        Toast.makeText(getContext(),"Please Finish the survey First", Toast.LENGTH_SHORT).show();
    }
    private void storeSurvey(View v){
        survey = arrayList.get(i);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.truebutton:
                        rateBar.setEnabled(true);
                        rateBar.setProgress(1);
                        rate = ((double) rateBar.getProgress());
                        break;
                    case R.id.falseButton:
                        rateBar.setEnabled(false);
                        rateBar.setProgress(0);
                        rate = ((double) rateBar.getProgress());
                        break;

                }
            }
        });
        int selectedId = radio.getCheckedRadioButtonId();
        radioButton = (RadioButton) v.findViewById(selectedId);
        isTF = (String) radioButton.getText();
        if(isTF.equals("False")){
            rate=0.0;
        }
        rate = ((double) rateBar.getProgress());
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
                rate = ((double) seekBar.getProgress());
            }
        });


        longTermSurvey = new LongTermSurvey();
        longTermSurvey.setID(survey.getID());
        longTermSurvey.setAnswerTF(isTF);
        longTermSurvey.setRating(rate);
        mydb.addLongTermSurvey(longTermSurvey);

    }

    private void setSurveyViews(){
        if(i<41) {
            question.setText(arrayList.get(i).getQuestion());
            desc.setText(arrayList.get(i).getDescription());
            qNum.setText("Q:" + arrayList.get(i).getID());
        }

    }


    private void xmlInit(){
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
    }
    private void parserXML(XmlPullParser parser) throws XmlPullParserException, IOException{
        int eventType = parser.getEventType();
        LongTermSurvey currentSurvey = null;
        while (eventType != XmlPullParser.END_DOCUMENT){
            String name;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    arrayList = new ArrayList<>();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if(name.equals("survey")){
                        currentSurvey = new LongTermSurvey();
                    }else if(currentSurvey != null){
                        switch (name) {
                            case "id":
                                currentSurvey.setID(Integer.valueOf(parser.nextText()));
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

    /**
     * Called when the fragment's activity has been created and this
     * fragment's view hierarchy instantiated.  It can be used to do final
     * initialization once these pieces are in place, such as retrieving
     * views or restoring state.  It is also useful for fragments that use
     * {@link #setRetainInstance(boolean)} to retain their instance,
     * as this callback tells the fragment when it is fully associated with
     * the new activity instance.  This is called after {@link #onCreateView}
     * and before {@link #onViewStateRestored(Bundle)}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();

        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        Toast.makeText(getActivity(), "Please Finish all the survey first", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
                return false;
            }
        });
    }
}