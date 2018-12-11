package be.thomasmore.projectmobiledevelopment.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import be.thomasmore.projectmobiledevelopment.R;
import be.thomasmore.projectmobiledevelopment.models.Kind;

public class ActivityKinderen extends AppCompatActivity {

    public void vulListView(){
        Kind kind1 = new Kind(1L, "kind1", 1L);
        Kind kind2 = new Kind(2L, "kind2", 1L);
        Kind kind3 = new Kind(3L, "kind3", 1L);
        Kind kind4 = new Kind(3L, "kind4", 1L);
        Kind kind5 = new Kind(3L, "kind5", 1L);
        Kind kind6 = new Kind(3L, "kind6", 1L);
        Kind kind7 = new Kind(3L, "kind7", 1L);


        List<Kind> kinderen = Arrays.asList(kind1, kind2, kind3, kind4, kind5, kind6, kind7);


        ArrayAdapter<Kind> adapter = new ArrayAdapter<Kind>(this, android.R.layout.simple_list_item_1, kinderen);

        final ListView listViewTest = (ListView) findViewById(R.id.listViewTest);
        listViewTest.setAdapter(adapter);
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
