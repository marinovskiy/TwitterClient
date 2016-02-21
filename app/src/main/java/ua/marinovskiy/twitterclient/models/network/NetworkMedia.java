package ua.marinovskiy.twitterclient.models.network;

import com.google.gson.annotations.SerializedName;

public class NetworkMedia {

    @SerializedName("media_url")
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
