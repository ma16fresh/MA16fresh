package com.jins_jp.memelib_realtime;

import android.content.Context;
import android.os.StrictMode;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by miura-j on 2016/11/08.
 */
public class YahooShopping {

    public String yahooshoppingSearch(Context context) throws IOException {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        String yahooshoppingrKey = context.getString(R.string.yahooshopKey);
        String baseurl = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch";
        // 目覚まし時計をエンコードしたもの
        String query = "%E7%9B%AE%E8%A6%9A%E3%81%BE%E3%81%97%E6%99%82%E8%A8%88";
        URL url = new URL(baseurl + "?appid=" + yahooshoppingrKey + "&query=" + query);
        HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();
        urlconn.setRequestMethod("GET");
        urlconn.setInstanceFollowRedirects(false);

        urlconn.connect();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(urlconn.getInputStream()));

        StringBuffer responseBuffer = new StringBuffer();
        while (true){
            String line = reader.readLine();
            if ( line == null ){
                break;
            }
            responseBuffer.append(line);
        }
        reader.close();
        urlconn.disconnect();

        String response = responseBuffer.toString();
        System.out.println(url);
        System.out.println(parse(response));
        System.out.println(url);

        return parse(response);
    }

    private String parse(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        return root.get("ResultSet").get("0").get("Result").get("0").get("Url").asText();
    }

}
