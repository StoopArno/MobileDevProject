package be.thomasmore.projectmobiledevelopment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import be.thomasmore.projectmobiledevelopment.activities.ActivityKinderen;
import be.thomasmore.projectmobiledevelopment.activities.ActivityMeting;
import be.thomasmore.projectmobiledevelopment.dataservices.GroepDataService;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper db;

    //temporary
    public void onClickKinderen(View view){
        Intent intent = new Intent(this, ActivityKinderen.class);
        startActivity(intent);
    }

    public void onClickMeting(View view){
        Intent intent = new Intent(this, ActivityMeting.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
