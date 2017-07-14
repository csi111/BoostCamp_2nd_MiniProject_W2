package com.example.choijinjoo.miniproject_w2_jinjoo.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.choijinjoo.miniproject_w2_jinjoo.R;
import com.example.choijinjoo.miniproject_w2_jinjoo.model.Restaurant;
import com.example.choijinjoo.miniproject_w2_jinjoo.tools.Utils;

/**
 * Created by choijinjoo on 2017. 7. 14..
 */

public class RestaurantViewHolder extends RecyclerView.ViewHolder {
    Context context;
    TextView txtvName;
    TextView txtvDescription;
    ImageView imgvRestaurant;
    ImageView imgvCheck;
    RestaurantAdapter.CheckedListener listener;

    public RestaurantViewHolder(Context context, View itemView, RestaurantAdapter.CheckedListener listener) {
        super(itemView);
        this.context = context;
        this.listener = listener;
        initLayout(itemView);
    }

    private void initLayout(View itemView) {
        txtvName = (TextView) itemView.findViewById(R.id.txtvName);
        txtvDescription = (TextView) itemView.findViewById(R.id.txtvDescription);
        imgvRestaurant = (ImageView) itemView.findViewById(R.id.imgvRestaurant);
        imgvCheck = (ImageView) itemView.findViewById(R.id.imgvCheck);
        imgvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.Checked(getAdapterPosition());
            }
        });

    }

    public void bindView(final Restaurant restaurant) {
        txtvName.setText(restaurant.getName());
        txtvDescription.setText(restaurant.getDescription());

        imgvRestaurant.setImageBitmap(Utils.getResizedBitmap(context,restaurant.getImage()));
        imgvCheck.setSelected(restaurant.isChecked());

    }

}
