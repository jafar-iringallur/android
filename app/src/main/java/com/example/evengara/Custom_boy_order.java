package com.example.evengara;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Custom_boy_order extends BaseAdapter {

    String[] pname, price, qty;
    private Context context;

    public Custom_boy_order(Context cc, String[] pname, String[] price, String[] qty) {
        this.context = cc;
        this.pname = pname;

        this.price = price;
        this.qty = qty;

    }

    @Override
    public int getCount() {
        return pname.length;
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

            gridView = inflator.inflate(R.layout.custom_boy_view_order, null);

        } else {
            gridView = (View) view;

        }


        TextView tv4 = (TextView) gridView.findViewById(R.id.textView84);
        TextView tv5 = (TextView) gridView.findViewById(R.id.textView85);
        TextView tv6 = (TextView) gridView.findViewById(R.id.textView104);



        tv4.setTextColor(Color.BLACK);
        tv4.setText(pname[i]);
        tv5.setTextColor(Color.BLACK);
        tv5.setText(qty[i]);
        tv6.setTextColor(Color.BLACK);
        tv6.setText(price[i]);

        return gridView;
    }
}
