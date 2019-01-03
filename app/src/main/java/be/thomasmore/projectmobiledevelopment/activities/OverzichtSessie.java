package be.thomasmore.projectmobiledevelopment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import be.thomasmore.projectmobiledevelopment.R;
import be.thomasmore.projectmobiledevelopment.adapters.OverzichtOefeningExpandableAdapter;
import be.thomasmore.projectmobiledevelopment.dataservices.GroepDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.KindDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.KindOefeningDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.MetingDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.SessieDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.WoordDataService;
import be.thomasmore.projectmobiledevelopment.models.Groep;
import be.thomasmore.projectmobiledevelopment.models.Kind;
import be.thomasmore.projectmobiledevelopment.models.KindOefening;
import be.thomasmore.projectmobiledevelopment.models.KindSessie;
import be.thomasmore.projectmobiledevelopment.models.Meting;
import be.thomasmore.projectmobiledevelopment.models.Woord;

public class OverzichtSessie extends AppCompatActivity {

    private static long sessieId;
    private MetingDataService metingDataService = new MetingDataService();
    private KindDataService kindDataService = new KindDataService();
    private SessieDataService sessieDataService = new SessieDataService();
    private GroepDataService groepDataService = new GroepDataService();
    private WoordDataService woordDataService = new WoordDataService();
    private KindOefeningDataService kindOefeningDataService = new KindOefeningDataService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overzicht_sessie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sessieId = getIntent().getLongExtra("sessieId", 1);
        vulVelden();
        vulExpandableListView();
    }

    public void onClickOverzichtSessieOefening(View v){
        Intent intent = new Intent(this, OverzichtOefening.class);
        intent.putExtra("type", "oef");
        intent.putExtra("oef", v.getTag().toString());
        intent.putExtra("sessieId", sessieId);
        startActivity(intent);
    }

    public void onClickOverzichtSessieMeting(View v){
        Intent intent = new Intent(this, OverzichtOefening.class);
        intent.putExtra("type", "meting");
        intent.putExtra("sessieId", sessieId);
        startActivity(intent);
    }

    private void vulVelden(){
        KindSessie kindSessie = sessieDataService.getSessie(sessieId);
        Kind kind = kindDataService.getKind(kindSessie.getKindID());
        Groep groep = groepDataService.getGroep(kind.getGroepID());

        TextView textViewNaam = (TextView) findViewById(R.id.overzicht_sessie_naam);
        TextView textViewGroep = (TextView) findViewById(R.id.overzicht_sessie_groep);

        textViewNaam.setText(kind.getNaam());
        textViewGroep.setText("Groep " + groep.getId());
    }

    private void vulExpandableListView(){
        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.overzicht_sessie_lijst_oef);

        List<String> oefeningen = new ArrayList<String>();
        oefeningen.add("meting");
        oefeningen.add("2");
        oefeningen.add("3");
        oefeningen.add("4");
        oefeningen.add("5");
        oefeningen.add("6");
        oefeningen.add("7");
        oefeningen.add("8");

        HashMap<String, List<String>> listDetails = new HashMap<>();
        HashMap<String, List<String>> listScores = new HashMap<>();

        List<String> offOefnamen = new ArrayList<>();

        for(String oefNaam: oefeningen){
            List<String> details = new ArrayList<>();
            String offOefNaam = "";
            if(oefNaam == "meting"){
                List<Meting> voormetingen = metingDataService.MetingWhereSessieId(1, sessieId);
                List<Meting> nametingen = metingDataService.MetingWhereSessieId(2, sessieId);
                List<String> scores = new ArrayList<>();

                int teller = 0;
                for(Meting meting : voormetingen) {
                    Woord woord = woordDataService.getWoord(meting.getWoordID());
                    details.add(woord.getWoord());

                    String metingscore = "";
                    if (meting.isJuist()) {
                        metingscore += getResources().getString(R.string.juist);
                    } else {
                        metingscore += getResources().getString(R.string.fout);
                    }
                    metingscore += " --> ";
                    //if (nametingen.get(teller).isJuist()) {
                    //    metingscore += R.string.juist;
                    //} else {
                    //    metingscore += R.string.fout;
                    //}
                    scores.add(metingscore);
                }
                offOefNaam = getString(R.string.overzicht_sessie_meting);
                offOefnamen.add(offOefNaam);

                listDetails.put(offOefNaam, details);
                listScores.put(offOefNaam, scores);
            } else{
                List<KindOefening> oefeningenLijst = kindOefeningDataService.ListWhereSessieAndWhereOefening(sessieId, Integer.parseInt(oefNaam));
                List<String> scores = new ArrayList<>();
                for(KindOefening oef: oefeningenLijst){
                    Woord woord = woordDataService.getWoord(oef.getWoordID());
                    details.add(woord.getWoord());

                    if(oefNaam == "2"){
                        if(oef.getScore() == 1){
                            scores.add(getResources().getString(R.string.juist));
                        } else{
                            scores.add(getResources().getString(R.string.fout));
                        }
                    } else{
                        scores.add(String.valueOf(oef.getScore()));
                    }
                }

                switch(oefNaam){
                    case "2":
                        offOefNaam = getString(R.string.overzicht_sessie_oef2);
                        offOefnamen.add(offOefNaam);
                        break;
                    case "3":
                        offOefNaam = getString(R.string.overzicht_sessie_oef3);
                        offOefnamen.add(offOefNaam);
                        break;
                    case "4":
                        offOefNaam = getString(R.string.overzicht_sessie_oef4);
                        offOefnamen.add(offOefNaam);
                        break;
                    case "5":
                        offOefNaam = getString(R.string.overzicht_sessie_oef5);
                        offOefnamen.add(offOefNaam);
                        break;
                    case "6":
                        offOefNaam = getString(R.string.overzicht_sessie_oef6_1);
                        offOefnamen.add(offOefNaam);
                        break;
                    case "7":
                        offOefNaam = getString(R.string.overzicht_sessie_oef6_2);
                        offOefnamen.add(offOefNaam);
                        break;
                    case "8":
                        offOefNaam = getString(R.string.overzicht_sessie_oef6_3);
                        offOefnamen.add(offOefNaam);
                        break;
                }
                listDetails.put(offOefNaam, details);
                listScores.put(offOefNaam, scores);
            }
        }
        OverzichtOefeningExpandableAdapter expandableListAdapter = new OverzichtOefeningExpandableAdapter(this, offOefnamen, listDetails, listScores);
        expandableListView.setAdapter(expandableListAdapter);
    }
}
