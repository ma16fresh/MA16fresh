package com.example.miura_j.ma16fresh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;


public class ZeroActivity extends Activity implements View.OnClickListener {
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zero);
        Button nextbtn = (Button) findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(this);

        Intent i = getIntent();
        message = i.getStringExtra("message");
    }
    protected void onStart() {
        super.onStart();
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, NextActivity.class);
        intent.putExtra("message",message);
        ZeroActivity.this.finish();
        startActivity(intent);
    }

}