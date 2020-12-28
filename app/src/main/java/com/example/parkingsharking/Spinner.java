package com.example.parkingsharking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Spinner extends Fragment {
    public Spinner() {
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button b = (Button) getActivity().findViewById(R.id.makersv_btn);
        final EditText city = (EditText) getActivity().findViewById(R.id.formcity);
        final TextView date = (TextView) getActivity().findViewById(R.id.editTextDate);
        final android.widget.Spinner s = (android.widget.Spinner) getActivity().findViewById(R.id.spinner);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), "You chose " + s.getSelectedItem(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity().getApplicationContext(), "Select something", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg,
                             Bundle savedInstanceState) {

        return li.inflate(R.layout.spinner, vg, false);
    }
}
