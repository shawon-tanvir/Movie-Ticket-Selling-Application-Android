package com.second.theatretics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {
    Button AddMovie ;
    Button AddHall;
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    String value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        AddMovie=(Button) findViewById(R.id.AdminAddMovie);
        AddHall=(Button) findViewById(R.id.AdminAddHall);

        sp=getSharedPreferences("key", Context.MODE_PRIVATE);
        value = sp.getString("email", "check");

        AddMovie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Log.wtf("Check",TotalSeat.getText().toString());

                startActivity(new Intent(AdminActivity.this,AddMovieList.class));

            }
        });
        AddHall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Log.wtf("Check",TotalSeat.getText().toString());

                startActivity(new Intent(AdminActivity.this,AddHallList.class));

            }
        });

    }
    public void Logoutfrom(View view){
        ed=sp.edit();
        ed.putString("email","");
        ed.commit();
        startActivity(new Intent(AdminActivity.this,MainActivity.class));
    }

}

