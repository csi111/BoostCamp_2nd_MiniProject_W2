package boost.jyoung.boostcamp_w2.ui.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import boost.jyoung.boostcamp_w2.ui.RestaurantFragment;

/**
 * Created by jyoung on 2017. 7. 13..
 */

public class PageAdapter extends FragmentPagerAdapter {
    int tabcount;

    public PageAdapter(FragmentManager fm, int tabcount) {
        super(fm);
        this.tabcount = tabcount;
    }

    @Override
    public Fragment getItem(int position) {
        RestaurantFragment fragment = new RestaurantFragment();
        Bundle sort = new Bundle();
        switch (position){
            case 0:
                sort.putInt("sort",0);
                break;
            case 1:
                sort.putInt("sort",1);
                break;
            case 2:
                sort.putInt("sort",2);
                break;
            default:
                sort.putInt("sort",0);
                break;
        }
        fragment.setArguments(sort);

        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return tabcount;
    }
}