package ua.marinovskiy.twitterclient.models.network;

import com.google.gson.annotations.SerializedName;

public class NetworkUser {

    private long id;

    private String name;

    @SerializedName("profile_image_url")
    private String imageUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
