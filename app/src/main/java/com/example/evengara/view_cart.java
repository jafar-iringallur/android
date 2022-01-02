package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class view_cart extends AppCompatActivity implements View.OnClickListener {
    ListView cart;
    Button order;
    String [] prdid,name,image,price,qty,cartid,total;
    float sumtotal=0;
    TextView tt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        cart=(ListView) findViewById(R.id.view_cart);
        order=(Button) findViewById(R.id.button7);
        order.setOnClickListener(this);
        tt=(TextView)findViewById(R.id.textView53) ;

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String maclis=sh.getString("mac_list","");
        String uid=sh.getString("uid","");
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5000/android_view_cart";



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                JSONArray js= jsonObj.getJSONArray("data");

                                name=new String[js.length()];
                                prdid=new String[js.length()];
                                price=new String[js.length()];
                                image=new String[js.length()];

                                qty=new String[js.length()];
                                cartid=new String[js.length()];
                                total=new String[js.length()];


                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);

                                    name[i]=u.getString("name");
                                    prdid[i]=u.getString("prdid");
                                    price[i]=u.getString("price");
                                    image[i]=u.getString("image");

qty[i]=u.getString("qty");
                                    total[i]=u.getString("total");
                                    cartid[i]=u.getString("cartid");


sumtotal=sumtotal+(Float.parseFloat(total[i]));

                                }
                                cart.setAdapter(new Custom_view_cart(getApplicationContext(),prdid,name,image,price,qty,cartid,total));
                            tt.setText(sumtotal+"");
                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        }    catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
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
                Map<String, String> params = new HashMap<String, String>();
                params.put("lid",sh.getString("lid",""));

                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
    }

    @Override
    public void onClick(View v) {
        Intent ij = new Intent(getApplicationContext(), conform_address.class);
        startActivity(ij);
    }
}