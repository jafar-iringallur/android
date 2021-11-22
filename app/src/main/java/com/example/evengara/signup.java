package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class signup extends AppCompatActivity implements View.OnClickListener {
    EditText name;
    EditText place;
    EditText post;
    EditText pin;
    EditText contact;
    EditText email;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=(EditText) findViewById(R.id.editTextTextPersonName3);
        place=(EditText) findViewById(R.id.editTextTextPersonName4);
        post=(EditText) findViewById(R.id.editTextTextPersonName5);
        pin=(EditText) findViewById(R.id.editTextTextPersonName6);
        contact=(EditText) findViewById(R.id.editTextTextPersonName7);
        email=(EditText) findViewById(R.id.editTextTextPersonName8);
        btn=(Button) findViewById(R.id.button2);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==btn)
        {
            String customer_name=name.getText().toString();
            String customer_place=place.getText().toString();
            String customer_post=post.getText().toString();
            String customer_pin=pin.getText().toString();
            String customer_contact=contact.getText().toString();
            String customer_email=email.getText().toString();

            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String ip = sh.getString("ip", "");
            String url = "http://" + ip + ":5000/android_signup";

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
                                    Toast.makeText(getApplicationContext(),"Registerd Successfully",Toast.LENGTH_LONG).show();
                                    Intent ij = new Intent(getApplicationContext(), login.class);
                                    startActivity(ij);


                                } else {
                                    Toast.makeText(getApplicationContext(),"Registration Faild",Toast.LENGTH_LONG).show();

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

                    params.put("name", customer_name);
                    params.put("place", customer_place);
                    params.put("post", customer_post);
                    params.put("pin", customer_pin);
                    params.put("contact", customer_contact);
                    params.put("email", customer_email);


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