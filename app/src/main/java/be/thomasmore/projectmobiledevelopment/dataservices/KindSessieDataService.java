package be.thomasmore.projectmobiledevelopment.dataservices;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import be.thomasmore.projectmobiledevelopment.App;
import be.thomasmore.projectmobiledevelopment.DatabaseHelper;
import be.thomasmore.projectmobiledevelopment.models.ConditieGroep;
import be.thomasmore.projectmobiledevelopment.models.KindSessie;

public class KindSessieDataService {

    private DatabaseHelper dbHelper = DatabaseHelper.getInstance(App.getAppContext());

    public KindSessie getKindSessie(long id){
        SQLiteDatabase db = dbHelper.getReadableDB();
        Cursor cursor = db.query(
                "kindsessie",
                new String[]{"id", "kindID"},
                "id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        KindSessie kindSessie = new KindSessie();

        if(cursor.moveToFirst()){
            kindSessie = new KindSessie(
                    cursor.getLong(0),
                    Long.parseLong(cursor.getString(1))
            );
        }

        cursor.close();
        db.close();
        return kindSessie;
    }
}
