package ua.marinovskiy.twitterclient.models.network;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class NetworkTweet {

    private long id;

    @SerializedName("created_at")
    private Date date;

    private String text;

    @SerializedName("retweet_count")
    private int reTweetCount;

    @SerializedName("favorite_count")
    private int favoriteCount;

    private NetworkUser user;

    @SerializedName("extended_entities")
    private NetworkExtendedEntities extendedEntities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getReTweetCount() {
        return reTweetCount;
    }

    public void setReTweetCount(int reTweetCount) {
        this.reTweetCount = reTweetCount;
    }

    public NetworkUser getUser() {
        return user;
    }

    public void setUser(NetworkUser user) {
        this.user = user;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public NetworkExtendedEntities getExtendedEntities() {
        return extendedEntities;
    }

    public void setExtendedEntities(NetworkExtendedEntities extendedEntities) {
        this.extendedEntities = extendedEntities;
    }
}
