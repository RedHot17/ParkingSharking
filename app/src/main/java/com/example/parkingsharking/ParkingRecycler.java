package com.example.parkingsharking;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ParkingRecycler extends AppCompatActivity {
    private DatabaseHelper mDB;
    private RecyclerView recycler;
    private ParkingAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_recycler);

        mDB = new DatabaseHelper(this);

        String city="";
        String date="";
        String hour="";
        String user="";

        Intent intent = getIntent();
        city = intent.getStringExtra("city");
        date = intent.getStringExtra("date");
        hour = intent.getStringExtra("hour");
        user = intent.getStringExtra("user");

        recycler = findViewById(R.id.parkingplaces);

        setAdapter(city, date, hour, user);




    }
    private void setAdapter(String city, String date, String hour, String user) {
        ParkingAdapter adapter = new ParkingAdapter(this, mDB, R.layout.parking_card_view, listener,city, date, hour, user);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);
    }
}
