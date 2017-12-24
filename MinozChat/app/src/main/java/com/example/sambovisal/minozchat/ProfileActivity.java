package com.example.sambovisal.minozchat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    private ImageView mProfileImage;
    private TextView mProfileName,mProfileStatus,mProfileTotal;
    private Button mProfileSendReq,mProfileDecReq;
    private DatabaseReference mDatabaseUser;
    private DatabaseReference mFriendDatabase;
    private ProgressDialog mProgress;
    private DatabaseReference mNotificationDatabase;

    private DatabaseReference mFriendReqData;
    private DatabaseReference mRootRef;

    private FirebaseUser mCurrent_user;
    private String current_state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final String user_id = getIntent().getStringExtra("Name");
        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("MyUsers").child(user_id);
        mProfileName = (TextView)findViewById(R.id.profile_disName);
        mProfileStatus = (TextView) findViewById(R.id.profile_status);
        mProfileTotal = (TextView)findViewById(R.id.profile_totalFriends);
        mProfileSendReq = (Button)findViewById(R.id.profile_send_req);
        mProfileDecReq = (Button)findViewById(R.id.profile_decline_btn);
        mProfileImage = (ImageView)findViewById(R.id.profileImage);
        mRootRef =FirebaseDatabase.getInstance().getReference();
        mFriendReqData = FirebaseDatabase.getInstance().getReference().child("Friend_Req");
        mFriendDatabase = FirebaseDatabase.getInstance().getReference().child("Friends");
        mNotificationDatabase = FirebaseDatabase.getInstance().getReference().child("notifications");
        mCurrent_user = FirebaseAuth.getInstance().getCurrentUser();
        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Loading User Data");
        mProgress.setMessage("Please wait...");
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.show();

        current_state = "not_friend";
        mProfileDecReq.setVisibility(View.INVISIBLE);

        mDatabaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String display_name = dataSnapshot.child("user").getValue().toString();
                String display_status = dataSnapshot.child("status").getValue().toString();
                String display_image = dataSnapshot.child("image").getValue().toString();

                mProfileName.setText(display_name);
                mProfileStatus.setText(display_status);

                Picasso.with(ProfileActivity.this).load(display_image).placeholder(R.drawable.default_avatar).into(mProfileImage);
                //--------------- FRIEND LIST AND REQUEST FEATURE
                mFriendReqData.child(mCurrent_user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(dataSnapshot.hasChild(user_id)){

                            String req_type = dataSnapshot.child(user_id).child("request_type").getValue().toString();
                            if(req_type.equals("received")){

                                current_state = "req_received";
                                mProfileSendReq.setText("Accept Friend Request");
                                mProfileDecReq.setVisibility(View.VISIBLE);
                                mProfileDecReq.setEnabled(true);
                            } else if(req_type.equals("sent")){

                                current_state = "req_sent";
                                mProfileSendReq.setText("Cancel Friend Request");

                                mProfileDecReq.setVisibility(View.INVISIBLE);
                                mProfileDecReq.setEnabled(false);

                            }
                            mProgress.dismiss();

                        }else{
                            mFriendDatabase.child(mCurrent_user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.hasChild(user_id)){

                                        current_state = "friends";
                                        mProfileSendReq.setText("UnFriend This Person");


                                        mProfileDecReq.setVisibility(View.INVISIBLE);
                                        mProfileDecReq.setEnabled(false);

                                    }

                                    mProgress.dismiss();
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                    mProgress.dismiss();
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mProfileSendReq.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                mProfileSendReq.setEnabled(false);

                //----------------NOT FRIEND STATE----------------------
                if(current_state.equals("not_friend")){

                    DatabaseReference mNotificationRef = mRootRef.child("notifications").child(user_id).push();
                    String mNotificationId = mNotificationRef.getKey();
                    HashMap<String,String> notificationData = new HashMap<String, String>();
                    notificationData.put("from",mCurrent_user.getUid());
                    notificationData.put("type","request");

                   Map requestMap = new HashMap<>();
                    requestMap.put("Friend_Req/"+mCurrent_user.getUid()+ "/"+ user_id+"/request_type","sent");
                    requestMap.put("Friend_Req/"+user_id+"/"+mCurrent_user.getUid()+"/request_type","received");
                    requestMap.put("notifications/"+user_id+"/"+mNotificationId,notificationData);
                    mRootRef.updateChildren(requestMap, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if(databaseError!=null){
                                Toast.makeText(ProfileActivity.this, "There was some errors of sending request", Toast.LENGTH_SHORT).show();
                            }
                            mProfileSendReq.setEnabled(true);
                            current_state = "req_sent";
                            mProfileSendReq.setText("Cancel Friend Request");
                        }
                    });

                }
                //--------------------Cancel FRIEND STATE ---------------------
                if(current_state.equals("req_sent")) {
                    mFriendReqData.child(mCurrent_user.getUid()).child(user_id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            mFriendReqData.child(user_id).child(mCurrent_user.getUid()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    mProfileSendReq.setEnabled(true);
                                    current_state = "not_friend";
                                    mProfileSendReq.setText("Send Friend Request");


                                    mProfileDecReq.setVisibility(View.INVISIBLE);
                                    mProfileDecReq.setEnabled(false);
                                }
                            });

                        }
                    });
                }
                //-----------------REQ received state
                if(current_state.equals("req_received")){

                    final String currentData = DateFormat.getDateTimeInstance().format(new Date());

                    Map friendsMap = new HashMap();
                    friendsMap.put("Friends/"+mCurrent_user.getUid()+"/"+user_id+"/date",currentData);
                    friendsMap.put("Friends/"+user_id+"/"+mCurrent_user.getUid()+"/date",currentData);

                    friendsMap.put("Friends_Req/"+mCurrent_user.getUid()+"/"+user_id,null);
                    friendsMap.put("Friends_Req/"+user_id+"/"+mCurrent_user.getUid(),null);

                    mRootRef.updateChildren(friendsMap, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if(databaseError==null){
                                mProfileSendReq.setEnabled(true);
                                current_state = "friends";
                                mProfileSendReq.setText("UnFriend This Person");

                                mProfileDecReq.setVisibility(View.INVISIBLE);
                                mProfileDecReq.setEnabled(false);
                            }else{
                                String error = databaseError.getMessage();
                                Toast.makeText(ProfileActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }

                if(current_state.equals("friends")){

                    Map unFriendMap = new HashMap();
                    unFriendMap.put("Friends/"+mCurrent_user.getUid()+"/"+user_id,null);
                    unFriendMap.put("Friends/"+user_id+"/"+mCurrent_user.getUid(),null);
                    mRootRef.updateChildren(unFriendMap, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if(databaseError==null){
                                current_state = "not_friend";
                                mProfileSendReq.setText("Send Friend Request");

                                mProfileDecReq.setVisibility(View.INVISIBLE);
                                mProfileDecReq.setEnabled(false);
                            }else{
                                String error = databaseError.getMessage();
                                Toast.makeText(ProfileActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                            mProfileSendReq.setEnabled(true);
                        }
                    });

                }
            }
        });
    }

}
