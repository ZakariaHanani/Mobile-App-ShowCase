package com.valasapplication.app.Activites;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import com.valasapplication.app.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class OfficeLocationActivity extends FragmentActivity {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_location);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this::onMapReady);
        }
    }

    private void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;


        LatLng officeLocation1 = new LatLng(30.4100068, -9.5578409);
        mMap.addMarker(new MarkerOptions().position(officeLocation1).title("siège 1 Agadir Maroc "));

        LatLng officeLocation2 = new LatLng(51.4939161,-0.1381619);
        mMap.addMarker(new MarkerOptions().position(officeLocation2).title("London UK"));

        LatLng officeLocation3 = new LatLng(30.4787069,-8.8563105);
        mMap.addMarker(new MarkerOptions().position(officeLocation3).title("siège 2 Taroudant Maroc"));

        // Déplacer la caméra pour afficher tous les marqueurs
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(officeLocation1, 4));
    }
}
