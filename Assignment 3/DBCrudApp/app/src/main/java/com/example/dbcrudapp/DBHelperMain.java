package com.example.dbcrudapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperMain extends SQLiteOpenHelper {

    public static final String DB_NAME = "UserDB.db";

    public DBHelperMain(Context context)
    {
        super(context,"UserDB.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username text primary key, password text, firstName text, lastName text, age int, city text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
    }

    public Boolean checkUsername(String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ?", new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else return false;
    }

    public Boolean checkUsernamePassword(String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ? and password = ?", new String[]{username,password});
        if(cursor.getCount()>0)
            return true;
        else return false;
    }

    public Boolean insertDataIntoDb(String username, String password, String firstName, String lastName, int age, String city)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("firstName",firstName);
        contentValues.put("lastName",lastName);
        contentValues.put("age",age);
        contentValues.put("city",city);
        long result = db.insert("users",null,contentValues);
        if(result==-1)
            return false;
        else return true;
    }

    public Boolean updateData(String username, String password, String firstName, String lastName, int age, String city)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("firstName", firstName);
        contentValues.put("lastName", lastName);
        contentValues.put("age", age);
        contentValues.put("city", city);
        Cursor cursor = db.rawQuery("select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            long result = db.update("users", contentValues, "username=?", new String[]{username});
            if (result == -1)
                return false;
            else return true;
        }
        else return false;
    }

    public Cursor viewData(String username)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ?",new String[]{username});
        return cursor;
    }

    public Boolean deleteData(String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("users","username = ?",new String[]{username});
        if(result == -1)
            return false;
        else return false;
    }
}
