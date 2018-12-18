package be.thomasmore.projectmobiledevelopment.dataservices;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

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
                "kind",      // tabelnaam
                new String[]{"id", "naam", "groepID"}, // kolommen
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
                    Long.parseLong(cursor.getString(2))
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
                        Long.parseLong(cursor.getString(2))
                );
                lijst.add(kind);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }

    public void addKind(Kind kind){
        SQLiteDatabase db = dbHelper.getWriteableDB();
        String sql = "INSERT INTO kind (naam, groepID) VALUES (?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);

        statement.bindString(1, kind.getNaam());
        statement.bindLong(2, kind.getGroepID());

        statement.executeInsert();
    }

    public void updatekind(Kind kind){
        SQLiteDatabase db = dbHelper.getWriteableDB();
        String sql = "UPDATE kind SET naam=?, groepID=? WHERE id=?";
        SQLiteStatement statement = db.compileStatement(sql);

        statement.bindString(1, kind.getNaam());
        statement.bindLong(2, kind.getGroepID());
        statement.bindLong(3, kind.getId());

        statement.executeUpdateDelete();
    }

    public void deleteKind(Long id){
        SQLiteDatabase db = dbHelper.getWriteableDB();
        String sql = "DELETE FROM kind WHERE id=?";
        SQLiteStatement statement = db.compileStatement(sql);

        statement.bindLong(1, id);

        statement.executeUpdateDelete();
    }
}
