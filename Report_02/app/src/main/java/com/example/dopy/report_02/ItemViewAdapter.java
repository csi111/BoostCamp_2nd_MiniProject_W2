package com.example.dopy.report_02;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dopy.report_02.model.DataBaseManager;
import com.example.dopy.report_02.model.Item;

import java.util.ArrayList;

/**
 * Created by Dopy on 2017-07-14.
 */

public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.baseViewHolder> {
    private static final int TYPE_HEADER=101;
    private static final int TYPE_ITEM=102;

    ArrayList<Item> arrayList;
    Context context;

    public ItemViewAdapter(Context context,ArrayList<Item> itemArrayList) {
        this.context = context;
        this.arrayList=itemArrayList;
    }

    @Override
    public baseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new baseViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(baseViewHolder holder, int position) {
        holder.bindView(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class baseViewHolder extends RecyclerView.ViewHolder{
        ImageView imgView;
        TextView name;
        TextView contents;

        public baseViewHolder(View itemView) {
            super(itemView);
            imgView=(ImageView)itemView.findViewById(R.id.imgMain);
            name=(TextView)itemView.findViewById(R.id.txtName);
            contents=(TextView)itemView.findViewById(R.id.txtContents);
        }

        public void bindView(Item item){
            imgView.setImageResource(item.getImagePath());
            name.setText(item.getName());
            contents.setText(item.getContents());
        }


    }
}
