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

public class Custom_view_cart extends BaseAdapter {

    String [] prdid,name,image,price,qty,cartid,total,shop;
    private Context context;

    public Custom_view_cart(Context cc,String[]prdid,String[]name,String[]image,String[]price,String[]qty,String[]cartid,String[]total,String[]shop)
    {
        this.context=cc;
        this.prdid=prdid;
        this.name=name;
        this.image=image;
        this.price=price;
        this.qty=qty;
        this.cartid=cartid;
        this.total=total;
        this.shop=shop;
    }
    @Override
    public int getCount() {
        return prdid.length;
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
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);

            gridView=inflator.inflate(R.layout.custom_view_cart,null);

        }
        else
        {
            gridView=(View)view;

        }
        ImageView tv1=(ImageView) gridView.findViewById(R.id.imageView);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView47);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView48);
        TextView tv6=(TextView)gridView.findViewById(R.id.textView110);
        Button btn1=(Button)gridView.findViewById(R.id.button6) ;
        TextView tv5=(TextView) gridView.findViewById(R.id.textView57) ;

        btn1.setTag(cartid[i]);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                String ip = sh.getString("ip", "");
                String url = "http://" + ip + ":5000/android_remove_cart";



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
                                        Toast.makeText(context,"Item deleted successfully...",Toast.LENGTH_LONG).show();
                                        Intent ij=new Intent(context,view_cart.class);
                                        ij.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(ij);

                                    } else {
                                        Toast.makeText(context,"Error..",Toast.LENGTH_LONG).show();

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

                        params.put("cartid", cartid[i]);



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

        tv2.setTextColor(Color.BLACK);
        tv2.setText(name[i]);
        tv3.setTextColor(Color.BLACK);
        tv3.setText(price[i]);
        tv4.setTextColor(Color.BLACK);
        tv4.setText(qty[i]);
        tv5.setText(total[i]);
        tv6.setText(shop[i]);
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url="http://" + ip + ":5000"+image[i];


        Picasso.with(context).load(url).transform(new CircleTransform()). into(tv1);

        return gridView;
    }
}
