package com.example.sambovisal.debugloginsignup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Toolbar mToolbar;
    String email;
    TextView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Home");
        mToolbar = (Toolbar)findViewById(R.id.main_tool);
        Intent i  = getIntent();
        email = i.getStringExtra("EMAIL");
        view = (TextView)findViewById(R.id.hello_id);
        view.setText(email);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.users_item:
                if(!email.equals("pisal@gamil.com")){
                    Toast.makeText(MainActivity.this,"Only Admin can access",Toast.LENGTH_LONG).show();
                }else{
                    Intent i = new Intent(this,UsersListActivity.class);
                    i.putExtra("EMAIL",email);
                    startActivity(i);
                }
                break;
            case R.id.logout_item:
                MainActivity.this.finish();
                break;
            case R.id.about_item:
                Intent about_intent = new Intent(this,AboutActivity.class);
                startActivity(about_intent);
        }

        return true;

    }
}
