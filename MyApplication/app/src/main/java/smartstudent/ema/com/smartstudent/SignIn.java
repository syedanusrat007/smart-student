package smartstudent.ema.com.smartstudent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {

    private TextView txt_newAc;
    private Button btn_logIn;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private EditText edt_email, edt_pass;
    private ProgressDialog progressDialog;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        txt_newAc = (TextView) findViewById(R.id.textViewNewAc);
        btn_logIn = (Button) findViewById(R.id.buttonLogIn);
        edt_email = (EditText) findViewById(R.id.editTextEm);
        edt_pass = (EditText) findViewById(R.id.editTextPass);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        Intent g= getIntent();
        role=g.getStringExtra("role");

        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            user = FirebaseAuth.getInstance().getCurrentUser();
            String userStat = user.getEmail();

            if(role.equals("auth")) {
                startActivity(new Intent(getApplicationContext(), AutorityProfile.class));
            }
            if(role.equals("tec")) {
                startActivity(new Intent(getApplicationContext(), TeacherProfile.class));
            }

        }

        txt_newAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(role.equals("auth")) {
                    Intent i = new Intent(SignIn.this, SignUpAuth.class);
                    i.putExtra("role",role);
                    startActivity(i);
                }
                if(role.equals("tec")) {
                    Intent i = new Intent(SignIn.this, SignUpTec.class);
                    i.putExtra("role",role);
                    startActivity(i);
                }


            }
        });
        btn_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserLogin();
            }
        });
    }


    private void UserLogin() {

        String email = edt_email.getText().toString().trim();
        String password = edt_pass.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Loggin in..");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.hide();
                            finish();
                            Intent i= new Intent(SignIn.this, Profile.class);
                            i.putExtra("role",role);
                            startActivity(i);

                        } else {
                            Toast.makeText(SignIn.this, "Wrong Email or Password!! ", Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                        }
                    }
                });
    }



}

