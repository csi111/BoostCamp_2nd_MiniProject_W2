package android.com.miniproject2.view;

import android.com.miniproject2.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by nasos on 2017-07-13.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView restName ;
    TextView description ;
    ImageView photo;
    ImageView check;
    int i = 1;
    public BaseViewHolder(View itemView) {
        super(itemView);
        restName = (TextView)itemView.findViewById(R.id.name);
        description = (TextView)itemView.findViewById(R.id.descriptPlace);
        photo = (ImageView) itemView.findViewById(R.id.photo);
        check = (ImageView) itemView.findViewById(R.id.checkImg);

        check.setOnClickListener(this);
    }

    public TextView getRestName() {
        return restName;
    }

    public TextView getDescription() {
        return description;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public ImageView getCheck() {
        return check;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checkImg:
                view.setTextDirection(View.TEXT_DIRECTION_FIRST_STRONG);
                if(i ==1){
                check.setImageResource(R.drawable.checked);
                    i = 0;
                }
                else{

                    check.setImageResource(R.drawable.check);
                    i=1;

                }
        }
    }

}
