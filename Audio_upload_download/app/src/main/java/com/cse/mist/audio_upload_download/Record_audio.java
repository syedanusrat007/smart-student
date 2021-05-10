package com.cse.mist.audio_upload_download;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Record_audio extends AppCompatActivity {

    ImageButton btrecd;
    TextView tvrcd;
    String audiopath;
    Boolean recording=false;
    MediaRecorder mediarecorder;
    StorageReference srAudio;
    DatabaseReference drAudio,druser;
    FirebaseAuth mauth;
    FirebaseUser fuser;
    Random random ;
    String RandomAudioFileName = "ABCDEFGHIJKLMNOP";


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_audio);


        btrecd = (ImageButton) findViewById(R.id.btn_rcd);
        random = new Random();

        tvrcd =(TextView) findViewById(R.id.tv_recordtxt);

        audiopath = Environment.getExternalStorageDirectory().getAbsolutePath()+  "/" +CreateRandomAudioFileName(5) + "NewRecord.mp3";

        mauth = FirebaseAuth.getInstance();
        fuser = mauth.getCurrentUser();

        srAudio = FirebaseStorage.getInstance().getReference();
        drAudio = FirebaseDatabase.getInstance().getReference().child("Audio");
        druser = FirebaseDatabase.getInstance().getReference().child("User").child(fuser.getUid());

        btrecd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()== motionEvent.ACTION_DOWN) {
                    RecordAudio();
                    tvrcd.setText("Record Started");
                }

                else
                if(motionEvent.getAction()== motionEvent.ACTION_UP) {
                    stopAudio();
                    tvrcd.setText("Record Stopped");
                }
                return false;
            }
        });



    }

    private String CreateRandomAudioFileName(int a ) {
        StringBuilder stringBuilder = new StringBuilder( a );
        int i = 0 ;
        while(i < a ) {
            stringBuilder.append(RandomAudioFileName.
                    charAt(random.nextInt(RandomAudioFileName.length())));

            i++ ;
        }
        return stringBuilder.toString();
    }


    public void RecordAudio(){

        recording = true;

        try{
           mediarecorder = new MediaRecorder();
           mediarecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
           mediarecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
           mediarecorder.setOutputFile(audiopath);
           mediarecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
           mediarecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediarecorder.start();
    }

    public  void stopAudio()
    {

        try{
            mediarecorder.stop();
        }catch(RuntimeException stopException){
            mediarecorder.release();
            mediarecorder = null;
        }


       uploadAudio();
    }

    private void uploadAudio() {
        Uri uri = Uri.fromFile(new File(audiopath));
        StorageReference filepath=srAudio.child("Audio").child(uri.getLastPathSegment());
        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                final DatabaseReference new_post= drAudio.push();

               druser.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                         new_post.child("Audiotitle").setValue(audiopath);
                         new_post.child("Userid").setValue(fuser.getUid());
                         new_post.child("Username").setValue(dataSnapshot.child("email").getValue()).
                                 addOnCompleteListener(new OnCompleteListener<Void>() {
                                     @Override
                                     public void onComplete(@NonNull Task<Void> task) {
                                        startActivity(new Intent(Record_audio.this, WelcomeTab.class));


                                     }
                                 });
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });

            }
        });

    }

}
