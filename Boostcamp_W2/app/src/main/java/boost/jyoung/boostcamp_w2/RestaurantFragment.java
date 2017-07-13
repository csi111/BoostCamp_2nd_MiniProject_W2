package boost.jyoung.boostcamp_w2;

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

/**
 * Created by jyoung on 2017. 7. 13..
 */

public class RestaurantFragment extends Fragment{
    RecyclerView recyclerView;
    ArrayList<RestaurantList> restaurantLists;
    int sort;
    public RestaurantFragment() {
    }

    public static RestaurantFragment newInstance(){
        RestaurantFragment fragment = new RestaurantFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.content_main, container, false);
        sort = getArguments().getInt("sort");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView)view.findViewById(R.id.rcv);
        setLayoutManager();
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RecyclerAdapter(sortList(makeList(), sort), getContext()));

    }

    public ArrayList<RestaurantList> makeList(){
        int length =getResources().getStringArray(R.array.list_title).length;
        TypedArray img = getResources().obtainTypedArray(R.array.list_img);
        String[] title = getResources().getStringArray(R.array.list_title);
        String[] content = getResources().getStringArray(R.array.list_content);
        int[] distance = getResources().getIntArray(R.array.list_distance);
        int[] popular = getResources().getIntArray(R.array.list_popular);
        int[] postdate = getResources().getIntArray(R.array.list_postdate);

        restaurantLists = new ArrayList<>();
        for (int i =0; i<length; i++){
            restaurantLists.add(new RestaurantList(img.getResourceId(i,-1), title[i], content[i], distance[i], popular[i], postdate[i]));
        }
        return restaurantLists;
    }

    public void setLayoutManager(){
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));
    }

    public ArrayList<RestaurantList> sortList(ArrayList<RestaurantList> list, int sort){
        if(sort == 0) {
            Collections.sort(list, new Comparator<RestaurantList>() {
                @Override
                public int compare(RestaurantList list, RestaurantList t1) {
                    return list.distance - t1.distance;
                }
            });
        }
        else if(sort == 1) {
            Collections.sort(list, new Comparator<RestaurantList>() {
                @Override
                public int compare(RestaurantList list, RestaurantList t1) {
                    return t1.popular - list.popular;
                }
            });
        }
        else if(sort == 2) {
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
