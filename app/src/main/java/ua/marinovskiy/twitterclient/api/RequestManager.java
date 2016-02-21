package ua.marinovskiy.twitterclient.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import ua.marinovskiy.twitterclient.MyApplication;

public class RequestManager {

    private static Api sApiInstance;

    public static Api getInstance() {
        if (sApiInstance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(defaultHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                            .setDateFormat("EEE MMM d HH:mm:ss zzzz yyyy").create()))
                    .build();

            sApiInstance = retrofit.create(Api.class);
        }
        return sApiInstance;
    }

    private static OkHttpClient defaultHttpClient() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain
                        .request()
                        .newBuilder()
                        .header(Constants.TIME_LINE_HEADER_TITLE,
                                Constants.TIME_LINE_HEADER_VALUE)
                        .build();
                return chain.proceed(request);
            }
        };
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(logging)
                .build();
    }

}
