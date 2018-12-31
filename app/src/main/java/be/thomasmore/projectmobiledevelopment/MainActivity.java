package be.thomasmore.projectmobiledevelopment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.nio.channels.OverlappingFileLockException;

import be.thomasmore.projectmobiledevelopment.activities.LijstKinderen;
import be.thomasmore.projectmobiledevelopment.activities.ActivityMeting;
import be.thomasmore.projectmobiledevelopment.activities.Oef62;
import be.thomasmore.projectmobiledevelopment.activities.OverzichtLijstKinderen;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper db;

    //temporary
    public void onClickKinderen(View view){
        Intent intent = new Intent(this, LijstKinderen.class);
        startActivity(intent);
    }

    public void onClickMeting(View view){
        Intent intent = new Intent(this, Oef62.class);
        startActivity(intent);
    }

    public void onClickOverzicht(View view){
        Intent intent = new Intent(this, OverzichtLijstKinderen.class);
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
