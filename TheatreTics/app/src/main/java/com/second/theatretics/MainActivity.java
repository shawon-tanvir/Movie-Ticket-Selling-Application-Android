package com.second.theatretics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    final DatabaseMovie db=new DatabaseMovie(this);
    ArrayList<MovieListType> imageArry = new ArrayList<MovieListType>();
    MoviePosterAdapter adapter;
    List<MovieListType> myMovieList;
    String[] listItemsValue = new String[15] ;
    int p=0;
    TextView helpemail;
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp=getSharedPreferences("key", Context.MODE_PRIVATE);
        value = sp.getString("email", "check");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        View inflatedView = getLayoutInflater().inflate(R.layout.activity_profile, null);
        helpemail=(TextView) inflatedView.findViewById(R.id.updateemailbox);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        myMovieList = db.getAllMovie();
        for (MovieListType cn : myMovieList) {
            //String log = "ID:" + cn.getM_id() + " Name: " + cn.getM_name() + " ,Poster: " + cn.getM_poster() + " ,Running: " + cn.getM_running();
            // Writing Contacts to log
            String c=cn.getM_name();
            //Log.d("Result: ", log);
            //Log.d("Result: ", "check");
            //add contacts data in arrayList
            listItemsValue[p++] = c;
            imageArry.add(cn);

        }
        adapter = new MoviePosterAdapter(this, R.layout.basic_movielist, imageArry);
        final ListView MovieList = (ListView) findViewById(R.id.Movielist);
        MovieList.setAdapter(adapter);


        MovieList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // TODO Auto-generated method stub
                //Toast.makeText(MainActivity.this, listItemsValue[position], Toast.LENGTH_SHORT).show();
                if(value.isEmpty()){
                    startActivity(new Intent(MainActivity.this,LoginwinActivity.class));
                    Toast.makeText(MainActivity.this, "You have to Log in First", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent myIntent = new Intent(MainActivity.this, HallListActivity.class);
                    myIntent.putExtra("MovieName",listItemsValue[position]);
                    startActivity(myIntent);
                }


            }
        });

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

        if (id == R.id.nav_manage) {
            String chy=value;
            if(value.isEmpty()){
                startActivity(new Intent(MainActivity.this,LoginwinActivity.class));
            }
            else{
                startActivity(new Intent(MainActivity.this,ProfileActivity.class));
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
