package be.thomasmore.projectmobiledevelopment.dataservices;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import be.thomasmore.projectmobiledevelopment.App;
import be.thomasmore.projectmobiledevelopment.DatabaseHelper;
import be.thomasmore.projectmobiledevelopment.models.AssociatieWoord;
import be.thomasmore.projectmobiledevelopment.models.Woord;

public class AssociatieDataService {

    private DatabaseHelper dbHelper = DatabaseHelper.getInstance(App.getAppContext());

    public List<AssociatieWoord> getAssociatieByWoord(long woordID){
        List<AssociatieWoord> lijst = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDB();
        Cursor cursor = db.query(
                "associatiewoorden",
                new String[]{"id", "woordID", "afbeeldingNr", "woord", "juist"},
                "woordID = ?",
                new String[]{String.valueOf(woordID)},
                null,
                null,
                null,
                null
        );

        if(cursor.moveToFirst()){
            do{
                AssociatieWoord associatie = new AssociatieWoord(
                        cursor.getLong(0),
                        Long.parseLong(cursor.getString(1)),
                        Integer.parseInt(cursor.getString(2)),
                        cursor.getString(3),
                        Integer.parseInt(cursor.getString(4))
                );
                lijst.add(associatie);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        Collections.shuffle(lijst);
        return lijst;
    }

    public AssociatieWoord getAssociatie(long id){
        SQLiteDatabase db = dbHelper.getReadableDB();
        Cursor cursor = db.query(
                "associatiewoorden",
                new String[]{"id", "woordID", "afbeeldingNr", "woord", "juist"},
                "id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        AssociatieWoord associatieWoord = new AssociatieWoord();

        if(cursor.moveToFirst()){
            associatieWoord = new AssociatieWoord(
                    cursor.getLong(0),
                    Long.parseLong(cursor.getString(1)),
                    Integer.parseInt(cursor.getString(2)),
                    cursor.getString(3),
                    Integer.parseInt(cursor.getString(4))
            );
        }
        cursor.close();
        db.close();
        return associatieWoord;
    }
}
