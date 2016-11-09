package com.example.miura_j.ma16fresh;

import android.app.Activity;
import android.os.Bundle;

import com.sendgrid.SendGridException;

import java.io.IOException;


public class FifthActivity extends Activity {
    level5_panishment l5 = new level5_panishment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
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
}