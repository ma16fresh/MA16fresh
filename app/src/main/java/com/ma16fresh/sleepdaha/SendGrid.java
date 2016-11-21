package com.ma16fresh.sleepdaha;

import android.content.Context;
import android.os.StrictMode;

import java.io.IOException;
import jp.co.flect.sendgrid.SendGridClient;
import jp.co.flect.sendgrid.SendGridException;
import jp.co.flect.sendgrid.model.WebMail;

public class SendGrid {

    public void sendBySendGrid4j(Context context) throws SendGridException, IOException {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        String SMTP_AUTH_USER = context.getString(R.string.sendgridUser);
        String SMTP_AUTH_PWD  = context.getString(R.string.sendgridPass);
        SendGridClient client = new SendGridClient(SMTP_AUTH_USER, SMTP_AUTH_PWD);
        WebMail mail = new WebMail();

        //送信元と送信先の設定
        mail.setFrom(context.getString(R.string.fromAddress));
        mail.setTo(context.getString(R.string.tosAddress));
        mail.setFromName("ma16fresh");
        mail.setToName("gacahame");

        //サブジェクトとメール内容
        mail.setSubject("gachame-san-he");
        mail.setText("Test-mall-text");

        //カテゴリ(タグみたいなもの？）がつけられる
        mail.setCategory("test-category");
        client.mail(mail);
    }
}
