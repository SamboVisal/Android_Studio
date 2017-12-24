package com.example.sambovisal.dbtest;

/**
 * Created by sambo visal on 05/10/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;



public class DBClass extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME = "Customers.db";
    public static final String TABLE_NAME = "Students_info";
    public static final String COL0 = "ID";
    public static final String COL1 = "Name";
    public static final String COL2 = "Email";

    public DBClass(Context context)
    {
        super(context,DATABASE_NAME,null,1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    //it create when memory allocate
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Email TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean insertData(String name, String email){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL1,name);
        cv.put(COL2,email);
        long res = db.insert(TABLE_NAME,null,cv);
        long k =res;
        if(res ==-1){
            return false;
        }
        else {
            return true;
           //Toast.makeText("done",Toast.LENGTH_LONG).show();
        }
    }
    public Cursor ViewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE_NAME,null);
        return cursor;
    }
    public boolean idCheck(String id){
        String[] column={COL0};
        String select = COL0 + " = ?";
        String[] selectArg = {id};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                column,
                select,
                selectArg,
                null,
                null,
                null
        );
        int count = cursor.getCount();
        if(count>0){
            return true;
        }else{
            return false;
        }

    }
    public boolean UpdateData(String id,String name,String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL0,id);
        values.put(COL1,name);
        values.put(COL2,email);
        int res = db.update(TABLE_NAME,values,"ID = ?",new String[] { id });
        if(res==0){
            return false;
        }
        else{
            return true;
        }
    }
    public Integer DeleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[] {id});
    }
    public boolean checkAvailable(String name, String email){
        String[] columns={COL0};
        String select = COL1 + " = ? " + "AND " + COL2 + " = ? ";
        String[] selectArg= {name,email};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                columns,
                select,
                selectArg,
                null,
                null,
                null
        );
        int count = cursor.getCount();
        if(count>0){
            return true;
        }else{
            return false;
        }
    }
}
