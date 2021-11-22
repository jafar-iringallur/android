package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class send_review extends AppCompatActivity implements View.OnClickListener {
    EditText review;
    RatingBar rating;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_review);
        review=(EditText) findViewById(R.id.editTextTextPersonName12);
        rating=(RatingBar) findViewById(R.id.ratingBar);
        btn=(Button) findViewById(R.id.button4);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==btn)
        {

        }
    }
}