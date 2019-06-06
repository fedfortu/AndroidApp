package com.example.cercafarmacie.Activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.example.cercafarmacie.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if(mapFragment != null) mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        String nome = getIntent().getStringExtra("nome");
        String comune = getIntent().getStringExtra("comune");
        String latitude = getIntent().getStringExtra("latitude");
        String longitude = getIntent().getStringExtra("longitude");

        latitude=latitude.replace(',', '.');
        longitude=longitude.replace(',','.');



        double latitudine= Double.parseDouble(latitude);
        double longitudine=Double.parseDouble(longitude);

        LatLng position = new LatLng(latitudine, longitudine);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 16f));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(position);
        markerOptions.title(nome);
        markerOptions.snippet(comune);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        Marker marker = googleMap.addMarker(markerOptions);
        marker.showInfoWindow();
    }
}
