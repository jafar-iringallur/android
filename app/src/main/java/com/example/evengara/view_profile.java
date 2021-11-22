package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class view_profile extends AppCompatActivity {
    TextView name;
    TextView place;
    TextView post;
    TextView pin;
    TextView contact;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        name=(TextView) findViewById(R.id.textView6);
        place=(TextView) findViewById(R.id.textView7);
        post=(TextView) findViewById(R.id.textView8);
        pin=(TextView) findViewById(R.id.textView9);
        contact=(TextView) findViewById(R.id.textView10);
        email=(TextView) findViewById(R.id.textView11);
    }
}