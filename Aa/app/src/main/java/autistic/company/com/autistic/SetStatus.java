package autistic.company.com.autistic;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class SetStatus extends AppCompatActivity {

    private Button st;
    private TextToSpeech tt;
    private  Button btn_auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_status);

        st=(Button)findViewById(R.id.buttonSt);
        btn_auth=(Button)findViewById(R.id.buttonA);


        btn_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SetStatus.this, SignIn.class);
                startActivity(i);
            }
        });

        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SetStatus.this,StudentSignIn.class);
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


    private void speakText(String st){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tt.speak(st, TextToSpeech.QUEUE_FLUSH,null,null);

        } else {
            tt.speak(st, TextToSpeech.QUEUE_FLUSH, null);

        }
    }


}
