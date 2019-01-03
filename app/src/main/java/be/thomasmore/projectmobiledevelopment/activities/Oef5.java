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
        Long woordID = getIntent().getLongExtra("woordID", 0);
        this.woord = woordDataService.getWoord(woordID);

        this.afbeeldingList = woordAfbeeldingDataService.getAfbeeldingenByWoord(this.woord.getId());

        TextView textViewWoord = (TextView) findViewById(R.id.textViewWoord);
        textViewWoord.setText(this.woord.getWoord());

        maakLayout();
    }

    //de layout maken
    private void maakLayout() {
        int k = 0;
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.layout_bottom);
        for (int i = 0; i < RIJ; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
            mainLayout.addView(linearLayout);
            for (int j = 0; j < KOLOM; j++) {
                final ImageView imageView = new ImageView(this);

                final LinearLayout.LayoutParams imageLayoutParams =
                        new LinearLayout.LayoutParams(500, 500);
                imageLayoutParams.leftMargin = 10;
                imageLayoutParams.topMargin = 10;
                imageLayoutParams.rightMargin = 10;
                imageView.setLayoutParams(imageLayoutParams);
                imageView.setTag(this.afbeeldingList.get(k).getId());

                imageView.setImageResource(R.drawable.duikbril);
                imageView.setImageResource(getResources().getIdentifier("jf" + this.woord.getWoord().toLowerCase() + this.afbeeldingList.get(k).getAfbeeldingNr(), "drawable", getPackageName()));

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!prenten.contains(v.getTag().toString()) && prenten.size() == 3){
                            Toast.makeText(App.getAppContext(), "Er zijn al 3 prenten gekozen", Toast.LENGTH_LONG).show();
                        }else{
                            if(prenten.contains(v.getTag().toString())){
                                imageView.setColorFilter(null);
                            }else{
                                imageView.setColorFilter(Color.GREEN, PorterDuff.Mode.LIGHTEN);
                            }
                            toggleAfbeelding(v.getTag().toString());
                        }
                    }
                });

                this.imagesView[k] = imageView;
                k++;
                linearLayout.addView(imageView);
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
