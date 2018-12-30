package be.thomasmore.projectmobiledevelopment.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.projectmobiledevelopment.R;
import be.thomasmore.projectmobiledevelopment.dataservices.KindOefeningDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.WoordDataService;
import be.thomasmore.projectmobiledevelopment.models.KindOefening;
import be.thomasmore.projectmobiledevelopment.models.Woord;
import be.thomasmore.projectmobiledevelopment.models.WoordAfbeelding;

public class Oef6 extends AppCompatActivity {

    //woorden
    private Long kindSessieID;
    private Woord woord;
    int score = 0;

    //dataservice
    private WoordDataService woordDataService = new WoordDataService();
    private KindOefeningDataService kindOefeningDataService = new KindOefeningDataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oef6);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.kindSessieID = getIntent().getLongExtra("kindSessieID", 0);
        Long woordID = getIntent().getLongExtra("woordID", 0);
        this.woord = woordDataService.getWoord(woordID);

        TextView textViewWoord = (TextView) findViewById(R.id.textViewWoord);
        textViewWoord.setText(this.woord.getWoord());
    }

    //audio afspelen
    public void playAudio(View v){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(this.woord.getWoord().toLowerCase() + "61", "raw", getPackageName()));
        mediaPlayer.start();
    }

    public void checkSmileyOp(View v){
        this.score = 1;
        schrijfWeg();
    }

    public void checkSmileyAf(View v){
        this.score = 0;
        schrijfWeg();
    }

    //score wegschrijven
    private void schrijfWeg(){
        kindOefeningDataService.addKindOefening(kindSessieID, woord.getId(), 6, score);

        //verdergaan door de flow van de app
        Intent intent = new Intent(this, SessieEinde.class);
        long id = 0;

        if(woord.getId() == 10){
            id = 0;
        }else{
            id = woord.getId();
        }

        if(id < 9){
            intent = new Intent(this, Oef1.class);
            intent.putExtra("kindSessieID", this.kindSessieID);
            id = this.woordDataService.getWoord(id + 1).getId();
            intent.putExtra("woordID", id);
        }

        startActivity(intent);
    }
}
