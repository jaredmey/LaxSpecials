package com.yourfullname.flashcards.laxspecials;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/*
--INSERT INTO bars (bar_name, bar_address) VALUES ('some bar name', 'some bar address')
--SELECT * FROM bars
--INSERT INTO specials (bar_id, special_day,special_description) VALUES (1,"Monday","Free Beer")
--SELECT * FROM specials

--Delete From specials where _id = 32
--SELECT _id, bar_id, special_day, special_description FROM specials WHERE special_day = "Monday";
--SELECT specials._id, specials.bar_id, bars.bar_name, special_day, special_description FROM specials INNER JOIN bars ON bars._id = specials.bar_id  WHERE special_day = "Monday"

*/


public class MainActivity extends AppCompatActivity {



    public static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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




    }

    public void sendToNextActivity(View v){
        Spinner days = (Spinner)findViewById(R.id.days);                //Using the id of the spinner in the layout file.
        Spinner bars = (Spinner)findViewById((R.id.bars));
        String selectedDay = String.valueOf(days.getSelectedItem());    //Which ever day gets selected becomes the value in 'selectedDay'.
        String selectedBar = String.valueOf(bars.getSelectedItem());
        Intent i = new Intent(this, DisplaySpecials.class);             //Creating a new intent named i. Transfer from 'this' to DisplaySpecials.
        if (!selectedBar.equals("Select bar") && selectedDay.equals("Select day")) {
            i.putExtra(DisplaySpecials.BAR, selectedBar);
            startActivity(i);                                               //Open up the next activity
        }
        else if (!selectedDay.equals("Select day") && selectedBar.equals("Select bar")){
            i.putExtra(DisplaySpecials.DAY, selectedDay);
            startActivity(i);                                               //Open up the next activity
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Only select a bar OR a day!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void sendToFavorites(View v){
        Spinner days = (Spinner)findViewById(R.id.days);                //Using the id of the spinner in the layout file.
        Spinner bars = (Spinner)findViewById((R.id.bars));
        String selectedDay = String.valueOf(days.getSelectedItem());    //Which ever day gets selected becomes the value in 'selectedDay'.
        String selectedBar = String.valueOf(bars.getSelectedItem());
        Intent i = new Intent(this, DisplaySpecials.class);             //Creating a new intent named i. Transfer from 'this' to DisplaySpecials.
        i.putExtra(DisplaySpecials.BAR, selectedBar);
        startActivity(i);                                               //Open up the next activity
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
