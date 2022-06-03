package com.example.lostfoundmap;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lostfoundmap.data.databaseHelper;
import com.example.lostfoundmap.model.LostFoundMod;

import java.util.ArrayList;
import java.util.List;

public class lostFoundList extends AppCompatActivity {
    ListView lView;
    databaseHelper db;
    ArrayList<String> lostFoundArrayList;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_found_list);

        lView = findViewById(R.id.list);
        lostFoundArrayList = new ArrayList<>();

        List<LostFoundMod> listView = MainActivity.db.fetchAllItems();
        for (int i = 0; i < listView.size(); i++)
        {
            Log.d(null, listView.get(i).getDescription());
//            lostFoundArrayList.add(lostFoundMod.getType() + " " + lostFoundMod.getName());
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lostFoundArrayList);
        lView.setAdapter(adapter);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent storedata = new Intent(lostFoundList.this, lostFoundData.class);
                storedata.putExtra("type", listView.get(position).getType());
                storedata.putExtra("name", listView.get(position).getName());
                storedata.putExtra("phone", listView.get(position).getPhone());
                storedata.putExtra("description", listView.get(position).getDescription());
                storedata.putExtra("date", listView.get(position).getDate());
                storedata.putExtra("location", listView.get(position).getLocation());
                startActivity(storedata);

            }
        });
    }
}