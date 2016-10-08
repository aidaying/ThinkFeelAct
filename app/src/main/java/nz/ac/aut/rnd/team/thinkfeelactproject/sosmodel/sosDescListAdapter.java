package nz.ac.aut.rnd.team.thinkfeelactproject.sosmodel;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import nz.ac.aut.rnd.team.thinkfeelactproject.R;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.DatabaseHandler;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.Desc;

/**
 * Created by Aida on 27/09/16.
 */

public class sosDescListAdapter extends ArrayAdapter<Desc>{

    private DatabaseHandler mydb;
    private ArrayList<Desc> descArrayList;
    private Context context;

    public sosDescListAdapter(Context context, ArrayList<Desc> descArrayList) {
        super(context, R.layout.sos_list_layout, descArrayList);
        this.context = context;
        this.descArrayList = descArrayList;
        mydb = new DatabaseHandler(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sos_list_layout,null);
        }

        final Desc d = descArrayList.get(position);
        if(d != null){}
        TextView descOutput = (TextView) v.findViewById(R.id.infoText);
        final TextView numOutput = (TextView) v.findViewById(R.id.numText);
        Button addOne = (Button) v.findViewById(R.id.addOneBtn);

        if(descOutput != null){
            descOutput.setText(d.getDesc());
        }
        if(numOutput != null){
            numOutput.setText(String.valueOf(d.getNum()));
        }
        addOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.setNum(d.getNum() + 1);
                mydb.alterDescInfo(d);
                notifyDataSetChanged();
            }
        });
        return v;
    }
}
