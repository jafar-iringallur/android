package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class view_profile extends AppCompatActivity {
    TextView name;
    TextView place;
    TextView post;
    TextView pin;
    TextView contact;
    TextView email;

    TabLayout tab;
    @Override
    public void onBackPressed() {
        super.onBackPressed();


        Intent ij=new Intent(getApplicationContext(),customer_home.class);
        startActivity(ij);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        name=(TextView) findViewById(R.id.textView32);
        place=(TextView) findViewById(R.id.textView33);
        post=(TextView) findViewById(R.id.textView35);
        pin=(TextView) findViewById(R.id.textView34);
        contact=(TextView) findViewById(R.id.textView36);
        email=(TextView) findViewById(R.id.textView37);
        tab=(TabLayout) findViewById(R.id.tab) ;


        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition()==0)
                {
                    Intent ij = new Intent(getApplicationContext(), view_profile.class);
                    startActivity(ij);
                }
                else if(tab.getPosition()==1)
                {
                    Intent ij = new Intent(getApplicationContext(), view_order_main.class);
                    startActivity(ij);
                }
                else if(tab.getPosition()==2)
                {
                    Intent ij = new Intent(getApplicationContext(), Test.class);
                    startActivity(ij);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip = sh.getString("ip", "");
        String url = "http://" + ip + ":5000/android_view_profile";



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String sucs = jsonObj.getString("status");
                            if (sucs.equalsIgnoreCase("ok")) {
                                name.setText(jsonObj.getString("name"));
                                place.setText(jsonObj.getString("place"));
                                post.setText(jsonObj.getString("post"));
                                pin.setText(jsonObj.getString("pin"));
                                contact.setText(jsonObj.getString("contact"));
                                email.setText(jsonObj.getString("email"));


                            } else {
                                Toast.makeText(getApplicationContext(),"Invalid username or password...",Toast.LENGTH_LONG).show();

                            }
                        } catch (Exception e) {


                            Toast.makeText(getApplicationContext(),"eeeee"+e.toString(),Toast.LENGTH_LONG).show();

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

                params.put("lid", sh.getString("lid",""));



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

}