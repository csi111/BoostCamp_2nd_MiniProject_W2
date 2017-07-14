package android.com.miniproject2;

import android.com.miniproject2.model.DBAdapter;
import android.com.miniproject2.model.ItemData;
import android.com.miniproject2.view.RecyclerAdapter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {


    TextView menuDist;
    TextView menuPop;
    TextView menuRecent;
    ArrayList<ItemData> itemDatas;
    LinearLayoutManager linearLayoutManager;
    StaggeredGridLayoutManager staggeredLayoutManager;
    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    ImageView change;
    int flag = 1;
    int state = 1;


    DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
        makedata(state);


    }

    private void initLayout() {

        menuDist = (TextView) findViewById(R.id.menu_dist);
        menuPop = (TextView) findViewById(R.id.menu_pop);
        menuRecent = (TextView) findViewById(R.id.menu_recent);
        menuDist.setOnClickListener(this);
        menuPop.setOnClickListener(this);
        menuRecent.setOnClickListener(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        change = (ImageView) findViewById(R.id.changeSelector);
        change.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_dist:
                menuDist.setTextColor(getResources().getColor(R.color.mint));
                menuPop.setTextColor(getResources().getColor(R.color.black));
                menuRecent.setTextColor(getResources().getColor(R.color.black));
                state = 1;
                break;
            case R.id.menu_pop:
                menuDist.setTextColor(getResources().getColor(R.color.black));
                menuPop.setTextColor(getResources().getColor(R.color.mint));
                menuRecent.setTextColor(getResources().getColor(R.color.black));
                state = 2;
                break;
            case R.id.menu_recent:
                menuDist.setTextColor(getResources().getColor(R.color.black));
                menuPop.setTextColor(getResources().getColor(R.color.black));
                menuRecent.setTextColor(getResources().getColor(R.color.mint));
                state = 3;
                break;
            case R.id.changeSelector:
                if (flag == 1) {
                    flag = 0;
                    change.setImageResource(R.drawable.ic_linear_block);
                } else {
                    flag = 1;
                    change.setImageResource(R.drawable.ic_staggered_block);
                }
                break;


        }
        makedata(state);
    }


    private void makedata(int check) {

        if (flag == 1) {

            linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);

        } else {

            staggeredLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(staggeredLayoutManager);
        }


        int tmpCheck;

        db = new DBAdapter(this);

// ---get all contacts---
        db.open();

        itemDatas = new ArrayList<ItemData>();
        Cursor c = db.getAllContactsByDistOrder();
        if (check == 2) c = db.getAllContactsByPopularity();
        else if (check == 3) c = db.getAllContactsByRecent();


        if (c.moveToFirst()) {
            do {

                tmpCheck = c.getInt(6);

                if (tmpCheck == 1) tmpCheck = R.drawable.check;
                else tmpCheck = R.drawable.checked;


                itemDatas.add(new ItemData(c.getInt(0), c.getString(1), c.getInt(2), getString(c.getInt(3)), tmpCheck));

            } while (c.moveToNext());
        }
        db.close();

        recyclerAdapter = new RecyclerAdapter(itemDatas, this);
        recyclerView.setAdapter(recyclerAdapter);

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

}
