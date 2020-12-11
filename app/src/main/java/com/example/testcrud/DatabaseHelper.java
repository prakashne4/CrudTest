package com.example.testcrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "User.db";
    public static final String TABLE_NAME = "User";

    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String USER_NAME = "user_Name";
    public static final String PASSWORD = "password";
    public static final String FIRST_NAME = "first_Name";
    public static final String LAST_NAME = "last_Name";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, USER_NAME TEXT, PASSWORD TEXT," +
                "FIRST_NAME TEXT, LAST_NAME TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean Register(String date, String userName, String Password, String firstName, String lastName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATE, date);
        values.put(USER_NAME, userName);
        values.put(PASSWORD, Password);
        values.put(FIRST_NAME, firstName);
        values.put(LAST_NAME, lastName);

        long result = db.insert(TABLE_NAME, null, values);
        Log.d("Register", "Register values :"+values);
        if (result == -1)
            return false;
        else
        return true;

    }

    public Cursor getUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from "+TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String Id, String date, String userName, String Password, String firstName, String lastName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, Id);
        values.put(DATE, date);
        values.put(USER_NAME, userName);
        values.put(PASSWORD, Password);
        values.put(FIRST_NAME, firstName);
        values.put(LAST_NAME, lastName);
        db.update(TABLE_NAME, values,"id = ?", new String[] { Id });
        return true;
    }

    public Integer deletData(String Id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id = ?", new String[] { Id });
    }


   /* public  boolean checkUserName(String userName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from User where userName=?",new String[] { userName });
        if(cursor.getCount() > 0)
            return false;
        else
            return true;
    }*/
}
