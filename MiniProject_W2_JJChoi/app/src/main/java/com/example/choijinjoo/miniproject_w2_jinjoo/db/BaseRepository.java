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

public class BaseRepository {

    public void release(){
        OpenHelperManager.releaseHelper();
    }
}
