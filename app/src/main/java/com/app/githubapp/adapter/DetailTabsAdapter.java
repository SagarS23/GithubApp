package com.app.githubapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.app.githubapp.fragments.IssuesFragment;
import com.app.githubapp.fragments.CommitsFragment;
import com.app.githubapp.fragments.InfoFragment;

/**
 * Created by Sagar Shimpi on 1/9/18.
 */

public class DetailTabsAdapter extends FragmentStatePagerAdapter {

    private int mTabCount;
    private String fullName = "";

    public DetailTabsAdapter(FragmentManager fm, String _fullName) {
        super(fm);
        //Initializing tab count
        this.mTabCount = 0;
        this.fullName = _fullName;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return InfoFragment.newInstance(fullName);
            case 1:
                return CommitsFragment.newInstance(fullName);
            case 2:
                return IssuesFragment.newInstance(fullName);
            default:
                return new InfoFragment();
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    public void setCount(int count) {
        mTabCount = count;
    }

}
