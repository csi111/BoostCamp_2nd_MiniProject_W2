package android.com.miniproject2.view;

import android.com.miniproject2.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by nasos on 2017-07-13.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    TextView restName ;
    TextView description ;
    ImageView photo;
    ImageView check;

    public BaseViewHolder(View itemView) {
        super(itemView);
        restName = (TextView)itemView.findViewById(R.id.name);
        description = (TextView)itemView.findViewById(R.id.descriptPlace);
        photo = (ImageView) itemView.findViewById(R.id.photo);
        check = (ImageView) itemView.findViewById(R.id.checkImg);

    }



}
