package com.example.parkingsharking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText e1, e2;
    Button b1;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        e1 = (EditText)findViewById(R.id.lemail);
        e2 = (EditText)findViewById(R.id.lpass);
        b1 = (Button)findViewById(R.id.login);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getText().toString();
                String password = e2.getText().toString();
                Boolean Checkemailpass = db.emailpassword(email, password);
                if(Checkemailpass == true) {
                    Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_SHORT).show();
                    Intent i2 = new Intent(LoginActivity.this, CityRecycler.class);
                    i2.putExtra("user",email );
                    startActivity(i2);
                }
                else
                    Toast.makeText(getApplicationContext(), "Wrong Email or password", Toast.LENGTH_SHORT).show();


            }
        });
    }
}
