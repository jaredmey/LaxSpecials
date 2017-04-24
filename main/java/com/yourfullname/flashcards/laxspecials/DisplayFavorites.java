package com.yourfullname.flashcards.laxspecials;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayFavorites extends AppCompatActivity {

    public static final String DAY = "day";
    public static final String BAR = "bar";

    MySQLiteOpenHelper dbHelper;
    BarDataAccess barda;
    FavoriteDataAccess favda;
    ArrayList<Bar> bars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_favorites);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new MySQLiteOpenHelper(this);                                    //dbHelper is an object in the MySQLiteOpenHelper class.
        dbHelper.getWritableDatabase();

        favda = new FavoriteDataAccess(dbHelper);
        //Access the data inside the Specials database
        ListView listView = (ListView) findViewById(R.id.listView);                 //A listview is displayed in the activity in which the id is clalled 'listView'.

        ArrayList<Special> specials = favda.getFavorites();   //An array of specials is created from the Specials database. The array is dependent on which day the user selected.
        //Toast.makeText(this, specials.toString(),Toast.LENGTH_LONG).show();
        listView = (ListView) findViewById(R.id.listView);                 //A listview is displayed in the activity in which the id is clalled 'listView'.
        ArrayAdapter<Special> adapter = new ArrayAdapter<Special>(this, R.layout.content_display_favorites, specials);
        listView.setAdapter(adapter);
    }
}
