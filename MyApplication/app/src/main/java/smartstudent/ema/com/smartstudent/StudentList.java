package smartstudent.ema.com.smartstudent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentList extends AppCompatActivity {

    private Button btnSet;
    int numOfSub;
    private EditText edt_level,edt_term, edt_num_sub;
    private String department,batch,eid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        btnSet=(Button)findViewById(R.id.buttonSet);
        edt_level=(EditText)findViewById(R.id.editLevel);
        edt_term=(EditText)findViewById(R.id.editTerm);
        edt_num_sub=(EditText)findViewById(R.id.editSub);

       /* Intent i= getIntent();
        eid=i.getStringExtra("eid"); */

        // numOfsubj.setText(String.format("%d",value));

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                department= edt_level.getText().toString().trim();
                batch= edt_term.getText().toString().trim();
                numOfSub= Integer.valueOf(edt_num_sub.getText().toString().trim() );

                Intent intent = new Intent(StudentList.this, Loop.class);
                intent.putExtra("ID", numOfSub);

                intent.putExtra("level", department);
                intent.putExtra("term", batch);
                intent.putExtra("flag","student");
                //intent.putExtra("eid",eid);
                startActivity(intent);
            }
        });


    }


}


