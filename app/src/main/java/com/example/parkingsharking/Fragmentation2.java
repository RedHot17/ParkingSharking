package com.example.parkingsharking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Fragmentation2 extends Fragment {
    public Fragmentation2(){}
    DatabaseHelper db;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button b = (Button) getActivity().findViewById(R.id.navbtn);
        final TextView park = getActivity().findViewById(R.id.help);
        final String parkname = String.valueOf(park);
        db = new DatabaseHelper(getContext());
        b.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View view){
                Toast.makeText(getActivity().getApplicationContext(), "навигација", Toast.LENGTH_SHORT).show();

                double lat = db.latitude(parkname);
                double lng = db.longitude(parkname);
                Intent i = new Intent (Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + lat + "," + lng + "&mode=d"));
                i.setPackage("com.google.android.apps.maps");
                if(i.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivity(i);
                }

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg,
                             Bundle savedInstanceState) {
        return li.inflate(R.layout.fragmentation_2, vg, false);
    }

}
