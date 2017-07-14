package android.com.miniproject2.view;

import android.com.miniproject2.R;
import android.com.miniproject2.model.ItemData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by nasos on 2017-07-13.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    ArrayList<ItemData> itemDatas;
    Context context;
    View view;

    public RecyclerAdapter(ArrayList<ItemData> itemDatas, Context context) {
        this.itemDatas = itemDatas;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = LayoutInflater.from(context).inflate(R.layout.item, null);



        return new BaseViewHolder(view);



    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        BaseViewHolder VHItem = (BaseViewHolder)holder;

        VHItem.restName.setText(itemDatas.get(position).getName());
        VHItem.photo.setImageResource(itemDatas.get(position).getImg());
        VHItem.description.setText(itemDatas.get(position).getDescription());
        VHItem.check.setImageResource(itemDatas.get(position).getCheck());




    }



    @Override
    public int getItemCount() {
        return itemDatas != null ? itemDatas.size() : 0;
    }



}
