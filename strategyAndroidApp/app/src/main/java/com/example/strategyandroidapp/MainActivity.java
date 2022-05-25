package com.example.strategyandroidapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity{
    private static final int PICK_ACCOUNT_FILE = 1;
    File keyStoreDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        keyStoreDir = new File(getExternalFilesDir(null) + "/keystore");
        setContentView(R.layout.activity_main);
        setUpAccountSpinner();
    }


    private void setUpAccountSpinner(){
        List<String> accountsList = setUpKeyStoreAndReturnAccountsAsStrings();
        Spinner accountsSpinner = (Spinner) findViewById(R.id.accountsSpinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getApplication(),
                android.R.layout.simple_spinner_dropdown_item, accountsList);
        accountsSpinner.setAdapter(arrayAdapter);
    }

    private List<String> setUpKeyStoreAndReturnAccountsAsStrings(){
        if(!keyStoreDir.exists())
            keyStoreDir.mkdir();
        List<String> stringAccounts = new LinkedList<String>();
        for(File account : keyStoreDir.listFiles()){
          stringAccounts.add(account.getName());
        }
        return stringAccounts;
    }

    public void createWeb3j(View view) {
        String url = ( "http://" + ((EditText) findViewById(R.id.nodeIp)).getText().toString());
        String accountPath = keyStoreDir+"/"+
                ((Spinner)findViewById(R.id.accountsSpinner)).getSelectedItem();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        String contractAddr = ((EditText) findViewById(R.id.contractAddress)).getText().toString();
        Intent intent = new Intent(this, ConnectedActivity.class);
        intent.putExtra("EXTRA_PASSWORD", password);
        intent.putExtra("EXTRA_ACCOUNTPATH", accountPath);
        intent.putExtra("EXTRA_URL", url);
        intent.putExtra("EXTRA_CONADDR", contractAddr);
        startActivity(intent);

    }


    public void uploadAccount(View view) {
        openFile();
    }

    public void openFile() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent, PICK_ACCOUNT_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PICK_ACCOUNT_FILE:
                copyFileToKeyStore(data);
                break;
        }
    }



    private void copyFileToKeyStore(Intent data){
        String fileName = DocumentFile.fromSingleUri(getApplicationContext(), data.getData()).getName();
        Log.i("web3j", fileName);
        try {
            InputStream in = getContentResolver().openInputStream(data.getData());
            OutputStream out = new FileOutputStream(keyStoreDir + "/" + fileName);
            byte[] buffer = new byte[1024];
            int len;
            while((len = in.read(buffer)) > 0){
                out.write(buffer, 0, len);
            }
            setUpAccountSpinner();
        } catch (FileNotFoundException e) {
            Log.i("web3j", e.getMessage());
        } catch (IOException e) {
            Log.i("web3j", e.getMessage());
        }
    }

    private void log(String text){
       Log.i("web3j", text);
    }

}