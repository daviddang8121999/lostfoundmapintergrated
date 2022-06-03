package com.example.lostfoundmap;

import androidx.appcompat.app.AppCompatActivity;
import com.example.lostfoundmap.data.databaseHelper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static databaseHelper db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new databaseHelper(getApplicationContext());

        Button newAdvert = findViewById(R.id.NewBtn);
        newAdvert.setOnClickListener(view -> createNewAdvert());

        Button lostAndFound = findViewById(R.id.ShowBtn);
        lostAndFound.setOnClickListener(view -> showLostAndFound());

        Button map = findViewById(R.id.MapBtn);
        map.setOnClickListener(view -> showOnMap());
    }

    public void createNewAdvert() {
        Intent intent = new Intent(this, newAdvert.class);
        startActivity(intent);
    }

    public void showLostAndFound() {
        Intent intent = new Intent(this, lostFoundList.class);
        startActivity(intent);
    }

    public void showOnMap() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}