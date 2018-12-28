package be.thomasmore.projectmobiledevelopment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import be.thomasmore.projectmobiledevelopment.App;
import be.thomasmore.projectmobiledevelopment.R;
import be.thomasmore.projectmobiledevelopment.adapters.KinderenListAdapter;
import be.thomasmore.projectmobiledevelopment.adapters.OverzichtKinderenListAdapter;
import be.thomasmore.projectmobiledevelopment.dataservices.KindDataService;
import be.thomasmore.projectmobiledevelopment.models.Kind;

public class OverzichtLijstKinderen extends AppCompatActivity {

    private KindDataService kindDataService = new KindDataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overzicht_lijst_kinderen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vulListView();
    }

    public void vulListView(){
        View.OnClickListener toonSessieListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //long kindId = (long)v.getTag();
                toonSessie();
            }
        };
        View.OnClickListener geenSessieListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(App.getAppContext(), "Geen sessies beschikbaar voor dit kind.", Toast.LENGTH_LONG).show();
            }
        };

        List<Kind> kinderen = kindDataService.getKinderen();

        OverzichtKinderenListAdapter adapter = new OverzichtKinderenListAdapter(getApplicationContext(), kinderen, toonSessieListner, geenSessieListener);

        final ListView overzichtKinderenListView = (ListView) findViewById(R.id.overzichtKinderenListView);
        overzichtKinderenListView.setAdapter(adapter);
    }

    private void toonSessie(){
        Intent intent = new Intent(this, OverzichtSessie.class);
        startActivity(intent);
    }

}
