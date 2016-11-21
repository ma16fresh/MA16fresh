package com.ma16fresh.sleepdaha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.jins_jp.meme.*;

public class Level1Activity extends Activity {
    int level = 1;
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
                            Intent intent = new Intent(Level1Activity.this, Level2Activity.class);;
                            startActivity(intent);
                        }
                    }
                }
            });
        }
    };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);
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
    }

    public boolean onTouchEvent(MotionEvent event){
        sm.SoundStop();
        return super.onTouchEvent(event);
    }
}
