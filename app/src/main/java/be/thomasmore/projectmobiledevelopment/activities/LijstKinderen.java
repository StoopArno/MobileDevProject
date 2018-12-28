package be.thomasmore.projectmobiledevelopment.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import be.thomasmore.projectmobiledevelopment.MainActivity;
import be.thomasmore.projectmobiledevelopment.R;
import be.thomasmore.projectmobiledevelopment.adapters.KinderenListAdapter;
import be.thomasmore.projectmobiledevelopment.dataservices.KindDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.SessieDataService;
import be.thomasmore.projectmobiledevelopment.models.Kind;

public class LijstKinderen extends AppCompatActivity {

    private KindDataService kindDataService = new KindDataService();

    public void vulListView(){
        View.OnClickListener editListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditKinderen((Long)v.getTag());
            }
        };
        View.OnClickListener delListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long kindId = (long)v.getTag();
                openConfirmDialog(kindId);
            }
        };
        View.OnClickListener startSessieListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long kindId = (long)v.getTag();
                toonPreteachingPlaat(kindId);
            }
        };

        List<Kind> kinderen = kindDataService.getKinderen();

        KinderenListAdapter adapter = new KinderenListAdapter(getApplicationContext(), kinderen, editListener, delListener, startSessieListner);

        final ListView listViewKinderen = (ListView) findViewById(R.id.listViewKinderen);
        listViewKinderen.setAdapter(adapter);
    }

    public void onClickNewKind(View v){
        openEditKinderen(0);
    }

    private void toonPreteachingPlaat(long kindId){
        Intent intent = new Intent(this, PreteachingPlaat.class);
        intent.putExtra("kindId", kindId);
        startActivity(intent);
    }

    private void openConfirmDialog(final long kindId){
        new AlertDialog.Builder(this)
                .setMessage("Bent u zeker dat u deze rij wilt verwijderen?")
                .setCancelable(false)
                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        kindDataService.deleteKind(kindId);
                        vulListView();
                    }
                })
                .setNegativeButton("Nee", null)
                .show();
    }

    private void openEditKinderen(long kindId){
        Intent intent = new Intent(this, EditKinderen.class);
        intent.putExtra("kindId", kindId);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lijst_kinderen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vulListView();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
