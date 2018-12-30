package be.thomasmore.projectmobiledevelopment.dataservices;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import be.thomasmore.projectmobiledevelopment.App;
import be.thomasmore.projectmobiledevelopment.DatabaseHelper;
import be.thomasmore.projectmobiledevelopment.models.AssociatieWoord;
import be.thomasmore.projectmobiledevelopment.models.ConditieGroep;
import be.thomasmore.projectmobiledevelopment.models.Kind;
import be.thomasmore.projectmobiledevelopment.models.Woord;

public class ConditieDataService {

    private DatabaseHelper dbHelper = DatabaseHelper.getInstance(App.getAppContext());

    public ConditieGroep getCondieNr(long groepID, long woordGroepID){
        SQLiteDatabase db = dbHelper.getReadableDB();
        Cursor cursor = db.query(
                "conditiegroep",
                new String[]{"id", "conditieNr", "groepID", "woordGroepID"},
                "groepID = ? AND woordGroepID = ?",
                new String[]{String.valueOf(groepID), String.valueOf(woordGroepID)},
                null,
                null,
                null,
                null
        );

        ConditieGroep conditieGroep = new ConditieGroep();

        if(cursor.moveToFirst()){
            conditieGroep = new ConditieGroep(
                    cursor.getLong(0),
                    Integer.parseInt(cursor.getString(1)),
                    Long.parseLong(cursor.getString(2)),
                    Long.parseLong(cursor.getString(3))
            );
        }

        cursor.close();
        db.close();
        return conditieGroep;
    }
}
