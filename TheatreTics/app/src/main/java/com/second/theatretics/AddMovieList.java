package com.second.theatretics;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
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

public class AddMovieList extends AppCompatActivity {

    ImageView Imv;
    Button addimage;
    String MovieName;
    EditText NameInput;
    byte[] image;
    byte[] img;
    final DatabaseMovie db=new DatabaseMovie(this);
    public static final int RESULT_LOAD_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie_list);

        addimage=(Button) findViewById(R.id.AddMovie);
        Imv=(ImageView) findViewById(R.id.MoviePoster);
        NameInput=(EditText) findViewById(R.id.MovieName);


        Button buttonLoadImage = (Button) findViewById(R.id.AddFromGallery);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });


       /* Bitmap bmp = null;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();*/


        //image = getBitmapAsByteArray(Imv.getDrawingCache());


        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Bitmap bitmap= BitmapFactory.decodeResource(getResources(),Imv.getDrawable());
                Bitmap bitmap=((BitmapDrawable) Imv.getDrawable()).getBitmap();
                ByteArrayOutputStream bos=new ByteArrayOutputStream();

                Check check = new Check();
                check.doInBackground(bitmap,bos);

                img=bos.toByteArray();
                Log.d("Hello ","Fuck");
                    db.addMovie(new MovieListType(NameInput.getText().toString(),img,1));
                    Toast.makeText (getApplicationContext(),"New Movie Is Added.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddMovieList.this,AdminActivity.class));

            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.MoviePoster);
            //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            imageView.setImageURI(selectedImage);

        }


    }
    /*public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("outputX", 256);
        intent.putExtra("outputY", 256);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 1) {
            final Bundle extras = data.getExtras();
            if (extras != null) {
                //Get image
                Bitmap newProfilePic = extras.getParcelable("data");
            }
        }
    }*/
    public class Check  {

        protected void doInBackground(Bitmap bitmap,ByteArrayOutputStream bos) {

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        }


    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}
