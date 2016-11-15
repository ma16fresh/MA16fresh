package com.example.miura_j.ma16fresh;

import android.content.Context;
import android.os.StrictMode;

import java.lang.*;
import java.io.File;
import java.io.IOException;
import com.sendgrid.*;

public class Example {
    public static void main(String[] args) throws IOException {
        Email from = new Email("kan-y@gnavi.co.jp");
        String subject = "Hello World from the SendGrid Java Library!";
        Email to = new Email("miura-j@gnavi.co.jp");
        Content content = new Content("text/plain", "Hello, Email!");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            Response response = sg.api(request);
            System.out.println(response.statusCode);
            System.out.println(response.body);
            System.out.println(response.headers);
        } catch (IOException ex) {
            throw ex;
        }
    }
}