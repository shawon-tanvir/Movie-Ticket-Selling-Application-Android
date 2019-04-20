package com.second.theatretics;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HallBookingActivity extends AppCompatActivity {


    final DatabaseHall dbHall=new DatabaseHall(this);
    HallListAdapter adapter;
    List<TheatresType> myTheatreList;
    List<ShowtimeType> myShowtimeList;
    List<SeattypeType> mySeattypeList;
    List<SeatType> mySeatList;
    String[] listFoodValue = new String[8] ;
    int[] listprice=new int[8];
    int p=0;


    int Theatre1id;
    int Theatre2id;
    int Theatre3id;
    int Showtime1id;
    int Showtime2id;
    int Showtime3id;
    int Seattype1id;
    int Seattype2id;

    int Seat1id;
    int Seat2id;
    int Seat3id;
    int Seat4id;
    int Seat5id;

    int DaySelected;
    int TheatreSelected;
    int ShowtimeSelected;
    int SeattypeSelected;
    String MovieName;
    String HallName;
    int HallId;
    int Movieid;
    int[] SeatArray;
    int count;
    //FloatingActionButton floatbutton;

    Button ButtonTheatre1;
    Button ButtonTheatre2;
    Button ButtonTheatre3;
    TextView tx;

    Button ButtonShowtime1;
    Button ButtonShowtime2;
    Button ButtonShowtime3;
    TextView txShowTime;

    Button ButtonSeattype1;
    Button ButtonSeattype2;
    TextView txSeattype;

    CheckBox CheckBox1;
    CheckBox CheckBox2;
    CheckBox CheckBox3;
    CheckBox CheckBox4;
    CheckBox CheckBox5;

    TextView CheckBoxText1;
    TextView CheckBoxText2;
    TextView CheckBoxText3;
    TextView CheckBoxText4;
    TextView CheckBoxText5;
    TextView txSeat;

    Button OrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_booking);

        Initialize();


        try {
            TextView MovieNameHallBooking = (TextView) findViewById(R.id.MovienameHallbooking);
            TextView HallNameHallBooking= (TextView) findViewById(R.id.HallnameHallbooking);
            Button ButtonToday=(Button) findViewById(R.id.Buttontoday);
            Button ButtonTomorrow=(Button) findViewById(R.id.Buttontommorrow);
            Button ButtonDayAfterTomorrow=(Button) findViewById(R.id.Buttondayaftertommorow);



            final DatabaseHall db = new DatabaseHall(this);

            Intent myIntent = getIntent();
            Movieid=myIntent.getIntExtra("MovieId",0);

            MovieName = myIntent.getStringExtra("MovieName");
            HallName = myIntent.getStringExtra("HallName");
            Log.wtf("check123","Done");
            HallId=myIntent.getIntExtra("HallId",0);



            Log.wtf("checkfor",MovieName);
            //Toast.makeText(FoodListActivity1.this, RestaurantName, Toast.LENGTH_SHORT).show();


            MovieNameHallBooking.setText("Movie Name: "+MovieName);
            HallNameHallBooking.setText("Hall Name: "+HallName);

            HallListType Day1 = db.getSingleHall(1,HallId,Movieid);
            Log.wtf("check123","Hall= "+HallId+" Movie= "+Movieid);
            HallListType Day2 = db.getSingleHall(2,HallId,Movieid);
            HallListType Day3 = db.getSingleHall(3,HallId,Movieid);
            Log.wtf("check123",Day1.h_address);

            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss");
            String Date1 = df.format(c.getTime());
            c.add(Calendar.DATE, 1);  // number of days to add
            String Date2 = df.format(c.getTime());
            c.add(Calendar.DATE, 1);  // number of days to add
            String Date3 = df.format(c.getTime());



            if(Day1.h_address!=""){
                ButtonToday.setVisibility(View.VISIBLE);

                ButtonToday.setText(Date1);
            }
            if(Day2.h_address!=""){

                ButtonTomorrow.setVisibility(View.VISIBLE);
                ButtonTomorrow.setText(Date2);
            }
            if(Day3.h_address!=""){

                ButtonDayAfterTomorrow.setVisibility(View.VISIBLE);
                ButtonDayAfterTomorrow.setText(Date3);
            }


        }
        catch(Exception e){
            //Toast.makeText(FoodListActivity.this, "Fuck", Toast.LENGTH_SHORT).show();
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
    public void btn1click(View view){
        DaySelected=1;


        myTheatreList = dbHall.getAllTheatres(1,HallId,Movieid);
        int t=0;
        Seatclear();
        Showtimeclear();
        Seattypeclear();
        OrderButton.setVisibility(View.INVISIBLE);
        ButtonTheatre1.setVisibility(View.INVISIBLE);
        ButtonTheatre2.setVisibility(View.INVISIBLE);
        ButtonTheatre3.setVisibility(View.INVISIBLE);
        tx.setVisibility(View.VISIBLE);
        for (TheatresType cn : myTheatreList) {

            if(HallId==2 || HallId==3 ){
                ButtonTheatre2.setText(cn.t_name);
                Theatre2id=cn.t_id;
                ButtonTheatre2.setVisibility(View.VISIBLE);
            }
            else {
                if (t == 0) {
                    ButtonTheatre1.setText(cn.t_name);
                    t++;
                    Theatre1id=cn.t_id;
                    ButtonTheatre1.setVisibility(View.VISIBLE);
                } else if (t == 1) {
                    ButtonTheatre2.setText(cn.t_name);
                    t++;
                    Theatre2id=cn.t_id;
                    ButtonTheatre2.setVisibility(View.VISIBLE);
                } else if (t == 2) {
                    ButtonTheatre3.setText(cn.t_name);
                    Theatre3id=cn.t_id;
                    ButtonTheatre3.setVisibility(View.VISIBLE);
                }
            }

        }
    }
    public void btn2click(View view){
        DaySelected=2;


        myTheatreList = dbHall.getAllTheatres(2,HallId,Movieid);
        int t=0;
        Seatclear();
        Showtimeclear();
        Seattypeclear();
        OrderButton.setVisibility(View.INVISIBLE);
        ButtonTheatre1.setVisibility(View.INVISIBLE);
        ButtonTheatre2.setVisibility(View.INVISIBLE);
        ButtonTheatre3.setVisibility(View.INVISIBLE);
        tx.setVisibility(View.VISIBLE);

        for (TheatresType cn : myTheatreList) {

            if(HallId==2 || HallId==3 ){
                ButtonTheatre2.setText(cn.t_name);
                ButtonTheatre2.setVisibility(View.VISIBLE);
                Theatre2id=cn.t_id;
            }
            else {
                if (t == 0) {
                    ButtonTheatre1.setText(cn.t_name);
                    t++;
                    Theatre1id=cn.t_id;
                    ButtonTheatre1.setVisibility(View.VISIBLE);
                } else if (t == 1) {
                    ButtonTheatre2.setText(cn.t_name);
                    t++;
                    Theatre2id=cn.t_id;
                    ButtonTheatre2.setVisibility(View.VISIBLE);
                } else if (t == 2) {
                    ButtonTheatre3.setText(cn.t_name);
                    Theatre3id=cn.t_id;
                    ButtonTheatre3.setVisibility(View.VISIBLE);
                }
            }

        }
    }
    public void btn3click(View view){
        DaySelected=3;

        myTheatreList = dbHall.getAllTheatres(3,HallId,Movieid);
        int t=0;
        Seatclear();
        Showtimeclear();
        Seattypeclear();
        OrderButton.setVisibility(View.INVISIBLE);
        ButtonTheatre1.setVisibility(View.INVISIBLE);
        ButtonTheatre2.setVisibility(View.INVISIBLE);
        ButtonTheatre3.setVisibility(View.INVISIBLE);
        tx.setVisibility(View.VISIBLE);
        for (TheatresType cn : myTheatreList) {

            if(HallId==2 || HallId==3 ){
                ButtonTheatre2.setText(cn.t_name);
                Theatre2id=cn.t_id;
                ButtonTheatre2.setVisibility(View.VISIBLE);
            }
            else {
                if (t == 0) {
                    ButtonTheatre1.setText(cn.t_name);
                    t++;
                    Theatre1id=cn.t_id;
                    ButtonTheatre1.setVisibility(View.VISIBLE);
                } else if (t == 1) {
                    ButtonTheatre2.setText(cn.t_name);
                    t++;
                    Theatre2id=cn.t_id;
                    ButtonTheatre2.setVisibility(View.VISIBLE);
                } else if (t == 2) {
                    ButtonTheatre3.setText(cn.t_name);
                    Theatre3id=cn.t_id;
                    ButtonTheatre3.setVisibility(View.VISIBLE);
                }
            }

        }
    }
    public void Theatre1btn1click(View view){

        TheatreSelected=Theatre1id;
        myShowtimeList = dbHall.getAllShowtime(DaySelected,Theatre1id,Movieid);
        int t=0;
        Seatclear();
        Seattypeclear();
        OrderButton.setVisibility(View.INVISIBLE);
        ButtonShowtime1.setVisibility(View.INVISIBLE);
        ButtonShowtime2.setVisibility(View.INVISIBLE);
        ButtonShowtime3.setVisibility(View.INVISIBLE);
        txShowTime.setVisibility(View.VISIBLE);


        for (ShowtimeType cn : myShowtimeList) {

                if (t == 0) {
                    ButtonShowtime1.setText(cn.st_timestart);
                    t++;
                    Showtime1id=cn.st_id;
                    ButtonShowtime1.setVisibility(View.VISIBLE);
                } else if (t == 1) {
                    ButtonShowtime2.setText(cn.st_timestart);
                    t++;
                    Showtime2id=cn.st_id;
                    ButtonShowtime2.setVisibility(View.VISIBLE);
                } else if (t == 2) {
                    ButtonShowtime3.setText(cn.st_timestart);
                    Showtime3id=cn.st_id;
                    ButtonShowtime3.setVisibility(View.VISIBLE);
                }


        }
    }
    public void Theatre2btn1click(View view){
        TheatreSelected=Theatre2id;
        myShowtimeList = dbHall.getAllShowtime(DaySelected,Theatre2id,Movieid);
        int t=0;
        Seatclear();
        Seattypeclear();
        OrderButton.setVisibility(View.INVISIBLE);
        ButtonShowtime1.setVisibility(View.INVISIBLE);
        ButtonShowtime2.setVisibility(View.INVISIBLE);
        ButtonShowtime3.setVisibility(View.INVISIBLE);
        txShowTime.setVisibility(View.VISIBLE);

        for (ShowtimeType cn : myShowtimeList) {

            if (t == 0) {
                ButtonShowtime1.setText(cn.st_timestart);
                t++;
                Showtime1id=cn.st_id;
                ButtonShowtime1.setVisibility(View.VISIBLE);
            } else if (t == 1) {
                ButtonShowtime2.setText(cn.st_timestart);
                t++;
                Showtime2id=cn.st_id;
                ButtonShowtime2.setVisibility(View.VISIBLE);
            } else if (t == 2) {
                ButtonShowtime3.setText(cn.st_timestart);
                Showtime3id=cn.st_id;
                ButtonShowtime3.setVisibility(View.VISIBLE);
            }


        }
    }
    public void Theatre3btn1click(View view){
        TheatreSelected=Theatre3id;
        myShowtimeList = dbHall.getAllShowtime(DaySelected,Theatre3id,Movieid);
        int t=0;
        Seatclear();
        Seattypeclear();
        OrderButton.setVisibility(View.INVISIBLE);
        ButtonSeattype1.setVisibility(View.INVISIBLE);
        ButtonSeattype2.setVisibility(View.INVISIBLE);
        txSeattype.setVisibility(View.VISIBLE);

        for (ShowtimeType cn : myShowtimeList) {

            if (t == 0) {
                ButtonShowtime1.setText(cn.st_timestart);
                t++;
                Showtime1id=cn.st_id;
                ButtonShowtime1.setVisibility(View.VISIBLE);
            } else if (t == 1) {
                ButtonShowtime2.setText(cn.st_timestart);
                t++;
                Showtime2id=cn.st_id;
                ButtonShowtime2.setVisibility(View.VISIBLE);
            } else if (t == 2) {
                ButtonShowtime3.setText(cn.st_timestart);
                Showtime3id=cn.st_id;
                ButtonShowtime3.setVisibility(View.VISIBLE);
            }


        }
    }

    public void Showtime1btn1click(View view){
        ShowtimeSelected=Showtime1id;
        Log.wtf("Showtimeselected",String.valueOf(ShowtimeSelected));

        mySeattypeList = dbHall.getAllSeattype(DaySelected,ShowtimeSelected);
        int t=0;
        Seatclear();
        OrderButton.setVisibility(View.INVISIBLE);
        ButtonSeattype1.setVisibility(View.INVISIBLE);
        ButtonSeattype2.setVisibility(View.INVISIBLE);
        txSeattype.setVisibility(View.VISIBLE);

        for (SeattypeType cn : mySeattypeList) {

            if (t == 0) {
                ButtonSeattype1.setText(cn.stt_name);
                t++;
                Seattype1id=cn.stt_id;
                ButtonSeattype1.setVisibility(View.VISIBLE);
            } else if (t == 1) {
                ButtonSeattype2.setText(cn.stt_name);
                t++;
                Seattype2id=cn.stt_id;
                ButtonSeattype2.setVisibility(View.VISIBLE);
            }


        }
    }

    public void Showtime2btn1click(View view){
        ShowtimeSelected=Showtime2id;
        Log.wtf("Showtimeselected",String.valueOf(ShowtimeSelected));
        mySeattypeList = dbHall.getAllSeattype(DaySelected,ShowtimeSelected);
        int t=0;
        Seatclear();
        OrderButton.setVisibility(View.INVISIBLE);
        ButtonSeattype1.setVisibility(View.INVISIBLE);
        ButtonSeattype2.setVisibility(View.INVISIBLE);
        txSeattype.setVisibility(View.VISIBLE);

        for (SeattypeType cn : mySeattypeList) {

            if (t == 0) {
                ButtonSeattype1.setText(cn.stt_name);
                t++;
                Seattype1id=cn.stt_id;
                ButtonSeattype1.setVisibility(View.VISIBLE);
            } else if (t == 1) {
                ButtonSeattype2.setText(cn.stt_name);
                t++;
                Seattype2id=cn.stt_id;
                ButtonSeattype2.setVisibility(View.VISIBLE);
            }


        }
    }

    public void Showtime3btn1click(View view){
        ShowtimeSelected=Showtime3id;
        Log.wtf("Showtimeselected",String.valueOf(ShowtimeSelected));
        mySeattypeList = dbHall.getAllSeattype(DaySelected,ShowtimeSelected);
        int t=0;

        Seatclear();
        OrderButton.setVisibility(View.INVISIBLE);
        ButtonSeattype1.setVisibility(View.INVISIBLE);
        ButtonSeattype2.setVisibility(View.INVISIBLE);
        txSeattype.setVisibility(View.VISIBLE);

        for (SeattypeType cn : mySeattypeList) {

            if (t == 0) {
                ButtonSeattype1.setText(cn.stt_name);
                t++;
                Seattype1id=cn.stt_id;
                ButtonSeattype1.setVisibility(View.VISIBLE);
            } else if (t == 1) {
                ButtonSeattype2.setText(cn.stt_name);
                t++;
                Seattype2id=cn.stt_id;
                ButtonSeattype2.setVisibility(View.VISIBLE);
            }


        }
    }

    public void Seattype1btn1click(View view){
        SeattypeSelected=Seattype1id;
        Log.wtf("Seattimeselected",String.valueOf(SeattypeSelected));
        mySeatList = dbHall.getAllSeat(DaySelected,SeattypeSelected);
        int t=0;
        seatidclear();
        CheckBoxEnable();
        CheckBox1.setVisibility(View.INVISIBLE);
        CheckBox2.setVisibility(View.INVISIBLE);
        CheckBox3.setVisibility(View.INVISIBLE);
        CheckBox4.setVisibility(View.INVISIBLE);
        CheckBox5.setVisibility(View.INVISIBLE);
        CheckBoxText1.setVisibility(View.INVISIBLE);
        CheckBoxText2.setVisibility(View.INVISIBLE);
        CheckBoxText3.setVisibility(View.INVISIBLE);
        CheckBoxText4.setVisibility(View.INVISIBLE);
        CheckBoxText5.setVisibility(View.INVISIBLE);
        txSeat.setVisibility(View.VISIBLE);
        OrderButton.setVisibility(View.VISIBLE);

        for (SeatType cn : mySeatList) {

            if (t == 0) {
                CheckBoxText1.setVisibility(View.VISIBLE);
                CheckBoxText1.setText(String.valueOf(cn.seat_number));
                t++;
                if(cn.seat_occupied==1){
                    CheckBox1.setVisibility(View.VISIBLE);
                    CheckBox1.setEnabled(false);
                }
                else {
                    CheckBox1.setVisibility(View.VISIBLE);
                    Seat1id = cn.seat_id;
                }
            } else if (t == 1) {
                CheckBoxText2.setVisibility(View.VISIBLE);
                CheckBoxText2.setText(String.valueOf(cn.seat_number));
                t++;
                if(cn.seat_occupied==1){
                    CheckBox2.setVisibility(View.VISIBLE);
                    CheckBox2.setEnabled(false);
                }
                else {
                    CheckBox2.setVisibility(View.VISIBLE);
                    Seat2id = cn.seat_id;
                }
            }
            else if (t == 2) {
                CheckBoxText3.setVisibility(View.VISIBLE);
                CheckBoxText3.setText(String.valueOf(cn.seat_number));
                t++;
                if(cn.seat_occupied==1){
                    CheckBox3.setVisibility(View.VISIBLE);
                    CheckBox3.setEnabled(false);
                }
                else {
                    CheckBox3.setVisibility(View.VISIBLE);
                    Seat3id = cn.seat_id;
                }
            }
            else if (t == 3) {
                CheckBoxText4.setVisibility(View.VISIBLE);
                CheckBoxText4.setText(String.valueOf(cn.seat_number));
                t++;
                if(cn.seat_occupied==1){
                    CheckBox4.setVisibility(View.VISIBLE);
                    CheckBox4.setEnabled(false);
                }
                else {
                    CheckBox4.setVisibility(View.VISIBLE);
                    Seat4id = cn.seat_id;
                }
            }
            else if (t == 4) {
                CheckBoxText5.setVisibility(View.VISIBLE);
                CheckBoxText5.setText(String.valueOf(cn.seat_number));
                t++;
                if(cn.seat_occupied==1){
                    CheckBox5.setVisibility(View.VISIBLE);
                    CheckBox5.setEnabled(false);
                }
                else {
                    CheckBox5.setVisibility(View.VISIBLE);
                    Seat5id = cn.seat_id;
                }
            }


        }
        Log.wtf("check12344","1= "+Seat1id+" 2= "+Seat2id+" 3= "+Seat3id+" 4= "+Seat4id+" 5= "+Seat5id);
    }
    public void Seattype2btn1click(View view){
        SeattypeSelected=Seattype2id;
        Log.wtf("Seattimeselected",String.valueOf(SeattypeSelected));
        mySeatList = dbHall.getAllSeat(DaySelected,SeattypeSelected);
        int t=0;
        seatidclear();
        CheckBoxEnable();
        CheckBox1.setVisibility(View.INVISIBLE);
        CheckBox2.setVisibility(View.INVISIBLE);
        CheckBox3.setVisibility(View.INVISIBLE);
        CheckBox4.setVisibility(View.INVISIBLE);
        CheckBox5.setVisibility(View.INVISIBLE);
        CheckBoxText1.setVisibility(View.INVISIBLE);
        CheckBoxText2.setVisibility(View.INVISIBLE);
        CheckBoxText3.setVisibility(View.INVISIBLE);
        CheckBoxText4.setVisibility(View.INVISIBLE);
        CheckBoxText5.setVisibility(View.INVISIBLE);
        txSeat.setVisibility(View.VISIBLE);
        OrderButton.setVisibility(View.VISIBLE);

        for (SeatType cn : mySeatList) {

            if (t == 0) {
                CheckBoxText1.setVisibility(View.VISIBLE);
                CheckBoxText1.setText(String.valueOf(cn.seat_number));
                t++;
                if(cn.seat_occupied==1){
                    CheckBox1.setVisibility(View.VISIBLE);
                    CheckBox1.setEnabled(false);
                }
                else {
                    CheckBox1.setVisibility(View.VISIBLE);
                    Seat1id = cn.seat_id;
                }
            } else if (t == 1) {
                CheckBoxText2.setVisibility(View.VISIBLE);
                CheckBoxText2.setText(String.valueOf(cn.seat_number));
                t++;
                if(cn.seat_occupied==1){
                    CheckBox2.setVisibility(View.VISIBLE);
                    CheckBox2.setEnabled(false);
                }
                else {
                    CheckBox2.setVisibility(View.VISIBLE);
                    Seat2id = cn.seat_id;
                }
            }
            else if (t == 2) {
                CheckBoxText3.setVisibility(View.VISIBLE);
                CheckBoxText3.setText(String.valueOf(cn.seat_number));
                t++;
                if(cn.seat_occupied==1){
                    CheckBox3.setVisibility(View.VISIBLE);
                    CheckBox3.setEnabled(false);
                }
                else {
                    CheckBox3.setVisibility(View.VISIBLE);
                    Seat3id = cn.seat_id;
                }
            }
            else if (t == 3) {
                CheckBoxText4.setVisibility(View.VISIBLE);
                CheckBoxText4.setText(String.valueOf(cn.seat_number));
                t++;
                if(cn.seat_occupied==1){
                    CheckBox4.setVisibility(View.VISIBLE);
                    CheckBox4.setEnabled(false);
                }
                else {
                    CheckBox4.setVisibility(View.VISIBLE);
                    Seat4id = cn.seat_id;
                }
            }
            else if (t == 4) {
                CheckBoxText5.setVisibility(View.VISIBLE);
                CheckBoxText5.setText(String.valueOf(cn.seat_number));
                t++;
                if(cn.seat_occupied==1){
                    CheckBox5.setVisibility(View.VISIBLE);
                    CheckBox5.setEnabled(false);
                }
                else {
                    CheckBox5.setVisibility(View.VISIBLE);
                    Seat5id = cn.seat_id;
                }
            }


        }
        Log.wtf("check12344","1= "+Seat1id+" 2= "+Seat2id+" 3= "+Seat3id+" 4= "+Seat4id+" 5= "+Seat5id);
    }

    public void OrderButtonclick(View view){
        SeatArray=new int[5];
        boolean check1=CheckBox1.isChecked();
        boolean check2=CheckBox2.isChecked();
        boolean check3=CheckBox3.isChecked();
        boolean check4=CheckBox4.isChecked();
        boolean check5=CheckBox5.isChecked();
        count=0;
        if(!check1 && !check2 && !check3 && !check4 && !check5 ){
            Toast.makeText(HallBookingActivity.this, "Please Choose Seats", Toast.LENGTH_SHORT).show();
        }
        else {
            if (check1) {
                SeatArray[count++] = Seat1id;
            }
            if (check2) {
                SeatArray[count++] = Seat2id;
            }
            if (check3) {
                SeatArray[count++] = Seat3id;
            }
            if (check4) {
                SeatArray[count++] = Seat4id;
            }
            if (check5) {
                SeatArray[count++] = Seat5id;
            }
            Intent myIntent = new Intent(HallBookingActivity.this, OrderActivity.class);
            myIntent.putExtra("MovieName", MovieName);
            myIntent.putExtra("MovieId", Movieid);
            myIntent.putExtra("HallName", HallName);
            myIntent.putExtra("HallId", HallId);

            myIntent.putExtra("DaySelected", DaySelected);
            myIntent.putExtra("TheatreSelected", TheatreSelected);
            myIntent.putExtra("ShowtimeSelected", ShowtimeSelected);
            myIntent.putExtra("SeattypeSelected", SeattypeSelected);
            myIntent.putExtra("SeatArray", SeatArray);
            myIntent.putExtra("count", count);

            //Log.wtf("Check",String.valueOf(SeatArray[count-1]));

            startActivity(myIntent);
        }

    }


    public void Initialize(){
        ButtonTheatre1=(Button) findViewById(R.id.Theatre1);
        ButtonTheatre2=(Button) findViewById(R.id.Theatre2);
        ButtonTheatre3=(Button) findViewById(R.id.Theatre3);
        tx=(TextView) findViewById(R.id.Theatretext);

        ButtonShowtime1=(Button) findViewById(R.id.Showtime1);
        ButtonShowtime2=(Button) findViewById(R.id.Showtime2);
        ButtonShowtime3=(Button) findViewById(R.id.Showtime3);
        txShowTime=(TextView) findViewById(R.id.Showtimetext);

        ButtonSeattype1=(Button) findViewById(R.id.Seattype1);
        ButtonSeattype2=(Button) findViewById(R.id.Seattype2);
        txSeattype=(TextView) findViewById(R.id.Seattypetext);

        CheckBox1=(CheckBox) findViewById(R.id.checkBox1);
        CheckBox2=(CheckBox) findViewById(R.id.checkBox2);
        CheckBox3=(CheckBox) findViewById(R.id.checkBox3);
        CheckBox4=(CheckBox) findViewById(R.id.checkBox4);
        CheckBox5=(CheckBox) findViewById(R.id.checkBox5);

        CheckBoxText1=(TextView) findViewById(R.id.checkBoxText1);
        CheckBoxText2=(TextView) findViewById(R.id.checkBoxText2);
        CheckBoxText3=(TextView) findViewById(R.id.checkBoxText3);
        CheckBoxText4=(TextView) findViewById(R.id.checkBoxText4);
        CheckBoxText5=(TextView) findViewById(R.id.checkBoxText5);
        txSeat=(TextView) findViewById(R.id.Seattext);

        OrderButton=(Button) findViewById(R.id.orderbutton);
    }
    void Showtimeclear(){
        ButtonShowtime1.setVisibility(View.INVISIBLE);
        ButtonShowtime2.setVisibility(View.INVISIBLE);
        ButtonShowtime3.setVisibility(View.INVISIBLE);
        txShowTime.setVisibility(View.INVISIBLE);
    }
    void Seattypeclear(){
        ButtonSeattype1.setVisibility(View.INVISIBLE);
        ButtonSeattype2.setVisibility(View.INVISIBLE);
        txSeattype.setVisibility(View.INVISIBLE);
    }
    void Seatclear(){
        CheckBox1.setVisibility(View.INVISIBLE);
        CheckBox2.setVisibility(View.INVISIBLE);
        CheckBox3.setVisibility(View.INVISIBLE);
        CheckBox4.setVisibility(View.INVISIBLE);
        CheckBox5.setVisibility(View.INVISIBLE);
        CheckBoxText1.setVisibility(View.INVISIBLE);
        CheckBoxText2.setVisibility(View.INVISIBLE);
        CheckBoxText3.setVisibility(View.INVISIBLE);
        CheckBoxText4.setVisibility(View.INVISIBLE);
        CheckBoxText5.setVisibility(View.INVISIBLE);
        txSeat.setVisibility(View.INVISIBLE);
    }
    void CheckBoxEnable(){
        CheckBox1.setEnabled(true);
        CheckBox2.setEnabled(true);
        CheckBox3.setEnabled(true);
        CheckBox4.setEnabled(true);
        CheckBox5.setEnabled(true);
    }
    void seatidclear(){
        Seat1id=0;
        Seat2id=0;
        Seat3id=0;
        Seat4id=0;
        Seat5id=0;
        CheckBox1.setChecked(false);
        CheckBox2.setChecked(false);
        CheckBox3.setChecked(false);
        CheckBox4.setChecked(false);
        CheckBox5.setChecked(false);
    }
}
