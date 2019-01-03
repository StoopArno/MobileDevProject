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

import java.util.List;

import be.thomasmore.projectmobiledevelopment.R;
import be.thomasmore.projectmobiledevelopment.dataservices.KindOefeningDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.LettergreepDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.WoordDataService;
import be.thomasmore.projectmobiledevelopment.models.Lettergreep;
import be.thomasmore.projectmobiledevelopment.models.Woord;

public class Oef63 extends AppCompatActivity {

    //woorden
    private Long kindSessieID;
    private Woord woord;
    int score = 0;

    //dataservice
    private WoordDataService woordDataService = new WoordDataService();
    private KindOefeningDataService kindOefeningDataService = new KindOefeningDataService();
    private LettergreepDataService lettergreepDataService = new LettergreepDataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oef63);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.kindSessieID = getIntent().getLongExtra("kindSessieID", 0);
        Long woordID = getIntent().getLongExtra("woordID", 1);
        this.woord = woordDataService.getWoord(woordID);

        maakLayout();
        MediaPlayer mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(this.woord.getWoord().toLowerCase() + "63", "raw", getPackageName()));
        mediaPlayer.start();
    }

    //de layout maken
    private void maakLayout(){
        ImageView imageView = (ImageView) findViewById(R.id.oef63_img);
        imageView.setTag(this.woord.getId());
        imageView.setImageResource(getResources().getIdentifier(this.woord.getWoord().toLowerCase(), "drawable", getPackageName()));

        TextView textViewWoord = (TextView) findViewById(R.id.oef63_textViewWoord);

        if(lettergreepDataService.hasLetterGreep(woord.getId())){
            List<Lettergreep> lettergrepen = lettergreepDataService.getLetterGrepen(woord.getId());

            textViewWoord.setText(lettergrepen.get(0).getLettergreep() + "-" + lettergrepen.get(1).getLettergreep());
        }else{
            textViewWoord.setText(this.woord.getWoord());
        }
    }

    //audio afspelen
    public void playAudio(View v){
        MediaPlayer mediaPlayer = new MediaPlayer();

        if(lettergreepDataService.hasLetterGreep(woord.getId())){
            mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(this.woord.getWoord().toLowerCase() + "63greep", "raw", getPackageName()));
        }else{
            mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(this.woord.getWoord().toLowerCase(), "raw", getPackageName()));
        }

        mediaPlayer.start();
    }

    private void verdergaan(){
        //verdergaan door de flow van de app
        Intent intent = new Intent(this, ActivityMeting.class);
        long id = 0;

        if(woord.getId() == 10){
            id = 0;
        }else{
            id = woord.getId();
        }

        if(id < 9){
            intent = new Intent(this, Oef1.class);
            id = this.woordDataService.getWoord(id + 1).getId();
            intent.putExtra("woordID", id);
            intent.putExtra("kindSessieID", this.kindSessieID);
        }else{
            intent.putExtra("metingNr", 2);
            intent.putExtra("kindSessieID", this.kindSessieID);
        }

        startActivity(intent);
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
        kindOefeningDataService.addKindOefening(this.kindSessieID, this.woord.getId(), 8, score);

        verdergaan();
    }
}
