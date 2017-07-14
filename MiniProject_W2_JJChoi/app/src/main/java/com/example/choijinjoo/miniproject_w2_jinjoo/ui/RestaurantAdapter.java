package com.example.choijinjoo.miniproject_w2_jinjoo.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.choijinjoo.miniproject_w2_jinjoo.R;
import com.example.choijinjoo.miniproject_w2_jinjoo.model.Restaurant;

import java.util.List;

/**
 * Created by choijinjoo on 2017. 7. 14..
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {
    List<Restaurant> items;
    Context context;
    CheckedListener listener;

    interface CheckedListener {
        void Checked(int position, Restaurant restaurant);
    }

    public RestaurantAdapter(Context context, List<Restaurant> items, CheckedListener listener) {
        this.items = items;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RestaurantViewHolder(context, LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        holder.bindView(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
