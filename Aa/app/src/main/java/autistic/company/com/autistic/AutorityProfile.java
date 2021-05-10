package autistic.company.com.autistic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class AutorityProfile extends AppCompatActivity {

    String eid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autority_profile);

       /* Intent j= getIntent();
        eid=j.getStringExtra("eid"); */



    }

    public boolean onCreateOptionsMenu(Menu manu){
        getMenuInflater().inflate(R.menu.authority_action_bar,manu);
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.log_out){
            FirebaseAuth.getInstance().signOut();
            Intent intent=new Intent(AutorityProfile.this,SignIn.class);
            startActivity(intent);
            finish();
            return true;
        }
        else if (id==R.id.term){
            Intent intent=new Intent(AutorityProfile.this,NewTerm.class);
            //intent.putExtra("eid",eid);
            startActivity(intent);
            return true;
        }
        else if (id==R.id.showSub){
            Intent intent=new Intent(AutorityProfile.this,ShowSubjects.class);
            startActivity(intent);
            return true;
        }
        else if (id==R.id.student_list){
            Intent intent=new Intent(AutorityProfile.this,StudentList.class);
            //intent.putExtra("eid",eid);
            startActivity(intent);
            return true;
        }
        return true;
    }
}
