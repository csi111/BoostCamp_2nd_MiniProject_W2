package android.com.miniproject2.model;

import android.com.miniproject2.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by nasos on 2017-07-13.
 */

public class DBAdapter {

    static final String KEY_ROWID = "_id";
    static final String KEY_NAME = "name";
    static final String KEY_IMAGE = "image";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_POPULARITY = "popularity";
    static final String KEY_DIST = "distance";
    static final String KEY_CHECK = "checked";

    static final String TAG = "DBAdapter";
    static final String DATABASE_NAME = "RestaurantDB";
    static final String DATABASE_TABLE = "restaurants";
    static final int DATABASE_VERSION = 5;


    static final String DATABASE_CREATE =
            "create table " + DATABASE_TABLE + " (_id integer primary key autoincrement, "
                    + KEY_NAME + " text not null, "
                    + KEY_IMAGE + " integer not null, "
                    + KEY_DESCRIPTION + " integer not null, "
                    + KEY_POPULARITY + " integer not null, "
                    + KEY_DIST + " integer not null ,"
                    + KEY_CHECK + " integer not null " + ");";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);


    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        Context ctx;

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {


            try {
                db.execSQL(DATABASE_CREATE);
                //data insert test
                db.execSQL("insert into " + DATABASE_TABLE + "(name, image, description, popularity, distance, checked) " +
                        "values ('파이어벨'," + R.drawable.res_photo1 + ", " + R.string.des_fire + ", " + 10 + "," + 300 + ", " + 1 + ");");
                db.execSQL("insert into " + DATABASE_TABLE + "(name, image, description, popularity, distance, checked) " +
                        "values ('다운타우너'," + R.drawable.res_photo2 + ", " + R.string.des_down + ", " + 1 + "," + 100 + ", " + 1 + ");");
                db.execSQL("insert into " + DATABASE_TABLE + "(name, image, description, popularity, distance, checked) " +
                        "values ('그릴파이브타코'," + R.drawable.res_photo3 + ", " + R.string.des_taco + ", " + 4 + "," + 10 + ", " + 1 + ");");
                db.execSQL("insert into " + DATABASE_TABLE + "(name, image, description, popularity, distance, checked) " +
                        "values ('런드리피자'," + R.drawable.res_photo4 + ", " + R.string.des_pizza + ", " + 2 + "," + 200 + ", " + 1 + ");");
                db.execSQL("insert into " + DATABASE_TABLE + "(name, image, description, popularity, distance, checked) " +
                        "values ('할랄가이즈'," + R.drawable.res_photo5 + ", " + R.string.des_halal + ", " + 50 + "," + 500 + ", " + 1 + ");");


            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }

    // ---opens the database---
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    // ---closes the database---
    public void close() {
        DBHelper.close();
    }

    // ---insert a contact into the database---
    public long insertData(String name, int img, int description, int pop, int dist, int flag) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_IMAGE, img);
        initialValues.put(KEY_DESCRIPTION, description);
        initialValues.put(KEY_POPULARITY, pop);
        initialValues.put(KEY_DIST, dist);
        initialValues.put(KEY_CHECK, flag);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    // ---deletes a particular contact---
    public boolean deleteContact(long rowId) {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    // ---retrieves all the contacts---
    public Cursor getAllContacts() {
        return db.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME,
                KEY_IMAGE, KEY_DESCRIPTION, KEY_POPULARITY, KEY_DIST, KEY_CHECK}, null, null, null, null, null);
    }


    public Cursor getAllContactsByRecent() {
        return db.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME,
                KEY_IMAGE, KEY_DESCRIPTION, KEY_POPULARITY, KEY_DIST, KEY_CHECK}, null, null, null, null, KEY_ROWID + " DESC");
    }

    public Cursor getAllContactsByPopularity() {
        return db.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME,
                KEY_IMAGE, KEY_DESCRIPTION, KEY_POPULARITY, KEY_DIST, KEY_CHECK}, null, null, null, null, KEY_POPULARITY + " DESC");
    }

    public Cursor getAllContactsByDistOrder() {
        return db.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME,
                KEY_IMAGE, KEY_DESCRIPTION, KEY_POPULARITY, KEY_DIST, KEY_CHECK}, null, null, null, null, KEY_DIST + " ASC");
    }

    // ---retrieves a particular contact---
    public Cursor getContact(long rowId) throws SQLException {
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME,
                        KEY_IMAGE, KEY_DESCRIPTION, KEY_POPULARITY, KEY_DIST, KEY_CHECK}, KEY_ROWID + "=" + rowId,
                null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    // ---updates a contact---
    public boolean updateChcek(int name, int flag) {
        ContentValues args = new ContentValues();
        args.put(KEY_CHECK, flag);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + name, null) > 0;
    }


}
