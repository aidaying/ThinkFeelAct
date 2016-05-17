package nz.ac.aut.rnd.team.thinkfeelactproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class FirstTimeSurveyFragment extends Fragment {

    DatabaseHandler mydb;
    TextView question;
    Button next;
    int i = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View surveyView = inflater.inflate(R.layout.fragment_first_time_survey, container, false);

        mydb = new DatabaseHandler(getActivity());
        final ArrayList<String> arrayList = mydb.getAllQuestion();

        question = (TextView) surveyView.findViewById(R.id.question);
        next = (Button) surveyView.findViewById(R.id.next);


        for(i = 0; i< arrayList.size();){
            question.setText(arrayList.get(i));


        }


        return surveyView;
    }
}
