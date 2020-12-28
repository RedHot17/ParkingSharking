package com.example.parkingsharking;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmReservation extends AppCompatActivity {
    String city = "";
    String date = "";
    String hour = "";
    String user = "";
    String park = "";
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_confirmation);

        db = new DatabaseHelper(this);


            Fragment frag1 = getFragmentManager().findFragmentById(R.id.fragment1);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            city = extras.getString("city");
            user = extras.getString("user");
            hour = extras.getString("hour");
            date = extras.getString("date");
            park = extras.getString("park");
        }

        TextView citytext = (TextView) findViewById(R.id.cityconf);
        TextView parktext = (TextView) findViewById(R.id.parkconf);
        TextView datetext = (TextView) findViewById(R.id.dateconf);
        TextView timetext = (TextView) findViewById(R.id.hourconf);

        TextView park2 = findViewById(R.id.help);

        citytext.setText(city);
        parktext.setText(park);
        datetext.setText(date);
        timetext.setText(hour);

        park2.setText(park);

    }

    public void functionConfirm(View v){

        final Button confirm = findViewById(R.id.potvrda);

        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Boolean insert = db.insertReservation(user, city, park, date, hour);
                if (insert == true) {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
