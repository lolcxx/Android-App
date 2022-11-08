package com.aadProject.studentSubjectData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "userdb.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase mydb) {
        mydb.execSQL("create table users(regno Text primary key, pass Text, sub1 Text, sub2 Text, sub3 Text) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase mydb, int i, int i1) {
        mydb.execSQL("drop table if exists users");
    }

    public Boolean insertData(String reg, String pass, String sub1, String sub2, String sub3){
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("regno",reg);
        contentValues.put("pass",pass);
        contentValues.put("sub1",sub1);
        contentValues.put("sub2",sub2);
        contentValues.put("sub3",sub3);
        long res = mydb.insert("users", null, contentValues);
        if(res == -1)
            return false;
        return true;
    }

    public Boolean checkRegNo(String reg){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from users where regno = ?", new String[] {reg});
        if(cursor.getCount() > 0)
            return true;
        return false;
    }

    public Boolean checkLogin(String regno, String pass){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from users where regno = ? and pass = ?", new String[] {regno,pass});
        if(cursor.getCount() > 0)
            return true;
        return false;
    }

    public List<UserItem> getAllUsers(){
        List<UserItem> list = new ArrayList<UserItem>();
        SQLiteDatabase mydb = this.getReadableDatabase();
        Cursor cursor = mydb.rawQuery("select * from users", null);
        if(cursor.moveToFirst()){
            do{
                UserItem newUser = new UserItem();
                newUser.setRegno(cursor.getString(0));
                newUser.setPass(cursor.getString(1));
                newUser.setSub1(cursor.getString(2));
                newUser.setSub2(cursor.getString(3));
                newUser.setSub3(cursor.getString(4));
                list.add(newUser);
            }while(cursor.moveToNext());
        }
        cursor.close();
        mydb.close();
        return list;
    }

    public Cursor getData(){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users",null);
        return cursor;
    }

    public Cursor getMyData(String reg){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where regno = ?", new String[] {reg});
        return cursor;
    }
}
