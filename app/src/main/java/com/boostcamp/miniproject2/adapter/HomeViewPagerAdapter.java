package com.boostcamp.miniproject2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.boostcamp.miniproject2.fragment.SortedFragment;

/**
 * Created by moon on 2017. 7. 13..
 */

public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    private int pageCount = 3;
    private SortedFragment sortedFragment;

    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public SortedFragment getSortedFragment() {
        return sortedFragment;
    }

    @Override
    public Fragment getItem(int position) {
        if (sortedFragment == null) {
            return SortedFragment.newInstance();
        } else {
            return sortedFragment;
        }
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "거리순";

            case 1:
                return "인기순";

            case 2:
                return "최근순";

            default:
                return "";
        }


    }
}
