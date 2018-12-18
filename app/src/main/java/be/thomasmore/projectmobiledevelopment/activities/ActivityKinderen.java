package be.thomasmore.projectmobiledevelopment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

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
                Log.d("test", "onClick: EDIT" + v.getTag());
            }
        };
        View.OnClickListener delListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "onClick: DEL" + v.getTag());
            }
        };
        View.OnClickListener startSessieListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "onClick: ROOT" + v.getTag());
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

    private void openEditKinderen(long kindId){
        Intent intent = new Intent(this, KinderenEdit.class);
        intent.putExtra("kindId", kindId);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinderen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vulListView();
    }

}
