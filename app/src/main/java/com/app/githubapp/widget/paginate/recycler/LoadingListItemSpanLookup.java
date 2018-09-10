package com.app.githubapp.widget.paginate.recycler;

/**
 * Created by Sagar Shimpi on 31/8/18.
 */

/** SpanSizeLookup that will be used to determine the span of loading list item. */
public interface LoadingListItemSpanLookup {

    /** @return the span of loading list item. */
    int getSpanSize();
}