package be.thomasmore.projectmobiledevelopment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import be.thomasmore.projectmobiledevelopment.R;
import be.thomasmore.projectmobiledevelopment.dataservices.GroepDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.KindDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.MetingDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.SessieDataService;
import be.thomasmore.projectmobiledevelopment.models.Groep;
import be.thomasmore.projectmobiledevelopment.models.Kind;
import be.thomasmore.projectmobiledevelopment.models.KindSessie;
import be.thomasmore.projectmobiledevelopment.models.Meting;

public class OverzichtSessie extends AppCompatActivity {

    private static long sessieId;
    private MetingDataService metingDataService = new MetingDataService();
    private KindDataService kindDataService = new KindDataService();
    private SessieDataService sessieDataService = new SessieDataService();
    private GroepDataService groepDataService = new GroepDataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overzicht_sessie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sessieId = getIntent().getLongExtra("sessieId", 1);
        vulVelden();
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

}
