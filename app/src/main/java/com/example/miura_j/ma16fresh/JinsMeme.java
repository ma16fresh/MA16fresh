/*package com.example.miura_j.ma16fresh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jins_jp.meme.MemeConnectListener;
import com.jins_jp.meme.MemeLib;
import com.jins_jp.meme.MemeScanListener;
import com.jins_jp.meme.MemeStatus;

import java.util.ArrayList;
import java.util.List;

public class JinsMeme extends Activity {

    String appClientId = "Application ID";
    String appClientSecret = "Client Secret";

    MemeLib memeLib;
    List<String> scannedAddresses;
    ArrayAdapter<String> scannedAddressAdapter;

    ListView deviceListView;

    boolean scanning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
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
                Intent intent = new Intent(MainActivity.this, MemeDataActivity.class);
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

        setSupportProgressBarIndeterminateVisibility(true);
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
            setSupportProgressBarIndeterminateVisibility(false);
        }
    }

    void stopScan() {
        setSupportProgressBarIndeterminateVisibility(false);
        invalidateOptionsMenu();

        //stop scanning JINS MEME
        if (memeLib.isScanning())
            memeLib.stopScan();
    }

    void clearList() {
        scannedAddressAdapter.clear();
        deviceListView.deferNotifyDataSetChanged();
    }

}*/
