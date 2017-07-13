package com.example.suno.boostcamp2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.suno.boostcamp2.fragment.BaseFragment;
import com.example.suno.boostcamp2.fragment.FamousPlaceFragment;

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
                return new FamousPlaceFragment();
            case 1:
                return new FamousPlaceFragment();
            default:
                return new BaseFragment();
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
