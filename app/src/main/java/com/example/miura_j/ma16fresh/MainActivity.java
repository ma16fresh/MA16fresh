package com.example.miura_j.ma16fresh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button定義
        Button shopbtn = (Button) findViewById(R.id.startbtn);
        //shopbtn.setVisibility(View.INVISIBLE);
        shopbtn.setOnClickListener(this);
    }

    //button event handler
    @Override
    public void onClick(View view) {
        //Intent(新しい画面ページ生成)
        Intent intent = new Intent(this, ZeroActivity.class);
        MainActivity.this.finish();
        startActivity(intent);

    }
}