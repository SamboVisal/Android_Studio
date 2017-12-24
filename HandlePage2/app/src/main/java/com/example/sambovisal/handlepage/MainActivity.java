package com.example.sambovisal.handlepage;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout l1,l2;
    Button n,p;
    static int pageNum=0,pageGone=0;
    FrameLayout frameLayout;
    Toolbar mToolbar;
    ArrayList<RelativeLayout> listlayout = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        l1 = (RelativeLayout) findViewById(R.id.page1);
        l2 = (RelativeLayout) findViewById(R.id.page2);
        frameLayout=(FrameLayout)findViewById(R.id.frameLayout);
        n = (Button)findViewById(R.id.next);
        p = (Button)findViewById(R.id.prev);

        mToolbar = (Toolbar) findViewById(R.id.main_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Web Developer");

        l1.setVisibility(View.VISIBLE);
        l2.setVisibility(View.GONE);

        final int count = frameLayout.getChildCount();
        for(int i=0;i<count;i++){
            listlayout.add((RelativeLayout) frameLayout.getChildAt(i));
        }
        for(int j=1;j<count;j++){
            listlayout.get(j).setVisibility(View.GONE);
        }
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(0<=pageNum && pageNum<count-1){
                    pageNum++;
                    listlayout.get(pageNum).setVisibility(View.VISIBLE);
                    listlayout.get(pageGone).setVisibility(View.GONE);
                    pageGone++;

                }
                else{
                    Toast.makeText(MainActivity.this,"Last page",Toast.LENGTH_SHORT).show();
                }
            }
        });

        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(pageNum>0){
                   pageNum--;
                   listlayout.get(pageNum).setVisibility(View.VISIBLE);

                   listlayout.get(pageGone).setVisibility(View.GONE);
                   pageGone--;
               }
               else{
                   Toast.makeText(MainActivity.this,"You are in the first page",Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId()==R.id.home){

        }
        if(item.getItemId()==R.id.about){

        }
        if(item.getItemId()==R.id.contact){

        }
        return false;
    }
}
