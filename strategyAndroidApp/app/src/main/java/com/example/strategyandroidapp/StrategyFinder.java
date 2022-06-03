package com.example.strategyandroidapp;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.strategyandroidapp.SixG_Strategy.*;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.web3j.protocol.core.DefaultBlockParameterName;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

public class StrategyFinder implements Runnable, OnCompleteListener<android.location.Location> {

   private SixG_Strategy strategyContract;
   private Strategy active_strategy;
   private List<Strategy> strategies;
   private Runtime runtime = Runtime.getRuntime();
   private AppCompatActivity activity;
   private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy  hh:mm");
   private String connections[] = {"wifi","Data"};
   private FusedLocationProviderClient fusedLocationClient;

    public StrategyFinder(SixG_Strategy contract, AppCompatActivity activity){
        strategyContract = contract;
        this.activity = activity;
        strategies = new ArrayList<Strategy>();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
    }

    @Override
    public void run() {
        getAndSetStrategy();
        subscribe();
        startLoop();
    }

    private void subscribe(){
        Flowable<StrategyChangeEventResponse> flow =
                strategyContract.strategyChangeEventFlowable(DefaultBlockParameterName.LATEST,
                        DefaultBlockParameterName.LATEST);
        flow.subscribe(event -> {
            Log.i("web3j", "new event");
            getAndSetStrategy();
        });
    }


    @SuppressLint("MissingPermission")
    private void startLoop(){
        boolean running = true;
        while(running){
            try {
                TimeUnit.SECONDS.sleep(10);
                Strategy new_strategy = chooseActiveStrategy();
                if (active_strategy != new_strategy){
                    active_strategy = new_strategy;
                    enableStrategy();
                }
            } catch (InterruptedException e) {
                Log.i("web3j", e.getMessage());
            }
        }
    }

    private void getAndSetStrategy(){
        setStrategies();
        active_strategy = chooseActiveStrategy();
        enableStrategy();
    }

    public void setStrategies(){
        try {
            strategies = strategyContract.getStrategies().send();
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
           Log.i("web3j", e.getMessage());
            Log.i("web3j", sw.toString());
        }
    }

    private Strategy chooseActiveStrategy(){
        long now = System.currentTimeMillis() / 1000L;
        Strategy returnStrat = null;
        for(Strategy strat : strategies){
            if(now > strat.startDate.longValue() && now < strat.endDate.longValue()  && checkLocation(strat) &&
                    ( returnStrat == null || strat.priority.intValue() > returnStrat.priority.intValue())) {
                returnStrat = strat;
            }
        }
        return returnStrat;
    }

    @SuppressLint("MissingPermission")
    private boolean checkLocation(Strategy strat) {
        android.location.Location stratLoc = new android.location.Location((String) null);
        stratLoc.setLongitude(strat.location.x.doubleValue() / 100000);
        stratLoc.setLatitude(strat.location.y.doubleValue() / 100000);
        Task<android.location.Location> locationTask = fusedLocationClient.getLastLocation().addOnCompleteListener(this);
        try {
            synchronized (this) {
                wait();
            }
            if (locationTask.isSuccessful()) {
                Log.i("web3j", "hallo");
                android.location.Location myLocation = locationTask.getResult();
                float distance = myLocation.distanceTo(stratLoc);
                return (distance < strat.location.radius.doubleValue() / 100000);
            }
            return false;
        }catch (Exception e){
            return  false;
        }
    }

    private void enableStrategy(){
        if(active_strategy != null) {
            String name = new String(active_strategy.name.toByteArray(), StandardCharsets.UTF_8);
            String description = new String(active_strategy.description.toByteArray(), StandardCharsets.UTF_8);
            String startString = unixTimeToString(active_strategy.startDate);
            String endString = unixTimeToString(active_strategy.endDate);
            String connectionString = connections[active_strategy.connectionType.intValue()];
            setUi(name, description, startString, endString, connectionString);
            setCircle();
        }else{
            setUi("No Strategy", "", "", "", "");
            removeCircle();
        }
    }


    private void setCircle(){
        assert(active_strategy != null);
        FragmentManager fm = activity.getSupportFragmentManager();
        MapsFragment mapsFragment = (MapsFragment) fm.findFragmentByTag("mapTag");
        Location location = active_strategy.location;
        double latitude = location.y.doubleValue() / 100000;
        double longitude = location.x.doubleValue() / 100000;
        double radius = location.radius.doubleValue() / 100000;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mapsFragment.setCircle(new LatLng(latitude,longitude), radius);
            }
        });
    }

    private void removeCircle(){
        FragmentManager fm = activity.getSupportFragmentManager();
        MapsFragment mapsFragment = (MapsFragment) fm.findFragmentByTag("mapTag");
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mapsFragment.removeCircle();
            }
        });
    }
    private void setUi(String name, String desc, String start, String end, String conn){
        TextView stratNameView = ((TextView)activity.findViewById(R.id.strategyName));
        TextView stratDescriptionView = ((TextView)activity.findViewById(R.id.strategyDesc));
        TextView stratStartView = ((TextView)activity.findViewById(R.id.startTime));
        TextView stratEndView = ((TextView)activity.findViewById(R.id.endTime));
        TextView stratConnectionType = ((TextView)activity.findViewById(R.id.connectionType));
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run(){
                stratNameView.setText(name);
                stratDescriptionView.setText(desc);
                stratStartView.setText(start);
                stratEndView.setText(end);
                stratConnectionType.setText(conn);
            }
        });

    }
    private String unixTimeToString(BigInteger unixSeconds){
        Date date = new Date(unixSeconds.intValue() * 1000L);
        return sdf.format(date);
    }

    @Override
    public void onComplete(@NonNull Task<android.location.Location> task) {
        synchronized (this){
            notify();
        }
    }
}