package nz.ac.aut.rnd.team.thinkfeelactproject.timeoutmodel;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nz.ac.aut.rnd.team.thinkfeelactproject.R;

public class Self_Care extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View selfCareView = inflater.inflate(R.layout.fragment_self_care,container,false);



        return selfCareView;
    }
}
