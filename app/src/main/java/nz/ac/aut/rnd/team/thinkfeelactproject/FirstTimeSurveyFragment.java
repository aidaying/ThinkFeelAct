package nz.ac.aut.rnd.team.thinkfeelactproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class FirstTimeSurveyFragment extends Fragment implements View.OnClickListener{

    DatabaseHandler mydb;
    TextView question;
    Button next;
    int i = 0;
    List<Survey> arrayList;
    Survey survey;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View surveyView = inflater.inflate(R.layout.fragment_first_time_survey, container, false);

        mydb = new DatabaseHandler(getActivity());
        arrayList = mydb.getAllQuestion();
        survey = arrayList.get(i);
        question = (TextView) surveyView.findViewById(R.id.question);
        next = (Button) surveyView.findViewById(R.id.nextbtn);
        setQuestionView();
        next.setOnClickListener(this);
        return surveyView;
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        survey = arrayList.get(i);
        setQuestionView();
        if(i>61){


        }

    }

    private void setQuestionView(){
        question.setText(arrayList.get(i).getQuestion());
        i++;

    }
}
