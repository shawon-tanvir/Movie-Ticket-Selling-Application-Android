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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {


    String value;
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    TextView Orderid;
    EditText PaymentPhone;
    EditText PaymentTransaction;
    Spinner Paymenttype;
    EditText PaymentAmount;
    PaymentType myPament;
    Button PaymentConfirm;
    String Date1;
    List<SeattrackType> mySeattrack;
    final DatabaseOrder db=new DatabaseOrder(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mySeattrack=new ArrayList<SeattrackType>();
        myPament=new PaymentType();
        Orderid=(TextView) findViewById(R.id.paymentorderId) ;
        PaymentPhone=(EditText) findViewById(R.id.paymentphone);
        PaymentTransaction=(EditText) findViewById(R.id.paymenttransaction);
        PaymentAmount=(EditText) findViewById(R.id.paymentamount);
        Paymenttype=(Spinner) findViewById(R.id.paymenttype);
        PaymentConfirm=(Button) findViewById(R.id.paymentconfirm);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date1 = df.format(c.getTime());

        sp=getSharedPreferences("key", Context.MODE_PRIVATE);
        ed=sp.edit();
        value = sp.getString("OrderId", "0");
        Orderid.setText("Booking ID: "+  value);


        PaymentConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Log.wtf("Check",TotalSeat.getText().toString());
                if(PaymentPhone.getText().toString().equals("") || PaymentTransaction.getText().toString().equals("") || PaymentAmount.getText().toString().equals("") || Paymenttype.getSelectedItem().toString().equals("")){
                    Toast.makeText(PaymentActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else {
                    String text = Paymenttype.getSelectedItem().toString();
                    Toast.makeText(PaymentActivity.this, text, Toast.LENGTH_SHORT).show();
                    myPament.o_id=Integer.parseInt(value);
                    myPament.p_phone=PaymentPhone.getText().toString();
                    myPament.p_datetime=Date1;
                    myPament.p_amount=Integer.parseInt(PaymentAmount.getText().toString());
                    myPament.p_transactionNumber=PaymentTransaction.getText().toString();
                    if(text.equals("Bkash")){
                        myPament.p_type=1;
                    }
                    else if(text.equals("Rocket")){
                        myPament.p_type=2;
                    }
                    db.addPayment(myPament);
                    Toast.makeText(PaymentActivity.this, "Payment Completed", Toast.LENGTH_SHORT).show();
                    db.ConfirmSeattrack(Integer.parseInt(value));
                    mySeattrack=db.getSeattrack(Integer.parseInt(value));
                    int Day;
                    int SeatId;
                    for (SeattrackType temp : mySeattrack) {

                        Day=temp.seat_day;
                        SeatId=temp.seat_id;
                        db.ConfirmSeat(Day,SeatId);
                    }
                    ed.putString("OrderId","");
                    ed.commit();
                    startActivity(new Intent(PaymentActivity.this,MainActivity.class));

                }


            }
        });
        /*Paymenttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //Change the selected item's text color
                Paymenttype.get
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });*/

    }

}
