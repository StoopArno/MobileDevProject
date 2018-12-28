package be.thomasmore.projectmobiledevelopment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.projectmobiledevelopment.R;
import be.thomasmore.projectmobiledevelopment.dataservices.GroepDataService;
import be.thomasmore.projectmobiledevelopment.dataservices.KindDataService;
import be.thomasmore.projectmobiledevelopment.models.Groep;
import be.thomasmore.projectmobiledevelopment.models.Kind;

public class EditKinderen extends AppCompatActivity {

    private Kind kind;
    private KindDataService kindDataService = new KindDataService();
    private GroepDataService groepDataService = new GroepDataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kinderen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Long kindId = getIntent().getLongExtra("kindId", 0);
        if(kindId != 0){
            kind = kindDataService.getKind(kindId);
        } else{
            kind = new Kind();
        }
        vulVelden();
    }

    public void onClickSave(View v){
        EditText editText = (EditText) findViewById(R.id.editKindNaam);
        Spinner dropdownGroep = (Spinner) findViewById(R.id.editKindGroep);
        kind.setNaam(editText.getText().toString());
        kind.setGroepID(dropdownGroep.getSelectedItemId() + 1);

        if(kind.getId() == 0){
            kindDataService.addKind(kind);
        } else{
            kindDataService.updatekind(kind);
        }
        Intent intent = new Intent(this, LijstKinderen.class);
        startActivity(intent);
    }

    private void vulVelden(){
        EditText editViewNaam = (EditText) findViewById(R.id.editKindNaam);
        Spinner dropdownGroep = (Spinner) findViewById(R.id.editKindGroep);

        List<String> dropdownItems = new ArrayList<String>();
        List<Groep> groepen = groepDataService.getGroepen();
        for (Groep groep: groepen ) {
            dropdownItems.add("Groep " + groep.getId());
        }

        editViewNaam.setText(kind.getNaam());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dropdownItems);

        dropdownGroep.setAdapter(adapter);
        Integer i = kind.getGroepID() != null ? kind.getGroepID().intValue() : 1;
        dropdownGroep.setSelection(i-1);
    }
}
