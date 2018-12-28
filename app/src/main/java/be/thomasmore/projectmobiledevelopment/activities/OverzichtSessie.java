package be.thomasmore.projectmobiledevelopment.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import be.thomasmore.projectmobiledevelopment.R;
import be.thomasmore.projectmobiledevelopment.dataservices.MetingDataService;
import be.thomasmore.projectmobiledevelopment.models.Meting;

public class OverzichtSessie extends AppCompatActivity {

    private static long sessieId;
    private MetingDataService metingDataService = new MetingDataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overzicht_sessie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sessieId = getIntent().getLongExtra("sessieId", 1);
    }

    private void vulMetingen(){
        Meting voormeting = metingDataService.MetingWhereSessieId(1, sessieId);
        Meting nameting = metingDataService.MetingWhereSessieId(2, sessieId);

        //TextView voormetingScore = (TextView) findViewById(R.id.over)
    }



}
