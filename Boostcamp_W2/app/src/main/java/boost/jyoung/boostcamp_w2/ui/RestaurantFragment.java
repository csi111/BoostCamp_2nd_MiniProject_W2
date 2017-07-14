package boost.jyoung.boostcamp_w2.ui;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import boost.jyoung.boostcamp_w2.Model.RestaurantList;
import boost.jyoung.boostcamp_w2.R;
import boost.jyoung.boostcamp_w2.SQLite.DBHelper;
import boost.jyoung.boostcamp_w2.ui.Adapter.RecyclerAdapter;

/**
 * Created by jyoung on 2017. 7. 13..
 */

public class RestaurantFragment extends Fragment {
    public static int CHECKED = 0;
    public static int UNCHECKED = 1;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    ArrayList<RestaurantList> restaurantLists;
    int sort;

    public RestaurantFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.content_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        restaurantLists = new ArrayList<>();

        final DBHelper dbHelper = new DBHelper(getContext(), "RestaurantList.db", null, 1);

        sort = getArguments().getInt("sort");

        recyclerView = (RecyclerView) view.findViewById(R.id.rcv);
        setLayoutManager(MainActivity.flag);
        recyclerView.setHasFixedSize(true);
        recyclerAdapter = new RecyclerAdapter(sortList(dbHelper.getDataFromDB(restaurantLists), sort), getContext());
        recyclerView.setAdapter(recyclerAdapter);
    }

    ////////////////////////// SQLite 미사용시 //////////////////////////
    public ArrayList<RestaurantList> makeList() {
        int length = getResources().getStringArray(R.array.list_title).length;
        TypedArray img = getResources().obtainTypedArray(R.array.list_img);
        String[] title = getResources().getStringArray(R.array.list_title);
        String[] content = getResources().getStringArray(R.array.list_content);
        int[] distance = getResources().getIntArray(R.array.list_distance);
        int[] popular = getResources().getIntArray(R.array.list_popular);
        int[] postdate = getResources().getIntArray(R.array.list_postdate);

        restaurantLists = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            restaurantLists.add(new RestaurantList(img.getResourceId(i, -1), title[i], content[i], distance[i], popular[i], postdate[i], UNCHECKED));
        }
        return restaurantLists;
    }


    public void setLayoutManager(boolean flag) {
        if (flag == true) {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
        } else
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, 1));
    }


    ////////////////////////// sort //////////////////////////
    public ArrayList<RestaurantList> sortList(ArrayList<RestaurantList> list, int sort) {
        if (sort == 0) {
            Collections.sort(list, new Comparator<RestaurantList>() {
                @Override
                public int compare(RestaurantList list, RestaurantList t1) {
                    return list.distance - t1.distance;
                }
            });
        } else if (sort == 1) {
            Collections.sort(list, new Comparator<RestaurantList>() {
                @Override
                public int compare(RestaurantList list, RestaurantList t1) {
                    return t1.popular - list.popular;
                }
            });
        } else if (sort == 2) {
            Collections.sort(list, new Comparator<RestaurantList>() {
                @Override
                public int compare(RestaurantList list, RestaurantList t1) {
                    return t1.postdate - list.postdate;
                }
            });
        }
        return list;
    }

}
