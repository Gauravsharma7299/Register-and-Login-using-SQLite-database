package com.example.greatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText user_name,user_password;
    DBHelper dbHelper;
    Button logins;
    TextView signups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_name=findViewById(R.id.et_L_Email);
        user_password=findViewById(R.id.et_L_Password);
        logins=findViewById(R.id.btn_L_Login);

        logins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=user_name.getText().toString();
                String userpass=user_password.getText().toString();
                if (username.equals("")||userpass.equals("")){
                    Toast.makeText(LoginActivity.this,"Fill all the credentials",Toast.LENGTH_SHORT).show();
                }else {
                    Boolean result=dbHelper.checkemail_password(username,userpass);
                    if (result==true){
                        Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();


                    }else{
                        Toast.makeText(LoginActivity.this,"Invalid Login credentials",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}