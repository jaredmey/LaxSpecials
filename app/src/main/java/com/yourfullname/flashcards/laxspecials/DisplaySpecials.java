package com.yourfullname.flashcards.laxspecials;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplaySpecials extends AppCompatActivity {

    public static final String DAY = "day";

    MySQLiteOpenHelper dbHelper;
    BarDataAccess barda;
    SpecialDataAccess specialda;
    ArrayList<Bar> bars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_specials);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        String selectedDay = getIntent().getStringExtra(DAY);
        Toast.makeText(this, selectedDay, Toast.LENGTH_LONG).show();

        dbHelper = new MySQLiteOpenHelper(this);                                    //dbHelper is an object in the MySQLiteOpenHelper class.
        dbHelper.getWritableDatabase();
        specialda = new SpecialDataAccess(dbHelper);                                //Access the data inside the Specials database
        ArrayList<Special> specials = specialda.getAllSpecialsByDay(selectedDay);   //An array of specials is created from the Specials database. The array is dependent on which day the user selected.
        //Toast.makeText(this, specials.toString(),Toast.LENGTH_LONG).show();
        ListView listView = (ListView) findViewById(R.id.listView);                 //A listview is displayed in the activity in which the id is clalled 'listView'.
        ArrayAdapter<Special> adapter = new ArrayAdapter<Special>(this, android.R.layout.simple_list_item_1, specials);
        listView.setAdapter(adapter);

    }
}
