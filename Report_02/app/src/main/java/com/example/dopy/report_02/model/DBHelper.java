package com.example.dopy.report_02.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dopy.report_02.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dopy on 2017-07-13.
 * http://dunkinpender.tistory.com/4
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "restaurant.db";
    private static final int DATABASE_VERSION = 12;
    DBHelper DataBasehelper;
    private static final int ORDER_BY_DISTANCE=101;
    private static final int ORDER_BY_POPULARITY=102;
    private static final int ORDER_BY_RECENT=103;
    Context context;
    private Dao<Item, Integer> mRestaurant;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    /*헬퍼 인스턴스가 생성되고 테이블을 생성한다.
    SQLite에서는 쿼리를 직접 입력해야 했지만 이 경우 사전에 정의한 클래스에 마크를 보고 테이블을 생성*/
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.d("DB","테이블생성");
            TableUtils.createTable(connectionSource, Item.class);  //Table 생성
            setUpData();
        } catch (SQLException e) {
            Log.e(OrmLiteSqliteOpenHelper.class.getName(), "Unable to create datbases", e);
        }
    }
    public void test(){
        ArrayList<Item> arrayList=DataBasehelper.select();
        for(int i=0;i<arrayList.size();i++){
            Log.d("TEST",arrayList.get(i).getName());
        }
    }

    //    버전이 업데이트 되었을 때 DB의 행동을 정의하게 된다.
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.d("DBhelper","UpgragdeDatabase");
            TableUtils.dropTable(connectionSource, Item.class, true);    //업데이트시 테이블 삭제
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(OrmLiteSqliteOpenHelper.class.getName(),
                    "Unable to upgrade database from version " + oldVersion + " to new " + newVersion, e);
        }
    }

    public Dao<Item, Integer> getStandardInfosDao() throws SQLException {
        if (mRestaurant == null) {
            mRestaurant = getDao(Item.class);
        }
        return mRestaurant;
    }
    //DB 컨트롤 부분
    public void insert(Item item) {
        try {
            Dao<Item, Integer> ItemIntegerDao = new DBHelper(context).getStandardInfosDao();
            ItemIntegerDao.createOrUpdate(item);
        } catch (SQLException e) {
            Log.e("DBHelper",e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Item> select(){
        ArrayList<Item> itemArrayList=null;
        try {
            Dao<Item, Integer> ItemIntegerDao = new DBHelper(context).getStandardInfosDao();
            QueryBuilder<Item,Integer> qb=ItemIntegerDao.queryBuilder().orderBy("popularity",false);
            itemArrayList=(ArrayList<Item>)ItemIntegerDao.query(qb.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemArrayList;
    }

    public void setUpData(){
        Log.d("DBhleper","SetupData");
        this.insert(new Item("rufXXX",
                "커피든 맥주든 혹은 와인이든 당신의 시간을 즐기는 데에는 오직 한 잔이면 충분하다. 극단 루프엑스가 운영하는 분위기 깡패 바&카페!",
                R.drawable.rufxxx,1,0,1));
        this.insert(new Item("이탈리",
                "근방 이탈리안 레스토랑의 판도를 뒤집었다. 이탈리아의 프리미엄 식자재 브랜드가 차린 곳이니 맛만큼은 그 어떤 곳보다 현지급.",
                R.drawable.italy,2,9,3));
        this.insert(new Item("매그놀리아",
                "혀가 녹아내릴 달콤함과 세포들이 순식간에 팽창해버릴 것만 같은 칼로리지만 결코 포기할 수 없다. 단 한 입으로 드라마 속 캐리가 된 기분!",
                R.drawable.megnolia,3,8,2));
        this.insert(new Item("능라",
                "메밀 향 짙은 면발을 툭툭 끊어내고 이 시리게 육수를 퍽퍽 퍼먹자. 만두에 제육, 거기에 막걸리까지- 평냉은 역시 겨울이 갑이다.",
                R.drawable.nungra,4,7,6));
        this.insert(new Item("알레그리아",
                "서울에서 굳이 마시기 위해 찾아와야 하는 최고의 라떼. 라떼와 아포가또 모두를 품은 카페 곤엘라또는 마셔보지 않은 자는 그 맛을 모른다.",
                R.drawable.algia,5,6,5));
        this.insert(new Item("로네펠트 티 카페",
                "끝없이 펼쳐진 차의 향연! 문을 열자마자 폐부를 깊이 파고드는 단아한 향은 차 덕후의 정신을 아득히 멀리 날려버린다. 세계 3대 홍차 브랜드의 위엄!",
                R.drawable.ronepelt,6,5,4));
        this.insert(new Item("블루밍가든",
                "\"비싼 음식이 맛있는 게 아니다. 맛있는 음식이 비싼 거다.\" 지갑은 얇아지고 월급은 마치 사이버 머니와 같지만 그래도 역시 여기다.",
                R.drawable.bluming,7,4,7));
        this.insert(new Item("아임홈",
                "입맛 까다로운 우리 엄마도 만족시킬 새하얀 설원 같은 밀크빙수! 내가 널 가장 처음 무너뜨리고 말겠다는 굳은 의지",
                R.drawable.imhome,8,3,8));
        this.insert(new Item("혜선이떡볶이",
                "홍대 유명 즉떡을 먹기 위해 이제 서울까지 가지 말자. 그 떡볶이의 기술을 그대로 이어받았으니. 심지어 더 맛있다는 평이 있을 정도!",
                R.drawable.heseng,9,2,9));
        this.insert(new Item("홀드미",
                "참 먹을 곳 많은 판교에서 굳이 이곳까지 오는 이유는 아주 간단하다. 그저 '맛있다'로 끝나지 않는 라떼와 캠벨포도쥬스가 있으니까.",
                R.drawable.holdme,0,1,0));
    }
}
