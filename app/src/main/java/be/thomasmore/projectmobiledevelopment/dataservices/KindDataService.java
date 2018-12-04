package be.thomasmore.projectmobiledevelopment.dataservices;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.projectmobiledevelopment.App;
import be.thomasmore.projectmobiledevelopment.DatabaseHelper;
import be.thomasmore.projectmobiledevelopment.models.Kind;

public class KindDataService {

    private DatabaseHelper dbHelper = DatabaseHelper.getInstance(App.getAppContext());
    private GroepDataService groepDS = new GroepDataService();

    public Kind getKind(long id){
        SQLiteDatabase db = dbHelper.getReadableDB();
        Cursor cursor = db.query(
                "land",      // tabelnaam
                new String[]{"id", "naam", "hoofdstad", "inwoners", "vlag", "oppervlakte", "munteenheid", "uitleg", "bezocht"}, // kolommen
                "id = ?",  // selectie
                new String[]{String.valueOf(id)}, // selectieparameters
                null,           // groupby
                null,           // having
                null,           // sorting
                null
        );

        Kind kind = new Kind();

        if (cursor.moveToFirst()) {
            kind = new Kind(
                    Long.parseLong(cursor.getString(0)),
                    cursor.getString(1),
                    groepDS.getGroep(Long.parseLong(cursor.getString(4)))
            );
        }
        cursor.close();
        db.close();
        return kind;
    }

    public List<Kind> getKinderen(){
        List<Kind> lijst = new ArrayList<Kind>();

        SQLiteDatabase db = dbHelper.getReadableDB();
        Cursor cursor = db.rawQuery(
                "SELECT  * FROM kind",
                null
        );

        if (cursor.moveToFirst()) {
            do {
                Kind kind = new Kind(
                        cursor.getLong(0),
                        cursor.getString(1),
                        groepDS.getGroep(Long.parseLong(cursor.getString(2)))
                );
                lijst.add(kind);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }
}
