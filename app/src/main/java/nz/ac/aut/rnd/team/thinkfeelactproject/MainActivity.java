package nz.ac.aut.rnd.team.thinkfeelactproject;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity {


    public static final String ShaPreferences = "ShaPreferences" ;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MultiDex.install(this);

        sharedPreferences = getSharedPreferences(ShaPreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
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
