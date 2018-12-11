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

    //Aanmaken van de database
    @Override
    public void onCreate(SQLiteDatabase db) {
        //aanmaken logica
        String CREATE_TABLE_CONDITIEGROEP = "CREATE TABLE conditiegroep (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "conditieNr INTEGER," +
                "groepID INTEGER," +
                "woordGroepID INTEGER," +
                "FOREIGN KEY (groepID) REFERENCES groep(id)," +
                "FOREIGN KEY (woordGroepID) REFERENCES woordgroep(id))";
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
                "id INTEGER PRIMARY KEY," +
                "woordGroepID INTEGER," +
                "woord TEXT," +
                "FOREIGN KEY (woordGroepID) REFERENCES woordgroep(id))";
        db.execSQL(CREATE_TABLE_WOORD);

        String CREATE_TABLE_WOORDGROEP = "CREATE TABLE woordgroep (" +
                "id INTEGER PRIMARY KEY," +
                "woordGroep TEXT)";
        db.execSQL(CREATE_TABLE_WOORDGROEP);

        //wegschrijven data
        insertGroep(db);
        insertLetterGreep(db);
        insertWoordGroep(db);
        insertWoord(db);
        insertConditieGroep(db);
    }

    //functies voor het wegschrijven van data
    private void insertGroep(SQLiteDatabase db){
        db.execSQL("INSERT INTO groep (id) VALUES (1)");
        db.execSQL("INSERT INTO groep (id) VALUES (2)");
        db.execSQL("INSERT INTO groep (id) VALUES (3)");
    }

    private void insertLetterGreep(SQLiteDatabase db){
        db.execSQL("INSERT INTO lettergreep (positieInWoord, lettergreep, woordID) VALUES (1, 'Duik', 10)");
        db.execSQL("INSERT INTO lettergreep (positieInWoord, lettergreep, woordID) VALUES (2, 'bril', 10)");
        db.execSQL("INSERT INTO lettergreep (positieInWoord, lettergreep, woordID) VALUES (1, 'Klim', 1)");
        db.execSQL("INSERT INTO lettergreep (positieInWoord, lettergreep, woordID) VALUES (2, 'touw', 1)");
        db.execSQL("INSERT INTO lettergreep (positieInWoord, lettergreep, woordID) VALUES (1, 'Kom', 5)");
        db.execSQL("INSERT INTO lettergreep (positieInWoord, lettergreep, woordID) VALUES (2, 'pas', 5)");
        db.execSQL("INSERT INTO lettergreep (positieInWoord, lettergreep, woordID) VALUES (1, 'Zak', 9)");
        db.execSQL("INSERT INTO lettergreep (positieInWoord, lettergreep, woordID) VALUES (2, 'Lamp', 9)");
    }

    private void insertWoordGroep(SQLiteDatabase db){
        db.execSQL("INSERT INTO woordgroep (id, woordGroep) VALUES (1, 'A')");
        db.execSQL("INSERT INTO woordgroep (id, woordGroep) VALUES (2, 'B')");
        db.execSQL("INSERT INTO woordgroep (id, woordGroep) VALUES (3, 'C')");
        db.execSQL("INSERT INTO woordgroep (id, woordGroep) VALUES (4, 'D')");
    }

    private void insertWoord(SQLiteDatabase db){
        db.execSQL("INSERT INTO woord (id, woordGroepID, woord) VALUES (1, 1, 'Klimtouw')");
        db.execSQL("INSERT INTO woord (id, woordGroepID, woord) VALUES (2, 1, 'Kroos')");
        db.execSQL("INSERT INTO woord (id, woordGroepID, woord) VALUES (3, 1, 'Riet')");
        db.execSQL("INSERT INTO woord (id, woordGroepID, woord) VALUES (4, 2, 'Val')");
        db.execSQL("INSERT INTO woord (id, woordGroepID, woord) VALUES (5, 2, 'Kompas')");
        db.execSQL("INSERT INTO woord (id, woordGroepID, woord) VALUES (6, 2, 'Steil')");
        db.execSQL("INSERT INTO woord (id, woordGroepID, woord) VALUES (7, 3, 'Zwaan')");
        db.execSQL("INSERT INTO woord (id, woordGroepID, woord) VALUES (8, 3, 'Kamp')");
        db.execSQL("INSERT INTO woord (id, woordGroepID, woord) VALUES (9, 3, 'Zaklamp')");
        db.execSQL("INSERT INTO woord (id, woordGroepID, woord) VALUES (10, 4, 'Duikbril')");
    }

    private void insertConditieGroep(SQLiteDatabase db){
        db.execSQL("INSERT INTO conditiegroep (conditieNr, groepID, woordGroepID) VALUES(1, 1, 1)");
        db.execSQL("INSERT INTO conditiegroep (conditieNr, groepID, woordGroepID) VALUES(1, 2, 3)");
        db.execSQL("INSERT INTO conditiegroep (conditieNr, groepID, woordGroepID) VALUES(1, 3, 2)");
        db.execSQL("INSERT INTO conditiegroep (conditieNr, groepID, woordGroepID) VALUES(2, 1, 2)");
        db.execSQL("INSERT INTO conditiegroep (conditieNr, groepID, woordGroepID) VALUES(2, 2, 1)");
        db.execSQL("INSERT INTO conditiegroep (conditieNr, groepID, woordGroepID) VALUES(2, 3, 3)");
        db.execSQL("INSERT INTO conditiegroep (conditieNr, groepID, woordGroepID) VALUES(3, 1, 3)");
        db.execSQL("INSERT INTO conditiegroep (conditieNr, groepID, woordGroepID) VALUES(3, 2, 2)");
        db.execSQL("INSERT INTO conditiegroep (conditieNr, groepID, woordGroepID) VALUES(3, 3, 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS conditiegroep");

        // Create tables again
        onCreate(db);
    }
}