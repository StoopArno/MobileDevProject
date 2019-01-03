package be.thomasmore.projectmobiledevelopment.activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

import be.thomasmore.projectmobiledevelopment.R;
import be.thomasmore.projectmobiledevelopment.dataservices.KindOefeningDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.WoordDataService;
import be.thomasmore.projectmobiledevelopment.models.Woord;

public class Oef62 extends AppCompatActivity {

    int IMAGE_DIMENSIONS;

    //woorden
    private Long kindSessieID;
    private Woord woord;

    //dataservice
    private WoordDataService woordDataService = new WoordDataService();
    private KindOefeningDataService kindOefeningDataService = new KindOefeningDataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oef62);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.kindSessieID = getIntent().getLongExtra("kindSessieID", 0);
        Long woordID = getIntent().getLongExtra("woordID", 1);
        this.woord = woordDataService.getWoord(woordID);
        IMAGE_DIMENSIONS = (int) (70 * getResources().getDisplayMetrics().density + 0.5f);
        vulVelden();
    }

    private void vulVelden(){
        final TextView textViewWoord = (TextView) findViewById(R.id.oef62_woord);
        textViewWoord.setText(woord.getWoord());

        textViewWoord.post(new Runnable() {
            @Override
            public void run() {
                setPosities();
            }
        });

        ImageView imageView = (ImageView) findViewById(R.id.oef62_img);
        imageView.setImageResource(getResources().getIdentifier(this.woord.getWoord().toLowerCase(), "drawable", getPackageName()));
    }


    private void setPosities(){
        TextView textViewWoord = (TextView) findViewById(R.id.oef62_woord);
        ImageView imageViewBij = (ImageView) findViewById(R.id.oef62_bij);

        int widthWoord = textViewWoord.getRight();

        imageViewBij.setRight(textViewWoord.getRight());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(IMAGE_DIMENSIONS, IMAGE_DIMENSIONS);
        params.setMargins(0, 0, widthWoord, 0); //Positie rechts
        //params.setMargins(widthWoord-(IMAGE_DIMENSIONS/2), 0, 0, 0); //Positie links
        imageViewBij.setLayoutParams(params);
    }

    private void speelAnimatie(long duratie){
        final ImageView imageViewBij = (ImageView) findViewById(R.id.oef62_bij);
        final TextView textViewWoord = (TextView) findViewById(R.id.oef62_woord);
        final int widthWoord = textViewWoord.getWidth();
        imageViewBij.setTranslationX(0);
        imageViewBij.animate().translationX(widthWoord).setDuration(duratie).start();
    }

    //audio afspelen
    public void playAudio(View v){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(this.woord.getWoord().toLowerCase(), "raw", getPackageName()));
        mediaPlayer.start();
        speelAnimatie(mediaPlayer.getDuration());
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
        kindOefeningDataService.addKindOefening(this.kindSessieID, this.woord.getId(), 7, score);

        verdergaan();
    }

}
