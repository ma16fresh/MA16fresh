package com.ma16fresh.sleepdaha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

import com.jins_jp.meme.*;

import java.util.Timer;

public class Level3Activity extends Activity {
    Slack sl = new Slack();
    String requestJSON = "{\"text\": \"あなたの部下→ @miura-j が居眠りをしてます。懲らしめてください。\", \"channel\": \"@miura-j\", \"link_names\": 1}";

    int level = 3;
    SoundMng sm = new SoundMng();
    MemeLib memeLib;
    MemeDataItemAdapter dataItemAdapter;
    int count = 0;

    final MemeRealtimeListener memeRealtimeListener = new MemeRealtimeListener() {
        @Override
        public void memeRealtimeCallback(final MemeRealtimeData memeRealtimeData) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(memeRealtimeData.getBlinkStrength());
                    if(memeRealtimeData.getBlinkStrength() != 0){
                        count++;
                        if(count == 1){
                            Thread.interrupted();
                            memeLib.stopDataReport ();
                            finish();
                            sm.SoundStop();
                            Intent intent = new Intent(Level3Activity.this, Level4Activity.class);
                            startActivity(intent);
                        }
                    }
                }
            });
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        memeLib.startDataReport(memeRealtimeListener);
    }


    protected void onStart() {
        super.onStart();
        memeLib = MemeLib.getInstance();
        dataItemAdapter = new MemeDataItemAdapter(this);
        sm.load(getApplicationContext(),level);
        sl.postBySlack(this,requestJSON);
    }

    public boolean onTouchEvent(MotionEvent event){
        sm.SoundStop();
        return super.onTouchEvent(event);
    }
}