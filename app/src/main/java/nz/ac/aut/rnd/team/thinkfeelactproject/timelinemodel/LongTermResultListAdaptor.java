package nz.ac.aut.rnd.team.thinkfeelactproject.timelinemodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import nz.ac.aut.rnd.team.thinkfeelactproject.R;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.DatabaseHandler;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.LongTermSurvey;

/**
 * Created by Aida on 22/10/2016.
 */

public class LongTermResultListAdaptor extends ArrayAdapter<LongTermSurvey> {

    private DatabaseHandler mydb;
    private ArrayList<LongTermSurvey> longTermItemArrayList;
    private Context context;

    public LongTermResultListAdaptor(Context context, ArrayList<LongTermSurvey> longTermItemArrayList) {
        super(context, R.layout.result_list_long_term_layout, longTermItemArrayList);
        this.context = context;
        this.longTermItemArrayList = longTermItemArrayList;
        mydb = new DatabaseHandler(context);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.result_list_long_term_layout,null);
        }

        final LongTermSurvey l = longTermItemArrayList.get(position);
        if(l != null){}
        TextView idOutput = (TextView) v.findViewById(R.id.longTermQuestionIdDisplay);
        TextView questionOutput = (TextView) v.findViewById(R.id.longTermQuestionDisplay);
        TextView rateOutput = (TextView) v.findViewById(R.id.longTermRateDisplay);
        ImageButton edit = (ImageButton) v.findViewById(R.id.editLongTermRatingButton);

        if(idOutput != null){
            idOutput.setText(String.valueOf(l.getID()));
        }
        if(questionOutput != null){
            questionOutput.setText(l.getQuestion());
        }
        if(rateOutput != null){
            rateOutput.setText(String.valueOf(l.getRating().intValue()));
        }
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l.setRating(1.0);
                mydb.addLongTermSurvey(l);
                notifyDataSetChanged();
            }
        });

        return v;
    }
}
