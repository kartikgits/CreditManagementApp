package com.northwindlabs.kartikeya.creditmanagement.data;

import android.provider.BaseColumns;

public class UserContract {

    public static final class UserEntry implements BaseColumns{
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_USER_NAME = "userName";
        public static final String COLUMN_BALANCE = "balance";
    }

    public static final class TransferHistoryEntry implements BaseColumns{
        public static final String TABLE_NAME = "transferHistory";
        public static final String COLUMN_USER_FROM_ID = "fromID";
        public static final String COLUMN_USER_TO_ID = "toID";
        public static final String COLUMN_CREDITS_TRANSFERRED = "credits";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }

}
