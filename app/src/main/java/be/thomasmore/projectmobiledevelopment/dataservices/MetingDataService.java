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
import be.thomasmore.projectmobiledevelopment.models.KindSessie;
import be.thomasmore.projectmobiledevelopment.models.Meting;

public class MetingDataService {

    private DatabaseHelper dbHelper = DatabaseHelper.getInstance(App.getAppContext());

    public void addMeting(Long kindSessieID, Long woordID, boolean juist, int metingNr){
        SQLiteDatabase db = dbHelper.getWriteableDB();
        String sql = "INSERT INTO meting (metingNr, juist, woordID, sessieID) VALUES (?, ?, ?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);

        int juistID = (juist)? 1 : 0;

        statement.bindLong(1, metingNr);
        statement.bindLong(2, juistID);
        statement.bindLong(3, woordID);
        statement.bindLong(4, kindSessieID);

        statement.executeInsert();
    }

    public List<Meting> MetingWhereSessieId(int metingsNr, long sessieId){
        SQLiteDatabase db = dbHelper.getReadableDB();
        Cursor cursor = db.query(
                "meting",      // tabelnaam
                new String[]{"id", "metingNr", "juist", "woordID", "sessieID"}, // kolommen
                "sessieID = ? AND metingNr=?",  // selectie
                new String[]{String.valueOf(sessieId), String.valueOf(metingsNr)}, // selectieparameters
                null,           // groupby
                null,           // having
                null,           // sorting
                null
        );


        List<Meting> metingen = new ArrayList<Meting>();

        Meting meting = new Meting();
        while(cursor.moveToNext()){

            meting = new Meting(
                    Long.parseLong(cursor.getString(0)),
                    cursor.getInt(1),
                    Boolean.parseBoolean(cursor.getString(2)),
                    Long.parseLong(cursor.getString(3)),
                    Long.parseLong(cursor.getString(4))
            );

            // Make sure boolean is parsed correctly
            if(cursor.getInt(2) == 1){
                meting.setJuist(true);
            }
            metingen.add(meting);
        }

        cursor.close();
        db.close();
        return metingen;
    }
}
