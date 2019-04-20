package com.second.theatretics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOrder extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=2;
    private static final String DATABASE_NAME="DatabaseTheatreTicks";


    public DatabaseOrder(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        //Log.v("db created", "yes");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addOrder(OrderType contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        /*String query = "INSERT INTO USERLIST(NAME,PASSWORD)" +
                "VALUES('"+contact.getName()+"','"+contact.getPassword()+"')";*/

        //String qry = "INSERT INTO CONTACT(NAME,PHONENO)VALUES('XYZ','016')";
        //db.execSQL(query);

        ContentValues value=new ContentValues();
        value.put("total_seat", contact.getTotal_seat());
        value.put("total_amount", contact.getTotal_amount());
        value.put("o_confirmation", contact.getO_confirmation());
        value.put("u_id", contact.getU_id());
        value.put("m_id", contact.getM_id());



        db.insert("Orders", null,value);

        db.close();
    }
    public void addSettrack(SeattrackType contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        /*String query = "INSERT INTO USERLIST(NAME,PASSWORD)" +
                "VALUES('"+contact.getName()+"','"+contact.getPassword()+"')";*/

        //String qry = "INSERT INTO CONTACT(NAME,PHONENO)VALUES('XYZ','016')";
        //db.execSQL(query);

        ContentValues value=new ContentValues();
        value.put("seat_day", contact.getSeat_day());
        value.put("seat_id", contact.getSeat_id());
        value.put("o_id", contact.getO_id());
        value.put("confirmation", contact.getConfirmation());



        db.insert("SeatTrack", null,value);

        db.close();

    }
    public void addPayment(PaymentType contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        /*String query = "INSERT INTO USERLIST(NAME,PASSWORD)" +
                "VALUES('"+contact.getName()+"','"+contact.getPassword()+"')";*/

        //String qry = "INSERT INTO CONTACT(NAME,PHONENO)VALUES('XYZ','016')";
        //db.execSQL(query);

        ContentValues value=new ContentValues();

        value.put("p_phone", contact.getP_phone());
        value.put("p_transactionNumber", contact.getP_transactionNumber());
        value.put("p_datetime", contact.getP_datetime());
        value.put("p_type", contact.getP_type());
        value.put("p_amount", contact.getP_amount());
        value.put("o_id", contact.getO_id());



        db.insert("Payments", null,value);

        db.close();

    }

    /*public List<SeattrackType> getSeattrack(int OrderId) {
        SQLiteDatabase db = this.getReadableDatabase();
        //Toast.makeText(DatabaseRestaurantList.this, Resname.toString(), Toast.LENGTH_SHORT).show();

        String query="";
        query = "Select * from SeatTrack where o_id="+OrderId;
        Log.wtf("check123","Query= "+query);
        Cursor cursor = db.rawQuery(query, null);

        //Log.d("checkin", Resname);
        //Cursor cursor = db.query(TABLE_NAME, new String[] {KEY_ID,KEY_NAME,KEY_CONTACTNO}, "Id=?",new String[]{String.valueOf(id)} ,null, null,null, null);
        List<SeatType> mySeattrack = new ArrayList<SeatType>();
        if (cursor.moveToFirst()) {
            mySeattrack = new SeattrackType(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(5)));
            //Toast.makeText(DatabaseRestaurantList.this, cursor.getString(1), Toast.LENGTH_SHORT).show();

        }
        return mySeattrack;
    }*/
    public void ConfirmSeattrack(int OrderId ){
        SQLiteDatabase db = this.getReadableDatabase();
        //Toast.makeText(DatabaseRestaurantList.this, Resname.toString(), Toast.LENGTH_SHORT).show();
        String query="";
        query = "Update SeatTrack set confirmation=1 where o_id="+OrderId;
        db.execSQL(query);
        db.close();
    }
    public void ConfirmSeat(int Day,int SeatId ){
        SQLiteDatabase db = this.getReadableDatabase();
        //Toast.makeText(DatabaseRestaurantList.this, Resname.toString(), Toast.LENGTH_SHORT).show();
        String query="";
        if(Day==1){
            query = "Update SeatDay1 set seat1_occupied=1 where seat1_id="+SeatId;
        }
        else if(Day==2){
            query = "Update SeatDay2 set seat2_occupied=1 where seat2_id="+SeatId;
        }
        else if(Day==3){
            query = "Update SeatDay3 set seat3_occupied=1 where seat3_id="+SeatId;
        }

        db.execSQL(query);
        db.close();
    }

    public List<SeattrackType> getSeattrack(int OrderId) {
        List<SeattrackType> mySeattrackList = new ArrayList<SeattrackType>();
        Log.wtf("checkinhello", String.valueOf(OrderId));
        String query="";

        query = "Select * from SeatTrack where o_id="+OrderId;
        //String selectquery = "Select * from Theatres where h_id="+HallId;// +" ORDER BY DESC";// where phoneno LIKE '017%'";
        Log.wtf("checkoutfinal", query);
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                SeattrackType HlT = new SeattrackType(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(4)));
                mySeattrackList.add(HlT);
                //Log.wtf("checkout", String.valueOf(HlT.seat_number));

            } while (cursor.moveToNext());
        }
        Log.wtf("checkout", query);
        db.close();

        return mySeattrackList;
    }

    public OrderType getLastOrder(int UserId) {
        SQLiteDatabase db = this.getReadableDatabase();
        //Toast.makeText(DatabaseRestaurantList.this, Resname.toString(), Toast.LENGTH_SHORT).show();

        String query="";
        query = "SELECT * FROM Orders WHERE o_id= (SELECT MAX(o_id) FROM Orders WHERE u_id ="+UserId+")";
        Log.wtf("check123","Query= "+query);
        Cursor cursor = db.rawQuery(query, null);

        //Log.d("checkin", Resname);
        //Cursor cursor = db.query(TABLE_NAME, new String[] {KEY_ID,KEY_NAME,KEY_CONTACTNO}, "Id=?",new String[]{String.valueOf(id)} ,null, null,null, null);
        OrderType myOrder = null;
        if (cursor.moveToFirst()) {
            myOrder = new OrderType(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)),Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(4)),Integer.parseInt(cursor.getString(5)));
            //Toast.makeText(DatabaseRestaurantList.this, cursor.getString(1), Toast.LENGTH_SHORT).show();

        }
        return myOrder;
    }


    public TheatresType getSingleTheatre(int TheatreId) {
        SQLiteDatabase db = this.getReadableDatabase();
        //Toast.makeText(DatabaseRestaurantList.this, Resname.toString(), Toast.LENGTH_SHORT).show();

        String query="";
        query = "Select * from Theatres where t_id="+TheatreId;
        Log.wtf("check123","Query= "+query);
        Cursor cursor = db.rawQuery(query, null);

        //Log.d("checkin", Resname);
        //Cursor cursor = db.query(TABLE_NAME, new String[] {KEY_ID,KEY_NAME,KEY_CONTACTNO}, "Id=?",new String[]{String.valueOf(id)} ,null, null,null, null);
        TheatresType myTheatre = null;
        if (cursor.moveToFirst()) {
            myTheatre = new TheatresType(Integer.parseInt(cursor.getString(0)), cursor.getString(1),Integer.parseInt(cursor.getString(2)));
            //Toast.makeText(DatabaseRestaurantList.this, cursor.getString(1), Toast.LENGTH_SHORT).show();

        }
        return myTheatre;
    }
    public ShowtimeType getSingleShowtime(int Day,int ShowtimeId) {
        SQLiteDatabase db = this.getReadableDatabase();
        //Toast.makeText(DatabaseRestaurantList.this, Resname.toString(), Toast.LENGTH_SHORT).show();

        String query="";
        if(Day==1) {
            query = "Select * from ShowtimeDay1 where st1_id=" + ShowtimeId;//String query = "SELECT ID,NAME,PHONENO FROM CONTACT WHERE ID = "+id+" OR NAME ='A'";
        }
        else if(Day==2) {
            query = "Select * from ShowtimeDay2 where st2_id=" + ShowtimeId;}
        else if(Day==3) {
            query = "Select * from ShowtimeDay3 where st3_id=" + ShowtimeId;}


        Cursor cursor = db.rawQuery(query, null);

        //Log.d("checkin", Resname);
        //Cursor cursor = db.query(TABLE_NAME, new String[] {KEY_ID,KEY_NAME,KEY_CONTACTNO}, "Id=?",new String[]{String.valueOf(id)} ,null, null,null, null);
        ShowtimeType myShowtime = null;
        if (cursor.moveToFirst()) {
            myShowtime = new ShowtimeType(Integer.parseInt(cursor.getString(0)), cursor.getString(1),Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)));
            //Toast.makeText(DatabaseRestaurantList.this, cursor.getString(1), Toast.LENGTH_SHORT).show();

        }
        return myShowtime;
    }
    public SeattypeType getSingleSeattype(int Day,int SeattypeId) {
        SQLiteDatabase db = this.getReadableDatabase();
        //Toast.makeText(DatabaseRestaurantList.this, Resname.toString(), Toast.LENGTH_SHORT).show();

        String query="";
        if(Day==1) {
            query = "Select * from SeattypeDay1 where stt1_id=" + SeattypeId;//String query = "SELECT ID,NAME,PHONENO FROM CONTACT WHERE ID = "+id+" OR NAME ='A'";
        }
        else if(Day==2) {
            query = "Select * from SeattypeDay2 where stt2_id=" + SeattypeId;}
        else if(Day==3) {
            query = "Select * from SeattypeDay3 where stt3_id=" + SeattypeId;}


        Cursor cursor = db.rawQuery(query, null);

        //Log.d("checkin", Resname);
        //Cursor cursor = db.query(TABLE_NAME, new String[] {KEY_ID,KEY_NAME,KEY_CONTACTNO}, "Id=?",new String[]{String.valueOf(id)} ,null, null,null, null);
        SeattypeType mySeattype = null;
        if (cursor.moveToFirst()) {
            mySeattype = new SeattypeType(Integer.parseInt(cursor.getString(0)), cursor.getString(1),Integer.parseInt(cursor.getString(2)));
            //Toast.makeText(DatabaseRestaurantList.this, cursor.getString(1), Toast.LENGTH_SHORT).show();

        }
        return mySeattype;
    }
    public SeatType getSingleSeat(int Day,int SeatId) {
        SQLiteDatabase db = this.getReadableDatabase();
        //Toast.makeText(DatabaseRestaurantList.this, Resname.toString(), Toast.LENGTH_SHORT).show();

        String query="";
        if(Day==1) {
            query = "Select * from SeatDay1 where seat1_id=" + SeatId;//String query = "SELECT ID,NAME,PHONENO FROM CONTACT WHERE ID = "+id+" OR NAME ='A'";
        }
        else if(Day==2) {
            query = "Select * from SeatDay2 where seat2_id=" + SeatId;}
        else if(Day==3) {
            query = "Select * from SeatDay3 where seat3_id=" + SeatId;}


        Cursor cursor = db.rawQuery(query, null);

        //Log.d("checkin", Resname);
        //Cursor cursor = db.query(TABLE_NAME, new String[] {KEY_ID,KEY_NAME,KEY_CONTACTNO}, "Id=?",new String[]{String.valueOf(id)} ,null, null,null, null);
        SeatType mySeat = null;
        if (cursor.moveToFirst()) {
            mySeat = new SeatType(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(4)));
            //Toast.makeText(DatabaseRestaurantList.this, cursor.getString(1), Toast.LENGTH_SHORT).show();

        }
        return mySeat;
    }

    /*public void updateContact(Contact contact)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        //String query = "UPDATE USERLIST SET password='"+contact.getPassword() +"' WHERE name = "+contact.getName();
        //db.execSQL(query);

		ContentValues value=new ContentValues();
		value.put(KEY_NAME, contact.getName());
		value.put(KEY_PASSWORD,contact.getPassword());

		db.update(TABLE_NAME, value, KEY_NAME+"=?",new String[] {String.valueOf(contact.getName())});


        db.close();
    }*/


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
    public void updateContact(String email,String Password)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        String query = "Update Users SET u_password='"+Password+"' where u_email = '"+email+"'";
        db.execSQL(query);


        db.close();
    }

    public UsersType PreviousPasswordSearch(String email){

        String selectquery="SELECT * FROM Users where u_email='"+email+"'";// where phoneno LIKE '017%'";

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(selectquery, null);
        UsersType contact1 = null;
        if(cursor.moveToFirst())
        {
                contact1 = new UsersType(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),Integer.parseInt(cursor.getString(6)));
                Log.wtf("email","email= "+contact1.getU_email()+" password= "+contact1.getU_password());
                Log.wtf("verb","");
                // Contact contact= new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
         }
        return contact1;
    }

    public UsersType SingleSearch(String email){

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
                if(contact1.getU_email().equals(email)){
                    db.close();
                    return contact1;
                }
                // Contact contact= new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));

            }while(cursor.moveToNext());
        }
        return contact1;
    }



    /*public List<Contact> getAllContact()
    {
        List<Contact> mycontactList=new ArrayList<Contact>();

        String selectquery="SELECT * FROM "+ TABLE_NAME;// where phoneno LIKE '017%'";

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(selectquery, null);

        if(cursor.moveToFirst())
        {
            do
            {
                Contact contact= new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
                mycontactList.add(contact);
            }while(cursor.moveToNext());
        }

        return mycontactList;
    }*/





}
