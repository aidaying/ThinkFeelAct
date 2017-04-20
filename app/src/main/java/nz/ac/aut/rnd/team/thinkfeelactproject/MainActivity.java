package nz.ac.aut.rnd.team.thinkfeelactproject;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import nz.ac.aut.rnd.team.thinkfeelactproject.firsttimeloadup.FirstTimeInitialPageActivity;
import nz.ac.aut.rnd.team.thinkfeelactproject.normalstartup.InitialPage;
/**
**This class is used to control which page to redirct to when the user start the application
**/
public class MainActivity extends FragmentActivity {


    public static final String ShaPreferences = "ShaPreferences" ;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MultiDex.install(this);
        // The Shared Perferences is used to store the user first name and last name, so it can remember in the application
        sharedPreferences = getSharedPreferences(ShaPreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //The following method is used to check whether the user is using this application for the first time or not,
        //If so, it will set the first time to true and remember it in the shared preferences 
        //Start up the activity with the survey question
        //else it will start the bucket model page if this is the second time.
        boolean firstTime = sharedPreferences.getBoolean("first",true);
        if(firstTime){
            editor.putBoolean("first",false);
            editor.apply();
            Intent intent= new Intent(this,FirstTimeInitialPageActivity.class);
            startActivity(intent);

        }
        else{
            Intent intent= new Intent(this,InitialPage.class);
            startActivity(intent);
        }


    }

}
