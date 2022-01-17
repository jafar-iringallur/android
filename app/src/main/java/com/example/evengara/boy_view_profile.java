package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
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
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class boy_view_profile extends AppCompatActivity {
    ImageView img;
    TextView name;
    TextView  place;
    TextView  post;
    TextView  pin;
    TextView age;
    TextView  contact;
    TextView  email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boy_view_profile);
        img=(ImageView) findViewById(R.id.imageView3);
        name=(TextView) findViewById(R.id.textView22);
        place=(TextView) findViewById(R.id.textView23);
        post=(TextView) findViewById(R.id.textView24);
        pin=(TextView) findViewById(R.id.textView25);
        age=(TextView) findViewById(R.id.textView26);
        contact=(TextView) findViewById(R.id.textView27);
        email=(TextView) findViewById(R.id.textView28);

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip = sh.getString("ip", "");
        String url = "http://" + ip + ":5000/android_boy_view_profile";



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
                                age.setText(jsonObj.getString("age"));
                                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                String ip = sh.getString("ip", "");

                                String url = "http://" + ip + ":5000" + jsonObj.getString("image");
//                                Toast.makeText(getApplicationContext(),"http://" + ip + ":5000"+jsonObj.getString("image").toString(),Toast.LENGTH_LONG).show();

                                Picasso.with(getApplicationContext()).load(url).transform(new CircleTransform()).into(img);



                            } else {
                                Toast.makeText(getApplicationContext(),"Something Error..",Toast.LENGTH_LONG).show();

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