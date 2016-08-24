package nz.ac.aut.rnd.team.thinkfeelactproject.normalstartup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import nz.ac.aut.rnd.team.thinkfeelactproject.R;
import nz.ac.aut.rnd.team.thinkfeelactproject.bucketmodel.BucketModelActivity;

public class InitialPage extends Activity {

    Button start;
    TextView usernameOutput;
    public static final String ShaPreferences = "ShaPreferences" ;
    public static final String Name = "nameKey" ;
    public static final String lastLoginDate = "lastLoginDateKey" ;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_page);
        sharedPreferences = getSharedPreferences(ShaPreferences, Context.MODE_PRIVATE);
        usernameOutput = (TextView) findViewById(R.id.usernameOutput);
       String name = sharedPreferences.getString(Name, null);
        usernameOutput.setText(name);
        start = (Button) findViewById(R.id.start_initial);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Calendar c = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yy");
                String date = simpleDateFormat.format(c.getTime());
                editor.putString(lastLoginDate, date);
                editor.commit();
                Intent intent = new Intent(view.getContext(),BucketModelActivity.class);
                startActivity(intent);
            }
        });

    }

}
