package boost.jyoung.boostcamp_w2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by jyoung on 2017. 7. 13..
 */

public class RecyclerAdapter extends RecyclerView.Adapter {
    ArrayList<RestaurantList> restaurantLists;
    Context context;

    public RecyclerAdapter(ArrayList<RestaurantList> restaurantLists, Context context) {
        this.restaurantLists = restaurantLists;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.listitem, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).bindView(restaurantLists);
    }

    @Override
    public int getItemCount() {
        return restaurantLists != null ? restaurantLists.size() : 0;
    }
}
