package com.example.strategyandroidapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothManager;
import android.content.Context;

public class DummyConnection {
    private Activity activity;
    private BluetoothManager bm;
    private final int WIFI = 0;
    private final int DATA = 1;
    public DummyConnection(Activity activity) {
        this.activity = activity;
         bm = (BluetoothManager) activity.getSystemService(Context.BLUETOOTH_SERVICE);
    }

    @SuppressLint("MissingPermission")
    public void setConnection(int connectionType){
            if(connectionType == DATA){
                bm.getAdapter().enable();
            }else{
                bm.getAdapter().disable();
            }
    }
}
