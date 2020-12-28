package com.example.parkingsharking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class Fragmentation1 extends Fragment {
    public Fragmentation1(){}
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle savedInstanceState){
        return li.inflate(R.layout.fragmentation_1, vg, false);
    }
}
