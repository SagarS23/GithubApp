package com.app.githubapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.app.githubapp.adapter.TrendingRepositoryAdapter;
import com.app.githubapp.api.ApiRequestHelper;
import com.app.githubapp.api.ApiService;
import com.app.githubapp.model.Repository;
import com.app.githubapp.model.SearchResult;
import com.app.githubapp.utils.ConnectionDetector;
import com.app.githubapp.utils.Utils;
import com.app.githubapp.widget.CustomTextViewRegular;
import com.app.githubapp.widget.HidingScrollListener;
import com.app.githubapp.widget.materialprogressbar.CustomProgressDialog;
import com.app.githubapp.widget.paginate.CustomLoadingListItemCreator;
import com.app.githubapp.widget.paginate.Paginate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * Created by Sagar Shimpi on 26/8/18.
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_framelayout)
    FrameLayout main_framelayout;
    private Context context;
    private GithubApp githubApp;
    private ConnectionDetector cd;

    @BindView(R.id.rv_repositories)
    RecyclerView rvRepositories;
    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;
    @BindView(R.id.textview_msg)
    CustomTextViewRegular textviewMsg;
    @BindView(R.id.rl_no_data_found)
    RelativeLayout rlNoDataFound;

    public static String createdAt = "action_this_month";
    private String starts = "stars";
    private String order = "desc";
    private String perPage = "10";
    private String logTag = "REPOSITORIES_LOG";

    private Paginate paginate;
    private boolean loading = false;
    private int currentPage = 1, lastPage = 10, nextPage = 1;

    private long lastPress;

    List<Repository> datalist;
    List<Repository> tempdatalist;

    TrendingRepositoryAdapter trendingRepositoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = MainActivity.this;

        setSupportActionBar(mainToolbar);
        mainToolbar.setTitle("Repo");

        datalist = new ArrayList<>();
        tempdatalist = new ArrayList<>();

        githubApp = (GithubApp) getApplicationContext();
        cd = new ConnectionDetector(context);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvRepositories.setLayoutManager(layoutManager);
        rvRepositories.setItemAnimator(new DefaultItemAnimator());

        getRepositoriesData(createdAt);

        rvRepositories.addOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                hideViews();
            }

            @Override
            public void onShow() {
                showViews();
            }
        });
    }

    private void hideViews() {
        mainToolbar.animate().translationY(-mainToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
    }

    private void showViews() {
        mainToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
    }

    private void getRepositoriesData(String created) {

        created = Utils.getCreatedDateFromPeriod(created);

        String query = "created:>" + created;

        if (cd.isConnectingToInternet()) {
            final CustomProgressDialog cpd = new CustomProgressDialog(context);
            cpd.show();

            ApiService apiService = githubApp.getApiRequestHelper().getClient();
            Call call = apiService.GET_REPOSITORIES(query, starts, order, perPage, nextPage);

            githubApp.getApiRequestHelper().callRetrofit(call, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    cpd.dismiss();
                    SearchResult result = (SearchResult) object;
                    if (result != null) {

                        datalist = result.getRepositories();
                        tempdatalist.addAll(datalist);
                        nextPage++;

                        if (currentPage == lastPage) {
                            setDataAdapter(false, tempdatalist);
                        } else {
                            setDataAdapter(true, tempdatalist);
                        }

                    } else {
                        Utils.showErrorToast(context, Utils.UNPROPER_RESPONSE);
                    }
                }

                @Override
                public void onFailure(String apiResponse) {
                    cpd.dismiss();
                    Log.e(logTag, "" + apiResponse);
                    if (apiResponse.contains("Git Repository is empty.")) {
                        rvRepositories.setVisibility(View.GONE);
                        rlNoDataFound.setVisibility(View.VISIBLE);
                        textviewMsg.setText(R.string.git_repository_is_empty);
                    } else if (apiResponse.contains("API rate limit exceeded")) {
                        rvRepositories.setVisibility(View.GONE);
                        rlNoDataFound.setVisibility(View.VISIBLE);
                        textviewMsg.setText(R.string.api_rate_limit_exceeded);
                    } else {
                        Utils.showErrorToast(context, Utils.UNPROPER_RESPONSE);
                    }
                }
            });
        } else {
            showSnackNoInternet();
        }
    }

    private void setDataAdapter(boolean b, List<Repository> data) {

        if (data.size() > 0) {
            trendingRepositoryAdapter = new TrendingRepositoryAdapter(context, data);
            rvRepositories.setAdapter(trendingRepositoryAdapter);

            if (b) {
                paginate = Paginate.with(rvRepositories, callbacks)
                        .setLoadingTriggerThreshold(7)
                        .addLoadingListItem(true)
                        .setLoadingListItemCreator(new CustomLoadingListItemCreator(rvRepositories))
                        .build();
            }
        }
    }

    Paginate.Callbacks callbacks = new Paginate.Callbacks() {
        @Override
        public void onLoadMore() {
            String m = "";
            if (lastPage != 1) {
                getNextPageData(nextPage, createdAt);
                loading = true;
            }
        }

        @Override
        public boolean isLoading() {
            return loading;
        }

        @Override
        public boolean hasLoadedAllItems() {
            return currentPage == lastPage;
        }
    };

    private void getNextPageData(int Page, String createdAt) {

        createdAt = Utils.getCreatedDateFromPeriod(createdAt);

        String query = "created:>" + createdAt;

        if (cd.isConnectingToInternet()) {

            ApiService apiService = githubApp.getApiRequestHelper().getClient();
            Call call = apiService.GET_REPOSITORIES(query, starts, order, perPage, Page);

            githubApp.getApiRequestHelper().callRetrofit(call, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    SearchResult result = (SearchResult) object;
                    if (result != null) {

                        datalist = result.getRepositories();
                        tempdatalist.addAll(datalist);
                        currentPage++;
                        nextPage++;
                        loading = false;
                        trendingRepositoryAdapter.notifyDataSetChanged();

                    } else {
                        Utils.showErrorToast(context, Utils.UNPROPER_RESPONSE);
                    }
                }

                @Override
                public void onFailure(String apiResponse) {
                    Utils.showErrorToast(context, Utils.UNPROPER_RESPONSE);
                }
            });
        } else {
            showSnackNoInternet();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!cd.isConnectingToInternet())
            showSnackNoInternet();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        //Set menu item checked initially
        menu.getItem(2).setChecked(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_today:
                createdAt = "action_today";
                UpdateList(createdAt);
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                return true;
            case R.id.menu_this_week:
                createdAt = "action_this_week";
                UpdateList(createdAt);
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                return true;
            case R.id.menu_this_month:
                createdAt = "action_this_month";
                UpdateList(createdAt);
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                return true;
            case R.id.menu_this_year:
                createdAt = "action_this_year";
                UpdateList(createdAt);
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Update list data
    private void UpdateList(String _createdAt) {
        if (cd.isConnectingToInternet()) {
            datalist = new ArrayList<>();
            tempdatalist = new ArrayList<>();
            trendingRepositoryAdapter.notifyDataSetChanged();
            getRepositoriesData(_createdAt);
        } else {
            showSnackNoInternet();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!cd.isConnectingToInternet())
            showSnackNoInternet();
    }

    @Override
    public void onBackPressed() {
        //Show back pressed toast for 5 seconds before app close
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastPress > 5000) {
            Utils.showLongToast(context, getString(R.string.press_back_again_to_exit));
            lastPress = currentTime;
        } else {
            super.onBackPressed();
        }
    }

    private void showSnackNoInternet() {

        Snackbar snackbar = Snackbar
                .make(main_framelayout, "No Internet Connection!", Snackbar.LENGTH_INDEFINITE)
                .setAction("Turn ON", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);

                    }
                });

        snackbar.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
