package com.app.githubapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.app.githubapp.GithubApp;
import com.app.githubapp.R;
import com.app.githubapp.api.ApiRequestHelper;
import com.app.githubapp.api.ApiService;
import com.app.githubapp.model.InfoRes;
import com.app.githubapp.utils.ConnectionDetector;
import com.app.githubapp.utils.Utils;
import com.app.githubapp.widget.CustomTextViewRegular;
import com.app.githubapp.widget.materialprogressbar.CustomProgressDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;

/**
 * Created by Sagar Shimpi on 8/9/18.
 */

public class InfoFragment extends Fragment {

    private Context context;
    private ConnectionDetector cd;
    private GithubApp githubApp;

    private Unbinder unbind;

    @BindView(R.id.nsv_top)
    NestedScrollView nsv_top;
    @BindView(R.id.textview_msg)
    CustomTextViewRegular textviewMsg;
    @BindView(R.id.rl_no_data_found)
    RelativeLayout rlNoDataFound;

    private String fullName = "";
    private String loginName = "";
    private String repoName = "";
    private String logTag = "INFO_LOG";

    @BindView(R.id.textview_repo_title)
    TextView textviewRepoTitle;
    @BindView(R.id.textview_fork_info)
    TextView textviewForkInfo;
    @BindView(R.id.textview_repo_desc)
    TextView textviewRepoDesc;
    @BindView(R.id.textview_repo_created_info)
    TextView textviewRepoCreatedInfo;
    @BindView(R.id.textview_issues_num)
    TextView textviewIssuesNum;
    @BindView(R.id.textview_stargazers_num)
    TextView textviewStargazersNum;
    @BindView(R.id.textview_forks_num)
    TextView textviewForksNum;
    @BindView(R.id.textview_watchers_num)
    TextView textviewWatchersNum;

    @BindView(R.id.textview_git_url)
    CustomTextViewRegular textview_git_url;
    @BindView(R.id.textview_ssh_url)
    CustomTextViewRegular textview_ssh_url;
    @BindView(R.id.textview_clone_url)
    CustomTextViewRegular textview_clone_url;
    @BindView(R.id.textview_key)
    CustomTextViewRegular textview_key;
    @BindView(R.id.textview_name)
    CustomTextViewRegular textview_name;
    @BindView(R.id.textview_node_id)
    CustomTextViewRegular textview_node_id;

    public InfoFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullName = getArguments().getString("user_name");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        unbind = ButterKnife.bind(this, view);

        context = getActivity();
        cd = new ConnectionDetector(context);
        githubApp = (GithubApp) context.getApplicationContext();

        String[] seperate = fullName.split("/");
        loginName = seperate[0].trim();
        repoName = seperate[1].trim();

        GetReadMe();

        return view;
    }

    private void GetReadMe() {

        if (cd.isConnectingToInternet()) {
            final CustomProgressDialog cpd = new CustomProgressDialog(context);
            cpd.show();

            ApiService apiService = githubApp.getApiRequestHelper().getClient();
            Call call = apiService.GET_INFO(loginName, repoName);

            githubApp.getApiRequestHelper().callRetrofit(call, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    cpd.dismiss();
                    InfoRes result = (InfoRes) object;
                    if (result != null) {

                        textviewRepoTitle.setText(result.getFullName());

                        textviewIssuesNum.setText("" + result.getOpenIssues());
                        textviewStargazersNum.setText("" + result.getStargazersCount());
                        textviewForksNum.setText("" + result.getForksCount());
                        textviewWatchersNum.setText("" + result.getWatchersCount());

                        textview_git_url.setText(result.getGitUrl());
                        textview_ssh_url.setText(result.getSshUrl());
                        textview_clone_url.setText(result.getCloneUrl());

                        if (result.getLicense() != null) {
                            textview_key.setText("Key : " + result.getLicense().getKey());
                            textview_name.setText("Name : " + result.getLicense().getName());
                            textview_node_id.setText("Node ID : " + result.getLicense().getNode_id());
                        } else {
                            textview_key.setText("Key : N/A");
                            textview_name.setText("Name : N/A");
                            textview_node_id.setText("Node ID : N/A");

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
                        nsv_top.setVisibility(View.GONE);
                        rlNoDataFound.setVisibility(View.VISIBLE);
                    } else if (apiResponse.contains("API rate limit exceeded")) {
                        nsv_top.setVisibility(View.GONE);
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
        InfoFragment f = new InfoFragment();
        Bundle b = new Bundle();
        b.putString("user_name", userName);
        f.setArguments(b);
        return f;
    }

}
