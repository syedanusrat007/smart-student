package smartstudent.ema.com.smartstudent;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class SignUpAuth extends AppCompatActivity {

    private EditText edt_nm, edt_eid, edt_email, edt_pass, edt_con_pass, edt_phn;
    private Button btn_signUp;

    private int code;
    private String nm,eid,email,pass,phn,conPass,role;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_auth);

        edt_nm=(EditText)findViewById(R.id.editTextName);
        edt_eid=(EditText)findViewById(R.id.editTextEmpId);
        edt_email=(EditText)findViewById(R.id.editTextEmail);
        edt_pass=(EditText)findViewById(R.id.editTextP);
        edt_con_pass=(EditText)findViewById(R.id.editTextCP);
        edt_phn=(EditText)findViewById(R.id.editTextPhn);

        Intent g= getIntent();
        role= g.getStringExtra("role");


        btn_signUp=(Button)findViewById(R.id.buttonSignUp);

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerifyUser();
            }
        });

        Random rand = new Random();
        code  = rand.nextInt(3000) + 1000;

    }

    private void VerifyUser(){

        nm= edt_nm.getText().toString().trim();
        eid= edt_eid.getText().toString().trim();
        email= edt_email.getText().toString().trim();
        pass= edt_pass.getText().toString().trim();
        conPass= edt_con_pass.getText().toString().trim();
        phn= edt_phn.getText().toString().trim();

        if(TextUtils.isEmpty(nm)){
            Toast.makeText(SignUpAuth.this, "Enter a name",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(eid)){
            Toast.makeText(SignUpAuth.this, "Enter employee ID",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(email)){
            Toast.makeText(SignUpAuth.this, "Enter email address",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(pass)){
            Toast.makeText(SignUpAuth.this, "Enter a password",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(conPass)){
            Toast.makeText(SignUpAuth.this, "Please reconfirm the password",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(phn)){
            Toast.makeText(SignUpAuth.this, "Enter a phone number",Toast.LENGTH_SHORT).show();
            return;
        }

        if(pass.length()<5){
            Toast.makeText(SignUpAuth.this, "password length should be greated than 5",Toast.LENGTH_SHORT).show();
            return;
        }

        if (!(pass.equals(conPass))){
            Toast.makeText(SignUpAuth.this, "password doesn't match",Toast.LENGTH_SHORT).show();
            return;
        }

        SendSms();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 1 seconds

                Intent intent = new Intent(SignUpAuth.this, RegAuth.class);
                intent.putExtra("nm",nm);
                intent.putExtra("eid",eid);
                intent.putExtra("phn",phn);
                intent.putExtra("email",email);
                intent.putExtra("pass",pass);
                intent.putExtra("code",code);
                intent.putExtra("role",role);

                startActivity(intent);


            }
        }, 100);


    }

    private  void SendSms() {

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phn,null, String.valueOf(code),null,null);
        Toast.makeText(getApplicationContext(),"A verification code is send to phone", Toast.LENGTH_LONG).show();

    }

}

