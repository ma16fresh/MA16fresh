package com.ma16fresh.sleepdaha;

import android.content.Context;
import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Slack {
    public static void postBySlack(Context context,String requestJSON) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        String url = context.getString(R.string.slackUrl);
        //String requestJSON = "{\"text\": \"こいつ→ @miura-j 居眠りしてます。評価下げてください。\", \"link_names\": 1}";
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setFixedLengthStreamingMode(requestJSON.getBytes().length);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            conn.connect();

            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.write(requestJSON.getBytes("UTF-8"));
            os.flush();
            os.close();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                StringBuffer responseJSON = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    responseJSON.append(inputLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
