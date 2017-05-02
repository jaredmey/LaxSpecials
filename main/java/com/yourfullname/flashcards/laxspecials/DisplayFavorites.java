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

public class DisplayFavorites extends AppCompatActivity {

    public static final String DAY = "day";
    public static final String BAR = "bar";

    MySQLiteOpenHelper dbHelper;
    BarDataAccess barda;
    FavoriteDataAccess favda;
    ArrayList<Bar> bars;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_favorites);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadActivity();
    }

    private void loadActivity() {
        dbHelper = new MySQLiteOpenHelper(this);                                    //dbHelper is an object in the MySQLiteOpenHelper class.
        dbHelper.getWritableDatabase();

        favda = new FavoriteDataAccess(dbHelper);

        ArrayList<Special> specials = favda.getFavorites();                         //An array of specials is created from the Specials database. The array is dependent on which day the user selected.
        for (Special i : specials) {
            System.out.println(i.getBarName() + " " + i.getDescription() + " " + i.getAddress());
        }
        //Toast.makeText(this, specials.toString(),Toast.LENGTH_LONG).show();
        ListView listView = (ListView) findViewById(R.id.listView2);                 //A listview is displayed in the activity in which the id is clalled 'listView'.
        ArrayAdapter<Special> adapter = new ArrayAdapter<Special>(this, R.layout.display_favorites_button, specials);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.delbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListView tags = (ListView) findViewById(R.id.listView2);
                SparseBooleanArray checked = tags.getCheckedItemPositions();
                int len = checked.size();
                for (int i = 0; i < len; i++) {
                    int key = checked.keyAt(i);
                    if (checked.get(key)) {
                        Special special = (Special) tags.getItemAtPosition(key);
                        //send data from special to Favorites Table in database
                        favda.removeSpecialFromFavorites(special.getId());
                    }
                }
                loadActivity();
            }
        });
    }
}
