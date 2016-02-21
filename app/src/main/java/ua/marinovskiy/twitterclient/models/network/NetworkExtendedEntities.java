package ua.marinovskiy.twitterclient.models.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NetworkExtendedEntities {

    @SerializedName("media")
    private List<NetworkMedia> mediaList;

    public List<NetworkMedia> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<NetworkMedia> mediaList) {
        this.mediaList = mediaList;
    }
}
