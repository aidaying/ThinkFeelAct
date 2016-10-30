package nz.ac.aut.rnd.team.thinkfeelactproject.timelinemodel;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import nz.ac.aut.rnd.team.thinkfeelactproject.R;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.DatabaseHandler;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.InputFilterMinMax;
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
                final Dialog addNewDialog = new Dialog(v.getContext());
                addNewDialog.setContentView(R.layout.layout_long_term_result_dialog);
                addNewDialog.setTitle("Update Rating");
                final Button ltEditBtn = (Button) addNewDialog.findViewById(R.id.ltEditBtn);
                Button ltCancelBtn = (Button) addNewDialog.findViewById(R.id.ltCancelBtn);
                final EditText ltRateAdjustInput = (EditText) addNewDialog.findViewById(R.id.ltRateAdjustInput);
                ltRateAdjustInput.setFilters(new InputFilter[]{new InputFilterMinMax("0","10")});
                addNewDialog.show();
                ltEditBtn.setEnabled(false);
                ltRateAdjustInput.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        ltEditBtn.setEnabled(s.toString().trim().length() > 0);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
                ltEditBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Double rate = Double.parseDouble(ltRateAdjustInput.getText().toString());
                        LongTermSurvey lt = new LongTermSurvey();
                        if(rate == 0.0){
                            lt.setID(l.getID());
                            lt.setRating(rate);
                            lt.setAnswerTF("False");
                        }else {
                            lt.setID(l.getID());
                            lt.setRating(rate);
                            lt.setAnswerTF("True");
                        }
                        mydb.alterLongTermRate(lt);
                        notifyDataSetChanged();
                        addNewDialog.dismiss();

                    }
                });

                ltCancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addNewDialog.cancel();
                    }
                });


            }
        });

        return v;
    }
}
