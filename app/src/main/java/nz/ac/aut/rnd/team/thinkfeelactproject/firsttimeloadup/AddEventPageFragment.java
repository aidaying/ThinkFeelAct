package nz.ac.aut.rnd.team.thinkfeelactproject.firsttimeloadup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import nz.ac.aut.rnd.team.thinkfeelactproject.bucketmodel.BucketModelActivity;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.DatabaseHandler;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.Event;
import nz.ac.aut.rnd.team.thinkfeelactproject.R;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.InputFilterMinMax;


public class AddEventPageFragment extends Fragment {
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
                if(isEmptyField(eventEntry)){
                    Toast.makeText(getContext(), "Fill in Event Name First", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(isEmptyField(dayEntry)){
                    Toast.makeText(getContext(), "Fill in Day First", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(isEmptyField(monthEntry)){
                    Toast.makeText(getContext(), "Fill in Month First", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(isEmptyField(yearEntry)){
                    Toast.makeText(getContext(), "Fill in Year First", Toast.LENGTH_SHORT).show();
                    return;
                }
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
        calculateButton.setEnabled(false);
        if (mydb.getAllTheRateFromEvent().size()>=3) {
            Toast.makeText(getContext(), "You can now choose calculate the result or keep adding event", Toast.LENGTH_LONG).show();
            calculateButton.setEnabled(true);
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
    private boolean isEmptyField (EditText editText){
        return  editText.getText().toString().length() <= 0;
    }
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
                        Toast.makeText(getActivity(), "Please Fill in At Least 3 event entry First", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
                return false;
            }
        });
    }
}



