package djiby.example.com.mmmtp4;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by djiby on 19/01/17.
 */

public class CustomerProvider extends ContentProvider {

    private CustomerDbHelper customerDbHelper;
    private SQLiteDatabase sqLiteDatabase;


    static final String AUTHORITY = "CustomerProvider";

    public static final String PROVIDER_NAME = "com.example.djiby.mmmtp1.CustomerProvider";

    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/customers");


    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "customers", 1);

    }


    @Override
    public boolean onCreate() {

        customerDbHelper = new CustomerDbHelper(getContext());
        sqLiteDatabase = customerDbHelper.getWritableDatabase();

        if (sqLiteDatabase != null) {
            return true;
        } else {
            return false;
        }
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(CustomerContract.FeedEntry.TABLE_NAME);
        Cursor cursor = queryBuilder.query(sqLiteDatabase, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowId = sqLiteDatabase.insert(CustomerContract.FeedEntry.TABLE_NAME, null, values);
        if(rowId > 0){
            Uri uri1 = ContentUris.withAppendedId(CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(uri1, null);
            return uri1;
        }
        Toast.makeText(getContext(), "Row insert failed", Toast.LENGTH_LONG).show();
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
