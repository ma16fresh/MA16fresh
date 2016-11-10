package com.example.miura_j.ma16fresh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import facebook4j.FacebookException;
import twitter4j.TwitterException;


public class SecondActivity extends Activity implements View.OnClickListener {
    TwitterPost tp = new TwitterPost();
    FacebookPost fp = new FacebookPost();
    SoundMng sm = new SoundMng();
    int level =2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        sm.load(getApplicationContext());
        Button nextbtn = (Button) findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(this);
        sm.SoundStart(level);
    }

    protected void onStart(){
        super.onStart();
        try {
            tp.twitterPost(this);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        try {
            fp.facebookPost(this);
        } catch (FacebookException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        sm.SoundStop();
        String result = "next go";
        Intent intent = new Intent(this, ThirdActivity.class);
        intent.putExtra("message",result);
        SecondActivity.this.finish();
        startActivity(intent);
    }

}