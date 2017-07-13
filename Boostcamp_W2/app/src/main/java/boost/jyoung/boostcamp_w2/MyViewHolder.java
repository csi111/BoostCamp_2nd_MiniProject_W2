package boost.jyoung.boostcamp_w2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jyoung on 2017. 7. 13..
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView itemImg;
    TextView itemTitle, itemContent;

    public MyViewHolder(View itemView) {
        super(itemView);
        itemImg = (ImageView)itemView.findViewById(R.id.item_img);
        itemTitle = (TextView)itemView.findViewById(R.id.item_title);
        itemContent = (TextView)itemView.findViewById(R.id.item_content);
    }

    public void bindView(ArrayList<RestaurantList> restaurantList){
        final int position = getAdapterPosition();
        itemImg.setImageResource(restaurantList.get(position).itemImg);
        itemTitle.setText(restaurantList.get(position).itemTitle);
        itemContent.setText(restaurantList.get(position).itemContent);
    }
}
