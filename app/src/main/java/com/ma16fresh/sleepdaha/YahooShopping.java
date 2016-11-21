package com.ma16fresh.sleepdaha;

import android.content.Context;
import android.os.StrictMode;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;


public class YahooShopping {

    public String searchByYahooshopping(Context context) throws IOException {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        String yahooshoppingKey = context.getString(R.string.yahooshopKey);
        String baseurl = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch";
        // 目覚まし時計をエンコードしたもの
        String query = "%E7%9B%AE%E8%A6%9A%E3%81%BE%E3%81%97%E6%99%82%E8%A8%88";
        URL url = new URL(baseurl + "?appid=" + yahooshoppingKey + "&query=" + query);
        System.out.println(url);
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
        return parse(response);
    }

    private String parse(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        int randomNumber = random(root.get("ResultSet").get("totalResultsReturned").asInt());
        return root.get("ResultSet").get("0").get("Result").get(String.valueOf(randomNumber)).get("Url").asText();
    }

    private int random(int totalResultsReturned){
        Random rd = new Random();
        return rd.nextInt(totalResultsReturned);
    }

}
