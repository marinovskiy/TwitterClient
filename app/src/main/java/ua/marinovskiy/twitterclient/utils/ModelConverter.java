package ua.marinovskiy.twitterclient.utils;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import ua.marinovskiy.twitterclient.models.db.Tweet;
import ua.marinovskiy.twitterclient.models.db.User;
import ua.marinovskiy.twitterclient.models.network.NetworkTweet;
import ua.marinovskiy.twitterclient.models.network.NetworkUser;

public class ModelConverter {

    public static Tweet convertToTweet(@NonNull NetworkTweet networkTweet) {
        Tweet tweet = new Tweet();
        tweet.setId(networkTweet.getId());
        tweet.setDate(networkTweet.getDate());
        tweet.setFavoriteCount(networkTweet.getFavoriteCount());
        tweet.setReTweetCount(networkTweet.getReTweetCount());
        tweet.setMessage(networkTweet.getText());
        tweet.setUser(convertToUser(networkTweet.getUser()));
        return tweet;
    }

    public static User convertToUser(@NonNull NetworkUser networkUser) {
        User user = new User();
        user.setId(networkUser.getId());
        user.setName(networkUser.getName());
        user.setImageUrl(networkUser.getImageUrl());
        return user;
    }

    public static List<Tweet> toTweetsList(List<NetworkTweet> networkTweetList) {
        List<Tweet> tweetList = new ArrayList<>(networkTweetList.size());
        for (int i = 0; i < networkTweetList.size(); i++) {
            tweetList.add(convertToTweet(networkTweetList.get(i)));
        }
        return tweetList;
    }

}
