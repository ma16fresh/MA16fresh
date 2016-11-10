package com.example.miura_j.ma16fresh;

import android.content.Context;
import android.os.StrictMode;

import java.lang.*;
import java.io.File;
import java.io.IOException;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

public class level5_panishment {
    public void sendgrid(Context context) throws SendGridException, IOException {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        String apiKey            = System.getenv(context.getString(R.string.sendgridKey));

        String tos             = System.getenv(context.getString(R.string.tosAddress))/*.split(",",0)*/;
        String from              = System.getenv(context.getString(R.string.fromAddress));

        SendGrid.Email email = new SendGrid.Email();
        email.addSmtpApiTo(tos);
        /*email.addSubstitution("fullname", new String[] { "田中 太郎", "佐藤 次郎", "鈴木 三郎" });
        email.addSubstitution("familyname", new String[] { "田中", "佐藤", "鈴木" });
        email.addSubstitution("place", new String[] { "office", "home", "office" });
        email.addSection("office", "中野");
        email.addSection("home", "目黒");
        email.addCategory("category1");*/
        email.setFrom(from);
        email.setFromName("睡MEME打破");
        //email.setSubject("[sendgrid-java-example] フクロウのお名前はfullnameさん");
        //email.setText("familyname さんは何をしていますか？rn 彼はplaceにいます。");
        //email.setHtml("<strong> familyname さんは何をしていますか？</strong><br />彼はplaceにいます。");
        email.setSubject("[睡MEME打破] 睡眠注意報");
        email.setText("三浦くんが寝ています。もう一度言います。三浦くんが業務中に寝ています。");
        //email.setHtml("<strong> familyname さんは何をしていますか？</strong><br />彼はplaceにいます。");
        //File file = new File("./gif.gif");
        //email.addAttachment("owl.gif", file);

        SendGrid sendgrid = new SendGrid(apiKey);
        SendGrid.Response response = sendgrid.send(email);
        System.out.println(response.getMessage());
    }
}