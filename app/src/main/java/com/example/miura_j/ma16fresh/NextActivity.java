package com.example.miura_j.ma16fresh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class NextActivity extends Activity implements View.OnClickListener {

    String message;
    int level = 1;

    Map<Integer, ByteWrapper> testMap = new HashMap<Integer, ByteWrapper>();
    NetCheck nc = new NetCheck(this);
    SoundMng sm = new SoundMng();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        sm.load(getApplicationContext());

        //ボタン生成
        Button nextbtn = (Button) findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(this);
        sm.SoundStart(level);
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

    public void SoundLoad() {
        /*
        AudioAttributes attr = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attr)
                .setMaxStreams(2)
                .build();
        wakeUp = soundPool.load(NextActivity.this, R.raw.okite, 1);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if (0 == status) {
                    SoundStart();
                }
            }
        });*/
    }
/*
    public void SoundStop() {
        soundPool.stop(streamId);
        soundPool.release();
    }

    public void SoundStart() {
        streamId = soundPool.play(wakeUp, 1F, 1F, 0, -1, 1F);
    }
    */
}
