package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class product_details extends AppCompatActivity implements View.OnClickListener {
    ImageView img;
    TextView name;
    TextView price;
    Spinner qty;
    TextView description;
    TextView made_date;
    TextView exp_date;
    Button add_cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        img = (ImageView) findViewById(R.id.imageViewa);
        name = (TextView) findViewById(R.id.textView47a);
        price = (TextView) findViewById(R.id.textView51a);
//        qty=(Spinner) findViewById(R.id.spinner);
        description = (TextView) findViewById(R.id.textView48a);
        made_date = (TextView) findViewById(R.id.textView52a);
        exp_date = (TextView) findViewById(R.id.textView53a);
        add_cart = (Button) findViewById(R.id.button5);
        add_cart.setOnClickListener(this);


        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip = sh.getString("ip", "");
        String url = "http://" + ip + ":5000/android_product_details";


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String sucs = jsonObj.getString("status");
                            if (sucs.equalsIgnoreCase("ok")) {
                                name.setText(jsonObj.getString("name"));
                                price.setText(jsonObj.getString("price"));
                                description.setText(jsonObj.getString("description"));
                                made_date.setText(jsonObj.getString("made_date"));
                                exp_date.setText(jsonObj.getString("exp_date"));


                                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                String ip = sh.getString("ip", "");

                                String url = "http://" + ip + ":5000" + jsonObj.getString("image");
//                                Toast.makeText(getApplicationContext(),"http://" + ip + ":5000"+jsonObj.getString("image").toString(),Toast.LENGTH_LONG).show();

                                Picasso.with(getApplicationContext()).load(url).transform(new CircleTransform()).into(img);

                            } else {
                                Toast.makeText(getApplicationContext(), "Invalid username or password...", Toast.LENGTH_LONG).show();

                            }
                        } catch (Exception e) {


                            Toast.makeText(getApplicationContext(), "eeeee" + e.toString(), Toast.LENGTH_LONG).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<>();

                params.put("prdid", sh.getString("prdid", ""));


                return params;
            }
        };
        int MY_SOCKET_TIMEOUT_MS = 100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);

    }

    @Override
    public void onClick(View v) {
        Intent ij=new Intent(getApplicationContext(),add_to_cart.class);
        startActivity(ij);
    }
}