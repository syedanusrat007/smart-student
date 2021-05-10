package smartstudent.ema.com.smartstudent;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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

        lin.setOnClickListener(this);

    }



    private void speakText(String st){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tt.speak(st,TextToSpeech.QUEUE_FLUSH,null,null);

        } else {
            tt.speak(st, TextToSpeech.QUEUE_FLUSH, null);

        }
    }


    @Override
    public void onClick(View view) {

        if(view==lin){

            Intent i = new Intent(MainActivity.this, SetStatus.class);
            startActivity(i);
        }

    }
}
