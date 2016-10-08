package nz.ac.aut.rnd.team.thinkfeelactproject.timeoutmodel.exercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import nz.ac.aut.rnd.team.thinkfeelactproject.R;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.ExerciseItem;

/**
 * Created by Aida on 5/10/16.
 */

public class ExerciseListAdapter extends ArrayAdapter<ExerciseItem> {

    private ArrayList<ExerciseItem> exerciseItemArrayList;
    private Context context;

    public ExerciseListAdapter(Context context, ArrayList<ExerciseItem> exerciseItemArrayList) {
        super(context, R.layout.exercise_list_layout, exerciseItemArrayList);
        this.context = context;
        this.exerciseItemArrayList = exerciseItemArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.exercise_list_layout,null);
        }

        final ExerciseItem e = exerciseItemArrayList.get(position);
        if(e != null){}
        ImageView image = (ImageView) v.findViewById(R.id.image);
        TextView excercise = (TextView) v.findViewById(R.id.exerciseText);

        if(image != null){
            int id = context.getResources().getIdentifier(e.getImage(), "drawable", context.getPackageName());
            image.setImageResource(id);
        }
        if(excercise != null) {
            excercise.setText(e.getName());
        }
        return v;
    }
}
