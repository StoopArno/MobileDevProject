package be.thomasmore.projectmobiledevelopment.dataservices;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.projectmobiledevelopment.App;
import be.thomasmore.projectmobiledevelopment.DatabaseHelper;
import be.thomasmore.projectmobiledevelopment.models.Woord;

public class WoordDataService {

    private DatabaseHelper dbHelper = DatabaseHelper.getInstance(App.getAppContext());

    public Woord getWoord(long id){
        SQLiteDatabase db = dbHelper.getReadableDB();
        Cursor cursor = db.query(
                "woord",      // tabelnaam
                new String[]{"id", "woord", "woordGroepID"}, // kolommen
                "id = ?",  // selectie
                new String[]{String.valueOf(id)}, // selectieparameters
                null,           // groupby
                null,           // having
                null,           // sorting
                null
        );

        Woord woord = new Woord();

        if (cursor.moveToFirst()) {
            woord = new Woord(
                    Long.parseLong(cursor.getString(0)),
                    cursor.getString(1),
                    Long.parseLong(cursor.getString(2))
            );
        }
        cursor.close();
        db.close();
        return woord;
    }

    public List<Woord> getWoorden(){
        List<Woord> lijst = new ArrayList<Woord>();

        SQLiteDatabase db = dbHelper.getReadableDB();
        Cursor cursor = db.rawQuery(
                "SELECT  * FROM woord",
                null
        );

        if (cursor.moveToFirst()) {
            do {
                Woord woord = new Woord(
                        cursor.getLong(0),
                        cursor.getString(2),
                        Long.parseLong(cursor.getString(1))
                );
                lijst.add(woord);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }

    public List<Woord> getWoordenByGroep(long woordGroepID){
        List<Woord> lijst = new ArrayList<Woord>();

        SQLiteDatabase db = dbHelper.getReadableDB();
        Cursor cursor = db.query(
                "woord",      // tabelnaam
                new String[]{"id", "woord", "woordGroepID"}, // kolommen
                "woordGroepID = ?",  // selectie
                new String[]{String.valueOf(woordGroepID)}, // selectieparameters
                null,           // groupby
                null,           // having
                null,           // sorting
                null
        );

        if (cursor.moveToFirst()) {
            do {
                Woord woord = new Woord(
                        cursor.getLong(0),
                        cursor.getString(1),
                        Long.parseLong(cursor.getString(2))
                );
                lijst.add(woord);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }
}
