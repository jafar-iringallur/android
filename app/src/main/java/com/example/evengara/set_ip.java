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
    public void onBackPressed() {
        super.onBackPressed();
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

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
            if(ipaddr.length()==0)
            {
                ip.setError("Missing");
            }
            else {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("ip", ipaddr);
                ed.commit();

                Intent ij = new Intent(getApplicationContext(), login.class);
                startActivity(ij);
            }
        }
    }
}