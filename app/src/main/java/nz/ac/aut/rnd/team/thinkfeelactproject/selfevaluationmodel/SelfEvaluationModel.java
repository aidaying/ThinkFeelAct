package nz.ac.aut.rnd.team.thinkfeelactproject.selfevaluationmodel;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import nz.ac.aut.rnd.team.thinkfeelactproject.R;

@SuppressWarnings("deprecation")
public class SelfEvaluationModel extends TabActivity
{
    TabHost selfTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_evaluation_model);
        selfTabHost = (TabHost) findViewById(android.R.id.tabhost);
        TabHost.TabSpec tab1 = selfTabHost.newTabSpec("First Tab");
        TabHost.TabSpec tab2 = selfTabHost.newTabSpec("Second Tab");
        TabHost.TabSpec tab3 = selfTabHost.newTabSpec("Third Tab");

        tab1.setIndicator("Emotion");
        tab1.setContent(new Intent(this,AddEmotionPage.class));
        tab2.setIndicator("Thought");
        tab2.setContent(new Intent(this,AddThoughtPage.class));
        tab3.setIndicator("BodyPain");
        tab3.setContent(new Intent(this,AddBodyPainPage.class));

        selfTabHost.addTab(tab1);
        selfTabHost.addTab(tab2);
        selfTabHost.addTab(tab3);
    }
}
