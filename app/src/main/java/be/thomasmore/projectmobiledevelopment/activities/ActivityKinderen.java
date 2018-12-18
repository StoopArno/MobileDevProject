package be.thomasmore.projectmobiledevelopment.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import be.thomasmore.projectmobiledevelopment.App;
import be.thomasmore.projectmobiledevelopment.MainActivity;
import be.thomasmore.projectmobiledevelopment.R;
import be.thomasmore.projectmobiledevelopment.adapters.KinderenListAdapter;
import be.thomasmore.projectmobiledevelopment.dataservices.KindDataService;
import be.thomasmore.projectmobiledevelopment.models.Kind;

public class ActivityKinderen extends AppCompatActivity {

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
                Log.d("test", "onClick: ROOT" + v.getTag());
                Toast.makeText(App.getAppContext(), "Vanuit deze knop zal je een sessie met een kind kunnen starten", Toast.LENGTH_LONG).show();
            }
        };

        List<Kind> kinderen = kindDataService.getKinderen();

        KinderenListAdapter adapter = new KinderenListAdapter(getApplicationContext(), kinderen, editListener, delListener, startSessieListner);

        final ListView listViewTest = (ListView) findViewById(R.id.listViewTest);
        listViewTest.setAdapter(adapter);
    }

    public void onClickNewKind(View v){
        openEditKinderen(0);
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
        Intent intent = new Intent(this, KinderenEdit.class);
        intent.putExtra("kindId", kindId);
        startActivity(intent);
    }

    private void startSessie(long kindID){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinderen);
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
