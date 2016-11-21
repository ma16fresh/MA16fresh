package com.ma16fresh.sleepdaha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.jins_jp.meme.*;

import facebook4j.FacebookException;
import twitter4j.TwitterException;


public class Level2Activity extends Activity{
    Twitter tw = new Twitter();
    Facebook fb = new Facebook();
    SoundMng sm = new SoundMng();
    int level = 2;
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
                            Intent intent = new Intent(Level2Activity.this, Level3Activity.class);
                            startActivity(intent);
                        }
                    }
                }
            });
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        memeLib.startDataReport(memeRealtimeListener);
    }


    protected void onStart(){
        super.onStart();
        memeLib = MemeLib.getInstance();
        dataItemAdapter = new MemeDataItemAdapter(this);
        sm.load(getApplicationContext(),level);
        try {
            tw.postByTwitter4j(this);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        try {
            fb.postByFacebook4j(this);
        } catch (FacebookException e) {
            e.printStackTrace();
        }
    }

    public boolean onTouchEvent(MotionEvent event){
        sm.SoundStop();
        return super.onTouchEvent(event);
    }

}