package com.jins_jp.memelib_realtime;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;


public class FifthActivity extends Activity{
    level5_panishment l5 = new level5_panishment();
    int level =5;
    SoundMng sm = new SoundMng();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        sm.load(getApplicationContext(),level);
        Button btn = (Button) findViewById(R.id.btn);

    }

    protected void onStart() {
        super.onStart();
        /*try {
            l5.sendgrid(this);
        } catch (SendGridException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public boolean onTouchEvent(MotionEvent event){
        sm.SoundStop();
        return super.onTouchEvent(event);
    }
}