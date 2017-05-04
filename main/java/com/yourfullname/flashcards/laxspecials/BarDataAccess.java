package com.yourfullname.flashcards.laxspecials;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class BarDataAccess {
    private SQLiteDatabase database;
    private MySQLiteOpenHelper dbHelper;

    public static final String TAG = "BarDataAccess";

    public static final String TABLE_NAME = "bars";
    public static final String COLUMN_BAR_ID = "_id";
    public static final String COLUMN_BAR_NAME = "bar_name";
    public static final String COLUMN_BAR_ADDRESS = "bar_address";

    //Defining the query to create the table and all of the columns that go along with it.
    public static final String TABLE_CREATE = String.format("create table %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)",
            TABLE_NAME,
            COLUMN_BAR_ID,
            COLUMN_BAR_NAME,
            COLUMN_BAR_ADDRESS
    );

    public BarDataAccess(MySQLiteOpenHelper dbHelper){
        this.dbHelper = dbHelper;
        this.database = this.dbHelper.getWritableDatabase();
    }

    public ArrayList<Bar> getAllBars(){

        ArrayList<Bar> bars = new ArrayList<Bar>();              //Creating an Arraylist called bars

        String query = String.format("SELECT %s, %s, %s FROM %s",COLUMN_BAR_ID, COLUMN_BAR_NAME, COLUMN_BAR_ADDRESS, TABLE_NAME);
        Cursor c = database.rawQuery(query,null);               //'c' is the handler on the Cursor

        // convert the results in the cursor into an array
        c.moveToFirst();
        while(!c.isAfterLast()){
            Bar b = new Bar();                                  //'b' is the handler on the Bar's object
            b.setId(c.getLong(0));                              //Get the Id from the database using the cursor
            b.setName(c.getString(1));                          //Get the Name from the database using the cursor
            b.setAddress(c.getString(2));                       //Get the Address from the database using the cursor
            bars.add(b);
            c.moveToNext();
        }
        c.close();                                              //The Cursor has to close in order to continue
        return bars;                                            //Returns the full list of Bars
    }

}
