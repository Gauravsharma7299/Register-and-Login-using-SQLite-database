package com.example.greatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
   private EditText name,dob,mobile_no,Address, email,password;
   private Button  register;
   private TextView sign_In;
   DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main2);

        name=findViewById(R.id.et_R_Name);
       dob =findViewById(R.id.et_R_DOB);
       mobile_no =findViewById(R.id.et_R_Mobile_no);
      Address  =findViewById(R.id.et_R_Address);
      email  =findViewById(R.id.et_R_Email);
      password  =findViewById(R.id.et_R_Password);
      register=findViewById(R.id.btn_R_register);
      sign_In=findViewById(R.id.tv_signIn);
      dbHelper=new DBHelper(this);

      register.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String names=name.getText().toString();
              Integer DOB= Integer.valueOf(dob.getText().toString());
              String mobile_No=mobile_no.getText().toString();
              String Addresses=Address.getText().toString();
              String Email=email.getText().toString();
              String Password=password.getText().toString();

              if(names.equals("")|| DOB.equals("")||mobile_No.equals("")||Addresses.equals("")||
              Email.equals("")||Password.equals("")){
                  Toast.makeText(MainActivity2.this,"Fill all the gaps",Toast.LENGTH_SHORT).show();
              }else {
                  Boolean usercheckResult=dbHelper.checkuserid(Email);
                  if (usercheckResult==false){
                      Boolean RegisterResult= dbHelper.insertData(names,DOB,mobile_No,Addresses,Email,Password);
                      if (RegisterResult==true){
                          Toast.makeText(MainActivity2.this,"Registration Successfull",Toast.LENGTH_SHORT).show();
                          Intent login=new Intent(getApplicationContext(),LoginActivity.class);
                          startActivity(login);
                      }else {
                          Toast.makeText(MainActivity2.this,"Registraion Failed",Toast.LENGTH_SHORT).show();
                      }

                  }else{
                      Toast.makeText(MainActivity2.this,"user already exists",Toast.LENGTH_SHORT).show();
                  }


              }


          }
      });
      sign_In.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent loginpage=new Intent(getApplicationContext(),LoginActivity.class);
              startActivity(loginpage);
          }
      });


    }
}