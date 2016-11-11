package com.example.miura_j.ma16fresh;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class FifthActivity extends Activity implements View.OnClickListener{
    level5_panishment l5 = new level5_panishment();
    int level =5;
    SoundMng sm = new SoundMng();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        sm.load(getApplicationContext(),level);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        //sm.SoundStart(level);

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

    @Override
    public void onClick(View view) {
        sm.SoundStop();
    }
}