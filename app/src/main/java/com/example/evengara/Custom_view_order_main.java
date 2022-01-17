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

public class Custom_view_order_main extends BaseAdapter {

    String [] ordermainid,date,time,status,total,payment;
    private Context context;

    public Custom_view_order_main(Context cc, String[]ordermainid, String[]date, String[]time, String[]status, String[]total, String[]payment)
    {
        this.context=cc;
        this.ordermainid=ordermainid;
        this.date=date;
        this.time=time;
        this.status=status;
        this.total=total;
        this.payment=payment;
    }
    @Override
    public int getCount() {
        return ordermainid.length;
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

            gridView=inflator.inflate(R.layout.custom_view_order_main,null);

        }
        else
        {
            gridView=(View)view;

        }

        TextView tv2=(TextView)gridView.findViewById(R.id.textView66);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView67);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView68);

        TextView tv5=(TextView) gridView.findViewById(R.id.textView70) ;
        TextView tv6=(TextView) gridView.findViewById(R.id.textView72) ;
        TextView tv7=(TextView) gridView.findViewById(R.id.textView123) ;
        Button btn1=(Button)gridView.findViewById(R.id.button15) ;

        btn1.setTag(ordermainid[i]);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String oid= v.getTag().toString();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);

                SharedPreferences.Editor ed = sh.edit();
                ed.putString("oid", oid);
                ed.commit();
                Intent ij = new Intent(context, view_order.class);
                ij.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ij);



            }
        });

        tv2.setTextColor(Color.BLACK);
        tv2.setText(ordermainid[i]);
        tv3.setTextColor(Color.BLACK);
        tv3.setText(time[i]);
        tv4.setTextColor(Color.BLACK);
        tv4.setText(date[i]);
        tv5.setTextColor(Color.BLACK);
        tv5.setText(total[i]);
        tv6.setTextColor(Color.BLACK);
        tv6.setText(status[i]);
        tv7.setTextColor(Color.BLACK);
        tv7.setText(payment[i]);

        return gridView;
    }
}
