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
import android.widget.RatingBar;
import android.widget.TextView;

public class Custom_view_review extends BaseAdapter {
    String [] revie,rat,date,cname;
    private Context context;
    public Custom_view_review(Context appcontext,String[]revie,String[]rat,String[]date,String[]cname)
    {
        this.context=appcontext;
        this.revie=revie;

        this.rat=rat;
        this.date=date;
        this.cname=cname;



    }
    @Override
    public int getCount() {
        return revie.length;
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

            gridView=inflator.inflate(R.layout.custom_view_review,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView44);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView45);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView46);
        RatingBar ratt=(RatingBar)gridView.findViewById(R.id.ratingBar3);

        tv1.setTextColor(Color.BLACK);
        tv1.setText(cname[i]);
        tv2.setTextColor(Color.BLACK);
        tv2.setText(date[i]);
        tv3.setTextColor(Color.BLACK);
        tv3.setText(revie[i]);

        float a=Float.parseFloat(rat[i]);
        ratt.setRating(a);



        return gridView;
    }

}
