package com.example.parkingsharking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.cityViewHolder>{
    private int cityrowLayout;
    private Context cityContext;
    private RecyclerViewClickListener listener;
    DatabaseHelper mDB;
    String user;

    public CityAdapter(Context cityContext, DatabaseHelper db, int rowLayout, RecyclerViewClickListener listener, String user) {

        this.cityrowLayout = rowLayout;
        this.cityContext = cityContext;
        this.listener = listener;
        mDB = db;
        this.user = user;
    }

    public class cityViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        public TextView cityName;
        public Button rsrvButton;
        public TextView cityInfo;
        public cityViewHolder(final View itemView) {
            super(itemView);
            cityName = (TextView) itemView.findViewById(R.id.cityname);
            cityInfo = (TextView) itemView.findViewById(R.id.cityinfo);
            rsrvButton = (Button) itemView.findViewById(R.id.rsrvbutton);
            rsrvButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Reservation.class);
                    intent.putExtra("cityname",  cityName.getText());
                    intent.putExtra( "username", user);
                    v.getContext().startActivity(intent);
                }
            });        }

        @Override
        public void onClick(View v) {
            listener.onClick(itemView, getAdapterPosition());
        }
    }

    @Override
    public CityAdapter.cityViewHolder onCreateViewHolder(ViewGroup parent, int i){
        View v = LayoutInflater.from(parent.getContext()).inflate(cityrowLayout, parent,false);
        return new cityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final cityViewHolder viewHolder, final int position){

        City current =  mDB.query(position);

        viewHolder.cityName.setText(current.getName());

        viewHolder.cityInfo.setText(String.valueOf(current.getParkings()));

        final cityViewHolder h = viewHolder;



        viewHolder.cityName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView tv = (TextView) v;
                Toast.makeText(cityContext, tv.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public int getItemCount(){
        return (int) mDB.count();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}
