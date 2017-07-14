package com.example.choijinjoo.miniproject_w2_jinjoo.db;

/**
 * Created by choijinjoo on 2017. 7. 13..
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.choijinjoo.miniproject_w2_jinjoo.R;
import com.example.choijinjoo.miniproject_w2_jinjoo.model.Restaurant;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "Restaurant.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Restaurant, Integer> restaurantDao = null;
    private RuntimeExceptionDao<Restaurant, Integer> restaurantRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Restaurant.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        RuntimeExceptionDao<Restaurant, Integer> dao = getRestaurantRuntimeDao();

        dao.create(makeMockData());
    }

    private List<Restaurant> makeMockData() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("해우소", "img_restaurant1",
                "꿀먹걸리가 인기메뉴고 맛있는 음식을 저렴하게 즐길 수 있는 해우소입니다.", 1.8, 4.5,
                Calendar.getInstance().getTime(), false));
        restaurants.add(new Restaurant("삼교리동치미막국수", "img_restaurant1",
                "막국수를 비빔으로 먹을지 물로 먹을지 고객이 만들어 먹는 삼교리동치미막국수입니다.", 1.7, 2.0,
                Calendar.getInstance().getTime(), false));
        restaurants.add(new Restaurant("오야오야", "img_restaurant1",
                "오야오야 강추", 3.5, 2.5,
                Calendar.getInstance().getTime(), false));
        restaurants.add(new Restaurant("까치둥지", "img_restaurant1",
                "꿀먹걸리가 인기메뉴고 맛있는 음식을 저렴하게 즐길 수 있는 해우소입니다.", 2.0, 1.0,
                Calendar.getInstance().getTime(), false));
        return restaurants;
    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Restaurant.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the Database Access Object (DAO) for our RestaurantData class. It will create it or just give the cached
     * value.
     */
    public Dao<Restaurant, Integer> getDao() throws SQLException {
        if (restaurantDao == null) {
            restaurantDao = getDao(Restaurant.class);
        }
        return restaurantDao;
    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our RestaurantData class. It will
     * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     */
    public RuntimeExceptionDao<Restaurant, Integer> getRestaurantRuntimeDao() {
        if (restaurantRuntimeDao == null) {
            restaurantRuntimeDao = getRuntimeExceptionDao(Restaurant.class);
        }
        return restaurantRuntimeDao;
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        restaurantDao = null;
        restaurantRuntimeDao = null;
    }
}