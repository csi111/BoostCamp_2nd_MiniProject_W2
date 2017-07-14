package boost.jyoung.boostcamp_w2.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import boost.jyoung.boostcamp_w2.Model.RestaurantList;

/**
 * Created by jyoung on 2017. 7. 14..
 */

public class DBHelper extends SQLiteOpenHelper {



    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE RESTAURANTLIST (itemImg INTEGER, itemTitle TEXT, itemContent TEXT, distance INTEGER, popular INTEGER, postdate INTEGER, check_state INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }


    public void insert(int itemImg, String itemTitle, String itemContent, int distance, int popular, int postdate, int check_state) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "select * from RESTAURANTLIST";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.getCount()<7) {

            db.execSQL("INSERT INTO RESTAURANTLIST VALUES('" + itemImg + "', '" + itemTitle + "', '" + itemContent + "', " + distance + ", " + popular + ", " + postdate + ", " + check_state + ");");
            db.close();
        }
    }


    public ArrayList<RestaurantList> getDataFromDB(ArrayList<RestaurantList> lists){
        String query = "select * from RESTAURANTLIST";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                RestaurantList model = new RestaurantList();
                model.setItemImg(cursor.getInt(0));
                model.setItemTitle(cursor.getString(1));
                model.setItemContent(cursor.getString(2));
                model.setDistance(cursor.getInt(3));
                model.setPopular(cursor.getInt(4));
                model.setPostdate(cursor.getInt(5));
                model.setCheck_state(cursor.getInt(6));

                lists.add(model);
            }while (cursor.moveToNext());
        }
        return lists;
    }

    public void Update(String itemTitle, int check_state){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("check_state", check_state);
        db.update("RESTAURANTLIST",values,"itemTitle = ?",new String[]{itemTitle});
    }


}
