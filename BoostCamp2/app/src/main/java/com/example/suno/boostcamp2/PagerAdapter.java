package com.example.suno.boostcamp2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by suno on 2017. 7. 13..
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;

    public PagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return;
            case 1:
                return;
            default:
                return new BaseFragment();
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
