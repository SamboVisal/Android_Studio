package com.example.sambovisal.minozchat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

//we have created app_bar_layout to implement all Activities pages
public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Toolbar mToolbar;
    private ProgressDialog mMainPro;
    private ViewPager mViewPage;
    private SectionPageAdapter mPageAdapter;
    private TabLayout mTablayout;

    private DatabaseReference mUserRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainPro = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        //after we include the layout already and put the id then just implement these code to add name to toolbar
        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Minoz Chat");
        //tabs
        //mViewPage for place to show our content
        mViewPage = (ViewPager) findViewById(R.id.tabpagers);
        mPageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        if (mAuth.getCurrentUser() != null) {


            mUserRef = FirebaseDatabase.getInstance().getReference().child("MyUsers").child(mAuth.getCurrentUser().getUid());

        }
        //show the fragments
        //first statement will be shown the content of each fragment but only one among all
        mViewPage.setAdapter(mPageAdapter);
        //set up your view pager
        mTablayout = (TabLayout) findViewById(R.id.main_tabs);
        //to show all of them just implement this code below, let the Adapter run in the main_tabs
        mTablayout.setupWithViewPager(mViewPage);
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser==null){
            sentTostart();
        }else{
            mUserRef.child("online").setValue("true");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null) {
            mUserRef.child("online").setValue(ServerValue.TIMESTAMP);
        }
    }

    private void sentTostart() {
        Intent startIntent = new Intent(MainActivity.this,StartActivity.class);
        startActivity(startIntent);
        finish();
    }

    //show all menu that we have manipulated in main_menu.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //main_menu.xml will be shown on MainActivity page
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId()== R.id.main_logout_btn){
            //this method is used to sign out for the user
            mMainPro.setTitle("Logging Out");
            mMainPro.setMessage("Please Wait...");
            mMainPro.setCanceledOnTouchOutside(false);
            mMainPro.show();
            mAuth.getInstance().signOut();
            sentTostart();
        }
        if (item.getItemId()==R.id.main_setting_btn){
            mMainPro.setTitle("Logging Out");
            mMainPro.setMessage("Please Wait...");
            mMainPro.setCanceledOnTouchOutside(false);
            mMainPro.show();
            mMainPro.dismiss();
            Intent i = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(i);
        }
        if(item.getItemId()==R.id.main_all_btn){

            Intent i = new Intent(MainActivity.this,UsersActivity.class);
            startActivity(i);
        }

        return true;
    }
}
