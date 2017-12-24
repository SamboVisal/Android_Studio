package com.example.sambovisal.minozchat;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.googlecode.mp4parser.authoring.Edit;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {
    private String mChat;
    private Toolbar mToolbar;
    private DatabaseReference mUserDatabase;
    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;
    private String mCurrent_id;

    private TextView mTitleView;
    private TextView mLastSeen;
    private CircleImageView mProfile;
    private ImageButton mChatAddBtn,mChatSendBtn;
    private EditText mSendText;

    private RecyclerView mMessageList;
    private SwipeRefreshLayout mRefreshLayout;

    private List<Messages> messagesList = new ArrayList<>();
    private LinearLayoutManager mLinearLayout;
    private MessageAdapter mMessageAdapter;
    private DatabaseReference mMessageDatabase;

    private static final int TOTOAL_ITEMS_TO_LOAD = 10;
    private int mCurrentPage = 1;

    //new Solution
    private int itemPos = 0;
    String mLastKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mUserDatabase = FirebaseDatabase.getInstance().getReference();



        mChat = getIntent().getStringExtra("Name");
        String uName = getIntent().getStringExtra("User_name");
        mToolbar = (Toolbar) findViewById(R.id.chat_bar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);


        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View action_view = inflater.inflate(R.layout.custom_bar,null);
        actionBar.setCustomView(action_view);

        mTitleView = (TextView) findViewById(R.id.custom_bar_title);
        mLastSeen = (TextView) findViewById(R.id.custom_bar_seen);
        mProfile = (CircleImageView) findViewById(R.id.image_custom_bar);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.message_swipe_layout);

        mChatAddBtn = (ImageButton) findViewById(R.id.chat_add_btn);
        mChatSendBtn = (ImageButton) findViewById(R.id.chat_send_btn);
        mSendText = (EditText) findViewById(R.id.chat_text);
        mMessageList = (RecyclerView) findViewById(R.id.chat_content);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        mLinearLayout = new LinearLayoutManager(this);

        mMessageAdapter = new MessageAdapter(messagesList);

        mMessageList.setHasFixedSize(true);
        mMessageList.setLayoutManager(mLinearLayout);
        mMessageList.setAdapter(mMessageAdapter);






        if(mAuth.getCurrentUser()!= null){


            mCurrent_id = mAuth.getCurrentUser().getUid();
            loadMessage();

        }
        mTitleView.setText(uName);

        mUserDatabase.child("MyUsers").child(mChat).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String online = dataSnapshot.child("online").getValue().toString();
                String image = dataSnapshot.child("image").getValue().toString();
                if(online.equals("true")){
                    mLastSeen.setText("Online");
                }else{
                    GetTimeAgo getTimeAgo = new GetTimeAgo();

                    long lastSeen = Long.parseLong(online);
                    String LastSeen = getTimeAgo.getTimeAgo(lastSeen,getApplicationContext());
                    mLastSeen.setText(LastSeen);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mRootRef.child("chat").child(mCurrent_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(!dataSnapshot.hasChild(mChat)){

                    Map mapChat = new HashMap();
                    mapChat.put("seen",false);
                    mapChat.put("timestamp", ServerValue.TIMESTAMP);

                    Map ChatUserMap = new HashMap();
                    //mChat for whom that we chat to
                    ChatUserMap.put("chat/"+mCurrent_id+"/"+mChat,mapChat);
                    ChatUserMap.put("chat/"+mChat+"/"+mCurrent_id,mapChat);

                    mRootRef.updateChildren(ChatUserMap, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if(databaseError!=null){

                                Log.d("Chat_Log",databaseError.getMessage().toString());

                            }
                        }
                    });

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mChatSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
                mSendText.setText(" ");
            }
        });


        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCurrentPage++;

                itemPos = 0;
                loadMoreMessage();
            }
        });

    }

    private void loadMoreMessage() {
        DatabaseReference messageRef = mRootRef.child("messages").child(mCurrent_id).child(mChat);
        Query messageQuery = messageRef.orderByKey().endAt(mLastKey).limitToLast(10);
        messageQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {



                Messages message = dataSnapshot.getValue(Messages.class);
                messagesList.add(itemPos++,message);
                if(itemPos==1){
                    String messageKey = dataSnapshot.getKey();
                    mLastKey = messageKey;

                }


                mMessageAdapter.notifyDataSetChanged();
                mMessageList.scrollToPosition(messagesList.size()-1);
                mRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void loadMessage() {

        DatabaseReference messageRef = mRootRef.child("messages").child(mCurrent_id).child(mChat);
        Query messageQuery = messageRef.limitToLast(mCurrentPage=TOTOAL_ITEMS_TO_LOAD);

            messageQuery.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Messages message = dataSnapshot.getValue(Messages.class);

                    itemPos++;

                    if(itemPos==1){
                        String messageKey = dataSnapshot.getKey();
                        mLastKey = messageKey;

                    }

                    messagesList.add(message);
                    mMessageAdapter.notifyDataSetChanged();
                    mMessageList.scrollToPosition(messagesList.size()-1);

                    mRefreshLayout.setRefreshing(false);

                    mLinearLayout.scrollToPositionWithOffset(10,0);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });





    }

    private void sendMessage() {

        final String message = mSendText.getText().toString();
        if(!TextUtils.isEmpty(message)){
            String current_user_ref = "messages/"+mCurrent_id+"/"+mChat;
            String chat_user_ref = "messages/"+mChat+"/"+mCurrent_id;

            DatabaseReference user_message_push = mRootRef.child("messages")
                    .child(mCurrent_id).child(mChat).push();
            String push_id = user_message_push.getKey();
            Map messageMap = new HashMap();
            messageMap.put("messages",message);
            messageMap.put("seen",false);
            messageMap.put("type","text");
            messageMap.put("time",ServerValue.TIMESTAMP);
            messageMap.put("from",mCurrent_id);

            Map userMessageMap = new HashMap();
            userMessageMap.put(current_user_ref+"/"+push_id,messageMap);
            userMessageMap.put(chat_user_ref+"/"+push_id,messageMap);

            mRootRef.updateChildren(userMessageMap, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                    if(databaseError!=null){

                        Log.d("Chat_Log",databaseError.getMessage().toString());


                    }

                }
            });
        }


    }


}
