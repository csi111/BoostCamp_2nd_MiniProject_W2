package com.example.suno.boostcamp2.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.suno.boostcamp2.MainActivity;
import com.example.suno.boostcamp2.RecyclerAdapter;
import com.example.suno.boostcamp2.data.FamousPlace;

import java.util.ArrayList;

/**
 * Created by suno on 2017. 7. 13..
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "FAMOUSPLACE";
    private static final String DATABASE_NAME = "famousplace.db";
    private static final int DATABASE_VERSION = 1;

    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String IMGURL = "imagUrl";
    private static final String EXPLANATION = "explanation";
    private static final String RECOMMANDATION_CNT = "recommandationCnt";
    private static final String DISTANCE = "distance";
    private static final String DATE = "date";
    private static final String FLAG = "flag";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ TABLE_NAME +"("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " text not null, "
                + IMGURL + " text not null, "
                + EXPLANATION + " text not null, "
                + RECOMMANDATION_CNT + " INTEGER not null, "
                + DISTANCE + " INTEGER not null, "
                + DATE + " INTEGER not null, "
                + FLAG + " INTEGER not null) ";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addData(FamousPlace fp){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID, fp.getId());
        values.put(NAME, fp.getName());
        values.put(IMGURL, fp.getImgUrl());
        values.put(EXPLANATION, fp.getExplanation());
        values.put(RECOMMANDATION_CNT, fp.getRecommandationCnt());
        values.put(DISTANCE, fp.getDistance());
        values.put(DATE, fp.getDate());
        values.put(FLAG, fp.getFlag());

        long newRowId;
        newRowId = db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public int updateData(FamousPlace fp){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FLAG, fp.getFlag());

        return db.update(TABLE_NAME, values, ID + " =? ",
                new String[]{String.valueOf(fp.getId()) });

    }

    public ArrayList<FamousPlace> getData(int orderedBy){
        ArrayList<FamousPlace> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery;
        if(orderedBy == MainActivity.ORDEREDBY_DISTANCE){
            selectQuery= "SELECT * FROM " + TABLE_NAME
                    + " ORDER BY " + DISTANCE + " ASC";
        }
        else if(orderedBy == MainActivity.ORDEREDBY_POPULARIRY){
            selectQuery= "SELECT * FROM " + TABLE_NAME
                    + " ORDER BY " + RECOMMANDATION_CNT + " DESC";
        }
        else{ //ORDEREDBY_LATEST
            selectQuery= "SELECT * FROM " + TABLE_NAME
                    + " ORDER BY " + DATE + " DESC";
        }

        Cursor c = db.rawQuery(selectQuery, null);

        while(c.moveToNext()){
            FamousPlace fp = new FamousPlace(RecyclerAdapter.TYPE_FAMOUS_PLACE);
            fp.setId(Integer.parseInt(c.getString(0)));
            fp.setName(c.getString(1));
            fp.setImgUrl(c.getString(2));
            fp.setExplanation(c.getString(3));
            fp.setRecommandationCnt(Integer.parseInt(c.getString(4)));
            fp.setDistance(Integer.parseInt(c.getString(5)));
            fp.setDate(Integer.parseInt(c.getString(6)));
            fp.setFlag(Integer.parseInt(c.getString(7)));

            list.add(fp);
        }

        return list;
    }

    public boolean checkNull(){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT count(*) FROM "+TABLE_NAME;
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        if(c.getCount() > 0){
            return false;
        }
        else
            return true;
    }

}
