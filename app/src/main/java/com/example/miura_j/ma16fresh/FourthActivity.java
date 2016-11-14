package com.example.miura_j.ma16fresh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import java.io.IOException;


public class FourthActivity extends Activity implements View.OnClickListener {
    SlackPost sp = new SlackPost();
    YahooShopping ap = new YahooShopping();
    String clockUrl = null;

    int level = 4;
    SoundMng sm = new SoundMng();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        sm.load(getApplicationContext(),level);
        Button nextbtn = (Button) findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(this);
    }

    protected void onStart() {
        super.onStart();
        try {
            clockUrl = ap.yahooshoppingSearch(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String requestJSON = "{\"text\": \"こいつ→ @miura-j 居眠りしてます。これ送りつけてください(給料から天引きで＾＾)。\\r\\n" + clockUrl + "\", \"channel\": \"@miura-j\", \"link_names\": 1}";
        sp.slackPost(this, requestJSON);
    }

    public void onClick(View view) {
        sm.SoundStop();
        Intent intent = new Intent(this, FifthActivity.class);
        FourthActivity.this.finish();
        startActivity(intent);
    }

    public boolean onTouchEvent(MotionEvent event){
        sm.SoundStop();
        return super.onTouchEvent(event);
    }
}