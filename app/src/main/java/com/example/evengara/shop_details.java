package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class shop_details extends AppCompatActivity implements View.OnClickListener {
    TextView name;
    TextView address;
    TextView contact;
    RatingBar rating;
FloatingActionButton btn;
    ListView review;
    ListView list;
    TextView r;
    ConstraintLayout no;
    String [] revie,rat,date,cname;

    @Override
    public void onBackPressed() {
        super.onBackPressed();


        Intent ij=new Intent(getApplicationContext(),customer_home.class);
        startActivity(ij);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);
        name=(TextView) findViewById(R.id.textView42);
        address=(TextView) findViewById(R.id.textView14);
        contact=(TextView) findViewById(R.id.textView43);
        r=(TextView) findViewById(R.id.textView12);
        rating=(RatingBar) findViewById(R.id.ratingBar2);
        review=(ListView)findViewById(R.id.review) ;
        no=(ConstraintLayout) findViewById(R.id.no);
        no.setVisibility(View.GONE);
btn=(FloatingActionButton) findViewById(R.id.floatingActionButton10) ;
btn.setOnClickListener(this);




        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip = sh.getString("ip", "");
        String url = "http://" + ip + ":5000/android_shop_details";



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
                                address.setText(jsonObj.getString("address"));

                                contact.setText(jsonObj.getString("contact"));

float a=Float.parseFloat(jsonObj.getString("rat"));
rating.setRating(a);
r.setText("( " +jsonObj.getString("rat")+ " )");



                                JSONArray js= jsonObj.getJSONArray("review");
                                revie=new String[js.length()];
                                rat=new String[js.length()];
                                date=new String[js.length()];
                                cname=new String[js.length()];

//
//
//                                JSONArray js1= jsonObj.getJSONArray("rating");
//                                rating=new String[js1.length()];

                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    revie[i]=u.getString("review");
                                    rat[i]=u.getString("rating");
                                    date[i]=u.getString("date");
                                    cname[i]=u.getString("cname");
//                                    type[i]=u.getString("type");
//                                    discription[i]=u.getString("description");
//                                    image[i]=u.getString("image");
//                                    status[i]=u.getString("status");


                                }
                                if(revie.length>0) {


                                    review.setAdapter(new Custom_view_review(getApplicationContext(), revie, rat, date, cname));
                                }
                                else
                                {
                                    review.setVisibility(View.INVISIBLE);
                                    no.setVisibility(View.VISIBLE);
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

                params.put("lid", sh.getString("shopid",""));
                params.put("type","shop");


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
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor ed = sh.edit();
        ed.putString("type", "shop");
        ed.commit();
        Intent ij = new Intent(getApplicationContext(), send_review.class);
        startActivity(ij);
    }
}