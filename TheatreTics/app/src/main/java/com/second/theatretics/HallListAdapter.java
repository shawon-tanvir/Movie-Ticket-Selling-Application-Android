package com.second.theatretics;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class HallListAdapter extends ArrayAdapter<HallListType> {
    Context context;
    int layoutResourceId;
    // BcardImage data[] = null;
    ArrayList<HallListType> data=new ArrayList<HallListType>();
    public HallListAdapter(Context context, int layoutResourceId, ArrayList<HallListType> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ImageHolder holder = null;
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ImageHolder();
            holder.Hallname = (TextView)row.findViewById(R.id.Hallname);
            //holder.pricetitle = (TextView)row.findViewById(R.id.pricetitle);
            row.setTag(holder);
        }
        else
        {
            holder = (ImageHolder)row.getTag();
        }

        HallListType picture = data.get(position);
        holder.Hallname.setText(picture.h_name);
        //convert byte to bitmap take from contact class


        //Log.d("Price: ", (Integer.toString( picture.price)));


        //holder.pricetitle.setText("Tk "+ Integer.toString( picture.price));
        Log.d("Food name: ",picture.h_name);
        return row;

    }
    static class ImageHolder
    {
        TextView Hallname;

    }
}
