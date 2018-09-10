package com.app.githubapp.api;


import android.text.Html;

import com.app.githubapp.BuildConfig;
import com.app.githubapp.GithubApp;
import com.app.githubapp.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sagar Shimpi on 26/8/18.
 */

public class ApiRequestHelper {

    public static interface OnRequestComplete {
        public void onSuccess(Object object);

        public void onFailure(String apiResponse);
    }

    private static ApiRequestHelper instance;
    private GithubApp application;
    private ApiService apiService;
    static Gson gson;
    private Retrofit retrofit;

    public static synchronized ApiRequestHelper init(GithubApp application) {
        if (null == instance) {
            instance = new ApiRequestHelper();
            instance.setApplication(application);
            gson = new GsonBuilder().setLenient().create();
            instance.createRestAdapter();
        }
        return instance;
    }

    /**
     * API Calls
     */

    public  <T> void callRetrofit(Call<T> call, final OnRequestComplete onRequestComplete) {

        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    
                    onRequestComplete.onSuccess(response.body());
                } else {
                    try {
                        onRequestComplete.onFailure(Html.fromHtml(response.errorBody().string()) + "");
                    } catch (IOException e) {
                        onRequestComplete.onFailure(Utils.UNPROPER_RESPONSE);
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (t != null && t.getMessage() != null)
                    onRequestComplete.onFailure(Html.fromHtml(t.getMessage()) + "");
                else
                    onRequestComplete.onFailure(Utils.UNPROPER_RESPONSE);
            }

        });
    }

    /**
     * End API Calls
     */

    private void createRestAdapter() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okhttp3.OkHttpClient.Builder httpClient = new okhttp3.OkHttpClient.Builder();
        httpClient.interceptors().add(logging);
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson));
        retrofit = builder.client(httpClient.build()).build();
        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getClient() {
        return apiService = retrofit.create(ApiService.class);
    }

    /**
     * End REST Adapter Configuration
     */

    public GithubApp getApplication() {
        return application;
    }

    public void setApplication(GithubApp application) {
        this.application = application;
    }
}
