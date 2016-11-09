package com.example.miura_j.ma16fresh;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class NextActivity extends Activity implements View.OnClickListener {

    SoundPool soundPool;
    int wakeUp;
    int streamId;
    String message;
    Map<Integer, ByteWrapper> testMap = new HashMap<Integer, ByteWrapper>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        SoundLoad();

        //ボタン生成
        Button returnbtn = (Button) findViewById(R.id.returnbtn);
        returnbtn.setOnClickListener(this);
        Button nextbtn = (Button) findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(this);

        Intent i = getIntent();
        message = i.getStringExtra("message");

    }

    protected void onStart() {
        super.onStart();

    }

    protected void onResume() {
        super.onResume();
        // SoundStart();
    }

    public void onClick(View view) {
        //音止める
        switch (view.getId()) {
            case R.id.nextbtn:
                Intent intent = new Intent(this, SecondActivity.class);

                if (message.equals("ONLINE")) {
                    NextActivity.this.finish();
                    startActivity(intent);
                    System.out.println("投稿する");
                    SoundStop();
                } else {
                    Toast.makeText(NextActivity.this, "ネット繋げ！！！！！！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.returnbtn:
                //Intent mainIntent = new Intent(this, MainActivity.class);
                //startActivity(mainIntent);
                break;
        }
    }

    public void SoundLoad() {
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
                    System.out.println(status);
                    SoundStart();
                }
            }
        });
    }

    public void SoundStop() {
        soundPool.stop(streamId);
        soundPool.release();
    }

    public void SoundStart() {
        streamId = soundPool.play(wakeUp, 1F, 1F, 0, -1, 1F);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解放しないとメモリ使用量は減らない
        this.testMap.clear();
        this.testMap = null;
    }
}
