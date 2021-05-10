package autistic.company.com.autistic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {

    private TextView tv1;

    DatabaseReference dbAuth, dbSt,dbTeacher;

    List<AuthNode> authList;

    private ListView listAuth;
    private FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String useremail;
    private ProgressDialog progressDialog;
    String authKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        dbAuth = FirebaseDatabase.getInstance().getReference("Auth");

        tv1 = (TextView) findViewById(R.id.textView7);
        progressDialog = new ProgressDialog(this);


        if (firebaseAuth.getCurrentUser() != null) {
            user = FirebaseAuth.getInstance().getCurrentUser();
            useremail = user.getEmail();

            tv1.setText(useremail);
        }

        authList = new ArrayList<>();

    }

    protected void onResume() {
        super.onResume();


    }

    protected void onStart() {
        super.onStart();

    //////////////////////////////////////////////
        dbAuth.addValueEventListener(new ValueEventListener() {
            Intent intentK = new Intent(Profile.this,AutorityProfile.class);

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                authList.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    authKey=snapshot.getKey();
                    AuthNode authority = snapshot.child("Information").getValue(AuthNode.class);
                    String authMail=authority.getEmail();

                    if(useremail.equals(authMail)){
                        authList.add(authority);
                        tv1.setText(authMail);

                        intentK.putExtra("nm",authority.getNm());
                        intentK.putExtra("eid",authority.getEid());
                        intentK.putExtra("pass",authority.getPass());
                        intentK.putExtra("email",authority.getEmail());
                        intentK.putExtra("phn",authority.getPhn());

                        intentK.putExtra("keyy",authKey);


                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                // Actions to do after 1 seconds

                                startActivity(intentK);
                                finish();

                            }
                        }, 100);
                    }
                }


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //////////////////////////////////////////////////////





    }
}
