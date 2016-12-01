package com.example.windykiss.homework15_0102.adapters;

/**
 * Created by WindyKiss on 11/29/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.windykiss.homework15_0102.fragments.GenresFragment;
import com.example.windykiss.homework15_0102.fragments.OfflineFragment;
import com.example.windykiss.homework15_0102.fragments.PlaylistFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private static final String TAG =  PagerAdapter.class.getSimpleName();
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        Log.d(TAG, position+"asdasd");

        switch (position) {
            case 0:
                return new GenresFragment();
            case 1:
                return new PlaylistFragment();
            case 2:
                return new OfflineFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
