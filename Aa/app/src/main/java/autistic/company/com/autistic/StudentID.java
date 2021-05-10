package autistic.company.com.autistic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class StudentID extends AppCompatActivity {

    private TextToSpeech tt;
    String id1, id2;
    DatabaseReference dbId;

    private FirebaseAuth firebaseAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_id);

        firebaseAuth = FirebaseAuth.getInstance();
        dbId = FirebaseDatabase.getInstance().getReference("Auth").child("department");

    }
//////////////////////

    private void speakText(String st){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tt.speak(st,TextToSpeech.QUEUE_FLUSH,null,null);

        } else {
            tt.speak(st, TextToSpeech.QUEUE_FLUSH, null);

        }
    }
    /////////////
}
