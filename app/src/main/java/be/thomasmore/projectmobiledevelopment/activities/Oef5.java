package be.thomasmore.projectmobiledevelopment.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
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
import be.thomasmore.projectmobiledevelopment.dataservices.ConditieDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.KindDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.KindOefeningDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.KindSessieDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.WoordAfbeeldingDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.WoordDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.WoordGroepDataService;
import be.thomasmore.projectmobiledevelopment.models.AssociatieWoord;
import be.thomasmore.projectmobiledevelopment.models.ConditieGroep;
import be.thomasmore.projectmobiledevelopment.models.Kind;
import be.thomasmore.projectmobiledevelopment.models.KindSessie;
import be.thomasmore.projectmobiledevelopment.models.Woord;
import be.thomasmore.projectmobiledevelopment.models.WoordAfbeelding;
import be.thomasmore.projectmobiledevelopment.models.Woordgroep;

public class Oef5 extends AppCompatActivity {

    //woorden
    private Long kindSessieID;
    private Woord woord;
    private List<WoordAfbeelding> afbeeldingList = new ArrayList<>();
    private List<String> prenten = new ArrayList<>();
    int score = 0;

    //dataservice
    private WoordDataService woordDataService = new WoordDataService();
    private KindOefeningDataService kindOefeningDataService = new KindOefeningDataService();
    private WoordAfbeeldingDataService woordAfbeeldingDataService = new WoordAfbeeldingDataService();
    private KindDataService kindDataService = new KindDataService();
    private KindSessieDataService kindSessieDataService = new KindSessieDataService();
    private ConditieDataService conditieDataService = new ConditieDataService();

    //voor layout
    int KOLOM = 2;
    int RIJ = 2;
    int AANTAL = RIJ * KOLOM;
    ImageView imagesView[] = new ImageView[AANTAL];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oef5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.kindSessieID = getIntent().getLongExtra("kindSessieID", 0);
        Long woordID = getIntent().getLongExtra("woordID", 1);
        this.woord = woordDataService.getWoord(woordID);

        this.afbeeldingList = woordAfbeeldingDataService.getAfbeeldingenByWoord(this.woord.getId());

        TextView textViewWoord = (TextView) findViewById(R.id.oef5_textViewWoord);
        textViewWoord.setText(this.woord.getWoord());

        maakLayout();
    }

    //de layout maken
    private void maakLayout() {
        int k = 0;
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.oef5_layout_bottom);
        for (int i = 0; i < RIJ; i++) {
            final LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams( new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mainLayout.addView(linearLayout);
            for (int j = 0; j < KOLOM; j++) {


                int LAYOUT_MARGIN = (int) (10 * getResources().getDisplayMetrics().density + 0.5f);
                int IMG_DIMENSIONS = (int) (150 * getResources().getDisplayMetrics().density + 0.5f);
                int TEXT_SIZE = (int) (9 * getResources().getDisplayMetrics().density + 0.5f);
                int LAYOUT_PADDING = (int) (3 * getResources().getDisplayMetrics().density + 0.5f);

                final ImageView imageView = new ImageView(this);
                LinearLayout.LayoutParams imgLayoutParams = new LinearLayout.LayoutParams(IMG_DIMENSIONS, IMG_DIMENSIONS);
                imageView.setLayoutParams(imgLayoutParams);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setImageResource(R.drawable.duikbril);
                imageView.setImageResource(getResources().getIdentifier("jf" + this.woord.getWoord().toLowerCase() + this.afbeeldingList.get(k).getAfbeeldingNr(), "drawable", getPackageName()));

                final LinearLayout linearLayoutItem = new LinearLayout(this);
                final LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                linearLayoutParams.setMargins(LAYOUT_MARGIN, LAYOUT_MARGIN, LAYOUT_MARGIN, LAYOUT_MARGIN);
                linearLayoutItem.setLayoutParams(linearLayoutParams);
                linearLayoutItem.setPadding(LAYOUT_PADDING, LAYOUT_PADDING, LAYOUT_PADDING, LAYOUT_PADDING);
                linearLayoutItem.setTag(this.afbeeldingList.get(k).getId());

                linearLayoutItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!prenten.contains(v.getTag().toString()) && prenten.size() == 3){
                            Toast.makeText(App.getAppContext(), "Er zijn al 3 prenten gekozen", Toast.LENGTH_LONG).show();
                        }else{
                            if(prenten.contains(v.getTag().toString())){
                                linearLayoutItem.setBackgroundResource(0);
                            }else{
                                linearLayoutItem.setBackgroundResource(R.drawable.border_green);
                            }
                            toggleAfbeelding(v.getTag().toString());
                        }
                    }
                });

                this.imagesView[k] = imageView;
                k++;
                linearLayoutItem.addView(imageView);
                linearLayout.addView(linearLayoutItem);
            }
        }
    }

    //audio afspelen
    public void playAudio(View v){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier("prenten" + this.woord.getWoord().toLowerCase(), "raw", getPackageName()));
        mediaPlayer.start();
    }

    //afbeeldingen togglen
    private void toggleAfbeelding(String tag){
        if(!prenten.contains(tag)){
            prenten.add(tag);
        }else{
            prenten.remove(tag);
        }
    }

    //antwoord checken
    public void onClickCheck(View v){
        boolean isJuist = true;

        for(String tag : prenten){
            WoordAfbeelding woordAfbeelding = woordAfbeeldingDataService.getWoordAfbeelding(Long.parseLong(tag));

            if(woordAfbeelding.getJuist() != 1){
                isJuist = false;
            }
        }

        if(prenten.size() != 3){
            isJuist = false;
        }

        if(isJuist){
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.prentjesgoed);
            mediaPlayer.start();

            score = score + 1;
            kindOefeningDataService.addKindOefening(this.kindSessieID, this.woord.getId(), 5, score);
            //verder gaan in de flow van de app
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    bepaalConditie();
                }
            });
        }else{
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.prentjesfout);
            mediaPlayer.start();

            score = score + 1;
        }
    }

    //conditie voor oefening 6 bepalen
    private void bepaalConditie(){
        KindSessie kindSessie = kindSessieDataService.getKindSessie(kindSessieID);
        Kind kind = kindDataService.getKind(kindSessie.getKindID());
        ConditieGroep conditieGroep = conditieDataService.getCondieNr(woord.getWoordgroepID(), kind.getGroepID());

        Intent intent = new Intent(this, Oef6.class);

        switch(conditieGroep.getConditieNr()){
            case 1:
                intent = new Intent(this, Oef6.class);
                break;
            case 2:
                intent = new Intent(this, Oef62.class);
                break;
            case 3:
                intent = new Intent(this, Oef63.class);
                break;
        }

        intent.putExtra("kindSessieID", this.kindSessieID);
        intent.putExtra("woordID", this.woord.getId());
        startActivity(intent);
    }
}
