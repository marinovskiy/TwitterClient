package ua.marinovskiy.twitterclient;

import android.app.Application;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
import ua.marinovskiy.twitterclient.utils.Prefs;

public class MyApplication extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    public static final String TWITTER_KEY = "wM4pJzqAZUH9pubUQ9X0Tj5hv";
    public static final String TWITTER_SECRET = "JTPikUb9GI2xzbgtWpTABaEtRJdNiIsuiGQmAxk9GnIL1NSXtq";

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        Prefs.init(this);
    }
}
