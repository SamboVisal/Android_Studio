package com.example.sambovisal.dbtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,email,id;
    Button mBtn;
    DBClass db;
    String NAME,EMAIL,ID;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText)findViewById(R.id.username);
        email= (EditText)findViewById(R.id.email);
        mBtn = (Button)findViewById(R.id.btn);
        id = (EditText)findViewById(R.id.id);
        db = new DBClass(this);
        mToolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Home Page");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu mnu) {
        MenuInflater menu = getMenuInflater();
        menu.inflate(R.menu.main_menu,mnu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.login_btn:
                Intent i = new Intent(this,LoginActivity.class);
                startActivity(i);
                break;
            case R.id.sign_btn:
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
        return true;
    }

    public void submit(View view){
        String stuname = name.getText().toString();
        String stuemail= email.getText().toString();
        if(stuname.isEmpty()&&stuemail.isEmpty()){
            Toast.makeText(this,"Value can not empty",Toast.LENGTH_LONG).show();
        }else{
            boolean res = db.insertData(stuname,stuemail);
            if(res == true){
                Toast.makeText(this,"Done",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"False",Toast.LENGTH_LONG).show();
            }
        }


    }
    public void viewMethod(View v){
        Cursor cursor = db.ViewData();
        StringBuffer bu = new StringBuffer();
        int count =cursor.getCount();
        if(count==0){
            Toast.makeText(this,"No data exists",Toast.LENGTH_LONG).show();
        }
        else{
            while(cursor.moveToNext()){

                bu.append("ID : "+cursor.getString(0)+"\n");
                bu.append("Name : "+cursor.getString(1)+"\n");
                bu.append("Email : "+cursor.getString(2)+"\n\n");
            }
            showMessage("Data",bu.toString());

        }
    }
    public void showMessage(String title,String Msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(Msg);
        builder.setCancelable(true);
        builder.show();
    }

    public void updateMethod(View v){
             //update
            boolean idc = db.idCheck(ID = id.getText().toString());
            if(idc==true){
                NAME = name.getText().toString();
                EMAIL = email.getText().toString();

                boolean res = db.UpdateData(ID,NAME,EMAIL);
                if(res==true){
                    Toast.makeText(this, "DONE UPDATED", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "False UPDATED", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "ID is not exist", Toast.LENGTH_SHORT).show();
            }

        }
    public void deleteMethod(View v){
        Integer b = db.DeleteData(ID = id.getText().toString());
        if(b>0){
            Toast.makeText(this, "DONE DELETED", Toast.LENGTH_SHORT).show();
        }
    }



}
