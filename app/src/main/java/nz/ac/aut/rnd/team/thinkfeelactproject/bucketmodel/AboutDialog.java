package nz.ac.aut.rnd.team.thinkfeelactproject.bucketmodel;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import nz.ac.aut.rnd.team.thinkfeelactproject.R;

/**
 * Created by viv on 11/10/2016.
 */

public class AboutDialog extends DialogFragment implements View.OnClickListener {
    Button okay;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bucket_info_dialog, null);
        okay = (Button) view.findViewById(R.id.bOkayButton);
        okay.setOnClickListener(this);
        setCancelable(true);
        getDialog().setTitle("Bucket Model");
        return view;
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.bOkayButton){
            dismiss();
        }
    }
}
