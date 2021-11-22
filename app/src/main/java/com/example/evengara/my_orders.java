package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class my_orders extends AppCompatActivity {
    ListView my_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        my_order=(ListView) findViewById(R.id.my_order);
    }
}