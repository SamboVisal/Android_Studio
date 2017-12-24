package com.example.sambovisal.minozchat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;

public class  SettingsActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseUser mCurrentUser;
    private static final int GALLERY_PICK=1;
    //layout
    private TextView mDisplayName;
    private CircleImageView mDisplayImage;
    private TextView mDisplayStatus;

    private Bitmap thumnail;

    //storage
    private StorageReference imageReference;

    private ProgressDialog mProgress;

  //  private String download_url;

    private Button status_btn;
    private Button image_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        //pointing to user id
        String user_id = mCurrentUser.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("MyUsers").child(user_id);

        imageReference = FirebaseStorage.getInstance().getReference();

        mDisplayName = (TextView)findViewById(R.id.setting_display_name);
        mDisplayStatus = (TextView)findViewById(R.id.setting_status);
        mDisplayImage = (CircleImageView)findViewById(R.id.dis_image);
        status_btn = (Button)findViewById(R.id.status_btn);
        image_btn = (Button) findViewById(R.id.change_image);

        mDatabase.keepSynced(true);
        //to set value
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               // Toast.makeText(SettingsActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();
                String name = dataSnapshot.child("user").getValue().toString();
                final String image = dataSnapshot.child("image").getValue().toString();
                String status = dataSnapshot.child("status").getValue().toString();
                String thumnail = dataSnapshot.child("thump_image").getValue().toString();
                mDisplayName.setText(name);
                mDisplayStatus.setText(status);

                if(!image.equals("default")){

                    //this will load the image on the screen
                    Picasso.with(SettingsActivity.this).load(image).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.default_avatar)
                            .into(mDisplayImage, new Callback() {
                                @Override
                                public void onSuccess() {

                                }

                                @Override
                                public void onError() {

                                    Picasso.with(SettingsActivity.this).load(image).placeholder(R.drawable.default_avatar).into(mDisplayImage);


                                }
                            });

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        status_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status_value= mDisplayStatus.getText().toString();
                Intent i = new Intent(SettingsActivity.this,StatusActivity.class);
                i.putExtra("status_value",status_value);
                startActivity(i);
            }
        });
        image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gallery_intent = new Intent();
                 gallery_intent.setType("image/*");
                gallery_intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery_intent,"Select Image"),GALLERY_PICK);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==GALLERY_PICK && resultCode== RESULT_OK){
            //String imageUi = data.getDataString();
            //Toast.makeText(SettingsActivity.this,imageUi,Toast.LENGTH_LONG).show();
            Uri imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setAspectRatio(1,1)
                    .setMinCropWindowSize(500,500)
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                mProgress = new ProgressDialog(SettingsActivity.this);
                mProgress.setTitle("Uploading image...");
                mProgress.setMessage("Please wait");
                mProgress.setCanceledOnTouchOutside(false);
                mProgress.show();


                Uri resultUri = result.getUri();

                File thump_path = new File(resultUri.getPath());

                //get current user id to store image as the id of the user
                String current_user_id = mCurrentUser.getUid();

                try {
                     thumnail = new Compressor(this)
                            .setMaxWidth(200)
                            .setMaxHeight(200)
                            .setQuality(75)
                            .compressToBitmap(thump_path);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                thumnail.compress(Bitmap.CompressFormat.JPEG,100,baos);
                final byte[] thumnail = baos.toByteArray();


                StorageReference filepath = imageReference.child("profile_images").child(current_user_id +" .jpg");
                final StorageReference thumb_filepath = imageReference.child("profile_images").child("thumbs").child(current_user_id);


                filepath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()){

                            //store image download in database
                            Toast.makeText(SettingsActivity.this,"Working",Toast.LENGTH_LONG).show();
                            @SuppressWarnings("VisibleForTests") final
                            String download_url = task.getResult().getDownloadUrl().toString();
                            UploadTask uploadTask = thumb_filepath.putBytes(thumnail);
                            uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                @SuppressWarnings("VisibleForTests")
                                @Override
                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> thumb_task) {

                                    final String thumb_download = thumb_task.getResult().getDownloadUrl().toString();

                                      if (thumb_task.isSuccessful()){

                                          Map update_hashMap = new HashMap<>();
                                          update_hashMap.put("image",download_url);
                                          update_hashMap.put("thump_image",thumb_download);

                                          //this will point to child image and set value to it and updateChildren
                                          mDatabase.updateChildren(update_hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                              @Override
                                              public void onComplete(@NonNull Task<Void> task) {
                                                  if(task.isSuccessful()){
                                                      mProgress.dismiss();
                                                      Toast.makeText(SettingsActivity.this,"Success Uploading",Toast.LENGTH_LONG).show();
                                                  }
                                              }
                                          });

                                      }else{
                                          Toast.makeText(SettingsActivity.this,"error",Toast.LENGTH_LONG).show();
                                          mProgress.dismiss();
                                      }
                                }
                            });



                        }
                        else{
                            Toast.makeText(SettingsActivity.this,"error",Toast.LENGTH_LONG).show();
                            mProgress.dismiss();
                        }
                    }
                });
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();

            }
        }
    }
    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(10);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }
}
