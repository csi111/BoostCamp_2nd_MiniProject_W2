package com.example.suno.boostcamp2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.suno.boostcamp2.data.FamousPlace;
import com.example.suno.boostcamp2.data.ItemType;
import com.example.suno.boostcamp2.util.DBHelper;

/**
 * Created by suno on 2017. 7. 14..
 */

public class FamousPlaceViewHolder extends RecyclerView.ViewHolder{
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
                }
                else{
                    fp.setFlag(0);
                    Log.d("CHECK", "0000");
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
