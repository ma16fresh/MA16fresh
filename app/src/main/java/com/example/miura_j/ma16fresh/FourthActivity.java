package com.example.miura_j.ma16fresh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FourthActivity extends Activity implements View.OnClickListener {
    SlackPost sp = new SlackPost();
    YahooShopping ap = new YahooShopping();
    String clockUrl = null;
    Map<Integer, ByteWrapper> testMap = new HashMap<Integer, ByteWrapper>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
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
        sp.slackPost(this,requestJSON);
    }

    public void onClick(View view) {
        String result = "next go";
        Intent intent = new Intent(this, FifthActivity.class);
        intent.putExtra("message",result);
        FourthActivity.this.finish();
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解放しないとメモリ使用量は減らない
        this.testMap.clear();
        this.testMap = null;
    }
}