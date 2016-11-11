package com.example.miura_j.ma16fresh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NextActivity extends Activity implements View.OnClickListener {
    SoundMng sm = new SoundMng();
    int level = 1;

    NetCheck nc = new NetCheck(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        sm.load(getApplicationContext(),level);
        //ボタン生成
        Button nextbtn = (Button) findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(this);
    }

    protected void onStart() {
        super.onStart();

    }

    public void onClick(View view) {
        sm.SoundStop();
        Intent intent = new Intent(this, SecondActivity.class);
        if (nc.checkNetwork()) {
            NextActivity.this.finish();
            startActivity(intent);
        } else {
            Toast.makeText(NextActivity.this, "ネット繋げ！！！！！！", Toast.LENGTH_SHORT).show();
        }
    }
}
