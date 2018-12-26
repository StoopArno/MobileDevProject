package be.thomasmore.projectmobiledevelopment.dataservices;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import be.thomasmore.projectmobiledevelopment.App;
import be.thomasmore.projectmobiledevelopment.DatabaseHelper;

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
}
