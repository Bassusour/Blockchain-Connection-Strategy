package com.example.strategyandroidapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapsFragment extends Fragment {

    public static final int LOCATION_REQUEST_CODE = 1;
    public GoogleMap gMap;
    private Circle circle;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng lyngby = new LatLng(55, 12);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(lyngby));
            gMap = googleMap;
            enableMyLocation();
        }
    };

    @SuppressLint("MissingPermission")
    public void enableMyLocation(){
        if(ContextCompat.checkSelfPermission(getActivity(), "android.permission.ACCESS_FINE_LOCATION")
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(getActivity(), new String[] {"android.permission.ACCESS_FINE_LOCATION"}, LOCATION_REQUEST_CODE);
        }else{
            gMap.setMyLocationEnabled(true);
            FusedLocationProviderClient flpc = LocationServices.getFusedLocationProviderClient(getActivity());
            flpc.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                   if (location != null){
                       gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                               new LatLng(location.getLatitude(), location.getLongitude()), 14.0f
                       ));
                   }
                }
            });
        }
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }


    public void setCircle(LatLng latLng, double radius){
        if(circle != null) circle.remove();
        CircleOptions co = new CircleOptions();
        co.center(latLng);
        int color = Color.argb(80,96,251 ,75);
        co.fillColor(color);
        Log.i("web3j", Float.toString(co.getStrokeWidth()));
        Log.i("web3j", latLng.toString());
        Log.i("web3j", Double.toString(radius));
        co.strokeWidth(2);
        co.radius(radius);
        circle = gMap.addCircle(co);
    }

    public void removeCircle(){
        if(circle != null)
        circle.remove();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}