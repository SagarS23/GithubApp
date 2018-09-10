package com.app.githubapp;

import android.app.Application;
import android.content.Context;
import com.app.githubapp.api.ApiRequestHelper;

/**
 * Created by Sagar Shimpi on 26/8/18.
 */

public class GithubApp extends Application {

    private ApiRequestHelper apiRequestHelper;
    private static Context context;
    private static GithubApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        doInit();
        instance = this;
    }

    public static Context getContext() {
        return context;
    }

    private void doInit() {
        context = getApplicationContext();
        this.apiRequestHelper = ApiRequestHelper.init(this);
    }

    public synchronized ApiRequestHelper getApiRequestHelper() {
        return apiRequestHelper;
    }

    public static GithubApp getInstance() {
        return instance;
    }


}
