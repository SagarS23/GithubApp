package com.app.githubapp.widget.paginate.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Sagar Shimpi on 31/8/18.
 */

class DefaultLoadingListItemSpanLookup implements LoadingListItemSpanLookup {

    private final int loadingListItemSpan;

    public DefaultLoadingListItemSpanLookup(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) {
            // By default full span will be used for loading list item
            loadingListItemSpan = ((GridLayoutManager) layoutManager).getSpanCount();
        } else {
            loadingListItemSpan = 1;
        }
    }

    @Override
    public int getSpanSize() {
        return loadingListItemSpan;
    }
}