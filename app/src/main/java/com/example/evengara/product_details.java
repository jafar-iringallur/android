package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class product_details extends AppCompatActivity {
    ImageView img;
    TextView name;
    TextView price;
    Spinner qty;
    TextView description;
    TextView made_date;
    TextView exp_date;
    Button add_cart;
    Button buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        img=(ImageView) findViewById(R.id.imageView);
        name=(TextView) findViewById(R.id.textView);
        price=(TextView) findViewById(R.id.textView2);
//        qty=(Spinner) findViewById(R.id.spinner);
        description=(TextView) findViewById(R.id.textView3);
        made_date=(TextView) findViewById(R.id.textView4);
        exp_date=(TextView) findViewById(R.id.textView5);
        add_cart=(Button) findViewById(R.id.button5);
        buy=(Button) findViewById(R.id.button6);


    }

}