package be.thomasmore.projectmobiledevelopment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import be.thomasmore.projectmobiledevelopment.MainActivity;
import be.thomasmore.projectmobiledevelopment.R;

public class SessieEinde extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessie_einde);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClickVerder(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
