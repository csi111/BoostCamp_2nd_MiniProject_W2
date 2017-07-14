package com.example.choijinjoo.miniproject_w2_jinjoo.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by choijinjoo on 2017. 7. 14..
 */

public class Utils {

    public static Bitmap getResizedBitmap(Context context, String id) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        // Calculate inSampleSize
        options.inSampleSize = 3;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(context.getResources(),
                context.getResources().getIdentifier(id, "drawable", context.getPackageName()), options);
    }

}
