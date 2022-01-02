package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class login extends AppCompatActivity implements View.OnClickListener {
    EditText username;
    EditText password;
    Button btn;
    TextView t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText) findViewById(R.id.editTextTextPersonName);
        password=(EditText) findViewById(R.id.editTextTextPersonName2);
        t1=(TextView)findViewById(R.id.textView30);
        t2=(TextView)findViewById(R.id.textView31);
        btn=(Button) findViewById(R.id.button);
        btn.setOnClickListener(this);
        t1.setOnClickListener(this);
        t2.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view==btn)
        {
            String user=username.getText().toString();
            String pass=password.getText().toString();


            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String ip = sh.getString("ip", "");
            String url = "http://" + ip + ":5000/android_login";



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
                                    String lid = jsonObj.getString("lid");
                                    SharedPreferences.Editor ed = sh.edit();
                                    ed.putString("lid", lid);
                                    ed.commit();

                                    if(jsonObj.getString("type").equalsIgnoreCase("delivery_boy")) {
                                        Intent ij = new Intent(getApplicationContext(), set_ip.class);
                                        startActivity(ij);
                                    }
                                    if(jsonObj.getString("type").equalsIgnoreCase("customer")) {
                                        Intent ij = new Intent(getApplicationContext(), customer_home.class);
                                        startActivity(ij);
                                    }
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

                    params.put("username", user);
                    params.put("password", pass);


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
        if(view==t1)
        {
            Intent ij = new Intent(getApplicationContext(), set_ip.class);
            startActivity(ij);
        }
        if(view==t2)
        {
            Intent ij = new Intent(getApplicationContext(), signup.class);
            startActivity(ij);
        }

    }


}



