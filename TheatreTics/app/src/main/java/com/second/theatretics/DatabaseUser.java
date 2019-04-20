package com.second.theatretics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUser extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=2;
    private static final String DATABASE_NAME="DatabaseTheatreTicks";


    public DatabaseUser(Context context) {
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

    public void addContact(UsersType contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        /*String query = "INSERT INTO USERLIST(NAME,PASSWORD)" +
                "VALUES('"+contact.getName()+"','"+contact.getPassword()+"')";*/

        //String qry = "INSERT INTO CONTACT(NAME,PHONENO)VALUES('XYZ','016')";
        //db.execSQL(query);

        ContentValues value=new ContentValues();
        value.put("u_name", contact.getU_name());
        value.put("u_address", contact.getU_address());
        value.put("u_phone", contact.getU_phone());
        value.put("u_email", contact.getU_email());
        value.put("u_password", contact.getU_password());
        value.put("u_catagory", contact.getU_catagory());


        db.insert("Users", null,value);

        db.close();

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


    public UsersType UserSearch(String email,String password){

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
                    return contact1;
                }
                // Contact contact= new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));

            }while(cursor.moveToNext());
        }
        db.close();
        return contact1;
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
