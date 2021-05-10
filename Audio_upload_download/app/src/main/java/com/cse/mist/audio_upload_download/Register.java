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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText etemail,etpass;
    DatabaseReference datauser;
    Button btreg;
    FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etemail = (EditText) findViewById(R.id.et_remail);
        etpass = (EditText) findViewById(R.id.et_rpass);
        btreg = (Button) findViewById(R.id.btn_reg);
        datauser = FirebaseDatabase.getInstance().getReference("User");

        firebaseAuth = FirebaseAuth.getInstance();

        btreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usereg();
            }

        });
    }

    private void Usereg() {

        final String email,pass;

        email = etemail.getText().toString().trim();
        pass = etpass.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }





        /*firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(Register.this, "Successful.", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Register.this, "not Successful.", Toast.LENGTH_LONG).show();
                }

            }
        });*/

        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        String mail = etemail.getText().toString().trim();
                        String pass = etpass.getText().toString().trim();


                        String id = new ParseEmail().Parse(email);
                        User use = new User( mail, pass);
                        datauser.child(id).setValue(use);
                        Toast.makeText(Register.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(Register.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(Register.this, WelcomeTab.class));
                            finish();
                        }
                    }
                });

    }


    public void LoginPage(View view) {

        startActivity(new Intent(Register.this, MainActivity.class));
    }
}
