package com.northwindlabs.kartikeya.creditmanagement.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserCreditsDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "userCredits.db";

    private static final int DATABASE_VERSION = 1;

    public UserCreditsDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + " (" +
                UserContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UserContract.UserEntry.COLUMN_USER_NAME + " TEXT NOT NULL, " +
                UserContract.UserEntry.COLUMN_EMAIL + " TEXT NOT NULL, " +
                UserContract.UserEntry.COLUMN_BALANCE + " INTEGER NOT NULL" +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_USER_TABLE);

        final String SQL_CREATE_CREDIT_TRANSFER_HISTORY_TABLE = "CREATE TABLE " + UserContract.TransferHistoryEntry.TABLE_NAME + " (" +
                UserContract.TransferHistoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UserContract.TransferHistoryEntry.COLUMN_USER_FROM_ID + " INTEGER NOT NULL, " +
                UserContract.TransferHistoryEntry.COLUMN_USER_TO_ID + " INTEGER NOT NULL, " +
                UserContract.TransferHistoryEntry.COLUMN_CREDITS_TRANSFERRED + " INTEGER NOT NULL, " +
                UserContract.TransferHistoryEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (" + UserContract.TransferHistoryEntry.COLUMN_USER_FROM_ID + ", " + UserContract.TransferHistoryEntry.COLUMN_USER_TO_ID +
                    ") REFERENCES " + UserContract.UserEntry.TABLE_NAME + "(" + UserContract.UserEntry._ID + ", " + UserContract.UserEntry._ID +
                    ") ON UPDATE CASCADE ON DELETE RESTRICT );";

        sqLiteDatabase.execSQL(SQL_CREATE_CREDIT_TRANSFER_HISTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Nothing to do
    }

    @Override
    public void onConfigure(SQLiteDatabase db){
        db.setForeignKeyConstraintsEnabled(true);
    }
}
