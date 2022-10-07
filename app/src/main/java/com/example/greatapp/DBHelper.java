package com.example.greatapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Login.DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE user(name Text,dob INTEGER PRIMARY KEY,mobile Text ,address Text," +
                "email Text,password Text )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists user");

    }

    public Boolean insertData(String name, Integer dob, String mobile, String address, String email, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("dob", dob);
        contentValues.put("mobile", mobile);
        contentValues.put("address", address);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = sqLiteDatabase.insert("user", null, contentValues);


        if (result == -1) {
            return false;
        }else {
            return true;
        }
    }
    public Boolean checkuserid(String email){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from user where email= ?",new String[]{email});
        if (cursor.getCount()>0){
            return true;

        }else {
            return  false;
        }
    }
    public  Boolean checkemail_password(String email,String password){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from user where email= ? and password =? ",new String[]{email,password});
        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }

    }
}

