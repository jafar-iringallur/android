package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class order_details extends AppCompatActivity {
    ImageView img;
    TextView orderid;
    TextView name;
    TextView shop;
    TextView price;
    TextView date;
    TextView qty;
    TextView status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        img=(ImageView) findViewById(R.id.imageView2);
        orderid=(TextView) findViewById(R.id.textView16);
        name=(TextView) findViewById(R.id.textView17);
        shop=(TextView) findViewById(R.id.textView18);
        price=(TextView) findViewById(R.id.textView19);
        date=(TextView) findViewById(R.id.textView20);
        qty=(TextView) findViewById(R.id.textView29);
        status=(TextView) findViewById(R.id.textView21);
    }
}