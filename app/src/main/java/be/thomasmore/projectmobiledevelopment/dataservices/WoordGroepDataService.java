package be.thomasmore.projectmobiledevelopment.dataservices;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import be.thomasmore.projectmobiledevelopment.App;
import be.thomasmore.projectmobiledevelopment.DatabaseHelper;
import be.thomasmore.projectmobiledevelopment.models.Woord;
import be.thomasmore.projectmobiledevelopment.models.Woordgroep;

public class WoordGroepDataService {

    private DatabaseHelper dbHelper = DatabaseHelper.getInstance(App.getAppContext());

    public Woordgroep getWoordGroepByWoord(long woordGroepID){
        SQLiteDatabase db = dbHelper.getReadableDB();
        Cursor cursor = db.query(
                "woordgroep",      // tabelnaam
                new String[]{"id", "woordgroep"}, // kolommen
                "id = ?",  // selectie
                new String[]{String.valueOf(woordGroepID)}, // selectieparameters
                null,           // groupby
                null,           // having
                null,           // sorting
                null
        );

        Woordgroep woordgroep = new Woordgroep();

        if (cursor.moveToFirst()) {
            woordgroep = new Woordgroep(
                    Long.parseLong(cursor.getString(0)),
                    cursor.getString(1)
            );
        }
        cursor.close();
        db.close();
        return woordgroep;
    }
}
