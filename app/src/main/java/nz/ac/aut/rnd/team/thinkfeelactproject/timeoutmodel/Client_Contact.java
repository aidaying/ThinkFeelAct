package nz.ac.aut.rnd.team.thinkfeelactproject.timeoutmodel;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import nz.ac.aut.rnd.team.thinkfeelactproject.R;

public class Client_Contact extends Fragment implements OnMapReadyCallback {


    private MapView mapView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View clientContactView = inflater.inflate(R.layout.fragment_client_contact, container, false);

        mapView = (MapView) clientContactView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        return clientContactView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng auckland = new LatLng(-36.881246, 174.614671);
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.addMarker(new MarkerOptions().position(auckland).title("Rainbow-In-Sight"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(auckland));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));
    }
}
