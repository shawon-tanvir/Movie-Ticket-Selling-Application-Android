package com.second.theatretics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OrderActivity extends AppCompatActivity {
    int DaySelected;
    int TheatreSelected;
    int ShowtimeSelected;
    int SeattypeSelected;
    String MovieName;
    String HallName;
    int HallId;
    int Movieid;
    String value;
    int[] SeatArray;
    int count;
    int totalamount=0;
    String seatnumber="";

    TextView Date;
    TextView Email;
    TextView Seats;
    TextView TotalSeat;
    TextView TotalAmount;
    TextView Movie;
    TextView Hall;
    TextView Theatre;
    TextView Showtime;
    TextView Seattype;
    OrderType myOrder;
    OrderType myOrderLast;
    UsersType myUser;
    SeattrackType mySeattrack;
    Button Confirm;
    SharedPreferences sp;

    DatabaseUser dbUser;
    DatabaseOrder db;

    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        db = new DatabaseOrder(this);
        dbUser = new DatabaseUser(this);
        sp=getSharedPreferences("key", Context.MODE_PRIVATE);
        ed=sp.edit();

        Date=(TextView) findViewById(R.id.orderDate);
        Email=(TextView) findViewById(R.id.orderemail);
        Seats=(TextView) findViewById(R.id.seatnumbers);
        TotalSeat=(TextView) findViewById(R.id.totalseat);
        TotalAmount=(TextView) findViewById(R.id.totalamount);
        Movie=(TextView) findViewById(R.id.ordermovie);
        Hall=(TextView) findViewById(R.id.orderhall);
        Theatre=(TextView) findViewById(R.id.orderTheatre);
        Showtime=(TextView) findViewById(R.id.orderShowtime);
        Seattype=(TextView) findViewById(R.id.orderSeattype);
        Confirm =(Button) findViewById(R.id.confirm);


        Intent myIntent = getIntent();
        Movieid=myIntent.getIntExtra("MovieId",0);

        MovieName = myIntent.getStringExtra("MovieName");
        HallName = myIntent.getStringExtra("HallName");
        HallId=myIntent.getIntExtra("HallId",0);
        DaySelected=myIntent.getIntExtra("DaySelected",0);
        TheatreSelected=myIntent.getIntExtra("TheatreSelected",0);
        ShowtimeSelected=myIntent.getIntExtra("ShowtimeSelected",0);
        SeattypeSelected=myIntent.getIntExtra("SeattypeSelected",0);
        SeatArray=new int[5];
        SeatArray=myIntent.getIntArrayExtra("SeatArray");
        count=myIntent.getIntExtra("count",0);
        Log.wtf("chekjjddbcck",String.valueOf(count));


        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss");
        String Date1 = df.format(c.getTime());
        c.add(Calendar.DATE, 1);  // number of days to add
        String Date2 = df.format(c.getTime());
        c.add(Calendar.DATE, 1);  // number of days to add
        String Date3 = df.format(c.getTime());

        if(DaySelected==1){
            Date.setText(Date1);
        }
        else if(DaySelected==2){
            Date.setText(Date2);
        }
        else if(DaySelected==3){
            Date.setText(Date3);
        }
        value = sp.getString("email", "check");

        Email.setText(value);
        Hall.setText(HallName);
        Movie.setText(MovieName);

        TheatresType myTheatre=db.getSingleTheatre(TheatreSelected);
        Theatre.setText(myTheatre.t_name);
        //Toast.makeText(OrderActivity.this, String.valueOf(ShowtimeSelected), Toast.LENGTH_SHORT).show();
        ShowtimeType myShowtime=db.getSingleShowtime(DaySelected,ShowtimeSelected);
        Showtime.setText(myShowtime.st_timestart);
        SeattypeType mySeattype=db.getSingleSeattype(DaySelected,SeattypeSelected);
        Seattype.setText(mySeattype.stt_name);

        SeatType mySeat;
        for(int i=0;i<count;i++){

            mySeat=db.getSingleSeat(DaySelected,SeatArray[i]);
            seatnumber+=mySeat.seat_number+"  ";
            totalamount+=mySeat.seat_fare;
        }
        Seats.setText(seatnumber);
        TotalAmount.setText(String.valueOf(totalamount));
        TotalSeat.setText(String.valueOf(count));

        myOrder=new OrderType();
        myOrderLast=new OrderType();
        mySeattrack=new SeattrackType();

        myOrder.setTotal_seat(count);
        myOrder.setTotal_amount(totalamount);
        myOrder.setO_confirmation(0);
        myUser=dbUser.SingleSearch(value);
        myOrder.setU_id(myUser.u_id);
        myOrder.setM_id(Movieid);

        mySeattrack.setSeat_day(DaySelected);
        mySeattrack.setConfirmation(0);


        Confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.wtf("Check",TotalSeat.getText().toString());


                db.addOrder(myOrder);
                myOrderLast=db.getLastOrder(myOrder.u_id);
                Toast.makeText(OrderActivity.this, String.valueOf(myOrderLast.o_id), Toast.LENGTH_SHORT).show();
                mySeattrack.setO_id(myOrderLast.o_id);
                for(int i=0;i<count;i++){
                    mySeattrack.setSeat_id(SeatArray[i]);
                    db.addSettrack(mySeattrack);
                }
                ed.putString("OrderId",String.valueOf(myOrderLast.o_id) );
                ed.commit();
                Toast.makeText(OrderActivity.this, "Booking is placed.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(OrderActivity.this,PaymentActivity.class));
            }
        });

    }
    /*public void ConfirmOrder (View view){
        setContentView(R.layout.activity_order);
        Log.wtf("Check",TotalSeat.getText().toString());
        String ts=TotalSeat.getText().toString();
        String ta=TotalAmount.getText().toString();
        myOrder.total_seat=Integer.valueOf(ts);
        myOrder.total_amount=Integer.valueOf(ta);
        myOrder.o_confirmation=0;
        myUser=dbUser.SingleSearch(value);
        myOrder.u_id=myUser.u_id;
        myOrder.m_id=Movieid;
        db.addOrder(myOrder);

        Toast.makeText(OrderActivity.this, "Booking is placed.", Toast.LENGTH_SHORT).show();
    }*/

}
