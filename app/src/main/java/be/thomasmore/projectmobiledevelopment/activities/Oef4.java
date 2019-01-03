package be.thomasmore.projectmobiledevelopment.activities;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
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
        Long woordID = getIntent().getLongExtra("woordID", 1);
        this.woord = woordDataService.getWoord(woordID);

        this.associatieList = associatieDataService.getAssociatieByWoord(this.woord.getId());

        TextView textViewWoord = (TextView) findViewById(R.id.oef4_textViewWoord);
        textViewWoord.setText(this.woord.getWoord());

        maakLayoutTop();
        maakLayoutBottom();
    }

    //de layout maken
    private void maakLayoutTop(){
        ImageView imageView = (ImageView) findViewById(R.id.oef4_image_top);
        imageView.setTag(this.woord.getId());
        imageView.setImageResource(getResources().getIdentifier(this.woord.getWoord().toLowerCase(), "drawable", getPackageName()));
    }

    private void maakLayoutBottom(){
        int k = 0;
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.oef4_layout_bottom);
        for (int i = 0; i < RIJ; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.setWeightSum(2);
            mainLayout.addView(linearLayout);
            for (int j = 0; j < KOLOM; j++) {
                final LinearLayout linearLayoutKolom = new LinearLayout(this);

                int LAYOUT_MARGIN = (int) (5 * getResources().getDisplayMetrics().density + 0.5f);
                int IMG_DIMENSIONS = (int) (110 * getResources().getDisplayMetrics().density + 0.5f);
                int TEXT_SIZE = (int) (7 * getResources().getDisplayMetrics().density + 0.5f);
                int LAYOUT_PADDING = (int) (3 * getResources().getDisplayMetrics().density + 0.5f);

                LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                linearLayoutParams.setMargins(LAYOUT_MARGIN, LAYOUT_MARGIN, LAYOUT_MARGIN, LAYOUT_MARGIN);
                linearLayoutKolom.setPadding(LAYOUT_PADDING, LAYOUT_PADDING, LAYOUT_PADDING, LAYOUT_PADDING);
                linearLayoutKolom.setLayoutParams(linearLayoutParams);
                linearLayoutKolom.setTag(this.associatieList.get(k).getId());
                linearLayoutKolom.setOrientation(LinearLayout.VERTICAL);


                ImageView imageView = new ImageView(this);
                LinearLayout.LayoutParams imgLayoutParams = new LinearLayout.LayoutParams(IMG_DIMENSIONS, IMG_DIMENSIONS);
                imageView.setLayoutParams(imgLayoutParams);
                imageView.setImageResource(R.drawable.duikbril);
                imageView.setImageResource(getResources().getIdentifier(this.woord.getWoord().toLowerCase() + associatieList.get(k).getAfbeeldingNr(), "drawable", getPackageName()));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                final TextView textViewAssociate = new TextView(this);
                LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                textViewAssociate.setGravity(Gravity.CENTER);
                textViewAssociate.setText(this.associatieList.get(k).getWoord());
                textViewAssociate.setTextSize(TEXT_SIZE);

                final int color = textViewAssociate.getCurrentTextColor();

                linearLayoutKolom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!prenten.contains(v.getTag().toString()) && prenten.size() == 3){
                            Toast.makeText(App.getAppContext(), "Er zijn al 3 prenten gekozen", Toast.LENGTH_LONG).show();
                        }else{
                            if(textViewAssociate.getCurrentTextColor() != Color.parseColor("#00b200")){
                                textViewAssociate.setTextColor(Color.parseColor("#00b200"));
                                linearLayoutKolom.setBackgroundResource(R.drawable.border_green);
                            }else{
                                textViewAssociate.setTextColor(color);
                                linearLayoutKolom.setBackgroundResource(0);
                            }
                            toggleAfbeelding(v.getTag().toString());
                        }
                    }
                });

                this.imagesView[k] = imageView;
                k++;

                linearLayoutKolom.addView(textViewAssociate);
                linearLayoutKolom.addView(imageView);
                linearLayout.addView(linearLayoutKolom);
            }
        }
    }

    //audio afspelen
    public void playAudio(View v){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier("prent" + this.woord.getWoord().toLowerCase(), "raw", getPackageName()));
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
            AssociatieWoord associatieWoord = associatieDataService.getAssociatie(Long.parseLong(tag));

            if(associatieWoord.getJuist() != 1){
                isJuist = false;
            }
        }

        if(prenten.size() != 3){
            isJuist = false;
        }

        if(isJuist){
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.prentjesgoed);
            mediaPlayer.start();

            final long kindSessieID = this.kindSessieID;
            final long woordID = woord.getId();

            score = score + 1;
            kindOefeningDataService.addKindOefening(this.kindSessieID, this.woord.getId(), 4, score);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    //verder gaan in de flow van de app
                    Intent intent = new Intent(App.getAppContext(), Oef5.class);
                    intent.putExtra("kindSessieID", kindSessieID);
                    intent.putExtra("woordID", woordID);
                    startActivity(intent);
                }
            });
        }else{
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.prentjesfout);
            mediaPlayer.start();

            score = score + 1;
        }
    }
}
