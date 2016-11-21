package com.ma16fresh.sleepdaha;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

import java.io.IOException;

import jp.co.flect.sendgrid.SendGridException;


public class Level5Activity extends Activity{
    SendGrid sg = new SendGrid();
    int level = 5;
    SoundMng sm = new SoundMng();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level5);
    }

    protected void onStart() {
        super.onStart();
        sm.load(getApplicationContext(),level);
        try {
            sg.sendBySendGrid4j(this);
        } catch (SendGridException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean onTouchEvent(MotionEvent event){
        sm.SoundStop();
        return super.onTouchEvent(event);
    }
}