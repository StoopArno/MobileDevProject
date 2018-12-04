package be.thomasmore.projectmobiledevelopment.dataservices;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.projectmobiledevelopment.App;
import be.thomasmore.projectmobiledevelopment.DatabaseHelper;
import be.thomasmore.projectmobiledevelopment.models.Groep;

public class GroepDataService {

    private DatabaseHelper dbHelper = DatabaseHelper.getInstance(App.getAppContext());

    public Groep getGroep(long id){
        SQLiteDatabase db = dbHelper.getReadableDB();
        Cursor cursor = db.query(
                "groep",
                new String[]{"id"},
                "id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        Groep groep = new Groep();
        if(cursor.moveToFirst()){
            groep = new Groep(
                    Long.parseLong(cursor.getString(0))
            );
        }
        cursor.close();
        db.close();
        return groep;
    }

    public List<Groep> getGroepen(){
        List<Groep> lijst = new ArrayList<Groep>();

        SQLiteDatabase db = dbHelper.getReadableDB();
        Cursor cursor = db.rawQuery(
                "SELECT  * FROM groep",
                null
        );

        if (cursor.moveToFirst()) {
            do {
                Groep groep = new Groep(
                        cursor.getLong(0)
                );
                lijst.add(groep);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }
}
