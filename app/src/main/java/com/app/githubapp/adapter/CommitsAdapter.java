package com.app.githubapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.githubapp.R;
import com.app.githubapp.model.CommitRes;
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

public class CommitsAdapter extends RecyclerView.Adapter<CommitsAdapter.ViewHolder> {

    private Context context;
    private List<CommitRes> commitRes;

    //Constructor
    public CommitsAdapter(Context context, List<CommitRes> _commitRes) {
        this.context = context;
        this.commitRes = _commitRes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_commits_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final CommitRes commitData = commitRes.get(position);

        if (commitData.getCommit().getCommitter().getName() != null)
            holder.textviewCommitter.setText(commitData.getCommit().getCommitter().getName());

        holder.textviewTime.setText(Utils.getNewsTimeStr(context, commitData.getCommit().getAuthor().getDate()));
        holder.textviewMsg.setText(commitData.getCommit().getMessage());

        if (commitData.getCommitter() != null && commitData.getCommitter().getAvatarUrl() != null)
            Glide.with(context).load(commitData.getCommitter().getAvatarUrl())
                    .apply(new RequestOptions().placeholder(R.drawable.iv_no_user).error(R.drawable.iv_no_user))
                    .into(holder.imageViewUser);
        else
            holder.imageViewUser.setImageResource(R.drawable.iv_no_user);

    }

    @Override
    public int getItemCount() {
        //Returning list
        return commitRes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textview_committer)
        CustomTextViewMedium textviewCommitter;
        @BindView(R.id.textview_time)
        CustomTextViewRegular textviewTime;
        @BindView(R.id.textview_msg)
        CustomTextViewRegular textviewMsg;
        @BindView(R.id.imageview_user_img)
        CircleImageView imageViewUser;

        //Binding with ButterKnife
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
