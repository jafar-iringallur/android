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

import com.squareup.picasso.Picasso;

public class Custom_view_products extends BaseAdapter {

    String [] prdid,pname,image,price;
    private Context context;

    public Custom_view_products(Context cc,String[]prdid,String[]pname,String[]image,String[]price)
    {
        this.context=cc;
        this.prdid=prdid;
        this.pname=pname;
        this.image=image;
        this.price=price;
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

            gridView=inflator.inflate(R.layout.custom_view_product,null);

        }
        else
        {
            gridView=(View)view;

        }
       ImageView tv1=(ImageView) gridView.findViewById(R.id.imageView5);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView49);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView50);
        Button btn1=(Button)gridView.findViewById(R.id.button12) ;

btn1.setTag(prdid[i]);
btn1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor ed=sh.edit();
        ed.putString("prdid",v.getTag().toString());
        ed.commit();

        Intent ij=new Intent(context,product_details.class);
        ij.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(ij);
    }
});

        tv2.setTextColor(Color.BLACK);
        tv2.setText(pname[i]);
        tv3.setTextColor(Color.BLACK);
        tv3.setText(price[i]);
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url="http://" + ip + ":5000"+image[i];


        Picasso.with(context).load(url).transform(new CircleTransform()). into(tv1);

        return gridView;
    }
}
