package nz.ac.aut.rnd.team.thinkfeelactproject.selfevalmodel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import nz.ac.aut.rnd.team.thinkfeelactproject.R;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.DatabaseHandler;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.Event;


public class SelfEvaluationActivity extends Activity implements View.OnClickListener{

    ToggleButton overview, mood, thoughts, body;
    Button addEvent, noPain;
    String thoughtWhatString, thoughtWhyHowString, thoughtFeelString, bodyString, moodString, eventName;
    double finalRating;
    View overviewLayout, moodLayout, thoughtsLayout, bodyLayout, moodLayoutSection;
    View scroll_overview, scroll_thoughts;
    View SE_OVERVIEW_moodLayout, SE_OVERVIEW_bodyLayout, SE_OVERVIEW_thoughtsLayout;
    RatingBar ratingBar;
    ImageButton m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14, m15, m16;
    ImageView moodImgView, ov_ImgView;
    TextView moodTextView, moodHeadText, ov_moodText, ov_bodyText, ratingText;
    TextView ov_thoughtsWhat, ov_thoughtsWhyHow, ov_thoughtsFeel;
    EditText thoughtWhatEdit, thoughtWhyHowEdit, thoughtFeelEdit, eventNameEdit, searchEditText;
    EditText[] editArray;
    //physical pain body buttons
    ImageButton img_headButton, img_upperLButton, img_upperRButton, img_chestButton, img_lowerLButton;
    ImageButton img_lowerRButton, img_lowerBButton, img_legButton, img_feetButton;
    ToggleButton headButton, chestButton, upArmButton, handButton, lowBodButton, legsButton, feetButton;
    ToggleButton[] tbArray; ImageButton[] bodyImgArray, moodArray;
    ArrayList<String> bodTexts; String[] moodNames; TypedArray moodIDs;
    Context context;
    boolean noPainButtonClicked;
    private DatabaseHandler mydb;
    private Event event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_se);

        mydb = new DatabaseHandler(this);
        event = new Event();

        //=======================OVERVIEW SECTION=====================================
        addEvent = (Button) findViewById(R.id.SE_OV_addEventButton);
        addEvent.setOnClickListener(this);
        overview = (ToggleButton) findViewById(R.id.overviewButton);
        mood = (ToggleButton) findViewById(R.id.moodButton);
        thoughts = (ToggleButton) findViewById(R.id.thoughtsButton);
        body = (ToggleButton) findViewById(R.id.bodyButton);
        overview.setOnClickListener(this);
        mood.setOnClickListener(this);
        thoughts.setOnClickListener(this);
        body.setOnClickListener(this);
        context = this;
        noPain = (Button) findViewById(R.id.noPainButton); noPain.setOnClickListener(this);

        overviewLayout = findViewById(R.id.overviewLayout);
        moodLayoutSection = findViewById(R.id.ov_moodSection);
        scroll_overview = findViewById(R.id.scroll_overview);
        scroll_thoughts = findViewById(R.id.scroll_thoughts);
        moodLayout = findViewById(R.id.moodLayout);
        thoughtsLayout = findViewById(R.id.thoughtsLayout);
        bodyLayout = findViewById(R.id.bodyLayout);
        moodHeadText = (TextView) findViewById(R.id.SE_OV_mdView);
        ov_moodText = (TextView) findViewById(R.id.SE_OV_mood);
        ov_bodyText = (TextView) findViewById(R.id.SE_OV_pain);

        ov_thoughtsWhat = (TextView) findViewById(R.id.SE_OV_thoughtsWhat);
        ov_thoughtsWhyHow = (TextView) findViewById(R.id.SE_OV_thoughtsWhyHow);
        ov_thoughtsFeel = (TextView) findViewById(R.id.SE_OV_thoughtsFeel);

        thoughtWhatEdit = (EditText) findViewById(R.id.SE_THOUGHTS_what);
        thoughtWhyHowEdit = (EditText) findViewById(R.id.SE_THOUGHTS_whyhow);
        thoughtFeelEdit = (EditText) findViewById(R.id.SE_THOUGHTS_feel);
        eventNameEdit = (EditText) findViewById(R.id.SE_OV_editEventName);
        //Adapts with scroll view and soft keyboard done button
        thoughtWhatEdit.setHorizontallyScrolling(false);
        thoughtWhatEdit.setMaxLines(5);
        thoughtWhyHowEdit.setHorizontallyScrolling(false);
        thoughtWhyHowEdit.setMaxLines(5);
        thoughtFeelEdit.setHorizontallyScrolling(false);
        thoughtFeelEdit.setMaxLines(5);

        ov_ImgView = (ImageView) findViewById(R.id.SE_OVERVIEW_moodImgView);
        ov_ImgView.setOnClickListener(this);
        moodHeadText.setOnClickListener(this);

        SE_OVERVIEW_moodLayout = findViewById(R.id.SE_OVERVIEW_moodLayout);
        SE_OVERVIEW_bodyLayout = findViewById(R.id.SE_OVERVIEW_bodyPain);
        SE_OVERVIEW_thoughtsLayout = findViewById(R.id.SE_OVERVIEW_thoughts);
        SE_OVERVIEW_moodLayout.setOnClickListener(this);
        SE_OVERVIEW_bodyLayout.setOnClickListener(this);
        SE_OVERVIEW_thoughtsLayout.setOnClickListener(this);


        //====================MOOD SECTION=====================================
        m1 = (ImageButton) findViewById(R.id.SE_MOOD_m1Button);
        m2 = (ImageButton) findViewById(R.id.SE_MOOD_m2Button);
        m3 = (ImageButton) findViewById(R.id.SE_MOOD_m3Button);
        m4 = (ImageButton) findViewById(R.id.SE_MOOD_m4Button);
        m5 = (ImageButton) findViewById(R.id.SE_MOOD_m5Button);
        m6 = (ImageButton) findViewById(R.id.SE_MOOD_m6Button);
        m7 = (ImageButton) findViewById(R.id.SE_MOOD_m7Button);
        m8 = (ImageButton) findViewById(R.id.SE_MOOD_m8Button);
        m9 = (ImageButton) findViewById(R.id.SE_MOOD_m9Button);
        m10 = (ImageButton) findViewById(R.id.SE_MOOD_m10Button);
        m11 = (ImageButton) findViewById(R.id.SE_MOOD_m11Button);
        m12 = (ImageButton) findViewById(R.id.SE_MOOD_m12Button);
        m13 = (ImageButton) findViewById(R.id.SE_MOOD_m13Button);
        m14 = (ImageButton) findViewById(R.id.SE_MOOD_m14Button);
        m15 = (ImageButton) findViewById(R.id.SE_MOOD_m15Button);
        m16 = (ImageButton) findViewById(R.id.SE_MOOD_m16Button);
        moodImgView = (ImageView) findViewById(R.id.SE_MOOD_imgView);
        moodTextView = (TextView) findViewById(R.id.SE_MOOD_moodView);
        m1.setOnClickListener(this);
        m2.setOnClickListener(this);
        m3.setOnClickListener(this);
        m4.setOnClickListener(this);
        m5.setOnClickListener(this);
        m6.setOnClickListener(this);
        m7.setOnClickListener(this);
        m8.setOnClickListener(this);
        m9.setOnClickListener(this);
        m10.setOnClickListener(this);
        m11.setOnClickListener(this);
        m12.setOnClickListener(this);
        m13.setOnClickListener(this);
        m14.setOnClickListener(this);
        m15.setOnClickListener(this);
        m16.setOnClickListener(this);

        //====================BODY SECTION=====================================
        img_headButton = (ImageButton) findViewById(R.id.ic_headButton);
        headButton = (ToggleButton) findViewById(R.id.SE_BODY_headButton);
        img_upperLButton = (ImageButton) findViewById(R.id.ic_upperLButton);
        chestButton = (ToggleButton) findViewById(R.id.SE_BODY_chestButton);
        img_upperRButton = (ImageButton) findViewById(R.id.ic_upperRButton);
        upArmButton = (ToggleButton) findViewById(R.id.SE_BODY_armButton);
        img_chestButton = (ImageButton) findViewById(R.id.ic_chestButton);
        handButton = (ToggleButton) findViewById(R.id.SE_BODY_handButton);
        img_lowerLButton = (ImageButton) findViewById(R.id.ic_lowerLButton);
        lowBodButton = (ToggleButton) findViewById(R.id.SE_BODY_lowBodyButton);
        img_lowerRButton = (ImageButton) findViewById(R.id.ic_lowerRButton);
        legsButton = (ToggleButton) findViewById(R.id.SE_BODY_legButton);
        img_lowerBButton = (ImageButton) findViewById(R.id.ic_lowerBodyButton);
        feetButton = (ToggleButton) findViewById(R.id.SE_BODY_feetButton);
        img_legButton = (ImageButton) findViewById(R.id.ic_legsButton);
        img_feetButton = (ImageButton) findViewById(R.id.ic_feetButton);

        //ImageButtons
        img_feetButton.setOnClickListener(this);
        img_lowerBButton.setOnClickListener(this);
        img_legButton.setOnClickListener(this);
        img_chestButton.setOnClickListener(this);
        img_headButton.setOnClickListener(this);
        img_upperLButton.setOnClickListener(this);
        img_upperRButton.setOnClickListener(this);
        img_lowerLButton.setOnClickListener(this);
        img_lowerRButton.setOnClickListener(this);
        //Buttons
        headButton.setOnClickListener(this);
        chestButton.setOnClickListener(this);
        upArmButton.setOnClickListener(this);
        handButton.setOnClickListener(this);
        lowBodButton.setOnClickListener(this);
        legsButton.setOnClickListener(this);
        feetButton.setOnClickListener(this);

        editArray = new EditText[]{thoughtWhyHowEdit, thoughtWhatEdit, thoughtFeelEdit, eventNameEdit};
        tbArray = new ToggleButton[]{headButton, chestButton,
                upArmButton, lowBodButton, handButton, legsButton, feetButton};
        bodyImgArray = new ImageButton[]{img_headButton, img_upperLButton, img_chestButton,
                img_lowerLButton, img_lowerBButton, img_legButton, img_feetButton};
        moodArray = new ImageButton[]{m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14, m15, m16};

        moodNames = getResources().getStringArray(R.array.mood_names);
        moodIDs = getResources().obtainTypedArray(R.array.moodIDs);

        //=========================RATING=====================================
        ratingBar = (RatingBar) findViewById(R.id.SE_OVERVIEW_StressRating);
        ratingText = (TextView) findViewById(R.id.ratingText);
        ratingBar.setStepSize(0.5f);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating >= 8)
                    ratingText.setTextColor(Color.parseColor("#ff484b"));
                else if (rating < 8 && rating >= 5)
                    ratingText.setTextColor(Color.parseColor("#ffa14a"));
                else
                    ratingText.setTextColor(Color.parseColor("#40d973"));
                ratingText.setText("Rating: " + rating);
                finalRating = (double)rating;
                hide(eventNameEdit);
            }
        });

        //MUST INCLUDE | buttons do not work on first click otherwise
        for (ImageButton eachButton : bodyImgArray) {
            eachButton.performClick();
        }

        noPainButtonClicked = false;

        overview.requestFocus();
    }




    /**
     *  SetOverviewDescriptions
     *
     *  gets an array of all the values from body, mood and thoughts
     *  and adds them to the over description
     */
    public void setOverviewDescriptions() {
        // SET WHAT, WHYHOW AND FEEL TEXTS TO OVERVIEW TEXT SECTION
        ov_thoughtsWhat.setText(thoughtWhatEdit.getEditableText().toString());
        ov_thoughtsWhyHow.setText(thoughtWhyHowEdit.getEditableText().toString());
        ov_thoughtsFeel.setText(thoughtFeelEdit.getEditableText().toString());


        String listOfBodySelections;

        for (ToggleButton eachButton : tbArray) {
            String currentText = eachButton.getText().toString();
            if (eachButton.isChecked()) {
                if (!bodTexts.contains(currentText))
                    bodTexts.add(currentText);
            } else {
                if (bodTexts.contains(currentText))
                    bodTexts.remove(currentText);
            }
        }


        if(bodTexts.isEmpty()) {  //if arrayList of selected body parts are empty
            if(!checkBodyClicked() && noPainButtonClicked == true) { //No body parts are selected  or if no pain button is clicked
                ov_bodyText.setText("No Physical Pain");
            }
        }else {
            if(ov_bodyText.getText().length()==0) {
                listOfBodySelections = TextUtils.join(", ", bodTexts);
                ov_bodyText.setText(listOfBodySelections);
            }
        }
    }

    /**
     * //CHECKS IF ANY SELECTION IS MADE ON BODY TAB
     * @return true if any selection has been done on the physical pain layout
     */
    public boolean checkBodyClicked(){
        boolean bodyCheck = false;
        for (ToggleButton bodyBoxItem : tbArray) {
            if (bodyBoxItem.isChecked())
                bodyCheck = true;
            else
                bodyCheck = false;
        }
        return bodyCheck;
    }


    /**
     * Confirms that the thoughts section is filled out
     *
     * thoughtString = STRING FOR THOUGHTS to be returned with add event button
     * @return true if completed
     */
    public boolean thoughtsComplete() {
        if (ov_thoughtsWhat.getText().length() != 0 && ov_thoughtsWhyHow.getText().length() != 0
                && ov_thoughtsFeel.getText().length() != 0) {
            return true;
        } else{
            return false;
        }
    }

    /**
     * Checks if the user has selected a mood yet
     * @return true if they have
     */
    public boolean moodComplete(){
        if(ov_moodText.getText().length() !=0){
            return true;
        }else
            return false;
    }

    /**
     * Checks if user has made a selection on the physical pain page
     * @return true if they have
     */
    public boolean bodyComplete(){
        if(ov_bodyText.getText().length()!=0){
            return true;
        }else
            return false;
    }

    /**
     * BOOLEAN CHECKING IF USER CAN ADD EVENT OR NOT ////////////////USED FOR ADDING TO DATABASE/////////////////////////////
     * ALSO ADDS INPUTTED VALUES INTO DATABASE IF SUCCESSFUL
     *
     * For clarity, specific strings will contain the values to be added to the database
     *
     * eventName = value for name of event
     * thought[What, Why/How, Feel]String = values for thoughts section
     * bodyString = value for body section
     * moodString = value for mood section
     * ratingString = value for the stress rating
     *
     * @return
     */
    public boolean setComplete(){
        String addMessage = "Added event";

        //ratingString = use for stress value

        /**
         *  ================================DATABASE INSERTION HERE============================================================
         */

        //Returns true if body, mood, eventName and ratingString is not empty
        if(thoughtsComplete() && moodComplete() && bodyComplete() && eventName!=null && finalRating!=0.0){
            eventName = eventNameEdit.getText().toString();
            thoughtWhatString = ov_thoughtsWhat.getText().toString();
            thoughtWhyHowString = ov_thoughtsWhyHow.getText().toString();
            thoughtFeelString = ov_thoughtsFeel.getText().toString();
            bodyString = ov_bodyText.getText().toString();
            moodString = ov_moodText.getText().toString();
            //Log testing - Displays in bright red text within -Android Monitor->Log Cat-
            Log.e("COMPLETE", "ALL SET TO ADD EVENT");
            Log.e("Event Name", eventName);
            Log.e("Body", bodyString);
            Log.e("Mood", moodString);
            Log.e("What happened: ", thoughtWhatString);
            Log.e("Why/How did it happen: ",thoughtWhyHowString);
            Log.e("How are you feeling: ", thoughtFeelString);
            Log.e("Rating", "" + finalRating);
            //End Log testing

            //Adding to database
            event.setName(eventName);
            Calendar c = Calendar.getInstance();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yy");
            String date = simpleDateFormat.format(c.getTime());
            event.setDate(date);
            event.setRating(finalRating);
            event.setEmotion(moodString);
            event.setPain(bodyString);
            event.setThoughtwhat(thoughtWhatString);
            event.setThoughtwhyhow(thoughtWhyHowString);
            event.setThoughtfeel(thoughtFeelString);

            Toast.makeText(this, addMessage, Toast.LENGTH_LONG).show();
            //Returns to Bucket model activity when event is added
            super.onBackPressed();
            return true;
        }else{
            Log.e("Event Name", eventNameEdit.getText().toString());
            addMessage = "Please complete sections that are still highlighted red. ";
            Log.e("FAILED", "NOT READY TO ADD EVENT");
            if(finalRating == 0) {
                Log.e("Rating", "NOT SET");
                addMessage += "Please set a stress level for this event.";
            }
            Toast.makeText(this, addMessage, Toast.LENGTH_LONG).show();
            return false;
        }
    }

    /**
     * =============================================================================================================================
     */

    /**
     * Method for hiding the soft keybooard
     * @param v The EditText view whos keyboard you want to hide
     */
    public void hide(EditText v) {
        InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    @Override
    public void onClick(View v) {

        if(eventNameEdit.getText().length()!=0){
            eventName = eventNameEdit.getText().toString();
        }

        ov_bodyText.setText("");
        bodTexts = null; bodTexts = new ArrayList<>();
        int buttonPressed = v.getId();

        if(buttonPressed!=R.id.editEvent||buttonPressed!=R.id.thoughtsWhat||
        buttonPressed!=R.id.thoughtsWhyHow||buttonPressed!=R.id.thoughtsFeel){
            hide(eventNameEdit);
            hide(thoughtFeelEdit);
            hide(thoughtWhatEdit);
            hide(thoughtWhyHowEdit);
        }

        if(buttonPressed==R.id.overviewButton||buttonPressed==R.id.thoughtsButton
                || buttonPressed==R.id.bodyButton||buttonPressed==R.id.moodButton
                || buttonPressed==R.id.SE_OVERVIEW_bodyPain || buttonPressed==R.id.SE_OVERVIEW_thoughts
                || buttonPressed==R.id.SE_OVERVIEW_moodLayout || buttonPressed==R.id.SE_OVERVIEW_moodImgView
                || buttonPressed==R.id.SE_OV_mdView){
            moodLayout.setVisibility(View.INVISIBLE);
            thoughtsLayout.setVisibility(View.INVISIBLE);
            bodyLayout.setVisibility(View.INVISIBLE); scroll_thoughts.setVisibility(View.INVISIBLE);
            scroll_overview.setVisibility(View.INVISIBLE); addEvent.setVisibility(View.INVISIBLE);
            overview.setChecked(false); overview.setBackgroundResource(R.drawable.del_button_border);
            thoughts.setChecked(false); thoughts.setBackgroundResource(R.drawable.del_button_border);
            body.setChecked(false); body.setBackgroundResource(R.drawable.del_button_border);
            mood.setChecked(false); mood.setBackgroundResource(R.drawable.del_button_border);
        }


        for(int i = 0; i < moodArray.length; i++){
            if(buttonPressed == moodArray[i].getId()) {
                ov_ImgView.requestLayout();
                ov_ImgView.getLayoutParams().height = 150;
                moodTextView.setText(moodNames[i]);
                ov_moodText.setText(moodNames[i]);
                moodImgView.setImageResource(moodIDs.getResourceId(i, -1));
                ov_ImgView.setImageResource(moodIDs.getResourceId(i, -1));
            }
        }
        setOverviewDescriptions();

        switch (buttonPressed) {
            case R.id.SE_OV_addEventButton:
                setComplete();
                break;
            case R.id.overviewButton:
                scroll_overview.setVisibility(View.VISIBLE);
                addEvent.setVisibility(View.VISIBLE);
                overview.setChecked(true);
                overview.setBackgroundResource(R.drawable.sel_button_border);
                break;
            case R.id.moodButton:
            case R.id.SE_OVERVIEW_moodLayout:
            case R.id.SE_OVERVIEW_moodImgView:
            case R.id.SE_OV_mdView:
            case R.id.ov_moodSection:
                moodLayout.setVisibility(View.VISIBLE);
                mood.setChecked(true);
                mood.setBackgroundResource(R.drawable.sel_button_border);
                break;
            case R.id.thoughtsButton:
            case R.id.SE_OVERVIEW_thoughts:
                scroll_thoughts.setVisibility(View.VISIBLE);
                thoughtsLayout.setVisibility(View.VISIBLE);
                thoughts.setChecked(true);
                thoughts.setBackgroundResource(R.drawable.sel_button_border);
                break;
            case R.id.bodyButton:
            case R.id.SE_OVERVIEW_bodyPain:
                bodyLayout.setVisibility(View.VISIBLE);
                body.setChecked(true);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                body.setBackgroundResource(R.drawable.sel_button_border);
                break;
            case (R.id.ic_headButton):
            case (R.id.SE_BODY_headButton):
                if(String.valueOf(img_headButton.getTag())=="head1"){
                    img_headButton.setImageResource(R.drawable.head2);
                    img_headButton.setTag("head2");
                    headButton.setChecked(true);
                    headButton.setBackgroundResource(R.drawable.bodydel_button_border);
                    Log.e(headButton.getTextOff().toString(), "CHECK: "+headButton.isChecked());
                    noPainButtonClicked = false;
                }else{
                    img_headButton.setImageResource(R.drawable.head1);
                    img_headButton.setTag("head1");
                    headButton.setChecked(false);
                    headButton.setBackgroundResource(R.drawable.body_button_border);
                }
                break;
            case (R.id.ic_chestButton):
            case (R.id.SE_BODY_chestButton):
                if(String.valueOf(img_chestButton.getTag())=="body1"){
                    img_chestButton.setImageResource(R.drawable.body2);
                    img_chestButton.setTag("body2");
                    chestButton.setChecked(true);
                    chestButton.setBackgroundResource(R.drawable.bodydel_button_border);
                    Log.e(chestButton.getTextOff().toString(), "CHECK: "+chestButton.isChecked());
                    noPainButtonClicked = false;
                }else{
                    img_chestButton.setImageResource(R.drawable.body1);img_chestButton.setTag("body1");
                    chestButton.setChecked(false);
                    chestButton.setBackgroundResource(R.drawable.body_button_border);
                }
                break;
            case (R.id.ic_upperLButton):
            case (R.id.ic_upperRButton):
            case (R.id.SE_BODY_armButton):
                if(String.valueOf(img_upperLButton.getTag())=="upper_leftarm1" || String.valueOf(img_upperLButton.getTag())== "upper_rightarm1"){
                    img_upperLButton.setImageResource(R.drawable.upper_leftarm2);img_upperLButton.setTag("upper_leftarm2");
                    img_upperRButton.setImageResource(R.drawable.upper_rightarm2);img_upperLButton.setTag("upper_rightarm2");
                    upArmButton.setChecked(true);
                    upArmButton.setBackgroundResource(R.drawable.bodydel_button_border);
                    Log.e(upArmButton.getTextOff().toString(), "CHECK: "+upArmButton.isChecked());
                    noPainButtonClicked = false;
                }else{
                    img_upperLButton.setImageResource(R.drawable.upper_leftarm1);
                    img_upperLButton.setTag("upper_leftarm1");
                    img_upperRButton.setImageResource(R.drawable.upper_rightarm1);
                    img_upperRButton.setTag("upper_rightarm1");
                    upArmButton.setChecked(false);
                    upArmButton.setBackgroundResource(R.drawable.body_button_border);
                }
                break;
            case (R.id.ic_lowerLButton):
            case (R.id.ic_lowerRButton):
            case (R.id.SE_BODY_handButton):
                if(String.valueOf(img_lowerLButton.getTag())=="lower_lefthand1" || String.valueOf(img_lowerRButton.getTag())== "lower_righthand1"){
                    img_lowerLButton.setImageResource(R.drawable.lower_lefthand2);img_lowerLButton.setTag("lower_lefthand2");
                    img_lowerRButton.setImageResource(R.drawable.lower_righthand2);img_lowerRButton.setTag("lower_righthand2");
                    handButton.setChecked(true);
                    handButton.setBackgroundResource(R.drawable.bodydel_button_border);
                    noPainButtonClicked = false;
                    Log.e(handButton.getTextOff().toString(), "CHECK: "+handButton.isChecked());
                }else{
                    img_lowerLButton.setImageResource(R.drawable.lower_lefthand1); img_lowerLButton.setTag("lower_lefthand1");
                    img_lowerRButton.setImageResource(R.drawable.lower_righthand1); img_lowerRButton.setTag("lower_righthand1");
                    handButton.setChecked(false);
                    handButton.setBackgroundResource(R.drawable.body_button_border);
                }
                break;
            case(R.id.ic_lowerBodyButton):
            case (R.id.SE_BODY_lowBodyButton):
                if(String.valueOf(img_lowerBButton.getTag())=="lower_body1"){
                    img_lowerBButton.setImageResource(R.drawable.lower_body2);img_lowerBButton.setTag("lower_body2");
                    lowBodButton.setChecked(true);
                    lowBodButton.setBackgroundResource(R.drawable.bodydel_button_border);
                    Log.e(lowBodButton.getTextOff().toString(), "CHECK: "+lowBodButton.isChecked());
                }else{
                    img_lowerBButton.setImageResource(R.drawable.lower_body1); img_lowerBButton.setTag("lower_body1");
                    lowBodButton.setBackgroundResource(R.drawable.body_button_border);
                    lowBodButton.setChecked(false);
                }
                break;
            case(R.id.ic_legsButton):
            case (R.id.SE_BODY_legButton):
                if(String.valueOf(img_legButton.getTag())=="legs1"){
                    img_legButton.setImageResource(R.drawable.legs2);img_legButton.setTag("legs2");
                    legsButton.setBackgroundResource(R.drawable.bodydel_button_border);
                    legsButton.setChecked(true);
                    Log.e(legsButton.getTextOff().toString(), "CHECK: "+legsButton.isChecked());
                    noPainButtonClicked = false;
                }else{
                    img_legButton.setImageResource(R.drawable.legs1); img_legButton.setTag("legs1");
                    legsButton.setBackgroundResource(R.drawable.body_button_border);
                    legsButton.setChecked(false);
                }
                break;
            case(R.id.ic_feetButton):
            case (R.id.SE_BODY_feetButton):
                if(String.valueOf(img_feetButton.getTag())=="feet1"){
                    img_feetButton.setImageResource(R.drawable.feet2);img_feetButton.setTag("feet2");
                    feetButton.setBackgroundResource(R.drawable.bodydel_button_border);
                    feetButton.setChecked(true);
                    Log.e(feetButton.getTextOff().toString(), "CHECK: "+feetButton.isChecked());
                    noPainButtonClicked = false;
                }else{
                    img_feetButton.setImageResource(R.drawable.feet1); img_feetButton.setTag("feet1");
                    feetButton.setBackgroundResource(R.drawable.body_button_border);
                    feetButton.setChecked(false);
                }
                break;
            case (R.id.noPainButton):
                noPainButton();
                break;
        }
    }


    public void noPainButton(){
        noPainButtonClicked = true;
        img_headButton.setImageResource(R.drawable.head1);
        img_headButton.setTag("head1");
        headButton.setChecked(false);
        headButton.setBackgroundResource(R.drawable.body_button_border);
        img_chestButton.setImageResource(R.drawable.body1);img_chestButton.setTag("body1");
        chestButton.setChecked(false);
        chestButton.setBackgroundResource(R.drawable.body_button_border);
        img_upperLButton.setImageResource(R.drawable.upper_leftarm1);
        img_upperLButton.setTag("upper_leftarm1");
        img_upperRButton.setImageResource(R.drawable.upper_rightarm1);
        img_upperRButton.setTag("upper_rightarm1");
        upArmButton.setChecked(false);
        upArmButton.setBackgroundResource(R.drawable.body_button_border);
        img_lowerLButton.setImageResource(R.drawable.lower_lefthand1); img_lowerLButton.setTag("lower_lefthand1");
        img_lowerRButton.setImageResource(R.drawable.lower_righthand1); img_lowerRButton.setTag("lower_righthand1");
        handButton.setChecked(false);
        handButton.setBackgroundResource(R.drawable.body_button_border);
        img_lowerBButton.setImageResource(R.drawable.lower_body1); img_lowerBButton.setTag("lower_body1");
        lowBodButton.setBackgroundResource(R.drawable.body_button_border);
        lowBodButton.setChecked(false);
        img_legButton.setImageResource(R.drawable.legs1); img_legButton.setTag("legs1");
        legsButton.setBackgroundResource(R.drawable.body_button_border);
        legsButton.setChecked(false);
        img_feetButton.setImageResource(R.drawable.feet1); img_feetButton.setTag("feet1");
        feetButton.setBackgroundResource(R.drawable.body_button_border);
        feetButton.setChecked(false);
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm!= null)
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void disableKeyboard(EditText editText) {
        if (Build.VERSION.SDK_INT >= 11) {
            //editText.setTextIsSelectable(true);
        } else {
            editText.setRawInputType(InputType.TYPE_NULL);
            editText.setFocusable(true);
        }
    }



}
