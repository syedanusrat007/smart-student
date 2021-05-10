package autistic.company.com.autistic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StudentSignIn extends AppCompatActivity {

    private Button btn_logIn, btn_New;
    private TextToSpeech tt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_in);

        btn_logIn=(Button)findViewById(R.id.buttonLogIn);
        btn_New=(Button)findViewById(R.id.buttonNew);

        btn_New.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(StudentSignIn.this,Department.class);
                startActivity(i);
            }
        });


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 1 seconds

                tt=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {

                        if(status != TextToSpeech.ERROR){
                            tt.setLanguage(Locale.UK);
                            String st1="Press  up  to log in";
                            speakText(st1);

                        }
                    }
                });

            }
        }, 50);
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 1 seconds

                tt=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {

                        if(status != TextToSpeech.ERROR){
                            tt.setLanguage(Locale.UK);
                            String st1="below for new account";
                            speakText(st1);
                        }

                    }
                });

            }
        }, 1000);

    }
    ///////////////

    private void speakText(String st){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tt.speak(st,TextToSpeech.QUEUE_FLUSH,null,null);

        } else {
            tt.speak(st, TextToSpeech.QUEUE_FLUSH, null);

        }
    }


}
