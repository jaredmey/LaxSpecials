package com.yourfullname.flashcards.laxspecials;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by jmeyer27 on 4/18/17.
 */

public class FavoriteDataAccess {


    private SQLiteDatabase database;
    private MySQLiteOpenHelper dbHelper;

    public static final String TAG = "FavoriteDataAccess";

    public static final String TABLE_NAME = "favorites";
    public static final String COLUMN_SPECIALS_ID = "_id";
    public static final String COLUMN_SPECIALS_USER_ID = "user_id";

    //Defining the query to create the table
    public static final String TABLE_CREATE = String.format("create table %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER)",
            TABLE_NAME,
            COLUMN_SPECIALS_ID,
            COLUMN_SPECIALS_USER_ID
    );

    public FavoriteDataAccess(MySQLiteOpenHelper dbHelper){
        this.dbHelper = dbHelper;
        this.database = this.dbHelper.getWritableDatabase();
    }

    public void insertSpecialIntoFavorites(Long id) {

        String query = String.format("INSERT INTO %s VALUES (%d%n, %d%n)", TABLE_NAME, id, 0);

        Log.d(TAG, query);
        Cursor c = database.rawQuery(query, null);
        c.close();

        //ContentValues insertVals = new ContentValues();

    }
}
