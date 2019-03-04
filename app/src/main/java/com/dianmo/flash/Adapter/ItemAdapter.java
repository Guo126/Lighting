package com.dianmo.flash.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.dianmo.flash.Entity.Item;
import com.dianmo.flash.R;

import java.util.List;

public class ItemAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Item> mDatas;

             //MyAdapter需要一个Context，通过Context获得Layout.inflater，然后通过inflater加载item的布局
            public ItemAdapter(Context context, List<Item> data) {

               mInflater = LayoutInflater.from(context);
               mDatas = data;
           }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
             if (convertView == null) {
                         convertView = mInflater.inflate(R.layout.settings, parent, false); //加载布局
                        holder = new ViewHolder();

                         holder.image = (ImageView) convertView.findViewById(R.id.image1);
                         holder.text1 = (TextView) convertView.findViewById(R.id.text1);



                       convertView.setTag(holder);
                     } else {   //else里面说明，convertView已经被复用了，说明convertView中已经设置过tag了，即holder
                       holder = (ViewHolder) convertView.getTag();
                    }

                 Item item = mDatas.get(position);

                 //holder.image.setBackgroundResource();
                 holder.image.setImageResource(item.getSrc());
                 holder.text1.setText(item.getText1());



               return convertView;

    }

    private class ViewHolder {
        ImageView image;
        TextView text1;


    }
}


