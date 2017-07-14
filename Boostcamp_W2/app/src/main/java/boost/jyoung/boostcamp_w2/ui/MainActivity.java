package boost.jyoung.boostcamp_w2.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ToggleButton;

import java.util.ArrayList;

import boost.jyoung.boostcamp_w2.Model.RestaurantList;
import boost.jyoung.boostcamp_w2.R;
import boost.jyoung.boostcamp_w2.SQLite.DBHelper;
import boost.jyoung.boostcamp_w2.ui.Adapter.PageAdapter;

import static boost.jyoung.boostcamp_w2.R.id.tablayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private PageAdapter pageAdapter;
    private ToggleButton managercontrol;
    static boolean flag = false;
    ArrayList<RestaurantList> restaurantLists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        initView();
        createDB();
    }

    public void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    public void initView(){

        managercontrol = (ToggleButton)findViewById(R.id.managercontrol);
        managercontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(managercontrol.isChecked()){
                    flag = true;
                    pageAdapter.notifyDataSetChanged();
                }else{
                    flag = false;
                    pageAdapter.notifyDataSetChanged();
                }
            }

        });

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        tabLayout = (TabLayout)findViewById(tablayout);

        setTab();

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.setCurrentItem(0);

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
    public void setTab(){
        String[] tabName = getResources().getStringArray(R.array.tab_name);
        for(int i=0; i<3; i++){
            tabLayout.addTab(tabLayout.newTab().setText(tabName[i]));
        }
    }


    public void createDB(){
        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "RestaurantList.db", null, 1);
        int length =getResources().getStringArray(R.array.list_title).length;
        String[] img = getResources().getStringArray(R.array.list_img2);
        String[] title = getResources().getStringArray(R.array.list_title);
        String[] content = getResources().getStringArray(R.array.list_content);
        int[] distance = getResources().getIntArray(R.array.list_distance);
        int[] popular = getResources().getIntArray(R.array.list_popular);
        int[] postdate = getResources().getIntArray(R.array.list_postdate);
        int[] resId = new int[length];


        restaurantLists = new ArrayList<>();
        for (int i =0; i<length; i++){
            resId[i] = getApplicationContext().getResources().getIdentifier(img[i],"drawable",getApplicationContext().getPackageName());
            dbHelper.insert(resId[i], title[i], content[i], distance[i], popular[i], postdate[i], RestaurantFragment.UNCHECKED);
        }
    }
}
