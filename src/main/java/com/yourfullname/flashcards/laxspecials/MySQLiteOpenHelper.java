package com.yourfullname.flashcards.laxspecials;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Jimmy on 4/6/2016.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = MySQLiteOpenHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "test.db";
    private static final int DATABASE_VERSION = 1;
//    private static final String TABLE_BARS = "bars";
//    private static final String COLUMN_ID = "_id";
//    private static final String COLUMN_BAR_ID = "id";
//    private static final String COLUMN_DAY = "day";
//    private static final String COLUMN_TIME = "time";
//    private static final String COLUMN_DESCRIPTION = "description";

    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i(TAG, "CONSTRUCTOR");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i(TAG, "SETTING UP DATABASE");

        db.execSQL(BarDataAccess.TABLE_CREATE);
        db.execSQL(SpecialDataAccess.TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
