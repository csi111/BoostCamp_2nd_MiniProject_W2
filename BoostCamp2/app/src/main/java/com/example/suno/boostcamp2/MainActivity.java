package com.example.suno.boostcamp2;

import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ToggleButton;

import com.example.suno.boostcamp2.data.FamousPlace;
import com.example.suno.boostcamp2.fragment.FamousPlaceFragment;
import com.example.suno.boostcamp2.util.DBHelper;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final int ORDEREDBY_DISTANCE = 101;
    public static final int ORDEREDBY_POPULARIRY = 102;
    public static final int ORDEREDBY_LATEST = 103;

    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private ToggleButton tgBtnRange;
    private DBHelper dbHelper;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        tgBtnRange = (ToggleButton)findViewById(R.id.toggleBtn_range);

        init();
        tabLayoutInit();

        tgBtnRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FamousPlaceFragment) pagerAdapter.getItem(viewPager.getCurrentItem())).stagger(tgBtnRange.isChecked());

            }
        });

        addData();

    }

    public void init(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void tabLayoutInit(){
        tabLayout.addTab(tabLayout.newTab().setText("거리순"));
        tabLayout.addTab(tabLayout.newTab().setText("인기순"));
        tabLayout.addTab(tabLayout.newTab().setText("최근순"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    public void addData(){
        dbHelper = new DBHelper(this);

        if(dbHelper.checkNull()){
            dbHelper.addData(new FamousPlace(RecyclerAdapter.TYPE_FAMOUS_PLACE, 1, "슈슈파나", "https://d2t7cq5f1ua57i.cloudfront.net/images/r_images/56386/53049/56386_53049_89_0_90_20167415735705.jpg"
                    ,"크림이 가득하고 색이 알록달록한 크림슈를 판매하는 슈슈파나입니다.", 77, 12,123, 0));
            dbHelper.addData(new FamousPlace(RecyclerAdapter.TYPE_FAMOUS_PLACE, 2, "디저트왕", "https://d2t7cq5f1ua57i.cloudfront.net/images/r_images/54386/55696/54386_55696_89_0_313_2016748443061.jpg"
                    ,"디저트계의 끝판왕 디저트왕입니다. 남녀노소 모두가 함께할 수 있는 메뉴가 준비되어 있습니다. 연인과 가족과 함께 방문해보세요!", 1237, 232,223, 0));
            dbHelper.addData(new FamousPlace(RecyclerAdapter.TYPE_FAMOUS_PLACE, 3, "이야기둘", "https://d2t7cq5f1ua57i.cloudfront.net/images/r_images/54519/51991/54519_51991_86_5_8099_201572552645459.jpg"
                    ,"개인화로에 구워먹는 판교 맛집, 분위기 좋은 이야기 둘입니다. 이자카야 분위기, 이자카야 메뉴들도 준비되어 있습니다.", 2237, 2332,2323, 0));
            dbHelper.addData(new FamousPlace(RecyclerAdapter.TYPE_FAMOUS_PLACE, 4, "호야초밥", "https://d2t7cq5f1ua57i.cloudfront.net/images/r_images/55023/56447/55023_56447_89_0_2818_201652154715907.jpg"
                    ,"줄서서먹는, 가성비좋은, 푸짐한맛집, 데이트코스, 남자친구랑 여자친구랑 오기 좋은, 연중무휴! 초밥의 세계에 빠져보실래요?", 12, 42, 33, 0));

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

}
