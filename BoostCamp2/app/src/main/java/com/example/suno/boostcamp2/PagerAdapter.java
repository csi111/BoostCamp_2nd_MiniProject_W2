package com.example.suno.boostcamp2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.suno.boostcamp2.data.FamousPlace;
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
        FamousPlaceFragment fp;
        Bundle bundle;

        switch (position){
            case 0:
                fp = new FamousPlaceFragment();
                bundle = new Bundle(1);
                bundle.putInt("orderedBy", MainActivity.ORDEREDBY_DISTANCE);
                fp.setArguments(bundle);
                return fp;
            case 1:
                fp = new FamousPlaceFragment();
                bundle = new Bundle(1);
                bundle.putInt("orderedBy", MainActivity.ORDEREDBY_POPULARIRY);
                fp.setArguments(bundle);
                return fp;
            case 2:
                fp = new FamousPlaceFragment();
                bundle = new Bundle(1);
                bundle.putInt("orderedBy", MainActivity.ORDEREDBY_LATEST);
                fp.setArguments(bundle);
                return fp;
            default:
                return new BaseFragment();
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
