package com.example.parkingsharking;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Reservation extends AppCompatActivity {
    String city = "";
    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_form);



        Fragment frag1 = getFragmentManager().findFragmentById(R.id.fragmentforma);
        Fragment frag2 = getFragmentManager().findFragmentById(R.id.fragmentspin);

        EditText cityname = (EditText) findViewById(R.id.formcity);



        Bundle extras = getIntent().getExtras();
        if(extras != null){
            city = extras.getString("cityname");
            username = extras.getString("username");
        }
        cityname.setText(city);

    }

    public void functionClick(View v){

        final android.widget.Spinner spinner = (Spinner) findViewById(R.id.spinner);

        TextView datepicker = (TextView) findViewById(R.id.editTextDate);
        String hour = spinner.getSelectedItem().toString();
        String date = datepicker.getText().toString();

        Intent i = new Intent(this, ParkingRecycler.class);
        i.putExtra("city", city);
        i.putExtra("username", username);
        i.putExtra("date", date);
        i.putExtra("hour", hour);
        startActivity(i);
    }
}
