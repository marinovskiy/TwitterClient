package ua.marinovskiy.twitterclient.screens.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Callback;
import retrofit2.Response;
import ua.marinovskiy.twitterclient.R;
import ua.marinovskiy.twitterclient.api.RequestManager;
import ua.marinovskiy.twitterclient.models.db.Tweet;
import ua.marinovskiy.twitterclient.models.network.NetworkTweet;
import ua.marinovskiy.twitterclient.ui.adapters.TimeLineAdapter;
import ua.marinovskiy.twitterclient.utils.DbManager;
import ua.marinovskiy.twitterclient.utils.ModelConverter;
import ua.marinovskiy.twitterclient.utils.Utils;

public class MainActivity extends BaseActivity {

    private static final String TAG = "LOG_TAG";

    @Bind(R.id.coordinator_layout_main)
    CoordinatorLayout mCoordinatorLayout;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.rv_tweets_list)
    RecyclerView mRecyclerView;

    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;

    /*@Bind(R.id.drawer)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.nav_view)
    NavigationView mNavigationView;

    @Bind(R.id.nav_header_tv_user_name)
    TextView mTvUserName;*/

    private Snackbar mSnackbar;

    private RealmResults<Tweet> mTweets;

    private final RealmChangeListener mRealmChangeListener = new RealmChangeListener() {
        @Override
        public void onChange() {
            if (mTweets != null && mTweets.isLoaded())
            updateUI(mTweets);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Nullable
    @Override
    protected Toolbar getToolbar() {
        return mToolbar;
    }

    @Nullable
    @Override
    protected String getActivityTitle() {
        return getString(R.string.app_name);
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        /*if (Prefs.getUserName() != null) {
            mTvUserName.setText(Prefs.getUserName());
        }
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_drawer0:
                        break;
                    case R.id.action_drawer1:
                        break;
                    case R.id.action_drawer2:
                        break;
                    case R.id.action_drawer3:
                        break;
                }

                mDrawerLayout.closeDrawers();
                return true;
            }
        });*/
        fetchTweetsFromDb();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (Utils.hasInternet(getApplicationContext())) {
            setProgressVisibility(true);
            fetchTweetsFromNetwork();
        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
        }
        mSnackbar = Snackbar
                .make(mCoordinatorLayout, "Can't load timeline", Snackbar.LENGTH_INDEFINITE)
                .setAction("RELOAD", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fetchTweetsFromNetwork();
                    }
                });
    }

    @Override
    protected void onPause() {
        if (mTweets != null) {
            mTweets.removeChangeListener(mRealmChangeListener);
        }
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }*/
    }

    private void fetchTweetsFromNetwork() {
        RequestManager.getInstance().userTimeLine().enqueue(new Callback<List<NetworkTweet>>() {
            @Override
            public void onResponse(Response<List<NetworkTweet>> response) {
                if (response.body() != null) {
                    List<Tweet> tweetList = ModelConverter.toTweetsList(response.body());
                    DbManager.saveTweets(tweetList);
                    //updateUI(tweetList);
                } else {
                    Log.e(TAG, "Error in onResponse" + response.errorBody());
                    setProgressVisibility(false);
                    mSnackbar.show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "Error in onFailure" + t);
                setProgressVisibility(false);
                mSnackbar.show();
            }
        });
    }

    private void fetchTweetsFromDb() {
        mTweets = DbManager.getTweets();
        mTweets.addChangeListener(mRealmChangeListener);
    }

    private void updateUI(List<Tweet> tweetList) {
        mRecyclerView.setAdapter(new TimeLineAdapter(tweetList));
        setProgressVisibility(false);
    }

    private void setProgressVisibility(boolean visible) {
        mProgressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

}
