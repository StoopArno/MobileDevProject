package be.thomasmore.projectmobiledevelopment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import be.thomasmore.projectmobiledevelopment.models.Groep;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper sInstance;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "logopedie-project";

    public static synchronized DatabaseHelper getInstance(Context context){
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteDatabase getReadableDB(){
        return this.getReadableDatabase();
    }

    public SQLiteDatabase getWriteableDB(){
        return this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_CONDITIEGROEP = "CREATE TABLE conditiegroep (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "conditieNr INTEGER," +
                "groepID INTEGER," +
                "FOREIGN KEY (groepID) REFERENCES groep(id))";
        db.execSQL(CREATE_TABLE_CONDITIEGROEP);

        String CREATE_TABLE_GROEP = "CREATE TABLE groep (" +
                "id INTEGER PRIMARY KEY)";
        db.execSQL(CREATE_TABLE_GROEP);

        String CREATE_TABLE_KIND = "CREATE TABLE kind (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "naam TEXT," +
                "groepID INTEGER," +
                "FOREIGN KEY (groepID) REFERENCES groep(id))";
        db.execSQL(CREATE_TABLE_KIND);

        String CREATE_TABLE_KINDOEFENING = "CREATE TABLE kindoefening (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sessieID INTEGER," +
                "oefeningNr INTEGER," +
                "score INTEGER," +
                "FOREIGN KEY (sessieID) REFERENCES kindsessie(id))";
        db.execSQL(CREATE_TABLE_KINDOEFENING);

        String CREATE_TABLE_KINDSESSIE = "CREATE TABLE kindsessie (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "kindID INTEGER," +
                "woordGroepID INTEGER," +
                "FOREIGN KEY (kindID) REFERENCES kind(id)," +
                "FOREIGN KEY (woordGroepID) REFERENCES woordgroep(id))";
        db.execSQL(CREATE_TABLE_KINDSESSIE);

        String CREATE_TABLE_LETTERGREEP = "CREATE TABLE lettergreep (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "positieInWoord INTEGER," +
                "lettergreep TEXT," +
                "woordID INTEGER," +
                "FOREIGN KEY (woordID) REFERENCES woord(id))";
        db.execSQL(CREATE_TABLE_LETTERGREEP);

        String CREATE_TABLE_METING = "CREATE TABLE meting (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "metingNr INTEGER," +
                "score INTEGER," +
                "woordID INTEGER," +
                "sessieID INTEGER," +
                "FOREIGN KEY (woordID) REFERENCES woord(id)," +
                "FOREIGN KEY (sessieID) REFERENCES sessie(id))";
        db.execSQL(CREATE_TABLE_METING);

        String CREATE_TABLE_WOORD = "CREATE TABLE woord (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "woordGroepID INTEGER," +
                "woord TEXT," +
                "FOREIGN KEY (woordGroepID) REFERENCES woordgroep(id))";
        db.execSQL(CREATE_TABLE_WOORD);

        String CREATE_TABLE_WOORDGROEP = "CREATE TABLE woordgroep (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "woordGroep TEXT)";
        db.execSQL(CREATE_TABLE_WOORDGROEP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS conditiegroep");

        // Create tables again
        onCreate(db);
    }
}