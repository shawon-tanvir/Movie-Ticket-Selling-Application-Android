package com.second.theatretics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginwinActivity extends AppCompatActivity {

    Button loginID ;
    EditText accessusername;
    EditText accesspassword;
    TextView Nameshow;
    TextView Emailshow;
    Button logbutton;
    UsersType contact;
    final DatabaseUser db=new DatabaseUser(this);;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginwin);
        View inflatedView = getLayoutInflater().inflate(R.layout.activity_profile, null);
        contact=new UsersType();
        loginID = (Button) findViewById(R.id.logbutton);
        accessusername = (EditText) findViewById(R.id.emailbox);
        accesspassword =(EditText) findViewById(R.id.passwordbox);

        Emailshow=(TextView) inflatedView.findViewById(R.id.updateemailbox);
        //Nameshow=(TextView) findViewById(R.id.navusername);


    }


    public void gotocreateID(View view){
        startActivity(new Intent(LoginwinActivity.this,CreateIdActivity.class));
    }
    public void checkDatabase(View view){
        String loginuserNameValue=(String) accessusername.getText().toString();
        Log.wtf("Namelog ",loginuserNameValue);
        String loginpasswordValue=(String) accesspassword.getText().toString();
        Log.wtf("Passlog ",loginpasswordValue);




        SharedPreferences sp=getSharedPreferences("key", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed=sp.edit();


        UsersType checkemail=new UsersType();
        //logbutton=(Button) mnav.getHeaderView(1).findViewById(R.id.loginbuttonnavigation);
        if(loginuserNameValue.equals("") || loginpasswordValue.equals(""))
        {
            Toast.makeText (getApplicationContext(),"Information Missing", Toast.LENGTH_SHORT).show();
        }
        else{
            contact=db.UserSearch(loginuserNameValue,loginpasswordValue);
            if(contact.u_email.equals(loginuserNameValue)) {
                if(contact.u_catagory==1) {
                    Toast.makeText(getApplicationContext(), "You are logged in", Toast.LENGTH_SHORT).show();
                    checkemail = db.SingleSearch(loginuserNameValue);
                    Emailshow.setText(checkemail.u_email.toString());
                    ed.putString("email", checkemail.u_email.toString());
                    ed.commit();
                    startActivity(new Intent(LoginwinActivity.this, MainActivity.class));
                }
                else if(contact.u_catagory==3){
                    startActivity(new Intent(LoginwinActivity.this, AdminActivity.class));
                }
            }
            else{
                accessusername.setText("");
                accesspassword.setText("");
                Toast.makeText(getApplicationContext(), "Email and Password did'nt match", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
