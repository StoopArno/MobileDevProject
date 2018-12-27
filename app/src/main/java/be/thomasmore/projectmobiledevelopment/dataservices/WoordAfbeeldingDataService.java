package be.thomasmore.projectmobiledevelopment.dataservices;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import be.thomasmore.projectmobiledevelopment.App;
import be.thomasmore.projectmobiledevelopment.DatabaseHelper;
import be.thomasmore.projectmobiledevelopment.models.AssociatieWoord;
import be.thomasmore.projectmobiledevelopment.models.WoordAfbeelding;

public class WoordAfbeeldingDataService {

    private DatabaseHelper dbHelper = DatabaseHelper.getInstance(App.getAppContext());

    public List<WoordAfbeelding> getAfbeeldingenByWoord(long woordID){
        List<WoordAfbeelding> lijst = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDB();
        Cursor cursor = db.query(
                "woordafbeeldingen",
                new String[]{"id", "woordID", "afbeeldingNr", "juist"},
                "woordID = ?",
                new String[]{String.valueOf(woordID)},
                null,
                null,
                null,
                null
        );

        if(cursor.moveToFirst()){
            do{
                WoordAfbeelding woordAfbeelding = new WoordAfbeelding(
                        cursor.getLong(0),
                        Long.parseLong(cursor.getString(1)),
                        Integer.parseInt(cursor.getString(2)),
                        Integer.parseInt(cursor.getString(3))
                );
                lijst.add(woordAfbeelding);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        Collections.shuffle(lijst);
        return lijst;
    }

    public WoordAfbeelding getWoordAfbeelding(long id){
        SQLiteDatabase db = dbHelper.getReadableDB();
        Cursor cursor = db.query(
                "woordafbeeldingen",
                new String[]{"id", "woordID", "afbeeldingNr", "juist"},
                "id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        WoordAfbeelding woordAfbeelding = new WoordAfbeelding();

        if(cursor.moveToFirst()){
            woordAfbeelding = new WoordAfbeelding(
                    cursor.getLong(0),
                    Long.parseLong(cursor.getString(1)),
                    Integer.parseInt(cursor.getString(2)),
                    Integer.parseInt(cursor.getString(3))
            );
        }
        cursor.close();
        db.close();
        return woordAfbeelding;
    }
}
