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



public class Custom_view_shops extends BaseAdapter {
    String [] shopid,name,contact,email,loginid;
    private Context context;

    public Custom_view_shops(Context appcontext,String[]shopid,String[]name,String[]contact,String[]email,String[]loginid)
    {
        this.context=appcontext;
        this.shopid=shopid;
        this.name=name;
        this.contact=contact;
        this.email=email;
        this.loginid=loginid;



    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);

            gridView=inflator.inflate(R.layout.custom_view_shops,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView38);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView40);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView39);
        Button btn1=(Button)gridView.findViewById(R.id.button11) ;
        Button btn2=(Button)gridView.findViewById(R.id.button10) ;

        tv1.setTextColor(Color.BLACK);
        tv1.setText(name[i]);
        tv2.setTextColor(Color.BLACK);
        tv2.setText(contact[i]);
        tv3.setTextColor(Color.BLACK);
        tv3.setText(email[i]);

        btn1.setTag(loginid[i]);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("shopid",v.getTag().toString());
                ed.commit();

                Intent ij=new Intent(context,shop_details.class);
                ij.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ij);
            }
        });

        btn2.setTag(loginid[i]);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("shopid",v.getTag().toString());
                ed.commit();

                Intent ij=new Intent(context,view_products.class);
                ij.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ij);
            }
        });
        return gridView;
    }
}
