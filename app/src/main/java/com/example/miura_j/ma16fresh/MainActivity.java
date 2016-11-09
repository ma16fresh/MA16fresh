package com.example.miura_j.ma16fresh;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    String message;
    boolean connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        message = "OFFLINE";
        connect = false;

        //button定義
        Button shopbtn = (Button) findViewById(R.id.shopbtn);
        //shopbtn.setVisibility(View.INVISIBLE);
        shopbtn.setOnClickListener(this);

    }

    protected void onResume() {
        super.onResume();
        //network 初期情報
        checkNetwork();
        textViewDisp(message);
    }

    //textViewの表示
    public void textViewDisp(String message) {
        TextView resultText = (TextView) findViewById(R.id.resultText);
        resultText.setText(message);
    }

    //button event handler
    @Override
    public void onClick(View view) {
        //Toastで表示するメッセージ渡す
        toastDisp(message);
        checkNetwork();
        textViewDisp(message);

        //Intent(新しい画面ページ生成)
        Intent intent = new Intent(this, NextActivity.class);
        intent.putExtra("message", message);
        startActivity(intent);
    }

    //network 接続状況
    public String checkNetwork() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();

        if (nInfo != null) {
            connect = nInfo.isConnected();
        }
        if (connect != false) {
            message = "ONLINE";
        } else {
            message = "OFFLINE";
        }
        return message;
    }

    //投げられたメッセージを表示
    public void toastDisp(String message) {

        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}