package com.app.githubapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.app.githubapp.GithubApp;
import com.app.githubapp.R;
import com.app.githubapp.adapter.CommitsAdapter;
import com.app.githubapp.api.ApiRequestHelper;
import com.app.githubapp.api.ApiService;
import com.app.githubapp.model.CommitRes;
import com.app.githubapp.utils.ConnectionDetector;
import com.app.githubapp.utils.Utils;
import com.app.githubapp.widget.CustomTextViewRegular;
import com.app.githubapp.widget.materialprogressbar.CustomProgressDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;

/**
 * Created by Sagar Shimpi on 8/9/18.
 */

public class CommitsFragment extends Fragment {

    private Context context;
    private ConnectionDetector cd;
    private GithubApp githubApp;

    private Unbinder unbind;

    @BindView(R.id.rv_commits)
    RecyclerView rvCommits;
    @BindView(R.id.rl_no_data_found)
    RelativeLayout rlNoDataFound;
    @BindView(R.id.textview_msg)
    CustomTextViewRegular textviewMsg;

    CommitsAdapter commitsAdapter;

    private String fullName = "";
    private String loginName = "";
    private String repoName = "";
    private String logTag = "COMMITS_LOG";

    public CommitsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fullName = getArguments().getString("full_name");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commits, container, false);
        unbind = ButterKnife.bind(this, view);

        context = getActivity();
        cd = new ConnectionDetector(context);
        githubApp = (GithubApp) context.getApplicationContext();

        String[] seperate = fullName.split("/");
        loginName = seperate[0].trim();
        repoName = seperate[1].trim();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvCommits.setLayoutManager(layoutManager);
        rvCommits.setItemAnimator(new DefaultItemAnimator());
        rvCommits.isNestedScrollingEnabled();

        GetCommits();

        return view;
    }

    private void GetCommits() {

        if (cd.isConnectingToInternet()) {
            final CustomProgressDialog cpd = new CustomProgressDialog(context);
            cpd.show();

            ApiService apiService = githubApp.getApiRequestHelper().getClient();
            Call call = apiService.GET_COMMITS(loginName, repoName);

            githubApp.getApiRequestHelper().callRetrofit(call, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    cpd.dismiss();
                    List<CommitRes> result = (ArrayList) object;
                    if (result != null && result.size() > 0) {
                        commitsAdapter = new CommitsAdapter(context, result);
                        rvCommits.setAdapter(commitsAdapter);
                    } else {
                        rvCommits.setVisibility(View.GONE);
                        rlNoDataFound.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(String apiResponse) {
                    cpd.dismiss();
                    Log.e(logTag, apiResponse);
                    if (apiResponse.contains("Git Repository is empty.")) {
                        rvCommits.setVisibility(View.GONE);
                        rlNoDataFound.setVisibility(View.VISIBLE);
                    } else if (apiResponse.contains("API rate limit exceeded")) {
                        rvCommits.setVisibility(View.GONE);
                        rlNoDataFound.setVisibility(View.VISIBLE);
                        textviewMsg.setText(R.string.api_rate_limit_exceeded);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
    }

    public static Fragment newInstance(String userName) {
        CommitsFragment f = new CommitsFragment();
        Bundle b = new Bundle();
        b.putString("full_name", userName);
        f.setArguments(b);
        return f;
    }
}
