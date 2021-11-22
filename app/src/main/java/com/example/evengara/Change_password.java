package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Change_password extends AppCompatActivity implements View.OnClickListener {
    EditText cur_password;
    EditText new_password;
    EditText confirm_password;
    Button change;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        cur_password=(EditText) findViewById(R.id.editTextTextPersonName9);
        new_password=(EditText) findViewById(R.id.editTextTextPersonName10);
        confirm_password=(EditText) findViewById(R.id.editTextTextPersonName11);
        change=(Button) findViewById(R.id.button3);
        change.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==change)
        {

        }
    }
}