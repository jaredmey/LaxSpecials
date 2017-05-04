package com.yourfullname.flashcards.laxspecials;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;


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

        String query = String.format("INSERT INTO %s VALUES (%d, %d)", TABLE_NAME, id, 0);

        ContentValues values = new ContentValues();
        values.put(COLUMN_SPECIALS_ID, id);
        values.put(COLUMN_SPECIALS_USER_ID, 0);
        this.database.insert(TABLE_NAME, null, values);

        Log.d(TAG, query);
    }

    public void removeSpecialFromFavorites(Long id) {

        this.database.delete(TABLE_NAME, "_id = " + id, null);

    }

    public ArrayList<Special> getFavorites()  {

        ArrayList<Special> specials = new ArrayList<Special>();

        String query = "SELECT DISTINCT specials._id, specials.bar_id, bars.bar_name, special_day, special_description, bar_address FROM favorites NATURAL JOIN specials NATURAL JOIN bars ORDER BY bars.bar_id ASC";
        Log.d(TAG, query);
        Cursor c = database.rawQuery(query,null);           //'c' is the handler on the Cursor

        // convert the results in the cursor into an array
        c.moveToFirst();
        while(!c.isAfterLast()){
            Special s = new Special();                      //'s' is the handler on the Special's object
            s.setId(c.getLong(0));                          //Get the Id from the database using the cursor
            s.setBarId(c.getLong(1));                       //Get the BarId from the database using the cursor
            s.setBarName(c.getString(2));                   //Get the BarName from the database using the cursor
            s.setDay(c.getString(3));                       //Get the Day from the database using the cursor
            s.setDescription(c.getString(4));               //Get the Description from the database using the cursor
            s.setAddress(c.getString(5));                   //Get the Address from the database using the cursor
            specials.add(s);
            c.moveToNext();
        }
        c.close();

        return specials;
    }
}
