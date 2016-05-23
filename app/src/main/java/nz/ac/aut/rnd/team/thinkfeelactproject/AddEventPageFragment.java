package nz.ac.aut.rnd.team.thinkfeelactproject;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import java.io.IOException;

import static nz.ac.aut.rnd.team.thinkfeelactproject.R.layout.fragment_add_event_page;

public class AddEventPageFragment extends Fragment {
    DatabaseHandler mydb;
    EditText eventEntry;
    SeekBar eventRateBar;
    EditText dateEntry;
    Button addButton;
    Button calculateButton;
    Event event;
    int rate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View addEventView =  inflater.inflate(fragment_add_event_page, container, false);

        mydb = new DatabaseHandler(getActivity().getApplicationContext());


        eventEntry = (EditText) addEventView.findViewById(R.id.eventEntry);
        dateEntry = (EditText) addEventView.findViewById(R.id.dateEntry);
        eventRateBar = (SeekBar) addEventView.findViewById(R.id.eventRateBar);
        eventRateBar.setProgress(0);
        eventRateBar.incrementProgressBy(1);
        eventRateBar.setMax(10);
        eventRateBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rate = eventRateBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        addButton = (Button) addEventView.findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = eventEntry.getText().toString();
                String date = dateEntry.getText().toString();


                event = new Event(name,date,rate);
                mydb.addEventCurrent(event);



            }
        });
        calculateButton = (Button) addEventView.findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StressCalculator stressCalculator = new StressCalculator();

            }
        });
        return addEventView;
    }


}
