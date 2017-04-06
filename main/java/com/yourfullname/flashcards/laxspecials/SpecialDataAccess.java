package com.yourfullname.flashcards.laxspecials;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Jimmy on 4/10/2016.
 */
public class SpecialDataAccess {

    private SQLiteDatabase database;
    private MySQLiteOpenHelper dbHelper;

    public static final String TAG = "BarDataAccess";

    public static final String TABLE_NAME = "specials";
    public static final String COLUMN_SPECIALS_ID = "_id";
    public static final String COLUMN_SPECIALS_BAR_ID = "bar_id";
    public static final String COLUMN_SPECIALS_BAR_DAY = "special_day";
    public static final String COLUMN_SPECIALS_BAR_DESCRIPTION = "special_description";

    //Defining the query to create the table
    public static final String TABLE_CREATE = String.format("create table %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER, %s TEXT, %s TEXT)",
            TABLE_NAME,
            COLUMN_SPECIALS_ID,
            COLUMN_SPECIALS_BAR_ID,
            COLUMN_SPECIALS_BAR_DAY,
            COLUMN_SPECIALS_BAR_DESCRIPTION
    );

    public SpecialDataAccess(MySQLiteOpenHelper dbHelper){
        this.dbHelper = dbHelper;
        this.database = this.dbHelper.getWritableDatabase();
    }

    //Lines 41 through 65 are not being used. This is just default code that passes through the basic components of the table.
    public ArrayList<Special> getAllSpecials(){

        ArrayList<Special>specials = new ArrayList<Special>();

        String query = String.format("SELECT %s, %s, %s, %s FROM %s",
                COLUMN_SPECIALS_ID,
                COLUMN_SPECIALS_BAR_ID,
                COLUMN_SPECIALS_BAR_DAY,
                COLUMN_SPECIALS_BAR_DESCRIPTION,
                TABLE_NAME);
        Cursor c = database.rawQuery(query,null);

        // convert the results in the cursor into an array
        c.moveToFirst();
        while(!c.isAfterLast()){
            Special s = new Special();
            s.setId(c.getLong(0));
            s.setBarId(c.getLong(1));
            s.setDay(c.getString(2));
            s.setDescription(c.getString(3));
            c.moveToNext();// I forgot this!!! took me
        }
        c.close();
        return specials;
    }

    public ArrayList<Special> getAllSpecialsByBar (String bar) {
        ArrayList<Special> specials = new ArrayList<Special>();

        String query = "SELECT specials._id, specials.bar_id, bars.bar_name, special_day, special_description, bar_address FROM specials INNER JOIN bars ON bars._id = specials.bar_id  WHERE bars.bar_name = '" + bar +"'";
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
        c.close();                                          //The Cursor has to close in order to continue
        return specials;                                    //Returns the full list of Specials
    }

    public ArrayList<Special> getAllSpecialsByDay (String day){

        ArrayList<Special> specials = new ArrayList<Special>();

        String query = "SELECT specials._id, specials.bar_id, bars.bar_name, special_day, special_description, bar_address FROM specials INNER JOIN bars ON bars._id = specials.bar_id  WHERE special_day = '" + day +"'";
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
        c.close();                                          //The Cursor has to close in order to continue
        return specials;                                    //Returns the full list of Specials
    }
}
