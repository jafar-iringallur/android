package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class set_ip extends AppCompatActivity implements View.OnClickListener {
    EditText ip;
    Button btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_ip);
        ip=(EditText) findViewById(R.id.editTextTextPersonName13);
        btn=(Button) findViewById(R.id.button9);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==btn)
        {
            String ipaddr=ip.getText().toString();
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor ed = sh.edit();
            ed.putString("ip", ipaddr);
            ed.commit();

            Intent ij = new Intent(getApplicationContext(), login.class);
            startActivity(ij);

        }
    }
}