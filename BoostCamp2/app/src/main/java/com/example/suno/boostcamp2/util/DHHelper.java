package com.example.suno.boostcamp2.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by suno on 2017. 7. 13..
 */

public class DHHelper extends SQLiteOpenHelper {
    public DHHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL();
    }

}
