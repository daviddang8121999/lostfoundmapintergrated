package com.example.lostfoundmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lostfoundmap.data.databaseHelper;

public class lostFoundData extends AppCompatActivity {

    TextView displayInfo;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_found_data);

        displayInfo = findViewById(R.id.DisplayInfo);

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String description = intent.getStringExtra("description");
        String date = intent.getStringExtra("date");
        String location = intent.getStringExtra("location");

        String data = "Type: " + type + "\n"
                + "Item name:" + name + "\n"
                + "Phone number:" + phone + "\n"
                + "Item Description:" + description + "\n"
                + "Date:" + date + "\n"
                + "Location:" + location;

        displayInfo.setText(data);
        button = findViewById(R.id.deleteBtn);

        databaseHelper db = new databaseHelper(lostFoundData.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteData(name, phone, date);
                displayInfo.setText("");

                Intent saveIntent = new Intent(lostFoundData.this, MainActivity.class);
                startActivity(saveIntent);
            }
        });

    }
}