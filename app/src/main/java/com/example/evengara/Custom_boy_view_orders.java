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
import android.widget.TextView;

public class Custom_boy_view_orders extends BaseAdapter {

    String [] ordermainid,date,time,customer,amount;
    private Context context;

    public Custom_boy_view_orders(Context cc,String[]ordermainid,String[]date,String[]time,String[]customer,String[]amount)
    {
        this.context=cc;
        this.ordermainid=ordermainid;
        this.date=date;
        this.time=time;
        this.customer=customer;
        this.amount=amount;
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

            gridView=inflator.inflate(R.layout.custom_boy_view_orders,null);

        }
        else
        {
            gridView=(View)view;

        }

        TextView tv2=(TextView)gridView.findViewById(R.id.textView66);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView67);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView68);
        TextView tv5=(TextView)gridView.findViewById(R.id.textView70);
        TextView tv6=(TextView)gridView.findViewById(R.id.textView72);

        Button btn2=(Button)gridView.findViewById(R.id.button15) ;

        btn2.setTag(i);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=(int) v.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("oid", ordermainid[id]);
                ed.putString("date", date[id]);
                ed.putString("time", time[id]);
                ed.putString("amount", amount[id]);

                ed.commit();
                Intent ij = new Intent(context, boy_order_details.class);
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
        tv5.setText(customer[i]);
        tv6.setTextColor(Color.BLACK);
        tv6.setText(amount[i]);

        return gridView;
    }
}
