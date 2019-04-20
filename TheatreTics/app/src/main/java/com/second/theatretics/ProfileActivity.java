package com.second.theatretics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    Button Update;
    TextView emailupdate;
    TextView nameupdate;
    TextView addressupdate;
    TextView phoneupdate;
    EditText previouspasswordupdate;
    EditText passwordupdate;
    EditText confirmpasswordupdate;
    UsersType check;
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    String value;
    Button Logout;
    final DatabaseUser db=new DatabaseUser(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Update=(Button) findViewById(R.id.updatebutton);
        emailupdate=(TextView) findViewById(R.id.updateemailbox);
        nameupdate=(TextView) findViewById(R.id.updatenamebox);
        addressupdate=(TextView) findViewById(R.id.updateaddressbox);
        phoneupdate=(TextView) findViewById(R.id.updatephonebox);
        previouspasswordupdate=(EditText) findViewById(R.id.previouspasswordbox);
        passwordupdate=(EditText) findViewById(R.id.updatepasswordbox);
        confirmpasswordupdate=(EditText) findViewById(R.id.updateconfirmpasswordbox);

        sp=getSharedPreferences("key", Context.MODE_PRIVATE);
        value = sp.getString("email", "check");
        emailupdate.setText(value);

        check=new UsersType();
        String ch=emailupdate.getText().toString();
        check=db.SingleSearch(emailupdate.getText().toString());
        nameupdate.setText(check.u_name);
        addressupdate.setText(check.u_address);
        phoneupdate.setText(check.u_phone);
    }
    public void UpddatePass(View view){

        if(passwordupdate.getText().toString().equals("") || confirmpasswordupdate.getText().toString().equals("") || previouspasswordupdate.getText().toString().equals(""))
        {
            Toast.makeText (getApplicationContext(),"Information Missing", Toast.LENGTH_SHORT).show();
        }
        else if(!check.u_password.equals(previouspasswordupdate.getText().toString())){
            Toast.makeText (getApplicationContext(),"Previous Password not matched", Toast.LENGTH_SHORT).show();
        }
        else if(!passwordupdate.getText().toString().equals(confirmpasswordupdate.getText().toString())){
            Toast.makeText(getApplicationContext(), "Password not matched", Toast.LENGTH_SHORT).show();
        }
        else {
            db.updateContact(emailupdate.getText().toString(),passwordupdate.getText().toString());
            Toast.makeText(getApplicationContext(), "Password Updated", Toast.LENGTH_SHORT).show();
        }
    }
    public void Logoutfrom(View view){
        emailupdate.setText("");
        ed=sp.edit();
        ed.putString("email","");
        ed.commit();
        startActivity(new Intent(ProfileActivity.this,MainActivity.class));
    }
}
