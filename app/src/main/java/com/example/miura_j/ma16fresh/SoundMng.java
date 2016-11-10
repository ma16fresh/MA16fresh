package com.example.miura_j.ma16fresh;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class SoundMng {
    Context context;
    boolean connect = false;

    public SoundMng(Context context){
        this.context = context;
    }

    public boolean checkNetwork() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();

        if (nInfo != null) {
            connect = nInfo.isConnected();
        }
        return connect;
    }
}

