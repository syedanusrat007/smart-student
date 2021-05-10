package smartstudent.ema.com.smartstudent;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class SetStatus extends AppCompatActivity implements View.OnClickListener{

    private TextToSpeech tt;
    private Button btn_st, btn_auth, btn_tec;

    DatabaseReference dbAuth, dbSt,dbTec;
    private FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String useremail, status;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_status);

        btn_st=(Button)findViewById(R.id.buttonSt);
        btn_auth=(Button)findViewById(R.id.buttonA);
        btn_tec=(Button)findViewById(R.id.buttonT);

       firebaseAuth = FirebaseAuth.getInstance();
        dbAuth = FirebaseDatabase.getInstance().getReference("Auth");
        dbTec = FirebaseDatabase.getInstance().getReference("Tec");

        if (firebaseAuth.getCurrentUser() != null) {

            user = FirebaseAuth.getInstance().getCurrentUser();
            useremail = user.getEmail();

        }
        else {
            status="no";
            useremail="no";
        }



        btn_st.setOnClickListener(this);
        btn_auth.setOnClickListener(this);
        btn_tec.setOnClickListener(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 1 seconds

                tt=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {

                        if(status != TextToSpeech.ERROR){
                            tt.setLanguage(Locale.UK);
                            String st1="Select your role";
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
                            String st1="Press  up  for teacher";
                            speakText(st1);

                        }
                    }
                });

            }
        }, 300);
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 1 seconds

                tt=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {

                        if(status != TextToSpeech.ERROR){
                            tt.setLanguage(Locale.UK);
                            String st1="middle for authority";
                            speakText(st1);

                        }
                    }
                });

            }
        }, 1000);
        handler.postDelayed(new Runnable() {
            public void run() {

                tt=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {

                        if(status != TextToSpeech.ERROR){
                            tt.setLanguage(Locale.UK);
                            String st1="below  for student";
                            speakText(st1);

                        }
                    }
                });

            }
        }, 1500);


    }

////////////////
    protected void onResume() {
        super.onResume();
    }
    //--------//
    protected void onStart() {
        super.onStart();
        dbAuth.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String authKey=snapshot.getKey();
                    AuthNode authority = snapshot.getValue(AuthNode.class);
                    String authMail=authority.getEmail();

                    if(useremail.equals(authMail)){
                        status= authority.getStatus();
                    }
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //-------//

        dbTec.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String tecKey=snapshot.getKey();
                    TecNode tec = snapshot.getValue(TecNode.class);
                    String tecMail=tec.getEmail();

                    if(useremail.equals(tecMail)){
                        status= tec.getStatus();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    //////////////////////////


    private void speakText(String st){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tt.speak(st, TextToSpeech.QUEUE_FLUSH,null,null);

        } else {
            tt.speak(st, TextToSpeech.QUEUE_FLUSH, null);

        }
    }
    ///////////////////


    @Override
    public void onClick(View view) {

        if(view==btn_st){
            Intent i = new Intent(SetStatus.this,StudentSignIn.class);
            startActivity(i);
        }
        if(view==btn_auth){

            if( (status.equals("Auth")) || (status.equals("no")) ){
                Intent i = new Intent(SetStatus.this,SignIn.class);
                i.putExtra("role","auth");
                startActivity(i);
                finish();
            }
            else {
                Toast.makeText(SetStatus.this,"Please log out from other profile",Toast.LENGTH_SHORT).show();
            }

        }
        if(view==btn_tec){

            if( (status.equals("Tec")) || (status.equals("no")) ){

                Intent i = new Intent(SetStatus.this,SignIn.class);
                i.putExtra("role","tec");
                startActivity(i);
                finish();
            }
            else {
                Toast.makeText(SetStatus.this,"Please log out from other profile",Toast.LENGTH_SHORT).show();
            }

        }

    }


}

