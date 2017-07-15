package com.example.suno.boostcamp2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.suno.boostcamp2.data.FamousPlace;
import com.example.suno.boostcamp2.data.ItemType;
import com.example.suno.boostcamp2.util.DBHelper;

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

    public class FamousPlaceViewHolder extends RecyclerView.ViewHolder{
        private static final String TAG = "FamousPlaceViewHolder";

        private Context context;
        private FamousPlace fp;
        private ImageView imgvPlacePhoto;
        private TextView tvPlaceName, tvExplanation;
        private CheckBox checkBox;

        public FamousPlaceViewHolder(View itemView, final Context context) {
            super(itemView);

            this.context = context;

            imgvPlacePhoto = (ImageView)itemView.findViewById(R.id.imageView_place_photo);
            tvPlaceName = (TextView)itemView.findViewById(R.id.textView_place_name);
            checkBox = (CheckBox)itemView.findViewById(R.id.checkBox_flag);
            tvExplanation = (TextView)itemView.findViewById(R.id.textView_explanation);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        fp.setFlag(1);
                        Log.d("CHECK", "1111");
                        try{
                            notifyItemChanged(getAdapterPosition());
                        } catch(Exception e){
                            Log.d(TAG, e.toString());
                        }

                    }
                    else{
                        fp.setFlag(0);
                        Log.d("CHECK", "0000");
                        try{
                            notifyItemChanged(getAdapterPosition());
                        } catch(Exception e){
                            Log.d(TAG, e.toString());
                        }
                    }
                    DBHelper dbHelper = new DBHelper(context);
                    dbHelper.updateData(fp);
                }
            });
        }

        public void bindView(ItemType item){
            fp = ((FamousPlace)item);

            Glide.with(context).load(fp.getImgUrl()).into(imgvPlacePhoto);
            tvPlaceName.setText(fp.getName());
            checkBox.setChecked(fp.getFlag() == 1 ? true : false);
            tvExplanation.setText(fp.getExplanation());

        }
    }
}
