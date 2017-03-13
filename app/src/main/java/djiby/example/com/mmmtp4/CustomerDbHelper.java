package djiby.example.com.mmmtp4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by djiby on 22/01/17.
 */

public class CustomerDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "customer.db";

    private static final String SQL_CREATE_CUSTOMERS =
            "CREATE TABLE " + CustomerContract.FeedEntry.TABLE_NAME + " (" +
                    CustomerContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    CustomerContract.FeedEntry.COLUMN_NAME_NOM + " TEXT," +
                    CustomerContract.FeedEntry.COLUMN_NAME_PRENOM + " TEXT," +
                    CustomerContract.FeedEntry.COLUMN_NAME_DATE + " TEXT," +
                    CustomerContract.FeedEntry.COLUMN_NAME_VILLE + " TEXT)";

    public CustomerDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CUSTOMERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CustomerContract.FeedEntry.TABLE_NAME);
        onCreate(db);
    }

}
