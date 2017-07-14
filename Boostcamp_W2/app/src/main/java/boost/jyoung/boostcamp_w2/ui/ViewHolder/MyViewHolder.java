package boost.jyoung.boostcamp_w2.ui.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import boost.jyoung.boostcamp_w2.Model.RestaurantList;
import boost.jyoung.boostcamp_w2.R;
import boost.jyoung.boostcamp_w2.SQLite.DBHelper;
import boost.jyoung.boostcamp_w2.ui.Adapter.RecyclerAdapter;
import boost.jyoung.boostcamp_w2.ui.MainActivity;
import boost.jyoung.boostcamp_w2.ui.RestaurantFragment;

/**
 * Created by jyoung on 2017. 7. 13..
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView itemImg;
    TextView itemTitle, itemContent;
    ToggleButton check_btn;
    DBHelper dbHelper;
    RecyclerAdapter recyclerAdapter;
    Context context;

    public MyViewHolder(View itemView) {
        super(itemView);
        itemImg = (ImageView) itemView.findViewById(R.id.item_img);
        itemTitle = (TextView) itemView.findViewById(R.id.item_title);
        itemContent = (TextView) itemView.findViewById(R.id.item_content);
        check_btn = (ToggleButton) itemView.findViewById(R.id.check_btn);
    }

    public void bindView(final ArrayList<RestaurantList> restaurantList, final RecyclerAdapter recyclerAdapter, final Context context) {
        this.recyclerAdapter = recyclerAdapter;
        this.context = context;
        final DBHelper dbHelper = new DBHelper(context, "RestaurantList.db", null, 1);

        final int position = getAdapterPosition();
        itemImg.setImageResource(restaurantList.get(position).getItemImg());
        itemTitle.setText(restaurantList.get(position).getItemTitle());
        itemContent.setText(restaurantList.get(position).getItemContent());

        check_btn.setChecked(restaurantList.get(position).getCheck_state() == 0);

        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check_btn.isChecked())
                    restaurantList.get(position).setCheck_state(RestaurantFragment.CHECKED);
                else
                    restaurantList.get(position).setCheck_state(RestaurantFragment.UNCHECKED);

                dbHelper.Update(restaurantList.get(position).getItemTitle(), restaurantList.get(position).getCheck_state());
                recyclerAdapter.notifyDataSetChanged();
                ((MainActivity) MainActivity.mcontext).pagerRefresh();
            }
        });

    }
}
