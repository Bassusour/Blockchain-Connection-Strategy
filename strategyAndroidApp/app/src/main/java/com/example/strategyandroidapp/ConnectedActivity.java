package com.example.strategyandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ConnectedActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("web3j", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected);
        connectToContract();
        setUpMap();
    }

    private void setUpMap(){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.mapFragmentContainer, new MapsFragment(), "mapTag").commit();
    }

    private void connectToContract(){
        Log.i("web3j", "starting thread");
        Intent intent = getIntent();
        String url = intent.getStringExtra("EXTRA_URL");
        String accountPath = intent.getStringExtra("EXTRA_ACCOUNTPATH");
        String password = intent.getStringExtra("EXTRA_PASSWORD");
        String contractAddress = intent.getStringExtra("EXTRA_CONADDR");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("web3j", "connecting");
                try {
                    SixG_Strategy contract = ContractCreater.create(url, accountPath, password, contractAddress);
                    Log.i("web3j", "connected");
                    removeLoadingUi();
                    startFindingBestStrategy(contract);
                }catch (Exception e){
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    e.printStackTrace(pw);
                    Log.i("web3j", sw.toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast toast = Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT);
                            toast.show();
                            finish();
                        }
                    });
                }
            }
       }).start();
    }

    private void startFindingBestStrategy(SixG_Strategy contract) {
        StrategyFinder sf = new StrategyFinder(contract, this);
        new Thread(sf).start();
    }

    private void removeLoadingUi() {
       runOnUiThread(new Runnable() {
           @Override
           public void run() {
               Log.i("web3j", "removing");
               findViewById(R.id.LineaLayout).setVisibility(View.VISIBLE);
               View loadingText = findViewById(R.id.connecting);
               ((ViewGroup)loadingText.getParent()).removeView(loadingText);
           }
       });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == MapsFragment.LOCATION_REQUEST_CODE &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED){
            ((MapsFragment)getSupportFragmentManager().findFragmentByTag("mapTag")).enableMyLocation();
        }
    }
}