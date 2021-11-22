package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class shop_details extends AppCompatActivity {
    TextView name;
    TextView address;
    TextView contact;
    RatingBar rating;
    Button view;
    Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);
        name=(TextView) findViewById(R.id.textView12);
        address=(TextView) findViewById(R.id.textView13);
        contact=(TextView) findViewById(R.id.textView14);
        rating=(RatingBar) findViewById(R.id.ratingBar2);
        view=(Button) findViewById(R.id.button7);
        add=(Button) findViewById(R.id.button8);

    }
}