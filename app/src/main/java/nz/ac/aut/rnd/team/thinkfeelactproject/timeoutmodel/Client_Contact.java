package nz.ac.aut.rnd.team.thinkfeelactproject.timeoutmodel;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.maps.MapView;

import nz.ac.aut.rnd.team.thinkfeelactproject.R;

public class Client_Contact extends Fragment {

    private MapView mapView = null;
    private String API = "";


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View clientContactView = inflater.inflate(R.layout.fragment_client__contact, container, false);





        return clientContactView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mapView = new MapView(getActivity());
        
    }
}
