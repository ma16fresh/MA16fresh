package com.example.miura_j.ma16fresh;

import android.content.Context;
import android.os.StrictMode;

import java.util.Date;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterPost {

    public void twitterPost(Context context) throws TwitterException{
        String twConsumerKey = context.getString(R.string.twConsumerKey);
        String twConsumerSecret = context.getString(R.string.twConsumerSecret);
        String twAccessToken = context.getString(R.string.twAccessToken);
        String twAccessTokenSecret = context.getString(R.string.twAccessTokenSecret);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        ConfigurationBuilder cb = new ConfigurationBuilder();
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter tw = tf.getInstance();
        tw.setOAuthConsumer(twConsumerKey,twConsumerSecret);
        AccessToken at = new AccessToken(twAccessToken, twAccessTokenSecret);
        tw.setOAuthAccessToken(at);
        tw.updateStatus("私は業務中に居眠りをしました。 on " + (new Date()).toString());
    }


}
