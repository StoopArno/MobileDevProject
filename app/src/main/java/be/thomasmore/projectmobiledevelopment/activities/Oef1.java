package be.thomasmore.projectmobiledevelopment.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import be.thomasmore.projectmobiledevelopment.R;
import be.thomasmore.projectmobiledevelopment.dataservices.WoordDataService;
import be.thomasmore.projectmobiledevelopment.models.Woord;

public class Oef1 extends AppCompatActivity {

    //woorden
    private Long kindSessieID;
    private Woord woord;

    //dataservice
    private WoordDataService woordDataService = new WoordDataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oef1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.kindSessieID = getIntent().getLongExtra("kindSessieID", 0);
        Long woordID = getIntent().getLongExtra("woordID", 0);
        this.woord = woordDataService.getWoord(woordID);

        TextView textViewWoord = (TextView) findViewById(R.id.textViewWoord);
        textViewWoord.setText(this.woord.getWoord());

        maakLayout();
    }

    //de layout maken
    private void maakLayout(){
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.layout_main);
        ImageView imageView = new ImageView(this);

        LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(1000, 1000);
        imageLayoutParams.leftMargin = 10;
        imageLayoutParams.topMargin = 10;
        imageLayoutParams.rightMargin = 10;
        imageView.setLayoutParams(imageLayoutParams);
        imageView.setTag(this.woord.getId());

        imageView.setImageResource(getResources().getIdentifier(this.woord.getWoord().toLowerCase(), "drawable", getPackageName()));
        mainLayout.addView(imageView);
    }

    //audio afspelen
    public void playAudio(View v){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier("def" + this.woord.getWoord().toLowerCase(), "raw", getPackageName()));
        mediaPlayer.start();
    }

    public void onClickVerder(View v){
        //verder gaan in de flow van de app
        Intent intent = new Intent(this, Oef2.class);
        intent.putExtra("kindSessieID", this.kindSessieID);
        intent.putExtra("woordID", this.woord.getId());
        startActivity(intent);
    }

}
