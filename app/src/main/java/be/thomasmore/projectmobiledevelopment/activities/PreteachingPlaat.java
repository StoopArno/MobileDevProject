package be.thomasmore.projectmobiledevelopment.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import be.thomasmore.projectmobiledevelopment.R;
import be.thomasmore.projectmobiledevelopment.dataservices.SessieDataService;
import be.thomasmore.projectmobiledevelopment.models.KindSessie;

public class PreteachingPlaat extends AppCompatActivity {

    private SessieDataService sessieDataService = new SessieDataService();
    private static long kindId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preteaching_plaat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        kindId = getIntent().getLongExtra("kindId", 0);
    }

    public void onClickPreteachPlaat(View v){
        startSessie(kindId);
    }

    public void playAudioPreteach(View v){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier("zinpreteaching", "raw", getPackageName()));
        mediaPlayer.start();
    }

    private void startSessie(long kindID){
        KindSessie kindSessie = new KindSessie();
        kindSessie.setKindID(kindID);

        Long id = sessieDataService.addSessie(kindSessie);

        Intent intent = new Intent(this, ActivityMeting.class);
        intent.putExtra("kindSessieID", id);
        intent.putExtra("metingNr", 1);
        startActivity(intent);
    }



}
