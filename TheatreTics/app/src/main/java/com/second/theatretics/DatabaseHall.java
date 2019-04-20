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




public class DatabaseHall extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "DatabaseTheatreTicks";
    private static final String TABLE_NAME = "HallList";
    private static final String KEY_ID = "h_id";
    private static final String KEY_NAME = "h_name";
    private static final String KEY_ADDRESS = "h_address";
    private static final String KEY_PHONE = "h_phone";
    private static final String KEY_U_ID = "u_id";

    public DatabaseHall(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
        Log.wtf("db created", "yes");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        //Log.v("db created", "yes");

        String CREATE_CONTACT_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_ADDRESS + " TEXT,"
                + KEY_PHONE + " TEXT,"
                + KEY_U_ID + " INTEGER" + ")";

        /*String sql= "CREATE TABLE CONTACT (ID INTEGER PRIMARY KEY" +
                ", NAME TEXT" +
                ", PASSWORD TEXT)";*/
        db.execSQL(CREATE_CONTACT_TABLE);
        Log.wtf("db created", "yes");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addHall(HallListType hlt) {
        SQLiteDatabase db = this.getWritableDatabase();

        /*String query = "INSERT INTO USERLIST(NAME,PASSWORD)" +
                "VALUES('"+contact.getName()+"','"+contact.getPassword()+"')";
*/
        //String qry = "INSERT INTO CONTACT(NAME,PHONENO)VALUES('XYZ','016')";
        //db.execSQL(query);
        //RestaurantListType RLT;
        ContentValues value = new ContentValues();
        value.put(KEY_NAME, hlt.getH_name());
        value.put(KEY_ADDRESS, hlt.getH_address());
        value.put(KEY_PHONE, hlt.getH_phone());
        value.put(KEY_U_ID, hlt.getU_id());
        db.insert(TABLE_NAME, null, value);
        Log.wtf("Check", "Done");

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

    public HallListType getSingleHall(int Day,int HId,int MId) {
        SQLiteDatabase db = this.getReadableDatabase();
        //Toast.makeText(DatabaseRestaurantList.this, Resname.toString(), Toast.LENGTH_SHORT).show();

        String query="";
        if(Day==1) {
            query = "Select * from HallList where h_id in(SELECT distinct HallList.h_id  FROM HallList,Theatres,ShowtimeDay1 where HallList.h_id=Theatres.h_id and Theatres.t_id=ShowtimeDay1.t_id and ShowtimeDay1.m_id=" + MId +" and HallList.h_id="+HId +")";
        }//String query = "SELECT ID,NAME,PHONENO FROM CONTACT WHERE ID = "+id+" OR NAME ='A'";

        else if(Day==2) {
            query = "Select * from HallList where h_id in(SELECT distinct HallList.h_id  FROM HallList,Theatres,ShowtimeDay2 where HallList.h_id=Theatres.h_id and Theatres.t_id=ShowtimeDay2.t_id and ShowtimeDay2.m_id=" + MId +" and HallList.h_id="+HId +")";
        }
        else if(Day==3) {
            query = "Select * from HallList where h_id in(SELECT distinct HallList.h_id  FROM HallList,Theatres,ShowtimeDay3 where HallList.h_id=Theatres.h_id and Theatres.t_id=ShowtimeDay3.t_id and ShowtimeDay3.m_id=" + MId +" and HallList.h_id="+HId +")";
        }
        Log.wtf("check123","Query= "+query);
        Cursor cursor = db.rawQuery(query, null);

        //Log.d("checkin", Resname);
        //Cursor cursor = db.query(TABLE_NAME, new String[] {KEY_ID,KEY_NAME,KEY_CONTACTNO}, "Id=?",new String[]{String.valueOf(id)} ,null, null,null, null);
        HallListType myHall = null;

        if (cursor.moveToFirst()) {
            myHall = new HallListType(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),cursor.getString(3),Integer.parseInt(cursor.getString(4)));
            //Toast.makeText(DatabaseRestaurantList.this, cursor.getString(1), Toast.LENGTH_SHORT).show();

        }

        return myHall;
    }



    public List<HallListType> getAllHall(int Movieid) {
        List<HallListType> myHallList = new ArrayList<HallListType>();
        Log.wtf("checkin", String.valueOf(Movieid));

        String selectquery = "Select * from HallList where h_id in(SELECT distinct HallList.h_id  FROM HallList,Theatres,ShowtimeDay1,ShowtimeDay2,ShowtimeDay3 where HallList.h_id=Theatres.h_id and Theatres.t_id=ShowtimeDay1.t_id and Theatres.t_id=ShowtimeDay2.t_id and Theatres.t_id=ShowtimeDay3.t_id and (ShowtimeDay1.m_id=" + Movieid+" or ShowtimeDay2.m_id="+ Movieid+ " or ShowtimeDay3.m_id="+Movieid+" ))";// +" ORDER BY DESC";// where phoneno LIKE '017%'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectquery, null);

        if (cursor.moveToFirst()) {
            do {
                HallListType HlT = new HallListType(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),cursor.getString(3),Integer.parseInt(cursor.getString(4)));
                myHallList.add(HlT);

            } while (cursor.moveToNext());
        }
        db.close();
        return myHallList;
    }

    public List<TheatresType> getAllTheatres(int Day,int HallId,int MovieId) {
        List<TheatresType> myTheatresList = new ArrayList<TheatresType>();
        Log.wtf("checkin", String.valueOf(HallId));
        String query="";
        if(Day==1) {
            query = "Select * from Theatres where t_id in(SELECT distinct Theatres.t_id  FROM HallList,Theatres,ShowtimeDay1 where HallList.h_id=Theatres.h_id and Theatres.t_id=ShowtimeDay1.t_id and ShowtimeDay1.m_id=" + MovieId +" and HallList.h_id="+HallId +")";
        }//String query = "SELECT ID,NAME,PHONENO FROM CONTACT WHERE ID = "+id+" OR NAME ='A'";

        else if(Day==2) {
            query = "Select * from Theatres where t_id in(SELECT distinct Theatres.t_id  FROM HallList,Theatres,ShowtimeDay2 where HallList.h_id=Theatres.h_id and Theatres.t_id=ShowtimeDay2.t_id and ShowtimeDay2.m_id=" + MovieId +" and HallList.h_id="+HallId +")";
        }
        else if(Day==3) {
            query = "Select * from Theatres where t_id in(SELECT distinct Theatres.t_id  FROM HallList,Theatres,ShowtimeDay3 where HallList.h_id=Theatres.h_id and Theatres.t_id=ShowtimeDay3.t_id and ShowtimeDay3.m_id=" + MovieId +" and HallList.h_id="+HallId +")";
        }
        //String selectquery = "Select * from Theatres where h_id="+HallId;// +" ORDER BY DESC";// where phoneno LIKE '017%'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                TheatresType HlT = new TheatresType(Integer.parseInt(cursor.getString(0)), cursor.getString(1),Integer.parseInt(cursor.getString(2)));
                myTheatresList.add(HlT);

            } while (cursor.moveToNext());
        }
        db.close();
        return myTheatresList;
    }
    public List<ShowtimeType> getAllShowtime(int Day,int TheatreId,int MovieId) {
        List<ShowtimeType> myShowtimeList = new ArrayList<ShowtimeType>();
        Log.wtf("checkin", String.valueOf(TheatreId));
        String query="";
        if(Day==1) {
            query = "Select * from ShowtimeDay1 where st1_id in(SELECT distinct ShowtimeDay1.st1_id  FROM Theatres,ShowtimeDay1 where Theatres.t_id=ShowtimeDay1.t_id and ShowtimeDay1.m_id=" + MovieId +" and Theatres.t_id="+TheatreId +")";
        }//String query = "SELECT ID,NAME,PHONENO FROM CONTACT WHERE ID = "+id+" OR NAME ='A'";

        else if(Day==2) {
            query = "Select * from ShowtimeDay2 where st2_id in(SELECT distinct ShowtimeDay2.st2_id  FROM Theatres,ShowtimeDay2 where Theatres.t_id=ShowtimeDay2.t_id and ShowtimeDay2.m_id=" + MovieId +" and Theatres.t_id="+TheatreId +")";
        }
        else if(Day==3) {
            query = "Select * from ShowtimeDay3 where st3_id in(SELECT distinct ShowtimeDay3.st3_id  FROM Theatres,ShowtimeDay3 where Theatres.t_id=ShowtimeDay3.t_id and ShowtimeDay3.m_id=" + MovieId +" and Theatres.t_id="+TheatreId +")";
        }
        //String selectquery = "Select * from Theatres where h_id="+HallId;// +" ORDER BY DESC";// where phoneno LIKE '017%'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ShowtimeType HlT = new ShowtimeType(Integer.parseInt(cursor.getString(0)), cursor.getString(1),Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)));
                myShowtimeList.add(HlT);

            } while (cursor.moveToNext());
        }
        db.close();
        Log.wtf("checkout", String.valueOf(TheatreId));
        return myShowtimeList;
    }
    public List<SeattypeType> getAllSeattype(int Day,int ShowtimeId) {
        List<SeattypeType> mySeattypeList = new ArrayList<SeattypeType>();
        Log.wtf("checkinhello", String.valueOf(ShowtimeId));
        String query="";
        if(Day==1) {
            query = "Select * from SeattypeDay1 where stt1_id in(SELECT distinct SeattypeDay1.stt1_id  FROM SeattypeDay1,ShowtimeDay1 where SeattypeDay1.st1_id=ShowtimeDay1.st1_id and ShowtimeDay1.st1_id="+ShowtimeId+")";
        }//String query = "SELECT ID,NAME,PHONENO FROM CONTACT WHERE ID = "+id+" OR NAME ='A'";

        else if(Day==2) {
            query = "Select * from SeattypeDay2 where stt2_id in(SELECT distinct SeattypeDay2.stt2_id  FROM SeattypeDay2,ShowtimeDay2 where SeattypeDay2.st2_id=ShowtimeDay2.st2_id and ShowtimeDay2.st2_id="+ShowtimeId+")";
        }
        else if(Day==3) {
            query = "Select * from SeattypeDay3 where stt3_id in(SELECT distinct SeattypeDay3.stt3_id  FROM SeattypeDay3,ShowtimeDay3 where SeattypeDay3.st3_id=ShowtimeDay3.st3_id and ShowtimeDay3.st3_id="+ShowtimeId+")";
        }
        //String selectquery = "Select * from Theatres where h_id="+HallId;// +" ORDER BY DESC";// where phoneno LIKE '017%'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                SeattypeType HlT = new SeattypeType(Integer.parseInt(cursor.getString(0)), cursor.getString(1),Integer.parseInt(cursor.getString(2)));
                mySeattypeList.add(HlT);

            } while (cursor.moveToNext());
        }
        Log.wtf("checkout", query);
        db.close();
        Log.wtf("checkout", String.valueOf(ShowtimeId));
        return mySeattypeList;
    }

    public List<SeatType> getAllSeat(int Day,int SeattypeId) {
        List<SeatType> mySeatList = new ArrayList<SeatType>();
        Log.wtf("checkinhello", String.valueOf(SeattypeId));
        String query="";
        if(Day==1) {
            query = "Select * from SeatDay1 where seat1_id in(SELECT distinct SeatDay1.seat1_id  FROM SeattypeDay1,SeatDay1 where SeattypeDay1.stt1_id=SeatDay1.stt1_id and SeatDay1.stt1_id="+SeattypeId+")";
        }//String query = "SELECT ID,NAME,PHONENO FROM CONTACT WHERE ID = "+id+" OR NAME ='A'";

        else if(Day==2) {
            query = "Select * from SeatDay2 where seat2_id in(SELECT distinct SeatDay2.seat2_id  FROM SeattypeDay2,SeatDay2 where SeattypeDay2.stt2_id=SeatDay2.stt2_id and SeatDay2.stt2_id="+SeattypeId+")";
        }
        else if(Day==3) {
            query = "Select * from SeatDay3 where seat3_id in(SELECT distinct SeatDay3.seat3_id  FROM SeattypeDay3,SeatDay3 where SeattypeDay3.stt3_id=SeatDay3.stt3_id and SeatDay3.stt3_id="+SeattypeId+")";
        }
        //String selectquery = "Select * from Theatres where h_id="+HallId;// +" ORDER BY DESC";// where phoneno LIKE '017%'";
        Log.wtf("checkoutfinal", query);
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                SeatType HlT = new SeatType(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(4)));
                mySeatList.add(HlT);
                Log.wtf("checkout", String.valueOf(HlT.seat_number));

            } while (cursor.moveToNext());
        }
        Log.wtf("checkout", query);
        db.close();

        return mySeatList;
    }



}



