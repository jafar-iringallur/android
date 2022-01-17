package com.example.evengara;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
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

public class Custom_view_order extends BaseAdapter {

    String[] name, image, price, qty,oid,status,shop;
    private Context context;

    public Custom_view_order(Context cc, String[] name, String[] image, String[] price, String[] qty, String[] oid,String[] status,String[] shop) {
        this.context = cc;
        this.name = name;
        this.image = image;
        this.price = price;
        this.qty = qty;
        this.oid = oid;
        this.status = status;
        this.shop=shop;

    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);

            gridView = inflator.inflate(R.layout.custom_view_order, null);

        } else {
            gridView = (View) view;

        }
        ImageView tv1 = (ImageView) gridView.findViewById(R.id.imageView6);


        TextView tv4 = (TextView) gridView.findViewById(R.id.textView50);
        TextView tv5 = (TextView) gridView.findViewById(R.id.textView60);
        TextView tv6 = (TextView) gridView.findViewById(R.id.textView62);
        TextView tv7 = (TextView) gridView.findViewById(R.id.textView109);
        TextView tv8 = (TextView) gridView.findViewById(R.id.textView124);
        Button btn=(Button) gridView.findViewById(R.id.button18);
        TableRow st=(TableRow) gridView.findViewById(R.id.st);


        if(status[i].equalsIgnoreCase("return"))
        {
            btn.setVisibility(View.GONE);
            tv7.setText("Returning");
        }
        else if(status[i].equalsIgnoreCase("done")) {
            st.setVisibility(View.GONE);
        }
        else {
            tv7.setText("Success");
        }


        btn.setTag(oid[i]);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                String ip = sh.getString("ip", "");
                String url = "http://" + ip + ":5000/android_return_order";



                RequestQueue requestQueue = Volley.newRequestQueue(context);
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    String sucs = jsonObj.getString("status");
                                    if (sucs.equalsIgnoreCase("ok")) {
                                        Toast.makeText(context,"Product Return successfully...",Toast.LENGTH_LONG).show();


                                    } else {
                                        Toast.makeText(context,"Invalid username or password...",Toast.LENGTH_LONG).show();

                                    }
                                } catch (Exception e) {


                                    Toast.makeText(context,"eeeee"+e.toString(),Toast.LENGTH_LONG).show();

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(context, "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                        Map<String, String> params = new HashMap<>();

                        params.put("oid",v.getTag().toString());



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
        });

        tv4.setTextColor(Color.BLACK);
        tv4.setText(name[i]);
        tv5.setTextColor(Color.BLACK);
        tv5.setText(qty[i]);
        tv6.setTextColor(Color.BLACK);
        tv6.setText(price[i]);
        tv7.setTextColor(Color.BLACK);


        tv8.setTextColor(Color.BLACK);
        tv8.setText(shop[i]);
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        String ip = sh.getString("ip", "");

        String url = "http://" + ip + ":5000" + image[i];


        Picasso.with(context).load(url).transform(new CircleTransform()).into(tv1);

        return gridView;
    }
}
