package com.jins_jp.memelib_realtime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.jins_jp.meme.MemeLib;
import com.jins_jp.meme.MemeRealtimeData;
import com.jins_jp.meme.MemeRealtimeListener;

public class ZeroActivity extends Activity {
    int level =0;
    SoundMng sm = new SoundMng();
    MemeLib memeLib;
    MemeDataItemAdapter dataItemAdapter;
    int bling_speed = 0;

    final MemeRealtimeListener memeRealtimeListener = new MemeRealtimeListener() {
        @Override
        public void memeRealtimeCallback(final MemeRealtimeData memeRealtimeData) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    bling_speed = memeRealtimeData.getBlinkSpeed();
                    System.out.println(bling_speed);
                    if(bling_speed != 0){
                        Intent intent = new Intent(ZeroActivity.this, NextActivity.class);
                        ZeroActivity.this.finish();
                        startActivity(intent);
                    }else{
                    //setSupportProgressBarIndeterminateVisibility(true);
                    dataItemAdapter.updateMemeData(memeRealtimeData);}
                    //dataItemAdapter.notifyDataSetChanged();
                    //setSupportProgressBarIndeterminateVisibility(false);
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zero);

        sm.load(getApplicationContext(),level);

        memeLib = MemeLib.getInstance();
        dataItemAdapter = new MemeDataItemAdapter(this);
    }
    protected void onStart() {
        super.onStart();
        System.out.println("???????????????????????");
    }

    protected void onDestroy() {
        super.onDestroy();
    }

   @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //Starts receiving realtime data
        memeLib.startDataReport(memeRealtimeListener);
    }
}