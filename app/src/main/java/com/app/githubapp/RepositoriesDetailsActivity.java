package com.app.githubapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.app.githubapp.adapter.DetailTabsAdapter;
import com.app.githubapp.api.ApiRequestHelper;
import com.app.githubapp.api.ApiService;
import com.app.githubapp.model.UserDetails;
import com.app.githubapp.utils.ConnectionDetector;
import com.app.githubapp.utils.Utils;
import com.app.githubapp.widget.CustomTextViewAwesome;
import com.app.githubapp.widget.CustomTextViewRegular;
import com.app.githubapp.widget.materialprogressbar.CustomProgressDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;

/**
 * Created by Sagar Shimpi on 2/9/18.
 */


public class RepositoriesDetailsActivity extends AppCompatActivity {

    private Context context;
    private GithubApp githubApp;
    private ConnectionDetector cd;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_user_image)
    CircleImageView iv_user_image;
    @BindView(R.id.textview_username)
    CustomTextViewRegular textview_username;
    @BindView(R.id.textview_location)
    CustomTextViewRegular textview_location;
    @BindView(R.id.textview_joined_date)
    CustomTextViewRegular textview_joined_date;
    @BindView(R.id.textview_repositories)
    CustomTextViewRegular textview_repositories;
    @BindView(R.id.textview_followers)
    CustomTextViewRegular textview_followers;
    @BindView(R.id.textview_followings)
    CustomTextViewRegular textview_followings;
    @BindView(R.id.textview_bio)
    CustomTextViewRegular textview_bio;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.textview_globe)
    CustomTextViewAwesome textviewGlobe;

    private String repoUrl = "";

    private String logTag = "REPODETAILS_LOG";

    DetailTabsAdapter detailTabsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repository_details_activity);
        ButterKnife.bind(this);

        context = RepositoriesDetailsActivity.this;

        githubApp = (GithubApp) getApplicationContext();
        cd = new ConnectionDetector(context);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        String userName = getIntent().getStringExtra("user_name");
        String fullName = getIntent().getStringExtra("full_name");

        Fetch_user_detail(userName);

        tabLayout.addTab(tabLayout.newTab().setText("INFO"));
        tabLayout.addTab(tabLayout.newTab().setText("COMMITS"));
        tabLayout.addTab(tabLayout.newTab().setText("ISSUES"));

        detailTabsAdapter = new DetailTabsAdapter(getSupportFragmentManager(), fullName);
        detailTabsAdapter.setCount(3);
        viewPager.setAdapter(detailTabsAdapter);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void Fetch_user_detail(String url) {

        if (cd.isConnectingToInternet()) {
            final CustomProgressDialog cpd = new CustomProgressDialog(context);
            cpd.show();

            ApiService apiService = githubApp.getApiRequestHelper().getClient();
            Call call = apiService.GET_USER_DETAILS(url);

            githubApp.getApiRequestHelper().callRetrofit(call, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    cpd.dismiss();
                    UserDetails result = (UserDetails) object;
                    if (result != null) {

                        Glide.with(context).load(result.getAvatarUrl()).apply(new RequestOptions().placeholder(R.drawable.iv_no_user)).into(iv_user_image);

                        repoUrl = result.getHtmlUrl();

                        if (result.getName() != null) {
                            textview_username.setVisibility(View.VISIBLE);
                            textview_username.setText(result.getName());
                        }
                        if (result.getBio() != null) {
                            textview_bio.setVisibility(View.VISIBLE);
                            textview_bio.setText(result.getBio());
                        }
                        if (result.getLocation() != null) {
                            textview_location.setVisibility(View.VISIBLE);
                            textview_location.setText(result.getLocation());
                        }
                        if (result.getCreatedAt() != null) {
                            textview_joined_date.setVisibility(View.VISIBLE);
                            textview_joined_date.setText(getResources().getString(R.string.joined) + " " + result.getCreatedAt().substring(0, result.getCreatedAt().indexOf("T")));
                        }

                        textview_repositories.setText(result.getPublicRepos());
                        textview_followers.setText(result.getFollowers());
                        textview_followings.setText(result.getFollowing());

                    } else {
                        Utils.showErrorToast(context, Utils.UNPROPER_RESPONSE);
                    }
                }

                @Override
                public void onFailure(String apiResponse) {
                    cpd.dismiss();
                    Log.e(logTag, "" + apiResponse);
                    if (apiResponse.contains("Git Repository is empty.")) {
                        Utils.showErrorToast(context, getString(R.string.git_repository_is_empty));
                    } else if (apiResponse.contains("API rate limit exceeded")) {
                        Utils.showErrorToast(context, getString(R.string.api_rate_limit_exceeded));
                    } else {
                        Utils.showErrorToast(context, Utils.UNPROPER_RESPONSE);
                    }
                }
            });
        } else {
            Utils.showErrorToast(context, Utils.NO_INTERNET_TITLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.textview_globe)
    public void onViewRepo() {

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(repoUrl));
        startActivity(i);

    }
}
