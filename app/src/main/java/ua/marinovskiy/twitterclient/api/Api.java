package ua.marinovskiy.twitterclient.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ua.marinovskiy.twitterclient.models.network.NetworkTweet;

public interface Api {

    @GET(Constants.TIME_LINE)
    Call<List<NetworkTweet>> userTimeLine();

}
