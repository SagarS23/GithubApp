package com.app.githubapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.githubapp.R;
import com.app.githubapp.model.IssuesRes;
import com.app.githubapp.utils.Utils;
import com.app.githubapp.widget.CustomTextViewMedium;
import com.app.githubapp.widget.CustomTextViewRegular;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Sagar Shimpi on 1/9/18.
 */

public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.ViewHolder> {

    private Context context;

    private List<IssuesRes> issuesRes;

    //Constructor
    public IssuesAdapter(Context context, List<IssuesRes> _issuesRes) {
        this.context = context;
        this.issuesRes = _issuesRes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_issues_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final IssuesRes issueData = issuesRes.get(position);

        holder.textviewIssueName.setText(issueData.getTitle());
        holder.textviewUsername.setText(issueData.getUser().getLogin());
        holder.textviewTime.setText(Utils.getNewsTimeStr(context, issueData.getCreatedAt()));
        holder.textviewIssueNumber.setText(context.getString(R.string.issue_number) + issueData.getNumber());

        if (issueData.getUser().getAvatarUrl() != null)
            Glide.with(context).load(issueData.getUser().getAvatarUrl())
                    .apply(new RequestOptions().placeholder(R.drawable.iv_no_user))
                    .into(holder.imageviewUserAvatar);

    }

    @Override
    public int getItemCount() {
        //Returning list
        return issuesRes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textview_issue_name)
        CustomTextViewMedium textviewIssueName;
        @BindView(R.id.imageview_user_avatar)
        CircleImageView imageviewUserAvatar;
        @BindView(R.id.textview_username)
        CustomTextViewRegular textviewUsername;
        @BindView(R.id.textview_time)
        CustomTextViewRegular textviewTime;
        @BindView(R.id.textview_issue_number)
        CustomTextViewRegular textviewIssueNumber;

        //Binding with ButterKnife
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
