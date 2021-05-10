package smartstudent.ema.com.smartstudent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class TeacherProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);
    }

    public boolean onCreateOptionsMenu(Menu manu){
        getMenuInflater().inflate(R.menu.teacher_action_bar,manu);
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.log_out){
            FirebaseAuth.getInstance().signOut();
            Intent intent=new Intent(TeacherProfile.this,SetStatus.class);
            //intent.putExtra("role","tec");
            startActivity(intent);
            finish();
            return true;
        }
        return true;
    }


}
