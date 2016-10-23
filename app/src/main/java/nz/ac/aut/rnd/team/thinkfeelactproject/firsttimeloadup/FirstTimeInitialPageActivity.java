package nz.ac.aut.rnd.team.thinkfeelactproject.firsttimeloadup;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import nz.ac.aut.rnd.team.thinkfeelactproject.R;


public class FirstTimeInitialPageActivity extends Activity {

    public static final String ShaPreferences = "ShaPreferences" ;
    public static final String Name = "nameKey" ;
    public static final String joinDate = "joinDateKey" ;
    public static final String lastLoginDate = "lastLoginDateKey" ;
    SharedPreferences sharedPreferences;
    EditText nameInput;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_initial_page);
        nameInput = (EditText)findViewById(R.id.nameInput);
        sharedPreferences = getSharedPreferences(ShaPreferences, Context.MODE_PRIVATE);
        final Button valueButton = (Button) findViewById(R.id.enter);

        valueButton.setEnabled(false);

        nameInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                valueButton.setEnabled(s.toString().trim().length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        valueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInput.getText().toString();
                Calendar c = Calendar.getInstance();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yy");
                String date = simpleDateFormat.format(c.getTime());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Name, name);
                editor.putString(joinDate, date);
                editor.putString(lastLoginDate, date);
                editor.apply();
                Intent intent = new Intent(view.getContext(), FirstTimeLauncherSurveysActivity.class);
                startActivity(intent);
                onBackPressed();
            }

        });
    }

    @Override
    public void onBackPressed() {
        super.onDestroy();
    }
}
