package com.ma16fresh.sleepdaha;

import android.content.Context;
import android.os.StrictMode;

import java.util.Date;

import facebook4j.*;
import facebook4j.auth.AccessToken;
import facebook4j.conf.ConfigurationBuilder;

public class Facebook {

    public void postByFacebook4j(Context context) throws FacebookException{
        String fbAppId = context.getString(R.string.fbAppId);
        String fbAppSecret = context.getString(R.string.fbAppSecret);
        String fbAccessToken = context.getString(R.string.fbAccessToken);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        ConfigurationBuilder cb = new ConfigurationBuilder();
        FacebookFactory ff = new FacebookFactory(cb.build());
        facebook4j.Facebook fb = ff.getInstance();
        fb.setOAuthAppId(fbAppId,fbAppSecret);
        AccessToken at = new AccessToken(fbAccessToken);
        fb.setOAuthAccessToken(at);
        fb.postStatusMessage("私は業務中に居眠りをしました。 on " + (new Date()).toString());
    }
}
