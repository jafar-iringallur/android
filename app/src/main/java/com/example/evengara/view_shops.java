package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class view_shops extends AppCompatActivity {
    ListView shop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_shops);
        shop=(ListView) findViewById(R.id.shop);
    }
}