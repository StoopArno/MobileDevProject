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

}
