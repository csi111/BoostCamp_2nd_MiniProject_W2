package com.example.choijinjoo.miniproject_w2_jinjoo.db;

import android.content.Context;
import android.util.Log;

import com.example.choijinjoo.miniproject_w2_jinjoo.model.Restaurant;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by choijinjoo on 2017. 7. 13..
 */

public class RestaurantRepository extends BaseRepository {
    private final String TAG = "RestaurantRepository";
    private DatabaseHelper dbHelper;
    private Dao<Restaurant, Integer> restaurantDao;

    public RestaurantRepository(Context context) {
        dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        restaurantDao = dbHelper.getRestaurantRuntimeDao();
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> results = new ArrayList<>();
        try {
            results.addAll(restaurantDao.queryForAll());
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
        return results;
    }

    public int updateRestaurant(Restaurant restaurant) {
        try {
            return restaurantDao.update(restaurant);
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
        return -1;
    }

    public List<Restaurant> getRestaurantsOrderByDistance() {
        List<Restaurant> results = new ArrayList<>();
        try {
            results.addAll(restaurantDao.queryBuilder().orderBy("distance", true).query());
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
        return results;
    }

    public List<Restaurant> getRestaurantsOrderByStar() {
        List<Restaurant> results = new ArrayList<>();
        try {
            results.addAll(restaurantDao.queryBuilder().orderBy("star", false).query());
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
        return results;
    }

    public List<Restaurant> getRestaurantsOrderByCreatedAt() {
        List<Restaurant> results = new ArrayList<>();
        try {
            results.addAll(restaurantDao.queryBuilder().orderBy("CreatedAt", false).query());
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
        return results;
    }
}
