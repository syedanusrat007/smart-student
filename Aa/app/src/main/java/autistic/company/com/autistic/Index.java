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

public class Index extends AppCompatActivity {


    private  Button bt_others,record;
    private TextToSpeech tt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        bt_others=(Button)findViewById(R.id.buttonNew);
        record=(Button)findViewById(R.id.buttonRec);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 1 seconds

                tt=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {

                        if(status != TextToSpeech.ERROR){
                            tt.setLanguage(Locale.UK);
                            String st1="Press  up  to record";
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
                            String st1="middle to play";
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
                            String st1="below  for others";
                            speakText(st1);

                        }


                    }
                });

            }
        }, 1500);





        bt_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Index.this,StudentsOthers.class);
                startActivity(i);

            }
        });




    }



    private void speakText(String st){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tt.speak(st,TextToSpeech.QUEUE_FLUSH,null,null);

        } else {
            tt.speak(st, TextToSpeech.QUEUE_FLUSH, null);

        }
    }



}
