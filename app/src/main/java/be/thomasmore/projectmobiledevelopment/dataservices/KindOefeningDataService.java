package be.thomasmore.projectmobiledevelopment.dataservices;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.projectmobiledevelopment.App;
import be.thomasmore.projectmobiledevelopment.DatabaseHelper;
import be.thomasmore.projectmobiledevelopment.models.KindOefening;
import be.thomasmore.projectmobiledevelopment.models.Meting;

public class KindOefeningDataService {

    private DatabaseHelper dbHelper = DatabaseHelper.getInstance(App.getAppContext());

    public void addKindOefening(Long kindSessieID, Long woordID, int oefeningNr, int score){
        SQLiteDatabase db = dbHelper.getWriteableDB();
        String sql = "INSERT INTO kindoefening (sessieID, woordID, oefeningNr, score) VALUES (?, ?, ?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);

        statement.bindLong(1, kindSessieID);
        statement.bindLong(2, woordID);
        statement.bindLong(3, oefeningNr);
        statement.bindLong(4, score);

        statement.executeInsert();
    }

    public List<KindOefening> ListWhereSessieAndWhereOefening(long sessieId, int oefNr){
        SQLiteDatabase db = dbHelper.getReadableDB();
        Cursor cursor = db.query(
                "kindoefening",      // tabelnaam
                new String[]{"id", "score", "oefeningNr", "sessieID", "woordID"}, // kolommen
                "sessieID = ? AND oefeningNr=?",  // selectie
                new String[]{String.valueOf(sessieId), String.valueOf(oefNr)}, // selectieparameters
                null,           // groupby
                null,           // having
                null,           // sorting
                null
        );


        List<KindOefening> kindoefeningen = new ArrayList<KindOefening>();
        Log.d("test", "ListWhereSessieAndWhereOefening: " + DatabaseUtils.dumpCursorToString(cursor));
        KindOefening kindOefening = new KindOefening();
        while(cursor.moveToNext()){

            kindOefening = new KindOefening(
                    Long.parseLong(cursor.getString(0)),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    Long.parseLong(cursor.getString(3)),
                    Long.parseLong(cursor.getString(4))
            );
            kindoefeningen.add(kindOefening);
        }

        cursor.close();
        db.close();
        return kindoefeningen;
    }
}
