package smartstudent.ema.com.smartstudent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewTerm extends AppCompatActivity {

    private Button btnSet;
    int numOfSub;
    private EditText edt_level,edt_term, edt_num_sub, edt_dep;
    String level,term,eid, deparment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_term);

        btnSet=(Button)findViewById(R.id.buttonSet);
        edt_level=(EditText)findViewById(R.id.editLevel);
        edt_term=(EditText)findViewById(R.id.editTerm);
        edt_num_sub=(EditText)findViewById(R.id.editSub);
        edt_dep=(EditText)findViewById(R.id.editDep);

               /* Intent i= getIntent();
        eid=i.getStringExtra("eid"); */

        // numOfsubj.setText(String.format("%d",value));

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                level= edt_level.getText().toString().trim();
                term= edt_term.getText().toString().trim();
                deparment=edt_dep.getText().toString().trim();
                numOfSub= Integer.valueOf(edt_num_sub.getText().toString().trim() );

                Intent intent = new Intent(NewTerm.this, Loop.class);
                intent.putExtra("ID", numOfSub);

                intent.putExtra("level", level);
                intent.putExtra("term", term);
                intent.putExtra("dep",deparment);
                intent.putExtra("flag","subject");
                //intent.putExtra("eid",eid);
                startActivity(intent);

            }
        });

    }



}






