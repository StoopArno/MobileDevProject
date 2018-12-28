package be.thomasmore.projectmobiledevelopment.dataservices;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import be.thomasmore.projectmobiledevelopment.App;
import be.thomasmore.projectmobiledevelopment.DatabaseHelper;
import be.thomasmore.projectmobiledevelopment.models.Woord;

public class ContextDataService {

    private DatabaseHelper dbHelper = DatabaseHelper.getInstance(App.getAppContext());

    public String getJuisteContextZin(long woordID){
        SQLiteDatabase db = dbHelper.getReadableDB();
        Cursor cursor = db.query(
                "contextjuist",
                new String[]{"id", "woordID", "zin"},
                "woordID = ?",
                new String[]{String.valueOf(woordID)},
                null,
                null,
                null,
                null
        );

        String zin = "";

        if(cursor.moveToFirst()){
            zin = cursor.getString(2);
        }
        cursor.close();
        db.close();
        return zin;
    }

    public String getFouteContextZin(long woordID){
        SQLiteDatabase db = dbHelper.getReadableDB();
        Cursor cursor = db.query(
                "contextfout",
                new String[]{"id", "woordID", "zin"},
                "woordID = ?",
                new String[]{String.valueOf(woordID)},
                null,
                null,
                null,
                null
        );

        String zin = "";

        if(cursor.moveToFirst()){
            zin = cursor.getString(2);
        }
        cursor.close();
        db.close();
        return zin;
    }
}
