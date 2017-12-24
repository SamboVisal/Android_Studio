package com.example.sambovisal.minozchat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersActivity extends AppCompatActivity {

    Toolbar mToolbar;
    private RecyclerView users_list;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        mToolbar = (Toolbar)findViewById(R.id.user_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("All Users");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        users_list = (RecyclerView)findViewById(R.id.users_list);
        users_list.setHasFixedSize(true);
        users_list.setLayoutManager(new LinearLayoutManager(this));

        mDatabase = FirebaseDatabase.getInstance().getReference().child("MyUsers");

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Users,UserViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users, UserViewHolder>(
                Users.class,
                R.layout.users_single_layout,
                UserViewHolder.class,
                mDatabase)
        {
            @Override
            protected void populateViewHolder(UserViewHolder viewHolder, Users model, int position) {
                    viewHolder.setDisName(model.getName());
                    viewHolder.setStatus(model.getStatus());
                    viewHolder.setUserImage(model.getThum_image(),getApplicationContext());
                    final String User_id = getRef(position).getKey();
                    viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent profile_intent = new Intent(UsersActivity.this,ProfileActivity.class);
                            profile_intent.putExtra("Name",User_id);
                            startActivity(profile_intent);
                        }
                    });
            }
        };
        users_list.setAdapter(firebaseRecyclerAdapter);
    }
    public static class UserViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public UserViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setDisName(String name){
            TextView textView = mView.findViewById(R.id.user_single_name);
            textView.setText(name);

        }
        public void setStatus(String status){
            TextView mStatus = mView.findViewById(R.id.user_single_status);
            mStatus.setText(status);
        }
        public void setUserImage(String thum_image, Context ctx){
            CircleImageView user = (CircleImageView) mView.findViewById(R.id.user_single_image);
            Picasso.with(ctx).load(thum_image).placeholder(R.drawable.default_avatar).into(user);
        }
    }
}
