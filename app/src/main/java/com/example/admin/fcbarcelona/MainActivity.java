package com.example.admin.fcbarcelona;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

    //The Main Activity sets up the List that holds all of the players details.
    ListView myListView;
    String[] players;
    String[] numbers;
    String[] countries;
    Button scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        //The players' details are printed as strings in textviews within the list
        myListView = (ListView)findViewById(R.id.myListView);
        scores = findViewById(R.id.scores);
        players = res.getStringArray(R.array.players);
        numbers = res.getStringArray(R.array.numbers);
        countries = res.getStringArray(R.array.countries);

        ItemsAdapter itemsAdapter = new ItemsAdapter(this, players, numbers, countries);
        myListView.setAdapter(itemsAdapter);

        //This sets up the next page in which the player's photo appears
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent showDetailActivity = new Intent(getApplicationContext(), DetailActivity.class);
                showDetailActivity.putExtra("com.example.admin.PLAYER_INDEX", i);
                startActivity(showDetailActivity);
            }
        });

        //This is for the button at the bottom of the screen, and navigates to an input screen for the database
        scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent showScores = new Intent(getApplicationContext(),Scoresdb.class);
                startActivity(showScores);
            }
        });

    }
}
