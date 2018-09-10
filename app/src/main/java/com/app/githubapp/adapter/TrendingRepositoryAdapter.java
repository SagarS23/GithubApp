package com.app.githubapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.githubapp.MainActivity;
import com.app.githubapp.R;
import com.app.githubapp.RepositoriesDetailsActivity;
import com.app.githubapp.model.Repository;
import com.app.githubapp.utils.Utils;
import com.app.githubapp.widget.CustomTextViewRegular;
import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sagar Shimpi on 1/9/18.
 */

public class TrendingRepositoryAdapter extends RecyclerView.Adapter<TrendingRepositoryAdapter.ViewHolder> {

    private Context context;
    private List<Repository> mRepositories;

    //Constructor
    public TrendingRepositoryAdapter(Context context, List<Repository> _mRepositories) {
        this.context = context;
        this.mRepositories = _mRepositories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_repository_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Repository repository = mRepositories.get(position);

        String fullName = "<b>" + repository.get_owner().getLogin() + "&#47;" + "</b>" + repository.getName();
        holder.textviewFullName.setText(Html.fromHtml(fullName));

        if (repository.getDescription() != null)
            holder.textviewDescription.setText(repository.getDescription());
        else
            holder.textviewDescription.setText(R.string.no_description_available);

        String language = repository.getLanguage();
        int numStars = repository.getStargazersCount();

        String periodName = Utils.getPeriodNameFromPeriod(MainActivity.createdAt);

        if (language != null) {
            holder.textview_language.setText(language);
        } else {
            holder.textview_language.setText(R.string.no_language_available);
        }

        holder.textview_stargazers.setText(numStars + " stars " + periodName);
        holder.textview_fork.setText(repository.getForks());

        if (repository.get_owner().getAvatarUrl() != null) {
            Glide.with(context).load(repository.get_owner().getAvatarUrl() + "&s=40")
                    .into(holder.imageviewOwner);
        }

        holder.mrlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, RepositoriesDetailsActivity.class)
                        .putExtra("user_name", "" + repository.get_owner().getLogin())
                        .putExtra("full_name", "" + repository.getFullName()));
            }
        });

    }

    @Override
    public int getItemCount() {
        //Returning list
        return mRepositories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textview_full_name)
        TextView textviewFullName;
        @BindView(R.id.textview_description)
        TextView textviewDescription;
        @BindView(R.id.imageview_owner)
        ImageView imageviewOwner;
        @BindView(R.id.textview_language)
        CustomTextViewRegular textview_language;
        @BindView(R.id.textview_stargazers)
        CustomTextViewRegular textview_stargazers;
        @BindView(R.id.textview_fork)
        CustomTextViewRegular textview_fork;
        @BindView(R.id.mrl_main)
        MaterialRippleLayout mrlMain;

        //Binding with ButterKnife
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
