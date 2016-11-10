package com.example.miura_j.ma16fresh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends Activity implements View.OnClickListener {
    SlackPost sp = new SlackPost();
    String requestJSON = "{\"text\": \"こいつ→ @miura-j 居眠りしてます。評価下げてください。\", \"channel\": \"@miura-j\", \"link_names\": 1}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Button nextbtn = (Button) findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(this);
    }
    protected void onStart() {
        super.onStart();
        sp.slackPost(this,requestJSON);
    }

    public void onClick(View view) {
        String result = "next go";
        Intent intent = new Intent(this, FourthActivity.class);
        intent.putExtra("message",result);
        ThirdActivity.this.finish();
        startActivity(intent);
    }

}