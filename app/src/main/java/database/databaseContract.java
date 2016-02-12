package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cesar.mendes on 09/02/2016.
 */
public final class databaseContract extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "listaCadeiras.db";

    public static final String TABLE_NAME = "lista_cadeiras";
    public static final String _ID = "_id";
    public static final String COLUMN_NAME_CADEIRA = "cadeira";
    public static final String COLUMN_NAME_ENTRY_CREDIT = "credits";
    public static final String COLUMN_NAME_YEAR = "year";
    public static final String COLUMN_ENTRY_VALUE = "nota";

    private static final String TEXT_TYPE = " TEXT";
    private static final String NUMBER_TYPE = " integer";
    //private static final String REAL_TYPE = " real";
    private static final String COMMA_SEP = ", ";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + databaseContract.TABLE_NAME +
            " (" + databaseContract._ID + " INTEGER PRIMARY KEY," +
            databaseContract.COLUMN_NAME_CADEIRA + TEXT_TYPE + COMMA_SEP +
            databaseContract.COLUMN_NAME_ENTRY_CREDIT + TEXT_TYPE + COMMA_SEP +
            databaseContract.COLUMN_NAME_YEAR + NUMBER_TYPE + COMMA_SEP +
            databaseContract.COLUMN_ENTRY_VALUE  + NUMBER_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + databaseContract.TABLE_NAME;


        public databaseContract(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
