package smartstudent.ema.com.smartstudent;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Loop extends AppCompatActivity {

    private TextView tx2;
    EditText edit_subj;
    Button btn_next;
    private   int i=1, showSub;
    String level, term,eid, flag,dep;


    DatabaseReference fRootRef;
    // DatabaseReference fRootRefChild = fRootRef.child(String.format("%d",i));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop);
        tx2=(TextView)findViewById(R.id.textView);


        Intent mIntent = getIntent();
        final int numOfSub = mIntent.getIntExtra("ID", 0);
        flag= mIntent.getStringExtra("flag");
        level= mIntent.getStringExtra("level");
        term= mIntent.getStringExtra("term");
        dep=mIntent.getStringExtra("dep");
        //eid=mIntent.getStringExtra("eid");

        showSub = numOfSub;


        tx2.setText(String.format("%d", showSub));

        edit_subj=(EditText)findViewById(R.id.editTextSubj);
        btn_next=(Button)findViewById(R.id.buttonNext);


        if(flag.equals("subject")) {

            String lv= "level"+level+" term"+term;

            fRootRef = FirebaseDatabase.getInstance().getReference().child("Auth").child(lv).child(dep);

        }

        else if(flag.equals("student")) {

            String trm="batch "+term;
            fRootRef = FirebaseDatabase.getInstance().getReference().child("Auth").child("department").child(level).child(trm);

        }



        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // fRootRef = FirebaseDatabase.getInstance().getReference(String.format("%d",i));

                if(i<numOfSub) {

                    String subName =edit_subj.getText().toString();
                    String Id = fRootRef.push().getKey();
                    fRootRef.child(Id).setValue(subName);

                    edit_subj.setText("");
                    i++;
                    showSub--;
                }
                else if(i==numOfSub){

                    String subName =edit_subj.getText().toString();
                    String Id = fRootRef.push().getKey();
                    fRootRef.child(Id).setValue(subName);
                    // fRootRef.setValue(eid);

                    edit_subj.setText("               FINISH ");
                    edit_subj.setTextColor(Color.parseColor("#0000FF"));
                    btn_next.setText("Ok");
                    i++;


                }
                else{

                    if(flag.equals("subject")) {
                        Toast.makeText(Loop.this,"Subjects are inserted into database",Toast.LENGTH_SHORT).show();
                        Intent i= new Intent(Loop.this,AutorityProfile.class);
                        startActivity(i);
                    }
                    else{

                        Toast.makeText(Loop.this,"Students are inserted into database",Toast.LENGTH_SHORT).show();
                        Intent i= new Intent(Loop.this,AutorityProfile.class);
                        startActivity(i);
                    }


                }

            }
        });




    }

}

