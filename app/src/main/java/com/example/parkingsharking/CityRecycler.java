package com.example.parkingsharking;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CityRecycler extends AppCompatActivity {
    private DatabaseHelper mDB;
    private RecyclerView recycler;
    private CityAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_recycler);

        mDB = new DatabaseHelper(this);

        recycler = findViewById(R.id.cities);


        String user="";
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            user = extras.getString("user");
        }
        setAdapter(user);

    }

    private void setAdapter(String user) {
        //setOnClickListener();
        CityAdapter adapter = new CityAdapter(this, mDB, R.layout.city_card_view, listener,user );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);
    }
}
