package be.thomasmore.projectmobiledevelopment.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import be.thomasmore.projectmobiledevelopment.MainActivity;
import be.thomasmore.projectmobiledevelopment.R;
import be.thomasmore.projectmobiledevelopment.dataservices.MetingDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.WoordDataService;
import be.thomasmore.projectmobiledevelopment.models.Kind;
import be.thomasmore.projectmobiledevelopment.models.KindSessie;
import be.thomasmore.projectmobiledevelopment.models.Meting;
import be.thomasmore.projectmobiledevelopment.models.Woord;

public class ActivityMeting extends AppCompatActivity {

    //woorden
    private Long kindSessieID;
    private Woord testWoord;
    private Woord woord;
    private List<Woord> woorden;

    //voor layout
    int KOLOM = 2;
    int RIJ = 2;
    int AANTAL = RIJ * KOLOM;
    ImageView imagesView[] = new ImageView[AANTAL];

    //andere
    List<Woord> images = new ArrayList<>();
    private int metingNr;
    private int teller = 0;

    //dataservice
    private WoordDataService woordDataService = new WoordDataService();
    private MetingDataService metingDataService = new MetingDataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.woorden = woordDataService.getWoorden();
        this.testWoord = woordDataService.getWoord(10);

        TextView textViewWoord = (TextView) findViewById(R.id.meting_textViewWoord);
        textViewWoord.setText(this.testWoord.getWoord());

        this.kindSessieID = getIntent().getLongExtra("kindSessieID", 0);
        this.metingNr = getIntent().getIntExtra("metingNr", 1);
        Log.d("test", kindSessieID.toString());

        getImages(teller);
        maakLayout();
    }

    //lijst met 4 images aanmaken (3 random, en de image met het juiste woord)
    private void getImages(int teller) {
        this.images.clear();

        //juiste woord ophalen
        if (this.teller == 0) {
            this.woord = this.testWoord;
        } else {
            this.woord = this.woorden.get(teller-1);

            TextView textViewWoord = (TextView) findViewById(R.id.meting_textViewWoord);
            textViewWoord.setText(this.woord.getWoord());
        }

        //random images ophalen
        List<Woord> alleWoorden = this.woorden;
        alleWoorden.add(this.testWoord);

        for (int i = 0; i < 3; i++) {
            Woord image = getRandomImage(alleWoorden);

            while (image == this.woord || this.images.contains(image)) {
                image = getRandomImage(alleWoorden);
            }

            this.images.add(image);
        }

        this.images.add(this.woord);
        Collections.shuffle(this.images);
    }

    //een random image ophalen
    private Woord getRandomImage(List<Woord> woorden) {
        Random rand = new Random();
        int n = rand.nextInt(9);
        return woorden.get(n);
    }

    //de layout maken
    private void maakLayout() {
        int k = 0;
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.meting_layout_main);
        for (int i = 0; i < RIJ; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
            mainLayout.addView(linearLayout);
            for (int j = 0; j < KOLOM; j++) {
                int IMAGE_DIMENSIONS = (int) (180 * getResources().getDisplayMetrics().density + 0.5f); //Vertaal dp naar int
                int MARGIN_VAL = (int) (5 * getResources().getDisplayMetrics().density + 0.5f);
                //View voor border
                LinearLayout borderLayout = new LinearLayout(this);
                LinearLayout.LayoutParams imageLayoutParams =  new LinearLayout.LayoutParams(IMAGE_DIMENSIONS, IMAGE_DIMENSIONS);
                imageLayoutParams.setMargins(MARGIN_VAL, MARGIN_VAL, MARGIN_VAL, MARGIN_VAL);

                borderLayout.setLayoutParams(imageLayoutParams);
                borderLayout.setBackgroundResource(R.drawable.border_black);
                borderLayout.setTag(this.images.get(k).getId());

                borderLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkReply(v.getTag().toString());
                    }
                });

                ImageView imageView = new ImageView(this);

                //LinearLayout.LayoutParams imageLayoutParams =  new LinearLayout.LayoutParams(IMAGE_DIMENSIONS, IMAGE_DIMENSIONS);
                //imageView.setLayoutParams(imageLayoutParams);

                //int paddingVal1 = (int)(5 * getResources().getDisplayMetrics().density + 0.5f);
                //int paddingVal2 = (int)(10 * getResources().getDisplayMetrics().density + 0.5f);
                //imageView.setPadding(paddingVal1, paddingVal1, paddingVal1, paddingVal1);

                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setBackgroundResource(R.drawable.border_black);

                imageView.setImageResource(R.drawable.duikbril);
                imageView.setImageResource(getResources().getIdentifier(this.images.get(k).getWoord().toLowerCase(), "drawable", getPackageName()));



                this.imagesView[k] = imageView;
                k++;
                //linearLayout.addView(imageView);
                borderLayout.addView(imageView);
                linearLayout.addView(borderLayout);
            }
        }
    }

    public void playAudio(View v){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(this.woord.getWoord().toLowerCase(), "raw", getPackageName()));
        mediaPlayer.start();
    }

    //antwoord nakijken
    private void checkReply(String woordID){
        boolean juist = false;

        Long juisteWoordID = this.woord.getId();

        if(juisteWoordID.toString().equals(woordID)){
            juist = true;
        }

        //antwoord wegschrijven
        metingDataService.addMeting(this.kindSessieID, this.woord.getId(), juist, this.metingNr);

        //checken of er nog een woord is
        if(teller != 9){
            //doorgaan voor een ander woord
            LinearLayout mainLayout = (LinearLayout) findViewById(R.id.meting_layout_main);
            mainLayout.removeAllViews();

            teller = teller + 1;
            getImages(teller);
            maakLayout();
        }else{
            //afhankelijk of het voor of -nameting is navigeren we verder in de app
            if(this.metingNr == 1){
                //verder gaan in de flow van de app
                Intent intent = new Intent(this, Oef1.class);
                intent.putExtra("kindSessieID", this.kindSessieID);
                Long id = Long.valueOf(10); //we beginnen altijd met woord 10
                intent.putExtra("woordID", id);
                startActivity(intent);
            }
        }
    }
}
