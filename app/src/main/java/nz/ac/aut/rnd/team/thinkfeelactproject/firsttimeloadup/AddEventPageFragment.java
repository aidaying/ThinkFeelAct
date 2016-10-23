package nz.ac.aut.rnd.team.thinkfeelactproject.firsttimeloadup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import nz.ac.aut.rnd.team.thinkfeelactproject.bucketmodel.BucketModelActivity;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.DatabaseHandler;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.Event;
import nz.ac.aut.rnd.team.thinkfeelactproject.R;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.InputFilterMinMax;


public class AddEventPageFragment extends Fragment implements TextWatcher{
    private DatabaseHandler mydb;
    private EditText eventEntry;
    private SeekBar eventRateBar;
    private EditText dayEntry;
    private EditText yearEntry;
    private EditText monthEntry;
    private Button addButton;
    private Button calculateButton;
    private TextView ratePercentage;
    private Event event;
    private String added_date;
    private double rate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View addEventView = inflater.inflate(R.layout.fragment_add_event_page, container, false);


        mydb = new DatabaseHandler(getActivity().getApplicationContext());
        ratePercentage = (TextView) addEventView.findViewById(R.id.ratePercentage);

        eventEntry = (EditText) addEventView.findViewById(R.id.eventEntry);
        dayEntry = (EditText) addEventView.findViewById(R.id.dayEntry);
        dayEntry.setFilters(new InputFilter[]{new InputFilterMinMax("1","31")});
        monthEntry = (EditText) addEventView.findViewById(R.id.monthEntry);
        monthEntry.setFilters(new InputFilter[]{new InputFilterMinMax("1","12")});
        yearEntry = (EditText) addEventView.findViewById(R.id.yearEntry);
        yearEntry.setFilters(new InputFilter[]{new InputFilterMinMax("1","2100")});
        eventRateBar = (SeekBar) addEventView.findViewById(R.id.eventRateBar);
        eventRateBar.setProgress(0);
        eventRateBar.incrementProgressBy(1);
        eventRateBar.setMax(10);
        eventRateBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                ratePercentage.setText(String.valueOf(rate));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                rate = seekBar.getProgress();
            }
        });
        addButton = (Button) addEventView.findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = eventEntry.getText().toString();
                String dd = dayEntry.getText().toString();
                String mm = monthEntry.getText().toString();
                String yy = yearEntry.getText().toString();
                added_date = dd + "-" + mm + "-" + yy;
                event = new Event();
                event.setName(name);
                event.setDate(added_date);
                event.setRating(rate);
                event.setEmotion("");
                event.setPain("");
                event.setThoughtwhat("");
                event.setThoughtwhyhow("");
                event.setThoughtfeel("");
                mydb.addEvent(event);
                Intent intent = new Intent(getActivity(), FirstTimeLauncherSurveysActivity.class);
                intent.putExtra("fragment_id",2 );
                startActivity(intent);

            }
        });
        calculateButton = (Button) addEventView.findViewById(R.id.calculateButton);

        if (mydb.getAllTheRateFromEvent().size()>=3) {

            calculateButton.setClickable(true);
            calculateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getActivity(), BucketModelActivity.class);
                    startActivity(intent);
                    getActivity().onBackPressed();

                }
            });

        }
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(addEventView.getWindowToken(), 0);

        return addEventView;
    }


    public void hide(EditText v) {
        InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}



