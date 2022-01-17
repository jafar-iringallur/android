package com.example.evengara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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

public class conform_address extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    EditText name;
    EditText place;
    EditText post;
    EditText pin;
    EditText contact;

    Button confirm;
    EditText accno;
    EditText pass;
    TextView amount;
    RadioButton online;
    RadioButton cod;
LinearLayout bank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conform_address);
        name=(EditText) findViewById(R.id.editTextTextPersonName16);
        place=(EditText) findViewById(R.id.editTextTextPersonName17);
        post=(EditText) findViewById(R.id.editTextTextPersonName18);
        pin=(EditText) findViewById(R.id.editTextTextPersonName19);
        contact=(EditText) findViewById(R.id.editTextTextPersonName20);
        online=(RadioButton) findViewById(R.id.radioButton) ;
        cod=(RadioButton) findViewById(R.id.radioButton2) ;
        bank=(LinearLayout)findViewById(R.id.bank) ;
        online.setOnCheckedChangeListener(this);
        cod.setOnCheckedChangeListener(this);
        online.setChecked(true);
        bank.setVisibility(View.VISIBLE);

        accno=(EditText) findViewById(R.id.editTextTextPersonName23);
        pass=(EditText) findViewById(R.id.editTextTextPassword2);
        amount=(TextView) findViewById(R.id.textView79);

        confirm=(Button) findViewById(R.id.button8) ;
        confirm.setOnClickListener(this);



        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip = sh.getString("ip", "");
        amount.setText(sh.getString("sumtotal",""));
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

    @Override
    public void onClick(View v) {
        String customer_name=name.getText().toString();
        String customer_place=place.getText().toString();
        String customer_post=post.getText().toString();
        String customer_pin=pin.getText().toString();
        String customer_contact=contact.getText().toString();
if(online.isChecked()) {
    String user=accno.getText().toString();
    String pas=pass.getText().toString();
    if(user.length()==0)
    {
        accno.setError("Missing");
    }
    if(pas.length()==0)
    {
        pass.setError("Missing");
    }
    else {
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip = sh.getString("ip", "");
        String url = "http://" + ip + ":5000/android_order";


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String sucs = jsonObj.getString("status");

                            if (sucs.equalsIgnoreCase("ok")) {
                                Toast.makeText(getApplicationContext(), "Order Placed Successully", Toast.LENGTH_LONG).show();
                                String oid = jsonObj.getString("oid");
                                SharedPreferences.Editor ed = sh.edit();
                                ed.putString("oid", oid);
                                ed.commit();
                                Intent ij = new Intent(getApplicationContext(), customer_home.class);
                                startActivity(ij);

                            } else if (sucs.equalsIgnoreCase("no")) {
                                Toast.makeText(getApplicationContext(), "Your Cart is Empty", Toast.LENGTH_LONG).show();
                                Intent ij = new Intent(getApplicationContext(), customer_home.class);
                                startActivity(ij);

                            } else {
                                Toast.makeText(getApplicationContext(), "failed..", Toast.LENGTH_LONG).show();

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


                params.put("lid", sh.getString("lid", ""));
                params.put("sumtotal", sh.getString("sumtotal", ""));

                //        params.put("lid", sh.getString("lid",""));
                params.put("name", customer_name);
                params.put("place", customer_place);
                params.put("post", customer_post);
                params.put("pin", customer_pin);
                params.put("contact", customer_contact);

                params.put("acc_no", accno.getText().toString());
                params.put("pass", pass.getText().toString());

                params.put("sumtotal", sh.getString("sumtotal", ""));


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
else{

    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    String ip = sh.getString("ip", "");
    String url = "http://" + ip + ":5000/android_order_cod";


    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObj = new JSONObject(response);
                        String sucs = jsonObj.getString("status");

                        if (sucs.equalsIgnoreCase("ok")) {
                            Toast.makeText(getApplicationContext(), "Order Placed Successully", Toast.LENGTH_LONG).show();
                            String oid = jsonObj.getString("oid");
                            SharedPreferences.Editor ed = sh.edit();
                            ed.putString("oid", oid);
                            ed.commit();
                            Intent ij = new Intent(getApplicationContext(), customer_home.class);
                            startActivity(ij);

                        } else if (sucs.equalsIgnoreCase("no")) {
                            Toast.makeText(getApplicationContext(), "Your Cart is Empty", Toast.LENGTH_LONG).show();
                            Intent ij = new Intent(getApplicationContext(), customer_home.class);
                            startActivity(ij);

                        } else {
                            Toast.makeText(getApplicationContext(), "failed..", Toast.LENGTH_LONG).show();

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


            params.put("lid", sh.getString("lid", ""));
            params.put("sumtotal", sh.getString("sumtotal", ""));

            //        params.put("lid", sh.getString("lid",""));
            params.put("name", customer_name);
            params.put("place", customer_place);
            params.put("post", customer_post);
            params.put("pin", customer_pin);
            params.put("contact", customer_contact);
//
//            params.put("acc_no", accno.getText().toString());
//            params.put("pass", pass.getText().toString());

            params.put("sumtotal", sh.getString("sumtotal", ""));


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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView==online)
        { if(isChecked==true){
           // Toast.makeText(getApplicationContext(),"Online",Toast.LENGTH_LONG).show();
            bank.setVisibility(View.VISIBLE);
            }}
        else{
            if (isChecked==true){
            //Toast.makeText(getApplicationContext(),"Cod",Toast.LENGTH_LONG).show();
            bank.setVisibility(View.GONE);
        }
    }}
}