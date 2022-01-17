package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintLayoutStates;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class product_details extends AppCompatActivity implements View.OnClickListener {
    ImageView img;
    TextView name;
    TextView price;
    TextView shop;

    TextView description;
TextView r;
    Button add_cart;
    RatingBar rating;
    FloatingActionButton btn;
    ImageView min;
    ImageView add;
    ListView review;
    EditText qty;
    ConstraintLayout no;
    String [] revie,rat,date,cname;
    @Override
    public void onBackPressed() {
        super.onBackPressed();


        Intent ij=new Intent(getApplicationContext(),view_products.class);
        startActivity(ij);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        img = (ImageView) findViewById(R.id.imageViewa);
        min = (ImageView) findViewById(R.id.min);
        add= (ImageView) findViewById(R.id.add);
        qty= (EditText) findViewById(R.id.qty);
        name = (TextView) findViewById(R.id.textView47a);
        price = (TextView) findViewById(R.id.textView51a);
        shop = (TextView) findViewById(R.id.textView112);
       r = (TextView) findViewById(R.id.textView30);
//        qty=(Spinner) findViewById(R.id.spinner);
        description = (TextView) findViewById(R.id.textView48a);
no=(ConstraintLayout) findViewById(R.id.no);
        no.setVisibility(View.GONE);
        add_cart = (Button) findViewById(R.id.button5);
        add_cart.setOnClickListener(this);
        rating=(RatingBar) findViewById(R.id.ratingBar4);
        review=(ListView)findViewById(R.id.lvs) ;
        int num = Integer.parseInt(qty.getText().toString());
        min.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(qty.getText().toString());
               qty.setText(String.valueOf(num-1));

            }
        });
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(qty.getText().toString());
                qty.setText(String.valueOf(num+1));

            }
        });
        btn=(FloatingActionButton) findViewById(R.id.floatingActionButton2) ;
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("type", "shop");
                ed.commit();
                Intent ij = new Intent(getApplicationContext(), send_review.class);
                startActivity(ij);
            }
        });

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
                                shop.setText(jsonObj.getString("shop"));
                                description.setText(jsonObj.getString("description"));
                               float a=Float.parseFloat(jsonObj.getString("rat"));
                                rating.setRating(a);
                                r.setText("( "+jsonObj.getString("rat") +" )");



                                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                String ip = sh.getString("ip", "");

                                String url = "http://" + ip + ":5000" + jsonObj.getString("image");
//                                Toast.makeText(getApplicationContext(),"http://" + ip + ":5000"+jsonObj.getString("image").toString(),Toast.LENGTH_LONG).show();

                                Picasso.with(getApplicationContext()).load(url).transform(new CircleTransform()).into(img);

                                JSONArray js= jsonObj.getJSONArray("review");
                                revie=new String[js.length()];
                                rat=new String[js.length()];
                                date=new String[js.length()];
                                cname=new String[js.length()];
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
                params.put("type","product");


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
        String ip = sh.getString("ip", "");
        String url = "http://" + ip + ":5000/android_add_cart";



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
                                Toast.makeText(getApplicationContext(),"Successully added",Toast.LENGTH_LONG).show();
                                Intent ij=new Intent(getApplicationContext(),view_cart.class);
                                startActivity(ij);
                            } else {
                                Toast.makeText(getApplicationContext(),"failed..",Toast.LENGTH_LONG).show();

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

                params.put("pid", sh.getString("prdid",""));
                params.put("uid", sh.getString("lid",""));

                params.put("qty", qty.getText().toString());



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