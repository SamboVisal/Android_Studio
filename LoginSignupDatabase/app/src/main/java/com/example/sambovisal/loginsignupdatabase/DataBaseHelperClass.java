package com.example.sambovisal.loginsignupdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sambo visal on 08/10/2017.
 */

public class DataBaseHelperClass extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LoginSignFrom.db";
    private static final String TABLE_NAME  = "CUSTOMERS";

    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASS = "pass";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table CUSTOMERS (id integer primary key not null auto_increment," +
            "name text not null, email text not null, pass text not null);";


    public DataBaseHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF NOT EXISTS " +TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(LogSigForm l) {


        db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        String query = "select * from CUSTOMERS";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        c.put(COL_ID,count);
        c.put(COL_NAME,l.getName());
        c.put(COL_EMAIL,l.getEmail());
        c.put(COL_PASS,l.getPass());
        long res = db.insert(TABLE_NAME,null,c);

        if(res == -1){
            return false;
        }else {
            return true;
        }

    }

    public String confrimPass(String uname) {
        db = this.getReadableDatabase();
        String query = "select uname,pass from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        String a,b;
        b = "Not found";
        if(cursor.moveToFirst()){
            a = cursor.getString(0);
            do {
                if(a.equals(uname)){
                    b = cursor.getString(1);
                }

            }while (cursor.moveToNext());
        }
        return b;
    }
}
