package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class send_review extends AppCompatActivity implements View.OnClickListener {
    EditText review;
    RatingBar rating;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_review);
        review=(EditText) findViewById(R.id.editTextTextPersonName12);
        rating=(RatingBar) findViewById(R.id.ratingBar);
        btn=(Button) findViewById(R.id.button4);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==btn)
        {
            String revie=review.getText().toString();
            Float rat=rating.getRating();

            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String ip = sh.getString("ip", "");
            String url = "http://" + ip + ":5000/android_send_review";

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
                                    Toast.makeText(getApplicationContext(),"Review Added Successfully",Toast.LENGTH_LONG).show();
                                    Intent ij = new Intent(getApplicationContext(), customer_home.class);
                                    startActivity(ij);


                                } else {
                                    Toast.makeText(getApplicationContext(),"Review already added",Toast.LENGTH_LONG).show();
                                    Intent ij = new Intent(getApplicationContext(), customer_home.class);
                                    startActivity(ij);
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

                    params.put("review", revie);
                    params.put("rating", rat+"");
                    if (sh.getString("type","")== "shop"){
                        params.put("id", sh.getString("shopid",""));
                    }
                   else {
                        params.put("id", sh.getString("prdid",""));
                    }
                    params.put("lid", sh.getString("lid",""));
                    params.put("type", sh.getString("type",""));



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
}