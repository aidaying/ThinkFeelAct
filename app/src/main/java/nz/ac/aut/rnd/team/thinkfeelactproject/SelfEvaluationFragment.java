package nz.ac.aut.rnd.team.thinkfeelactproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelfEvaluationFragment extends Fragment {





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View selfEvaluationView =  inflater.inflate(R.layout.fragment_self_evaluation, container, false);



        return selfEvaluationView;
    }

}
