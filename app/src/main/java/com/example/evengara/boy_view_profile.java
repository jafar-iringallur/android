package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class boy_view_profile extends AppCompatActivity {
    ImageView img;
    TextView name;
    TextView  place;
    TextView  post;
    TextView  pin;
    TextView age;
    TextView  contact;
    TextView  email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boy_view_profile);
        img=(ImageView) findViewById(R.id.imageView3);
        name=(TextView) findViewById(R.id.textView22);
        place=(TextView) findViewById(R.id.textView23);
        post=(TextView) findViewById(R.id.textView24);
        pin=(TextView) findViewById(R.id.textView25);
        age=(TextView) findViewById(R.id.textView26);
        contact=(TextView) findViewById(R.id.textView27);
        email=(TextView) findViewById(R.id.textView28);
    }
}