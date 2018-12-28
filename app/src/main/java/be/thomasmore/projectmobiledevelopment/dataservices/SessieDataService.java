package be.thomasmore.projectmobiledevelopment.dataservices;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import be.thomasmore.projectmobiledevelopment.App;
import be.thomasmore.projectmobiledevelopment.DatabaseHelper;
import be.thomasmore.projectmobiledevelopment.models.KindSessie;

public class SessieDataService {

    private DatabaseHelper dbHelper = DatabaseHelper.getInstance(App.getAppContext());

    public Long addSessie(KindSessie kindSessie){
        SQLiteDatabase db = dbHelper.getWriteableDB();
        String sql = "INSERT INTO kindsessie (kindID) VALUES (?)";
        SQLiteStatement statement = db.compileStatement(sql);

        statement.bindLong(1, kindSessie.getKindID());

        return statement.executeInsert();
    }

    public long countSessieWhereKindId(long kindId){
        SQLiteDatabase db = dbHelper.getReadableDB();
        return DatabaseUtils.queryNumEntries(db, "KindSessie", "kindID=?", new String[]{kindId + ""});
    }
}
