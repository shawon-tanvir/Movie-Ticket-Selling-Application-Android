package com.second.theatretics;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AddHallList extends AppCompatActivity {

    ImageView Imv;
    Button addhall;
    String MovieName;
    EditText HallName;
    EditText HallAddress;
    EditText HallPhone;
    EditText HallOwner;

    final DatabaseHall db=new DatabaseHall(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hall_list);

        addhall=(Button) findViewById(R.id.AddHall);

        HallName=(EditText) findViewById(R.id.AddHallName);
        HallAddress=(EditText) findViewById(R.id.AddHallAddress);
        HallPhone=(EditText) findViewById(R.id.AddHallPhone);
        HallOwner=(EditText) findViewById(R.id.AddHallOwner);

       /* Bitmap bmp = null;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();*/



        addhall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Hello ","Fuck");
                    db.addHall(new HallListType(HallName.getText().toString(),HallAddress.getText().toString(),HallPhone.getText().toString(),Integer.parseInt(HallOwner.getText().toString())));
                    Toast.makeText (getApplicationContext(),"New Hall Is Added.", Toast.LENGTH_SHORT).show();
                Log.wtf("Hello ","Fuck");

                startActivity(new Intent(AddHallList.this,AdminActivity.class));
            }
        });


    }


}
