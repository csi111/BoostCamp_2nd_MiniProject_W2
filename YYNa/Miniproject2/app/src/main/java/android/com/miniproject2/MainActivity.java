package android.com.miniproject2;

import android.com.miniproject2.model.ItemData;
import android.com.miniproject2.view.RecyclerAdapter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ArrayList<ItemData> itemDatas;
    LinearLayoutManager linearLayoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        makedata();
//
//        DBAdapter db = new DBAdapter(this);
////---add a contact---
//        db.open();
//        long id = db.insertData("Michael Jackson", 12333,"mike@gmail.com", 1,332);
//        id = db.insertData("hohoho", 1,"aaaaa@gmail.com", 3331,999);
//        db.close();
//// ---get all contacts---
//        db.open();
//        Cursor c = db.getAllContacts();
//        if (c.moveToFirst()) {
//            do {
//                DisplayContact(c);
//            } while (c.moveToNext());
//        }
//        db.close();
//
//        mainText.setText(tmp);



    }

    private void makedata() {


        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);






        // add data
        itemDatas = new ArrayList<ItemData>();
        itemDatas.add(new ItemData("맛집1",R.drawable.photo,"설명?",R.drawable.check));
        itemDatas.add(new ItemData("맛집2",R.drawable.photo,"설명?",R.drawable.checked));
        itemDatas.add(new ItemData("맛집3",R.drawable.photo,"설명?",R.drawable.check));
        itemDatas.add(new ItemData("맛집4",R.drawable.photo,"설명?",R.drawable.checked));


        recyclerAdapter = new RecyclerAdapter(itemDatas, this);
        recyclerView.setAdapter(recyclerAdapter);

    }

//
//    public void DisplayContact(Cursor c) {
//        tmp += "name   " + c.getString(1) + "   img  "+c.getInt(2)  + "  dest  "+c.getString(3)  + "   dist   "+c.getInt(4) +  "   pop   "+c.getInt(5) +'\n' ;
//
//    }





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
}
