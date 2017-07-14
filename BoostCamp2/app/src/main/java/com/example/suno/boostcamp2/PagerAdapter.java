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
    FamousPlaceFragment fp1, fp2, fp3;

    public PagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;

        Bundle bundle;
        fp1 = new FamousPlaceFragment();
        bundle = new Bundle(1);
        bundle.putInt("orderedBy", MainActivity.ORDEREDBY_DISTANCE);
        fp1.setArguments(bundle);

        fp2 = new FamousPlaceFragment();
        bundle = new Bundle(1);
        bundle.putInt("orderedBy", MainActivity.ORDEREDBY_POPULARIRY);
        fp2.setArguments(bundle);

        fp3 = new FamousPlaceFragment();
        bundle = new Bundle(1);
        bundle.putInt("orderedBy", MainActivity.ORDEREDBY_LATEST);
        fp3.setArguments(bundle);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return fp1;
            case 1:
                return fp2;
            case 2:
                return fp3;
            default:
                return new BaseFragment();
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
