package com.yourfullname.flashcards.laxspecials;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;


public class DisplayMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        //Not complete yet
        Intent i = new Intent(this, DisplayFavorites.class);             //Creating a new intent named i. Transfer from 'this' to DisplaySpecials.
        startActivity(i);                                               //Open up the next activity
    }
}
