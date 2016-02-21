package ua.marinovskiy.twitterclient.screens.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import butterknife.Bind;
import ua.marinovskiy.twitterclient.R;
import ua.marinovskiy.twitterclient.utils.Prefs;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.toolbar_login)
    Toolbar mToolbar;

    @Bind(R.id.twitter_login_button)
    TwitterLoginButton mLoginButton;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Nullable
    @Override
    protected Toolbar getToolbar() {
        return mToolbar;
    }

    @Nullable
    @Override
    protected String getActivityTitle() {
        return null;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        //mLoginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        if ((TextUtils.isEmpty(Prefs.getAccessToken())) || (Prefs.isExpired())) {
            mLoginButton.setCallback(new Callback<TwitterSession>() {
                @Override
                public void success(Result<TwitterSession> result) {
                    TwitterSession session = result.data;
                    Prefs.setAccessToken(session.getAuthToken().token);
                    Prefs.setExpired(session.getAuthToken().isExpired());
                    Prefs.setUserId(session.getUserId());
                    Prefs.setUserName(session.getUserName());

                    String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }

                @Override
                public void failure(TwitterException e) {
                    Log.d("TwitterKit", "Login with Twitter failure", e);
                }
            });
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mLoginButton.onActivityResult(requestCode, resultCode, data);
    }
}
