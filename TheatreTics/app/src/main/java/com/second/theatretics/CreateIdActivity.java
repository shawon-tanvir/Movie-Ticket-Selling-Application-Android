package com.second.theatretics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateIdActivity extends AppCompatActivity {
    Button createID ;
    EditText createusername;
    EditText createaddress;
    EditText createphone;
    EditText createemail;
    EditText createpassword;
    EditText createconfirmpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_id);

        createID = (Button) findViewById(R.id.createbutton);
        createusername =(EditText) findViewById(R.id.createnamebox);
        createaddress =(EditText) findViewById(R.id.createaddressbox);
        createphone =(EditText) findViewById(R.id.createphonebox);
        createemail =(EditText) findViewById(R.id.createemailbox);
        createpassword= (EditText) findViewById(R.id.createpasswordbox);
        createconfirmpassword =(EditText) findViewById(R.id.createconfirmpasswordbox);
        final DatabaseUser db=new DatabaseUser(this);

        createID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userNameValue= createusername.getText().toString();
                String userAddressValue= createaddress.getText().toString();
                String userPhoneValue= createphone.getText().toString();
                String userEmailValue= createemail.getText().toString();
                String passwordValue= createpassword.getText().toString();
                String confirmPasswordValue= createconfirmpassword.getText().toString();
                UsersType check=new UsersType();
                check=db.SingleSearch(userEmailValue);
                if(userNameValue.equals("") || userAddressValue.equals("") || userPhoneValue.equals("") || userEmailValue.equals("") || passwordValue.equals("") || confirmPasswordValue.equals(""))
                {
                    Toast.makeText (getApplicationContext(),"Information Missing", Toast.LENGTH_SHORT).show();
                }
                else if(!passwordValue.equals(confirmPasswordValue)){
                    Toast.makeText (getApplicationContext(),"Password not matched", Toast.LENGTH_SHORT).show();
                }
                else if(check.u_email.equals(userEmailValue)){
                    Toast.makeText (getApplicationContext(),"Email Already Registered", Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addContact(new UsersType(userNameValue,userAddressValue,userPhoneValue,userEmailValue,passwordValue,1));
                    Toast.makeText (getApplicationContext(),"You are Added.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CreateIdActivity.this,LoginwinActivity.class));
                }
            }
        });
    }
    public void onClickbutton(View view){
        //startActivity(new Intent(createID.this,AddRestaurantList.class));
    }
}

