package com.example.parkingsharking;

import android.app.Fragment;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Navigation extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Fragment frag3 = getFragmentManager().findFragmentById(R.id.mapfrag);
    }
}
