package be.thomasmore.projectmobiledevelopment.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import be.thomasmore.projectmobiledevelopment.R;
import be.thomasmore.projectmobiledevelopment.dataservices.KindOefeningDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.WoordDataService;
import be.thomasmore.projectmobiledevelopment.models.Woord;

public class Oef2 extends AppCompatActivity {

    //woorden
    private Long kindSessieID;
    private Woord woord;

    //dataservice
    private WoordDataService woordDataService = new WoordDataService();
    private KindOefeningDataService kindOefeningDataService = new KindOefeningDataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oef2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.kindSessieID = getIntent().getLongExtra("kindSessieID", 0);
        Long woordID = getIntent().getLongExtra("woordID", 0);
        this.woord = woordDataService.getWoord(woordID);

        maakLayout();
    }

    //de layout maken
    private void maakLayout(){
        ImageView imageView = (ImageView) findViewById(R.id.oef2_woord_image);

        imageView.setTag(this.woord.getId());

        imageView.setImageResource(getResources().getIdentifier(this.woord.getWoord().toLowerCase(), "drawable", getPackageName()));
    }

    //audio afspelen
    public void playAudio(View v){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier("zeg" + this.woord.getWoord().toLowerCase(), "raw", getPackageName()));
        mediaPlayer.start();
    }

    //als het kind juist antwoord
    public void onClickJuist(View v){
        schrijfWeg(1);
    }

    //als het kind fout antwoord
    public void onClickFout(View v){
        schrijfWeg(0);
    }

    //antwoord wegschrijven
    private void schrijfWeg(int score){
        kindOefeningDataService.addKindOefening(this.kindSessieID, this.woord.getId(), 2, score);

        //verder gaan in de flow van de app
        Intent intent = new Intent(this, Oef3.class);
        intent.putExtra("kindSessieID", this.kindSessieID);
        intent.putExtra("woordID", this.woord.getId());
        startActivity(intent);
    }
}
