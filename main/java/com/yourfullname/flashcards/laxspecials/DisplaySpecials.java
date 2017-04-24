package com.yourfullname.flashcards.laxspecials;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplaySpecials extends AppCompatActivity {

    public static final String DAY = "day";
    public static final String BAR = "bar";

    MySQLiteOpenHelper dbHelper;
    BarDataAccess barda;
    SpecialDataAccess specialda;
    FavoriteDataAccess favda;
    ArrayList<Bar> bars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_specials);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String selectedDay = getIntent().getStringExtra(DAY);
        String selectedBar = getIntent().getStringExtra(BAR);

        dbHelper = new MySQLiteOpenHelper(this);                                    //dbHelper is an object in the MySQLiteOpenHelper class.
        dbHelper.getWritableDatabase();

        specialda = new SpecialDataAccess(dbHelper);
        favda = new FavoriteDataAccess(dbHelper);
        //Access the data inside the Specials database
        ListView listView = (ListView) findViewById(R.id.listView);                 //A listview is displayed in the activity in which the id is clalled 'listView'.
        specialda = new SpecialDataAccess(dbHelper);                                //Access the data inside the Specials database

        if (selectedDay != null) {
            Toast.makeText(this, selectedDay, Toast.LENGTH_LONG).show();
            ArrayList<Special> specials = specialda.getAllSpecialsByDay(selectedDay);   //An array of specials is created from the Specials database. The array is dependent on which day the user selected.
            //Toast.makeText(this, specials.toString(),Toast.LENGTH_LONG).show();
            listView = (ListView) findViewById(R.id.listView);                 //A listview is displayed in the activity in which the id is clalled 'listView'.
            ArrayAdapter<Special> adapter = new ArrayAdapter<Special>(this, R.layout.display_specials_button, specials);
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            listView.setAdapter(adapter);
        }
        else {
            Toast.makeText(this, selectedBar, Toast.LENGTH_LONG).show();
            ArrayList<Special> specials = specialda.getAllSpecialsByBar(selectedBar);   //An array of specials is created from the Specials database. The array is dependent on which day the user selected.
            for (int i = 0; i < specials.size(); i++) {
                Log.d("LAXSpecials", "" + specials.get(i));
            }
            //Toast.makeText(this, specials.toString(),Toast.LENGTH_LONG).show();
            listView = (ListView) findViewById(R.id.listView);                 //A listview is displayed in the activity in which the id is clalled 'listView'.
            ArrayAdapter<Special> adapter = new ArrayAdapter<Special>(this, R.layout.display_specials_button, specials);
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            listView.setAdapter(adapter);
        }

        Button button = (Button) findViewById(R.id.favbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListView tags = (ListView) findViewById(R.id.listView);
                int len = tags.getCount();
                SparseBooleanArray checked = tags.getCheckedItemPositions();
                for (int i = 0; i < len; i++) {
                    int key = checked.keyAt(i);
                    if (checked.get(key)) {
                        Special special = (Special) tags.getItemAtPosition(key);
                        //send data from special to Favorites Table in database
                        favda.insertSpecialIntoFavorites(special.getId());
                    }
                }
            }
        });
    }
}
