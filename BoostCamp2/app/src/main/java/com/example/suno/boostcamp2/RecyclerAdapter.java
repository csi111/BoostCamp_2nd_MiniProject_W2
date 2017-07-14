package com.example.suno.boostcamp2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.suno.boostcamp2.data.FamousPlace;

import java.util.ArrayList;

/**
 * Created by suno on 2017. 7. 13..
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_FAMOUS_PLACE = 1;

    private ArrayList<FamousPlace> itemList;
    private Context context;

    public RecyclerAdapter(ArrayList<FamousPlace> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_FAMOUS_PLACE:
                return new FamousPlaceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_famous_place, parent, false), context);

        }
        //TODO null처리 할 것!!!
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case TYPE_FAMOUS_PLACE:

                ((FamousPlaceViewHolder)holder).bindView(itemList.get(position));
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return itemList.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

}
