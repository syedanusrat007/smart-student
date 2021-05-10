package smartstudent.ema.com.smartstudent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    DatabaseReference dbAuth, dbTec, dbSt;
    private FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String useremail;
    String krishokKey;

    private TextView tv1;
    Intent g;
    String role;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        dbAuth = FirebaseDatabase.getInstance().getReference("Auth");
        dbTec = FirebaseDatabase.getInstance().getReference("Tec");


        tv1 = (TextView) findViewById(R.id.tv);

        if (firebaseAuth.getCurrentUser() != null){
            user=FirebaseAuth.getInstance().getCurrentUser();
            useremail = user.getEmail();

            tv1.setText(useremail);
        }

        g= getIntent();
        role=g.getStringExtra("role");

    }

    protected  void onResume(){
        super.onResume();


    }

    protected void onStart() {
        super.onStart();

        dbAuth.addValueEventListener(new ValueEventListener() {
            Intent intentK = new Intent(Profile.this,AutorityProfile.class);

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String authKey=snapshot.getKey();
                    AuthNode krishok = snapshot.getValue(AuthNode.class);
                    String krishokMail=krishok.getEmail();

                    if(useremail.equals(krishokMail)){
                        tv1.setText(krishokMail);
                        //intentK.putExtra("keyy",krishokKey);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {

                                if(role.equals("auth")) {
                                    startActivity(intentK);
                                    finish();
                                }
                                else{
                                    FirebaseAuth.getInstance().signOut();
                                    Toast.makeText(Profile.this, "Select role properly", Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(Profile.this,SetStatus.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }, 200);
                    }
                }



            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /////////////////////////////////////////////
        dbTec.addValueEventListener(new ValueEventListener() {
            Intent intentK = new Intent(Profile.this,TeacherProfile.class);

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    krishokKey=snapshot.getKey();
                    TecNode krishok = snapshot.getValue(TecNode.class);
                    String krishokMail=krishok.getEmail();

                    if(useremail.equals(krishokMail)){
                        tv1.setText(krishokMail);
                        //intentK.putExtra("keyy",krishokKey);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                if(role.equals("tec")) {
                                    startActivity(intentK);
                                    finish();
                                }
                                else{
                                    FirebaseAuth.getInstance().signOut();
                                    Toast.makeText(Profile.this, "Select role properly", Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(Profile.this,SetStatus.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }, 200);
                    }
                }



            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}
