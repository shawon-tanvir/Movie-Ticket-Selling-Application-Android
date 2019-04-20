package com.second.theatretics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

import java.util.ArrayList;
import java.util.List;




public class DatabaseMovie extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "DatabaseTheatreTicks";
    private static final String TABLE_NAME = "MovieList";
    private static final String KEY_ID = "m_d";
    private static final String KEY_NAME = "m_name";
    private static final String KEY_POSTER = "m_poster";
    private static final String KEY_RUNNING = "m_running";

    public DatabaseMovie(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        //Log.v("db created", "yes");
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_POSTER + " BLOB,"
                + KEY_RUNNING + " INTEGER" + ")";

        /*String sql= "CREATE TABLE CONTACT (ID INTEGER PRIMARY KEY" +
                ", NAME TEXT" +
                ", PASSWORD TEXT)";*/
        db.execSQL(CREATE_CONTACT_TABLE);
        //Log.v("db created", "yes");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addMovie(MovieListType mlt) {
        SQLiteDatabase db = this.getWritableDatabase();

        /*String query = "INSERT INTO USERLIST(NAME,PASSWORD)" +
                "VALUES('"+contact.getName()+"','"+contact.getPassword()+"')";
*/
        //String qry = "INSERT INTO CONTACT(NAME,PHONENO)VALUES('XYZ','016')";
        //db.execSQL(query);
        //RestaurantListType RLT;
        ContentValues value = new ContentValues();
        value.put(KEY_NAME, mlt.getM_name());
        value.put(KEY_POSTER, mlt.getM_poster());
        value.put(KEY_RUNNING, mlt.getM_running());

        db.insert(TABLE_NAME, null, value);
        Log.d("Check", "Done");

        db.close();

    }

   /* public boolean search(RestaurantListType rlt){

        String selectquery="SELECT * FROM "+ TABLE_NAME;// where phoneno LIKE '017%'";

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(selectquery, null);
        Contact contact1 = null;
        if(cursor.moveToFirst())
        {

            do
            {

                contact1 = new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2));

                if(contact1.getName().equals(rlt.getName()) && contact1.getPassword().equals(rlt.getPassword())){
                    db.close();
                    return true;
                }
                // Contact contact= new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));

            }while(cursor.moveToNext());
        }
        db.close();
        return false;
    }*/

    public List<MovieListType> getSingleMovies(String Resname) {
        SQLiteDatabase db = this.getReadableDatabase();
        //Toast.makeText(DatabaseRestaurantList.this, Resname.toString(), Toast.LENGTH_SHORT).show();
        String query = "SELECT * FROM MovieList WHERE m_running=1 and m_name ='" + Resname + "'";
        //String query = "SELECT ID,NAME,PHONENO FROM CONTACT WHERE ID = "+id+" OR NAME ='A'";

        List<MovieListType> myMovieList = new ArrayList<MovieListType>();


        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                MovieListType MlT = new MovieListType(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getBlob(2),Integer.parseInt(cursor.getString(3)));
                myMovieList.add(MlT);

            } while (cursor.moveToNext());
        }
        db.close();
        return myMovieList;
    }

    public MovieListType getSingleMovie(String Resname) {
        SQLiteDatabase db = this.getReadableDatabase();
        //Toast.makeText(DatabaseRestaurantList.this, Resname.toString(), Toast.LENGTH_SHORT).show();
        String query = "SELECT m_d,m_name,m_poster,m_running FROM MovieList WHERE m_name ='" + Resname + "'";
        //String query = "SELECT ID,NAME,PHONENO FROM CONTACT WHERE ID = "+id+" OR NAME ='A'";

        Cursor cursor = db.rawQuery(query, null);
        Log.wtf("checkin", Resname);
        //Cursor cursor = db.query(TABLE_NAME, new String[] {KEY_ID,KEY_NAME,KEY_CONTACTNO}, "Id=?",new String[]{String.valueOf(id)} ,null, null,null, null);
        MovieListType myMovie = null;

        if (cursor.moveToFirst()) {
            myMovie = new MovieListType(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getBlob(2),Integer.parseInt(cursor.getString(3)));
            //Toast.makeText(DatabaseRestaurantList.this, cursor.getString(1), Toast.LENGTH_SHORT).show();

        }

        return myMovie;
    }

    public List<MovieListType> getAllMovie() {
        List<MovieListType> myMovieList = new ArrayList<MovieListType>();

        String selectquery = "SELECT * FROM " + TABLE_NAME +" where m_running=1 ";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectquery, null);

        if (cursor.moveToFirst()) {
            do {
                MovieListType MlT = new MovieListType(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getBlob(2),Integer.parseInt(cursor.getString(3)));
                myMovieList.add(MlT);

            } while (cursor.moveToNext());
        }
        db.close();
        return myMovieList;
    }
    public boolean UserSearch(String email,String password){

        String selectquery="SELECT * FROM Users";// where phoneno LIKE '017%'";

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(selectquery, null);
        UsersType contact1 = null;
        if(cursor.moveToFirst())
        {

            do
            {

                contact1 = new UsersType(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),Integer.parseInt(cursor.getString(6)));
                Log.wtf("email","email= "+contact1.getU_email()+" password= "+contact1.getU_password());
                Log.wtf("verb","");
                if(contact1.getU_email().equals(email) && contact1.getU_password().equals(password)){
                    db.close();
                    return true;
                }
                // Contact contact= new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));

            }while(cursor.moveToNext());
        }
        db.close();
        return false;
    }


}



