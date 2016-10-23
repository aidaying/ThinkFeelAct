package nz.ac.aut.rnd.team.thinkfeelactproject.timeoutmodel;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.auth.GooglePlayServicesAvailabilityException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import nz.ac.aut.rnd.team.thinkfeelactproject.R;

public class Client_Contact extends Fragment implements OnMapReadyCallback {


    private MapView mapView;
;
    private boolean mapsSupported = true;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MapsInitializer.initialize(getActivity());
        if (mapView != null) {
            mapView.onCreate(savedInstanceState);
        }
        initializeMap();

    }
    private void initializeMap() {

            mapView = (MapView) getActivity().findViewById(R.id.map);
            mapView.getMapAsync(this);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View clientContactView = inflater.inflate(R.layout.fragment_client_contact, container, false);

        mapView = (MapView) clientContactView.findViewById(R.id.map);


        return clientContactView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        initializeMap();
    }
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng auckland = new LatLng(-36.881246, 174.614671);
        CameraUpdate cameraPosition = CameraUpdateFactory.newLatLngZoom(auckland, 15);
        googleMap.addMarker(new MarkerOptions().position(auckland).title("Rainbow-In-Sight"));
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.moveCamera(cameraPosition);
        googleMap.animateCamera(cameraPosition);
    }
}