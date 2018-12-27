package be.thomasmore.projectmobiledevelopment.dataservices;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import be.thomasmore.projectmobiledevelopment.App;
import be.thomasmore.projectmobiledevelopment.DatabaseHelper;
import be.thomasmore.projectmobiledevelopment.models.KindSessie;

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
}
