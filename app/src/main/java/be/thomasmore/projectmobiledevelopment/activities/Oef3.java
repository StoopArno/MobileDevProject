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

import java.util.Random;

import be.thomasmore.projectmobiledevelopment.App;
import be.thomasmore.projectmobiledevelopment.R;
import be.thomasmore.projectmobiledevelopment.dataservices.ContextDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.KindOefeningDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.WoordDataService;
import be.thomasmore.projectmobiledevelopment.models.Woord;

public class Oef3 extends AppCompatActivity {

    //woorden
    private Long kindSessieID;
    private Woord woord;
    int juistOfFout = 0;
    String zin = "";
    String zinType = "";
    int score = 0;
    boolean zinCheck = false;

    //dataservice
    private WoordDataService woordDataService = new WoordDataService();
    private KindOefeningDataService kindOefeningDataService = new KindOefeningDataService();
    private ContextDataService contextDataService = new ContextDataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oef3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.kindSessieID = getIntent().getLongExtra("kindSessieID", 0);
        Long woordID = getIntent().getLongExtra("woordID", 0);
        this.woord = woordDataService.getWoord(woordID);

        kiesZin();
    }

    //random beslissen of we de juiste of foute zin weergeven
    private void kiesZin(){
        Random rand = new Random();
        this.juistOfFout = rand.nextInt(1)+0;


        switch(this.juistOfFout){
            case 0:
                this.zin = contextDataService.getFouteContextZin(this.woord.getId());
                this.zinType = "fout";
                break;
            case 1:
                this.zin = contextDataService.getJuisteContextZin(this.woord.getId());
                this.zinType = "juist";
                break;
        }

        TextView textViewWoord = (TextView) findViewById(R.id.textViewWoord);
        textViewWoord.setText(this.zin);
    }

    //de andere zin nemen, afhankelijk van of de eerste juist of fout was
    private void andereZin(){
        if(this.juistOfFout == 0){
            this.juistOfFout = 1;
            this.zin = contextDataService.getJuisteContextZin(this.woord.getId());
            this.zinType = "juist";
        }else{
            this.juistOfFout = 0;
            this.zin = contextDataService.getFouteContextZin(this.woord.getId());
            this.zinType = "fout";
        }

        TextView textViewWoord = (TextView) findViewById(R.id.textViewWoord);
        textViewWoord.setText(this.zin);
    }

    //audio afspelen
    public void playAudio(View v){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier("zin" + this.zinType + this.woord.getWoord().toLowerCase(), "raw", getPackageName()));
        mediaPlayer.start();
    }

    //audio als het kind juist antwoord dat de zin juist is
    private void audioJuistZin(){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.goedezin);
        mediaPlayer.start();
        if(!this.zinCheck){
            //wachten op deze audio om af te spelen voor we vragen om opnieuw te doen
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    MediaPlayer mediaPlayer2 = MediaPlayer.create(App.getAppContext(), R.raw.nogeenzin);
                    mediaPlayer2.start();
                }
            });
        }
    }

    //audio als het kind juist antwoord dat de zin fout is
    private void audioFouteZin(){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.foutezin);
        mediaPlayer.start();
        if(!this.zinCheck){
            //wachten op deze audio om af te spelen voor we vragen om opnieuw te doen
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    MediaPlayer mediaPlayer2 = MediaPlayer.create(App.getAppContext(), R.raw.nogeenzin);
                    mediaPlayer2.start();
                }
            });
        }
    }

    //audio als het kind fout antwoord
    private void audioFoutAntwoord(){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.foutantwoord);
        mediaPlayer.start();
    }

    //als het kind op de groene duim klikt
    public void checkDuimOp(View v){
        if(juistOfFout == 1){
            if(score == 0){
                score = 1;
            }else{
                score = score + 1;
            }
            audioJuistZin();
            schrijfWeg();
        }else{
            score = score +1;
            audioFoutAntwoord();
        }
    }

    //als het kind op de rode duim klikt
    public void checkDuimAf(View v){
        if(juistOfFout == 0){
            if(score == 0){
                score = 1;
            }else{
                score = score + 1;
            }
            audioFouteZin();
            schrijfWeg();
        }else{
            score = score + 1;
            audioFoutAntwoord();
        }
    }

    //antwoord wegschrijven
    private void schrijfWeg(){
        kindOefeningDataService.addKindOefening(this.kindSessieID, this.woord.getId(), 3, this.score);

        if(!this.zinCheck){
            this.score = 0;

            //nog eentje doen
            zinCheck = true;
            andereZin();
        }//else{
            //verder gaan in de flow van de app
            //Intent intent = new Intent(this, Oef3.class);
            //intent.putExtra("kindSessieID", this.kindSessieID);
            //intent.putExtra("woordID", this.woord.getId());
            //startActivity(intent);
        //}
    }
}
