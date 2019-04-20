package com.second.theatretics;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class HallListActivity extends AppCompatActivity {
    //RestaurantListType RLS;

    // gets the previously created intent
    String MovieName;
    final DatabaseHall dbHall=new DatabaseHall(this);
    ArrayList<HallListType> imageArry = new ArrayList<HallListType>();
    HallListAdapter adapter;
    List<HallListType> myHallList;
    String[] listItemValue = new String[8] ;
    int[] HallId = new int[8] ;
    int[] listprice=new int[8];
    int p=0;
    int Movieid;
    //FloatingActionButton floatbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_list);

        try {
            ImageView img = (ImageView) findViewById(R.id.imageIconHallList);
            TextView txt= (TextView) findViewById(R.id.MovienameHallList);
            final DatabaseMovie db = new DatabaseMovie(this);

            Intent myIntent = getIntent();
            //
            MovieName = myIntent.getStringExtra("MovieName");

            MovieListType RLS = db.getSingleMovie(MovieName);
            Log.wtf("checkfor",MovieName);
            //Toast.makeText(FoodListActivity1.this, RestaurantName, Toast.LENGTH_SHORT).show();

            byte[] outImage = RLS.getM_poster();
            Movieid=RLS.getM_id();
            ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            img.setImageBitmap(theImage);
            txt.setText(MovieName);
        }
        catch(Exception e){
            //Toast.makeText(FoodListActivity.this, "Fuck", Toast.LENGTH_SHORT).show();
        }
        try{
            final DatabaseHall dbHall=new DatabaseHall(this);
            myHallList = dbHall.getAllHall(Movieid);
            for (HallListType cn : myHallList) {

                String c=cn.h_name;
                listItemValue[p] = c;
                HallId[p++]=cn.h_id;

                imageArry.add(cn);

            }
            adapter = new HallListAdapter(this, R.layout.basic_halllinklist, imageArry);

            final ListView HallList = (ListView) findViewById(R.id.foodlist);

            HallList.setAdapter(adapter);
            HallList.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    //Toast.makeText(MainActivity.this, listItemsValue[position], Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(HallListActivity.this, HallBookingActivity.class);
                    myIntent.putExtra("MovieName",MovieName);
                    myIntent.putExtra("MovieId",Movieid);
                    myIntent.putExtra("HallName",listItemValue[position]);
                    myIntent.putExtra("HallId",HallId[position]);

                    startActivity(myIntent);

                }
            });
            Log.d("Check:   ","Hoise");
        }
        catch(Exception e){
            Log.d("Check:   ","Problem");
        }
        /*try {
            final DatabaseCart dbcart = new DatabaseCart(this);
            final ListView dataList = (ListView) findViewById(R.id.foodlist);
            dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    //Toast.makeText(MainActivity.this, listItemsValue[position], Toast.LENGTH_SHORT).show();
                    //Intent myIntent = new Intent(FoodListActivity1.this, .class);
                    //myIntent.putExtra("RestaurantName",listItemsValue[position]);
                    dbcart.addContact(listFoodValue[position], listprice[position]);
                    Toast.makeText(FoodListActivity1.this, "Added to Your Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(FoodListActivity1.this, CartActivity.class));

                    //startActivity(myIntent);

                }
            });
        }
        catch(Exception e){
            Log.d("Check:   ","Problem");
        }
        try {
            floatbutton = (FloatingActionButton) findViewById(R.id.fab);
            floatbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    startActivity(new Intent(FoodListActivity1.this, CartActivity.class));

                    Log.d("Check", "Problem in 1ch ");
                }
            });
        }
        catch(Exception e){
            Log.d("Check:   ","Problem");
        }*/


    }




}
