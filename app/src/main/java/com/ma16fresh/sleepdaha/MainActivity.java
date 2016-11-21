package com.ma16fresh.sleepdaha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jins_jp.meme.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity /*implements View.OnClickListener */{

    String appClientId = "609734100985891";
    String appClientSecret = "gulec60gg9s12c1n1uocr9439jtuaejd";

    MemeLib memeLib;
    List<String> scannedAddresses;
    ArrayAdapter<String> scannedAddressAdapter;

    ListView deviceListView;

    boolean scanning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            init();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (scanning) {
            menu.findItem(R.id.action_scan).setVisible(false);
            menu.findItem(R.id.action_stop).setVisible(true);
        } else {
            menu.findItem(R.id.action_scan).setVisible(true);
            menu.findItem(R.id.action_stop).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_scan) {
            startScan();
            scanning = true;
            return true;
        } else if (itemId == R.id.action_stop) {
            stopScan();
            clearList();
            scanning = false;
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void init() {
        //Authentication and authorization of App and SDK
        MemeLib.setAppClientID(getApplicationContext(), appClientId, appClientSecret);
        memeLib = MemeLib.getInstance();//MemeLib is singleton
        memeLib.setVerbose(true);

        deviceListView = (ListView) findViewById(R.id.deviceListView);
        deviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                stopScan();

                String address = scannedAddresses.get(i);
                memeLib.connect(address);

                clearList();
                scanning = false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        //Sets MemeConnectListener to get connection result.
        memeLib.setMemeConnectListener(new MemeConnectListener() {
            @Override
            public void memeConnectCallback(boolean b) {
                //describe actions after connection with JINS MEME
                finish();
                Intent intent = new Intent(MainActivity.this, Level0Activity.class);
                startActivity(intent);
            }

            @Override
            public void memeDisconnectCallback() {
                //describe actions after disconnection from JINS MEME
            }
        });

        // Configure auto connection to JINS MEME
        memeLib.setAutoConnect(true);
    }

    void startScan() {
        scannedAddresses = new ArrayList<>();
        scannedAddressAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scannedAddresses);
        deviceListView.setAdapter(scannedAddressAdapter);

        //setSupportProgressBarIndeterminateVisibility(true);
        invalidateOptionsMenu();

        //starts scanning JINS MEME
        MemeStatus status = memeLib.startScan(new MemeScanListener() {
            @Override
            public void memeFoundCallback(String address) {
                scannedAddresses.add(address);

                runOnUiThread(new Runnable() {
                    public void run() {
                        scannedAddressAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        if (status == MemeStatus.MEME_ERROR_APP_AUTH) {
            Toast.makeText(this, "App Auth Failed", Toast.LENGTH_LONG).show();
            //setSupportProgressBarIndeterminateVisibility(false);
        }
    }

    void stopScan() {
        //setSupportProgressBarIndeterminateVisibility(false);
        invalidateOptionsMenu();

        //stop scanning JINS MEME
        if (memeLib.isScanning())
            memeLib.stopScan();
    }

    void clearList() {
        scannedAddressAdapter.clear();
        deviceListView.deferNotifyDataSetChanged();
    }
   /* @Override
    public void onClick(View view) {
        System.out.println("click");
        startScan();
    }*/
}
