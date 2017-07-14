package com.example.choijinjoo.miniproject_w2_jinjoo.ui;

import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.choijinjoo.miniproject_w2_jinjoo.R;
import com.example.choijinjoo.miniproject_w2_jinjoo.db.RestaurantRepository;
import com.example.choijinjoo.miniproject_w2_jinjoo.db.SharedPreferenceHelper;
import com.example.choijinjoo.miniproject_w2_jinjoo.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtvDistance;
    TextView txtvStar;
    TextView txtvCreatedAt;
    ImageView imgvRearrange;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    RecyclerView recvRestaurants;

    RestaurantAdapter adapter;
    RestaurantRepository restaurantRepository;
    List<TextView> filters = new ArrayList<>();

    RestaurantAdapter.CheckedListener checkedListener = new RestaurantAdapter.CheckedListener() {
        @Override
        public void Checked(int position, Restaurant restaurant) {
            restaurant.setChecked(!restaurant.isChecked());
            if (restaurantRepository.updateRestaurant(restaurant) != -1)
                adapter.notifyItemChanged(position);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restaurantRepository = new RestaurantRepository(this);

        initLayout();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initLayout() {

        // bind View
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtvDistance = (TextView) findViewById(R.id.txtvDistance);
        txtvStar = (TextView) findViewById(R.id.txtvStar);
        txtvCreatedAt = (TextView) findViewById(R.id.txtvCreatedAt);
        imgvRearrange = (ImageView) findViewById(R.id.imgvRearrange);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        recvRestaurants = (RecyclerView) findViewById(R.id.recvRestaurants);

        // init Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.toolbar_title));

        // init Drawer Layout
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        // set ClickListener
        txtvDistance.setOnClickListener(this);
        txtvStar.setOnClickListener(this);
        txtvCreatedAt.setOnClickListener(this);
        imgvRearrange.setOnClickListener(this);

        // add filter into filters
        filters.add(txtvDistance);
        filters.add(txtvStar);
        filters.add(txtvCreatedAt);


        // init RecyclerView
        rearrangeLayout();
        txtvDistance.setSelected(true);
        adapter = new RestaurantAdapter(this, restaurantRepository.getRestaurantsOrderByDistance(), checkedListener);
        recvRestaurants.setAdapter(adapter);

    }


    private void rearrangeLayout() {
        boolean staggered = SharedPreferenceHelper.getBooleanValue(this, "rearrange_stggered", false);
        imgvRearrange.setSelected(staggered);
        if (staggered)
            recvRestaurants.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        else
            recvRestaurants.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtvDistance:
                changeRecvItems(txtvDistance, restaurantRepository.getRestaurantsOrderByDistance());
                break;
            case R.id.txtvStar:
                changeRecvItems(txtvStar, restaurantRepository.getRestaurantsOrderByStar());
                break;
            case R.id.txtvCreatedAt:
                changeRecvItems(txtvCreatedAt, restaurantRepository.getRestaurantsOrderByCreatedAt());
                break;
            case R.id.imgvRearrange:
                SharedPreferenceHelper.rearrange(this);
                rearrangeLayout();
                break;
        }
    }

    private void changeRecvItems(View selectedView, List<Restaurant> items) {
        initTextViewState();
        selectedView.setSelected(true);
        adapter.changeItems(items);
    }

    private void initTextViewState() {
        for (TextView filter : filters) {
            if (filter.isSelected())
                filter.setSelected(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        restaurantRepository.release();
    }
}
