package be.thomasmore.projectmobiledevelopment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
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

    public DatabaseHelper(Context context) {
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
                "woordID INTEGER," +
                "oefeningNr INTEGER," +
                "score INTEGER," +
                "FOREIGN KEY (woordID) REFERENCES woord(id)," +
                "FOREIGN KEY (sessieID) REFERENCES kindsessie(id))";
        db.execSQL(CREATE_TABLE_KINDOEFENING);

        String CREATE_TABLE_KINDSESSIE = "CREATE TABLE kindsessie (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "kindID INTEGER," +
                "FOREIGN KEY (kindID) REFERENCES kind(id))";
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
                "juist INTEGER," +
                "woordID INTEGER," +
                "sessieID INTEGER," +
                "FOREIGN KEY (woordID) REFERENCES woord(id)," +
                "FOREIGN KEY (sessieID) REFERENCES kindsessie(id))";
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

        String CREATE_TABLE_CONTEXTJUIST = "CREATE TABLE contextjuist (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "woordID INTEGER," +
                "zin TEXT," +
                "FOREIGN KEY (woordID) REFERENCES woord(id))";
        db.execSQL(CREATE_TABLE_CONTEXTJUIST);

        String CREATE_TABLE_CONTEXTFOUT = "CREATE TABLE contextfout (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "woordID INTEGER," +
                "zin TEXT," +
                "FOREIGN KEY (woordID) REFERENCES woord(id))";
        db.execSQL(CREATE_TABLE_CONTEXTFOUT);

        String CREATE_TABLE_ASSOCIATIEWOORDEN = "CREATE TABLE associatiewoorden (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "woordID INTEGER," +
                "afbeeldingNr INTEGER," +
                "woord TEXT," +
                "juist INTEGER," +
                "FOREIGN KEY (woordID) REFERENCES woord(id))";
        db.execSQL(CREATE_TABLE_ASSOCIATIEWOORDEN);

        String CREATE_TABLE_WOORDAFBEELDINGEN = "CREATE TABLE woordafbeeldingen (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "woordID INTEGER," +
                "afbeeldingNr INTEGER," +
                "juist INTEGER," +
                "FOREIGN KEY (woordID) REFERENCES woord(id))";
        db.execSQL(CREATE_TABLE_WOORDAFBEELDINGEN);

        //wegschrijven data
        insertGroep(db);
        insertLetterGreep(db);
        insertWoordGroep(db);
        insertWoord(db);
        insertConditieGroep(db);
        insertTestKinderen(db);
        insertContextJuist(db);
        insertContextFout(db);
        insertAssociatieWoorden(db);
        insertWoordAfbeeldingen(db);
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

    private void insertTestKinderen(SQLiteDatabase db){
        db.execSQL("INSERT INTO kind (naam, groepID) VALUES ('testKind1', 1)");
        db.execSQL("INSERT INTO kind (naam, groepID) VALUES ('testKind2', 1)");
    }

    private void insertContextJuist(SQLiteDatabase db){
        db.execSQL("INSERT INTO contextjuist (woordID, zin) VALUES (1, 'In de turnzaal klim ik omhoog in het klimtouw. ')");
        db.execSQL("INSERT INTO contextjuist (woordID, zin) VALUES (2, 'De vijver is groen door het kroos. ')");
        db.execSQL("INSERT INTO contextjuist (woordID, zin) VALUES (3, 'De eenden zitten bij het water tussen het riet')");
        db.execSQL("INSERT INTO contextjuist (woordID, zin) VALUES (4, 'Wat was dat een pijnlijke val!')");
        db.execSQL("INSERT INTO contextjuist (woordID, zin) VALUES (5, 'Omdat papa niet weet waar we naartoe moeten lopen, kijkt hij op zijn kompas. ')");
        db.execSQL("INSERT INTO contextjuist (woordID, zin) VALUES (6, 'Jan loopt de steile berg omhoog.')");
        db.execSQL("INSERT INTO contextjuist (woordID, zin) VALUES (7, 'In de vijver in het park zwemt een witte zwaan.')");
        db.execSQL("INSERT INTO contextjuist (woordID, zin) VALUES (8, 'De kinderen zitten te eten tussen de tenten van het kamp.')");
        db.execSQL("INSERT INTO contextjuist (woordID, zin) VALUES (9, 'De jongen schijnt met de zaklamp in de donkere grot.')");
        db.execSQL("INSERT INTO contextjuist (woordID, zin) VALUES (10, 'Met zijn duikbril kan de jongen de vissen onder water goed bekijken.')");
    }

    private void insertContextFout(SQLiteDatabase db){
        db.execSQL("INSERT INTO contextfout (woordID, zin) VALUES (1, 'Ik wacht op de bus in het klimtouw.')");
        db.execSQL("INSERT INTO contextfout (woordID, zin) VALUES (2, 'Oma en het kroos zitten in de auto.')");
        db.execSQL("INSERT INTO contextfout (woordID, zin) VALUES (3, 'Ik ga naar buiten met mijn jas en het riet aan.')");
        db.execSQL("INSERT INTO contextfout (woordID, zin) VALUES (4, 'Jan zit op de val aan tafel.')");
        db.execSQL("INSERT INTO contextfout (woordID, zin) VALUES (5, 'Mama belt met het kompas naar papa.')");
        db.execSQL("INSERT INTO contextfout (woordID, zin) VALUES (6, 'Papa leest een steil verhaaltje voor. ')");
        db.execSQL("INSERT INTO contextfout (woordID, zin) VALUES (7, 'De zwaan fietst in het park. ')");
        db.execSQL("INSERT INTO contextfout (woordID, zin) VALUES (8, 'Jonas wast zich met het kamp.')");
        db.execSQL("INSERT INTO contextfout (woordID, zin) VALUES (9, 'Jef opent de deur met de zaklamp.')");
        db.execSQL("INSERT INTO contextfout (woordID, zin) VALUES (10, 'Met een duikbril kan ik schrijven op papier.')");
    }

    private void insertWoordAfbeeldingen(SQLiteDatabase db){
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (10, 1, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (10, 2, 0)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (10, 3, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (10, 4, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (1, 1, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (1, 2, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (1, 3, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (1, 4, 0)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (2, 1, 0)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (2, 2, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (2, 3, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (2, 4, 0)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (3, 1, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (3, 2, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (3, 3, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (3, 4, 0)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (4, 1, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (4, 2, 0)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (4, 3, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (4, 4, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (5, 1, 0)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (5, 2, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (5, 3, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (5, 4, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (6, 1, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (6, 2, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (6, 3, 0)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (6, 4, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (7, 1, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (7, 2, 0)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (7, 3, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (7, 4, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (8, 1, 0)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (8, 2, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (8, 3, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (8, 4, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (9, 1, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (9, 2, 1)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (9, 3, 0)");
        db.execSQL("INSERT INTO woordafbeeldingen (woordID, afbeeldingNr, juist) VALUES (9, 4, 1)");
    }

    private void insertAssociatieWoorden(SQLiteDatabase db){
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (10, 1, 'ogen', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (10, 2, 'in de zee', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (10, 3, 'zwemmen', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (10, 4, 'schrijven', 0)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (1, 1, 'klimmen', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (1, 2, 'sterk', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (1, 3, 'in de turnzaal', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (1, 4, 'het zwembad', 0)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (2, 1, 'groen', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (2, 2, 'in de vijver', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (2, 3, 'de lamp', 0)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (2, 4, 'de eend', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (3, 1, 'de vijver', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (3, 2, 'de eend', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (3, 3, 'het bos', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (3, 4, 'de bril', 0)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (4, 1, 'de pijn', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (4, 2, 'naar voor', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (4, 3, 'de pleister', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (4, 4, 'de appel', 0)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (5, 1, 'wandelen', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (5, 2, 'rugzak', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (5, 3, 'de landkaart', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (5, 4, 'het bad', 0)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (6, 1, 'de berg', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (6, 2, 'beklimmen', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (6, 3, 'de trap', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (6, 4, 'de bloem', 0)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (7, 1, 'de vijver', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (7, 2, 'vleugels', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (7, 3, 'wit', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (7, 4, 'het boek', 0)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (8, 1, 'de tent', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (8, 2, 'het kampvuur', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (8, 3, 'de slaapzak', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (8, 4, 'de deur', 0)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (9, 1, 'het licht', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (9, 2, 'de batterij', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (9, 3, 'in het donker', 1)");
        db.execSQL("INSERT INTO associatiewoorden (woordID, afbeeldingNr, woord, juist) VALUES (9, 4, 'het paard', 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Create tables again
        onCreate(db);
    }
}