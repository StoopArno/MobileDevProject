package be.thomasmore.projectmobiledevelopment.dataservices;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.projectmobiledevelopment.App;
import be.thomasmore.projectmobiledevelopment.DatabaseHelper;
import be.thomasmore.projectmobiledevelopment.models.Groep;
import be.thomasmore.projectmobiledevelopment.models.Lettergreep;

public class LettergreepDataService {

    private DatabaseHelper dbHelper = DatabaseHelper.getInstance(App.getAppContext());

    public boolean hasLetterGreep(long woordID){
        boolean has = false;

        SQLiteDatabase db = dbHelper.getReadableDB();
        Cursor cursor = db.query(
                "lettergreep",
                new String[]{"id"},
                "woordID = ?",
                new String[]{String.valueOf(woordID)},
                null,
                null,
                null,
                null
        );

        if(cursor.moveToFirst()){
            has = true;
        }

        cursor.close();
        db.close();
        return has;
    }

    public List<Lettergreep> getLetterGrepen(long woordID){
        List<Lettergreep> lijst = new ArrayList<Lettergreep>();

        SQLiteDatabase db = dbHelper.getReadableDB();
        Cursor cursor = db.query(
                "lettergreep",
                new String[]{"id", "positieInWoord", "lettergreep", "woordID"},
                "woordID = ?",
                new String[]{String.valueOf(woordID)},
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                Lettergreep lettergreep = new Lettergreep(
                        cursor.getLong(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getLong(3)
                );
                lijst.add(lettergreep);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }
}
