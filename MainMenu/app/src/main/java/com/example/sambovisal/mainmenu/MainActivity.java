package com.example.sambovisal.mainmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu meu) {
        MenuInflater men = getMenuInflater();
        men.inflate(R.menu.menu,meu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.setting:
                Toast.makeText(MainActivity.this,"This Is Setting",Toast.LENGTH_LONG).show();

                return true;
            case R.id.about1:
                Toast.makeText(MainActivity.this,"This is about",Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(i);
                return true;
            case R.id.contact:
                Toast.makeText(MainActivity.this,"This is contact",Toast.LENGTH_LONG).show();
                Intent u = new Intent(MainActivity.this,ContactActivity.class);
                startActivity(u);
        }
        return false;
    }


}
