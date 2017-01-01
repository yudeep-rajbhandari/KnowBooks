package com.example.linux.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by linux on 11/24/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="KnowBooks.db";
    public static final String TABLE_NAME="users";
    public static final String COL_1="Name";
    public static final String COL_2="Department";
    public static final String COL_3="Year";
    public static final String COL_4="Roll";
    public static final String COL_5="Phone";
    public static final String COL_6="Password";
    public static final String COL_7="Email";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();



    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " +TABLE_NAME+ " (Roll INTEGER,Name TEXT,Department TEXT,Year INTEGER,Phone INTEGER,Password TEXT,email TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" +TABLE_NAME);
        onCreate(sqLiteDatabase);


    }

    public String searchpass(String username){
        SQLiteDatabase sqLiteDatabase =this.getReadableDatabase();
        String query="Select Name,Password from " +TABLE_NAME;
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        String a,b;
        b="not found";
        if(cursor.moveToFirst()){
            do{
                a=cursor.getString(0);
                if (a.equals(username)){
                    b=cursor.getString(1);
                    break;
                }
            }
        while(cursor.moveToNext());


        }
return b;
    }

   public boolean insertData(String Name,String Department,String Year,String Roll, String Phone,String Password,String Email){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,Name);
        contentValues.put(COL_2,Department);
        contentValues.put(COL_3,Year);
        contentValues.put(COL_4,Roll);
        contentValues.put(COL_5,Phone);
        contentValues.put(COL_6,Password);
        contentValues.put(COL_7,Email);
        long result=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        if(result==-1){
            return false;
        }
 else
            return true;
    }


    public List<String> getAllLabels(){
        List<String> list = new ArrayList<String>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        // Select All Query
        String selectQuery = "Select * from " +TABLE_NAME;


        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String NAME = cursor.getString(1);
                list.add(NAME);
                //list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        sqLiteDatabase.close();

        // returning lables
        return list;
    }

}
