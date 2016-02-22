package ua.marinovskiy.twitterclient.utils;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;
import ua.marinovskiy.twitterclient.models.db.Tweet;

public class DbManager {

    private static final String TAG = "DbManager";

    public static void saveTweets(final List<Tweet> tweetList) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(tweetList);
                //realm.close();
            }
        }, new Realm.Transaction.Callback() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "onSuccess: tweets saved");
                super.onSuccess();
            }

            @Override
            public void onError(Exception e) {
                Log.i(TAG, "onSuccess: tweets have not save");
                super.onError(e);
            }
        });
    }

    public static RealmResults<Tweet> getTweets() {
        return Realm.getDefaultInstance().where(Tweet.class)
                .findAllAsync();
    }

    public static void attachChangeListener(RealmChangeListener realmChangeListener) {
        Realm.getDefaultInstance().addChangeListener(realmChangeListener);
    }

    public static void detachChangeListener(RealmChangeListener realmChangeListener) {
        Realm.getDefaultInstance().removeChangeListener(realmChangeListener);
    }

}
