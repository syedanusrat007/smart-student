package autistic.company.com.autistic;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Flushable;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

   private TextView tv1, tv2;
   private TextToSpeech tt;
   private String st1,st2,st3;
    LinearLayout lin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1=(TextView)findViewById(R.id.textViewWelcome);
        tv2=(TextView)findViewById(R.id.textViewDigLec);
        lin=(LinearLayout)findViewById(R.id.linFirst);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 1 seconds

                tt=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {

                        if(status != TextToSpeech.ERROR){
                            tt.setLanguage(Locale.UK);
                            String st1=tv1.getText().toString();
                            String st2=tv2.getText().toString();
                            String st3 = st1+" "+st2;
                            speakText(st3);

                        }


                    }
                });

            }
        }, 1000);

        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 10 seconds
                Intent i = new Intent(MainActivity.this,SetStatus.class);
                startActivity(i);


            }
        }, 5000);


        lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,SetStatus.class);
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
