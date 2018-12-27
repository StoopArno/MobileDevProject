package be.thomasmore.projectmobiledevelopment.activities;

import android.graphics.Color;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.projectmobiledevelopment.App;
import be.thomasmore.projectmobiledevelopment.R;
import be.thomasmore.projectmobiledevelopment.dataservices.AssociatieDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.ContextDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.KindOefeningDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.WoordDataService;
import be.thomasmore.projectmobiledevelopment.models.AssociatieWoord;
import be.thomasmore.projectmobiledevelopment.models.Woord;

public class Oef4 extends AppCompatActivity {

    //woorden
    private Long kindSessieID;
    private Woord woord;
    private List<AssociatieWoord> associatieList = new ArrayList<>();
    private List<String> prenten = new ArrayList<>();
    int score = 0;

    //dataservice
    private WoordDataService woordDataService = new WoordDataService();
    private KindOefeningDataService kindOefeningDataService = new KindOefeningDataService();
    private AssociatieDataService associatieDataService = new AssociatieDataService();

    //voor layout
    int KOLOM = 2;
    int RIJ = 2;
    int AANTAL = RIJ * KOLOM;
    ImageView imagesView[] = new ImageView[AANTAL];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oef4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.kindSessieID = getIntent().getLongExtra("kindSessieID", 0);
        Long woordID = getIntent().getLongExtra("woordID", 0);
        this.woord = woordDataService.getWoord(woordID);

        this.associatieList = associatieDataService.getAssociatieByWoord(this.woord.getId());

        TextView textViewWoord = (TextView) findViewById(R.id.textViewWoord);
        textViewWoord.setText(this.woord.getWoord());

        maakLayoutTop();
        maakLayoutBottom();
    }

    //de layout maken
    private void maakLayoutTop(){
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.layout_main);
        ImageView imageView = new ImageView(this);

        LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(200, 200);
        imageLayoutParams.leftMargin = 10;
        imageLayoutParams.topMargin = 10;
        imageLayoutParams.rightMargin = 10;
        imageView.setLayoutParams(imageLayoutParams);
        imageView.setTag(this.woord.getId());

        imageView.setImageResource(getResources().getIdentifier(this.woord.getWoord().toLowerCase(), "drawable", getPackageName()));
        mainLayout.addView(imageView);
    }

    private void maakLayoutBottom(){
        int k = 0;
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.layout_bottom);
        for (int i = 0; i < RIJ; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
            linearLayout.setWeightSum(2);
            mainLayout.addView(linearLayout);
            for (int j = 0; j < KOLOM; j++) {
                RelativeLayout relativeLayout = new RelativeLayout(this);
                relativeLayout.setLayoutParams(
                        new RelativeLayout.LayoutParams(500,
                        ViewGroup.LayoutParams.MATCH_PARENT));

                ImageView imageView = new ImageView(this);
                final TextView textViewAssociate = new TextView(this);

                LinearLayout.LayoutParams imageLayoutParams =
                        new LinearLayout.LayoutParams(500, 500);
                //imageLayoutParams.leftMargin = 500;
                //imageLayoutParams.rightMargin = 500;
                imageView.setLayoutParams(imageLayoutParams);
                imageView.setTag(associatieList.get(k).getId());
                textViewAssociate.setText(associatieList.get(k).getWoord());

                final int color = textViewAssociate.getCurrentTextColor();

                imageView.setImageResource(R.drawable.duikbril);
                imageView.setImageResource(getResources().getIdentifier(this.woord.getWoord().toLowerCase() + associatieList.get(k).getAfbeeldingNr(), "drawable", getPackageName()));

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!prenten.contains(v.getTag().toString()) && prenten.size() == 3){
                            Toast.makeText(App.getAppContext(), "Er zijn al 3 prenten gekozen", Toast.LENGTH_LONG).show();
                        }else{
                            if(textViewAssociate.getCurrentTextColor() != Color.parseColor("#00b200")){
                                textViewAssociate.setTextColor(Color.parseColor("#00b200"));
                            }else{
                                textViewAssociate.setTextColor(color);
                            }
                            toggleAfbeelding(v.getTag().toString());
                        }
                    }
                });

                this.imagesView[k] = imageView;
                k++;
                relativeLayout.addView(imageView);
                relativeLayout.addView(textViewAssociate);
                linearLayout.addView(relativeLayout);
            }
        }
    }

    //audio afspelen
    public void playAudio(View v){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier("prent" + this.woord.getWoord().toLowerCase(), "raw", getPackageName()));
        mediaPlayer.start();
    }

    //afbeeldingen toggles
    private void toggleAfbeelding(String tag){
        if(!prenten.contains(tag)){
            prenten.add(tag);
        }else{
            prenten.remove(tag);
        }
        Log.d("test", tag);
    }

    //antwoord checken
    public void onClickCheck(View v){
        boolean isJuist = true;

        for(String tag : prenten){
            AssociatieWoord associatieWoord = associatieDataService.getAssociatie(Long.parseLong(tag));

            if(associatieWoord.getJuist() != 1){
                isJuist = false;
            }
        }

        if(isJuist){
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.prentjesgoed);
            mediaPlayer.start();

            score = score + 1;
            kindOefeningDataService.addKindOefening(this.kindSessieID, this.woord.getId(), 4, score);
            //verder gaan in de flow van de app
        }else{
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.prentjesfout);
            mediaPlayer.start();

            score = score + 1;
        }
    }
}
