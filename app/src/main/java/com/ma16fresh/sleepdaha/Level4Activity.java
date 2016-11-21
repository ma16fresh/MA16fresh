package com.ma16fresh.sleepdaha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.jins_jp.meme.*;

import java.io.IOException;

public class Level4Activity extends Activity {
    Slack sl = new Slack();
    YahooShopping ys = new YahooShopping();
    String clockUrl = null;

    int level = 4;
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
                        if(count == 3){
                            Thread.interrupted();
                            memeLib.stopDataReport ();
                            finish();
                            sm.SoundStop();
                            Intent intent = new Intent(Level4Activity.this, Level5Activity.class);
                            startActivity(intent);
                        }
                    }
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4);
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
        try {
            clockUrl = ys.searchByYahooshopping(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String requestJSON = "{\"text\": \"@miura-j が居眠りをしてます。以下のURLの目覚まし時計を上長( @miura-j )に送りつけてください。\\r\\n" + clockUrl + "\", \"channel\": \"@miura-j\", \"link_names\": 1}";
        sl.postBySlack(this, requestJSON);
    }

    public boolean onTouchEvent(MotionEvent event){
        sm.SoundStop();
        return super.onTouchEvent(event);
    }
}