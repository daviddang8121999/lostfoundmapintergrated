package com.example.lostfoundmap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lostfoundmap.data.databaseHelper;
import com.example.lostfoundmap.model.LostFoundMod;

@SuppressWarnings("ALL")
public class newAdvert extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;

    EditText locationText;

    LocationManager locationManager;
    LocationListener locationListener;
    Button getDirectionsButton;

    String current_location;
    String type;
    String name;
    String phone;
    String description;
    String date;
    String location;
    Double latitude;
    Double longitude;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[]permissions, @NonNull int[]grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_advert);

        RadioButton radio_lost = findViewById(R.id.lostRad);
        EditText edName = findViewById(R.id.editName);
        EditText edPhone = findViewById(R.id.editPhone);
        EditText edDescription = findViewById(R.id.editDes);
        EditText edDate = findViewById(R.id.editDate);
        locationText = findViewById(R.id.LocText);

        databaseHelper db = new databaseHelper(this);
        radioGroup = findViewById(R.id.radioGroup);

        Button Post = findViewById(R.id.PostBtn);
        Post.setOnClickListener(view -> {
            int postTypeId = radioGroup.getCheckedRadioButtonId();

            radioButton = findViewById(postTypeId);

            String radType;
            if (radio_lost.isChecked()){
                radType = "lost";
            }
            else
            {
                radType = "found";
            }

            name = edName.getText().toString();
            phone = edPhone.getText().toString();
            description = edDescription.getText().toString();
            date = edDate.getText().toString();
            location = locationText.getText().toString();

            LostFoundMod lostFoundMod = new LostFoundMod(null, type, name, phone, description, date, location, latitude, longitude);

            Long postData = db.insertLostFound(lostFoundMod);

            if (postData > 0)
                Toast.makeText(newAdvert.this, "Successful Entry Log", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(newAdvert.this, "Unseccessful Entry Log", Toast.LENGTH_SHORT).show();

            Intent saveIntent = new Intent( newAdvert.this, MainActivity.class);
            startActivity(saveIntent);

        });

        getDirectionsButton = findViewById(R.id.LocBtn);
        getDirectionsButton.setOnClickListener(view -> locationText.setText(current_location));


        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = location -> current_location = location.toString();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0, locationListener);
    }

    public void checkPostType(View v) {
        int postTypeId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(postTypeId);

        Toast.makeText(this, "Post Type Selected:" + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3)
        {
            if (resultCode == RESULT_OK && data != null)
            {
                latitude = data.getDoubleExtra("latitude", 0);
                longitude = data.getDoubleExtra("longitude", 0);
                String name = data.getStringExtra("name");
                locationText.setText(name);
            }
        }


    }
}
