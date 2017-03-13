package djiby.example.com.mmmtp4;

import android.provider.BaseColumns;

/**
 * Created by djiby on 22/01/17.
 */

public final class CustomerContract {

    private CustomerContract() {
    }

    public static class FeedEntry implements BaseColumns {

        public static final String TABLE_NAME = "customer";
        public static final String COLUMN_NAME_NOM = "nom";
        public static final String COLUMN_NAME_PRENOM = "prenom";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_VILLE = "ville";
    }
}
