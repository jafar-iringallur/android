package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

public class boy_view_orders extends AppCompatActivity {
    EditText search;
    ListView orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boy_view_orders);
        search=(EditText) findViewById(R.id.editTextTextPersonName14);
        orders=(ListView) findViewById(R.id.list2);
    }
}