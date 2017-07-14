package com.example.dopy.report_02;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dopy.report_02.model.DBHelper;
import com.example.dopy.report_02.model.DataBaseManager;
import com.example.dopy.report_02.model.Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DBHelper DataBasehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//데이터 베이스 생성
        DataBaseManager.init(this);
        test();
    }
    public void test(){
        ArrayList<Item> arrayList=DataBaseManager.getInstance().select();
        for(int i=0;i<arrayList.size();i++){
            Log.d("TEST",arrayList.get(i).getName());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
/*    public void setUpData(){
        Log.d("DBhleper","SetupData");
        DataBasehelper.insert(new Item("rufXXX",
                "커피든 맥주든 혹은 와인이든 당신의 시간을 즐기는 데에는 오직 한 잔이면 충분하다. 극단 루프엑스가 운영하는 분위기 깡패 바&카페!",
                R.drawable.rufxxx,1,0,1));
        DataBasehelper.insert(new Item("이탈리",
                "근방 이탈리안 레스토랑의 판도를 뒤집었다. 이탈리아의 프리미엄 식자재 브랜드가 차린 곳이니 맛만큼은 그 어떤 곳보다 현지급.",
                R.drawable.italy,2,9,3));
        DataBasehelper.insert(new Item("매그놀리아",
                "혀가 녹아내릴 달콤함과 세포들이 순식간에 팽창해버릴 것만 같은 칼로리지만 결코 포기할 수 없다. 단 한 입으로 드라마 속 캐리가 된 기분!",
                R.drawable.megnolia,3,8,2));
        DataBasehelper.insert(new Item("능라",
                "메밀 향 짙은 면발을 툭툭 끊어내고 이 시리게 육수를 퍽퍽 퍼먹자. 만두에 제육, 거기에 막걸리까지- 평냉은 역시 겨울이 갑이다.",
                R.drawable.nungra,4,7,6));
        DataBasehelper.insert(new Item("알레그리아",
                "서울에서 굳이 마시기 위해 찾아와야 하는 최고의 라떼. 라떼와 아포가또 모두를 품은 카페 곤엘라또는 마셔보지 않은 자는 그 맛을 모른다.",
                R.drawable.algia,5,6,5));
        DataBasehelper.insert(new Item("로네펠트 티 카페",
                "끝없이 펼쳐진 차의 향연! 문을 열자마자 폐부를 깊이 파고드는 단아한 향은 차 덕후의 정신을 아득히 멀리 날려버린다. 세계 3대 홍차 브랜드의 위엄!",
                R.drawable.ronepelt,6,5,4));
        DataBasehelper.insert(new Item("블루밍가든",
                "\"비싼 음식이 맛있는 게 아니다. 맛있는 음식이 비싼 거다.\" 지갑은 얇아지고 월급은 마치 사이버 머니와 같지만 그래도 역시 여기다.",
                R.drawable.bluming,7,4,7));
        DataBasehelper.insert(new Item("아임홈",
                "입맛 까다로운 우리 엄마도 만족시킬 새하얀 설원 같은 밀크빙수! 내가 널 가장 처음 무너뜨리고 말겠다는 굳은 의지",
                R.drawable.imhome,8,3,8));
        DataBasehelper.insert(new Item("혜선이떡볶이",
                "홍대 유명 즉떡을 먹기 위해 이제 서울까지 가지 말자. 그 떡볶이의 기술을 그대로 이어받았으니. 심지어 더 맛있다는 평이 있을 정도!",
                R.drawable.heseng,9,2,9));
        DataBasehelper.insert(new Item("홀드미",
                "참 먹을 곳 많은 판교에서 굳이 이곳까지 오는 이유는 아주 간단하다. 그저 '맛있다'로 끝나지 않는 라떼와 캠벨포도쥬스가 있으니까.",
                R.drawable.holdme,0,1,0));
    }*/
}
