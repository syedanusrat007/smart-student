package com.cse.mist.audio_upload_download;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button btnlogin,btnreg;
    EditText etemail,etpass;
    private ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnreg = (Button) findViewById(R.id.bt_reg);
        btnlogin = (Button) findViewById(R.id.bt_login);
        etemail = (EditText) findViewById(R.id.et_email);
        etpass = (EditText) findViewById(R.id.et_pass);


        firebaseAuth = FirebaseAuth.getInstance();

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SingIn();
            }
        });
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() !=null) {
                    startActivity(new Intent(MainActivity.this, WelcomeTab.class));

                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(authStateListener);

    }

    private void SingIn() {


        String Memail,Mpass;

        Memail = etemail.getText().toString().trim();
        Mpass = etpass.getText().toString().trim();

        if (TextUtils.isEmpty(Memail)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(Mpass)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }



        firebaseAuth.signInWithEmailAndPassword(Memail,Mpass).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Successful.", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "not Successful.", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    }

