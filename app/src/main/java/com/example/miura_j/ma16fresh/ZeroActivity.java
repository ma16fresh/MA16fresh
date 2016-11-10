package com.example.miura_j.ma16fresh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ZeroActivity extends Activity implements View.OnClickListener {
    int level =0;
    SoundMng sm = new SoundMng();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zero);
        sm.load(getApplicationContext());
        Button nextbtn = (Button) findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(this);

        Intent i = getIntent();

        sm.SoundStart(level);
    }
    protected void onStart() {
        super.onStart();
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, NextActivity.class);
        sm.SoundStop();
        ZeroActivity.this.finish();
        startActivity(intent);
    }

}