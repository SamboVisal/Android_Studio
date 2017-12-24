package com.example.sambovisal.minozchat;

import android.app.ProgressDialog;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StatusActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextInputLayout mStatus;
    private Button mBtn;

    private DatabaseReference mDatabaseStatus;
    private FirebaseUser mUser;

    private ProgressDialog mDialogue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        mToolbar = (Toolbar)findViewById(R.id.status_layout);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Your Status");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        String current_user = mUser.getUid();

        mDatabaseStatus = FirebaseDatabase.getInstance().getReference().child("MyUsers").child(current_user);

        mDialogue = new ProgressDialog(StatusActivity.this);

        mStatus = (TextInputLayout)findViewById(R.id.your_status);
        mBtn = (Button)findViewById(R.id.save_btn);

        //get old status
        String status_value = getIntent().getStringExtra("status_value");
        mStatus.getEditText().setText(status_value);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDialogue.setTitle("Changing Your Status");
                mDialogue.setMessage("Please Wait...");
                mDialogue.show();
                String status = mStatus.getEditText().getText().toString();
                mDatabaseStatus.child("status").setValue(status).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            //if the task is complete then just remove the dialogue
                            mDialogue.dismiss();
                        }
                        else{
                            Toast.makeText(StatusActivity.this,"There are some error",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
}
