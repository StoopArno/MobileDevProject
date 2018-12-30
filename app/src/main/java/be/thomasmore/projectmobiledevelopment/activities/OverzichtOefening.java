package be.thomasmore.projectmobiledevelopment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.projectmobiledevelopment.MainActivity;
import be.thomasmore.projectmobiledevelopment.R;
import be.thomasmore.projectmobiledevelopment.adapters.OverzichtKinderenListAdapter;
import be.thomasmore.projectmobiledevelopment.adapters.OverzichtOefeningAdapter;
import be.thomasmore.projectmobiledevelopment.dataservices.KindOefeningDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.MetingDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.WoordDataService;
import be.thomasmore.projectmobiledevelopment.models.KindOefening;
import be.thomasmore.projectmobiledevelopment.models.Meting;
import be.thomasmore.projectmobiledevelopment.models.Woord;

public class OverzichtOefening extends AppCompatActivity {

    private int oefNr;
    private boolean isMeting = false;
    private long sessieId;

    private WoordDataService woordDataService = new WoordDataService();
    private MetingDataService metingDataService = new MetingDataService();
    private KindOefeningDataService kindOefeningDataService = new KindOefeningDataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overzicht_oefening);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sessieId = getIntent().getLongExtra("sessieId", 0);
        if (getIntent().getStringExtra("type").equals("oef")) {
            oefNr = Integer.parseInt(getIntent().getStringExtra("oef"));
        } else{
            isMeting = true;
        }

        toonWoorden();
    }

    private void toonWoorden(){
        List<Woord> woorden = new ArrayList<Woord>();
        List<String> scores = new ArrayList<String>();

        if(isMeting){
            List<Meting> voormetingen = metingDataService.MetingWhereSessieId(1, sessieId);
            List<Meting> nametingen = metingDataService.MetingWhereSessieId(2, sessieId);

            int teller = 0;
            for(Meting meting : voormetingen){
                Woord woord = woordDataService.getWoord(meting.getWoordID());
                woorden.add(woord);

                String metingscore = "";
                if(meting.isJuist()){
                    metingscore+=getResources().getString(R.string.juist);
                } else{
                    metingscore +=getResources().getString(R.string.fout);
                }
                metingscore+=" --> ";
                //if(nametingen.get(teller).isJuist()){
                //    metingscore+=R.string.juist;
                //} else{
                //    metingscore+=R.string.fout;
                //}
                scores.add(metingscore);
                teller++;
            }
        } else{
            List<KindOefening> oefeningen = kindOefeningDataService.ListWhereSessieAndWhereOefening(sessieId, oefNr);
            for(KindOefening oef: oefeningen){
                Woord woord = woordDataService.getWoord(oef.getWoordID());
                woorden.add(woord);

                if(oefNr == 2){
                    if(oef.getScore() == 1){
                        scores.add(getResources().getString(R.string.juist));
                    } else{
                        scores.add(getResources().getString(R.string.fout));
                    }
                } else{
                    scores.add(String.valueOf(oef.getScore()));
                }

            }

        }
        OverzichtOefeningAdapter adapter = new OverzichtOefeningAdapter(getApplicationContext(), woorden, scores);
        ListView overzichtOefeningWoordenList = (ListView) findViewById(R.id.overzicht_oefening_woorden);
        overzichtOefeningWoordenList.setAdapter(adapter);
    }

    @Override
    public void onBackPressed(){
        finish();
    }

}
