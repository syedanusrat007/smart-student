package smartstudent.ema.com.smartstudent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegAuth extends AppCompatActivity {

    private DatabaseReference db;
    private ProgressDialog dlg;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference dbAuth, dbTec;

    private EditText edt_code;
    private Button btn_reg;
    int code;
    String nm,eid, phn, pass, email,role;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_auth);


        edt_code=(EditText)findViewById(R.id.editTextV);
        btn_reg=(Button)findViewById(R.id.buttonReg);

        Intent j = getIntent();
        nm = j.getStringExtra("nm");
        eid = j.getStringExtra("eid");
        phn = j.getStringExtra("phn");
        pass = j.getStringExtra("pass");
        email = j.getStringExtra("email");
        code = j.getIntExtra("code",0);

        role=j.getStringExtra("role");

        dlg= new ProgressDialog(this);
        firebaseAuth= FirebaseAuth.getInstance();

        dbAuth= FirebaseDatabase.getInstance().getReference().child("Auth");
        dbTec= FirebaseDatabase.getInstance().getReference().child("Tec");

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Register();
            }
        });

    }

    private void Register() {

        String cd = edt_code.getText().toString().trim();
        String prevCd = String.valueOf(code);

        if (cd.equals(prevCd)) {

            dlg.setMessage("Creating user...");
            dlg.setCanceledOnTouchOutside(false);
            dlg.show();

            //now we can create the user
            firebaseAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegAuth.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                dlg.hide();

                                if(role.equals("auth")) {
                                    AddAuth();
                                    Intent i= new Intent(RegAuth.this,SignIn.class);
                                    // i.putExtra("eid",eid);
                                    i.putExtra("role",role);
                                    startActivity(i);
                                }
                                if(role.equals("tec")) {
                                    AddTec();
                                    Intent i= new Intent(RegAuth.this,SignIn.class);
                                    // i.putExtra("eid",eid);
                                    i.putExtra("role",role);
                                    startActivity(i);
                                }

                            } else {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    Toast.makeText(RegAuth.this, "Email Exists !", Toast.LENGTH_SHORT).show();
                                }
                                Toast.makeText(RegAuth.this,"Registration Failed!  " + task.getException().getMessage(), //ADD THIS
                                        Toast.LENGTH_LONG).show();
                                dlg.hide();

                            }
                        }
                    });

        }
        else {
            Toast.makeText(RegAuth.this,"Invalid Code",Toast.LENGTH_SHORT).show();
        }

    }

    private  void AddAuth() {

        String Id = dbAuth.push().getKey();
        String stat="Auth";

        AuthNode authority = new AuthNode(nm,eid,phn,pass,stat,email);

        dbAuth.child(Id).setValue(authority);

    }

    private  void AddTec() {

        String Id = dbTec.push().getKey();
        String stat="Tec";

        TecNode tec = new TecNode(nm,eid,phn,pass,stat,email);

        dbTec.child(Id).setValue(tec);

    }

}

