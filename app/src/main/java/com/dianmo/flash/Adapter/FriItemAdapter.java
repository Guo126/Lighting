package com.dianmo.flash.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.dianmo.flash.Entity.FriItem;
import com.dianmo.flash.R;

import java.util.List;

public class FriItemAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<FriItem> mDatas;

             //MyAdapter需要一个Context，通过Context获得Layout.inflater，然后通过inflater加载item的布局
            public FriItemAdapter(Context context, List<FriItem> data) {

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
                    convertView = mInflater.inflate(R.layout.listitem_meslist, parent, false); //加载布局
                        holder = new ViewHolder();
                        holder.image = (ImageView) convertView.findViewById(R.id.friImg);
                        //holder.num = (ImageView) convertView.findViewById(R.id.image1);
                        holder.name = (TextView) convertView.findViewById(R.id.name);
                        holder.mes = (TextView) convertView.findViewById(R.id.mes);
                        holder.time = (TextView) convertView.findViewById(R.id.time);
                       convertView.setTag(holder);
                     } else {   //else里面说明，convertView已经被复用了，说明convertView中已经设置过tag了，即holder
                       holder = (ViewHolder) convertView.getTag();
                    }

                 FriItem item = mDatas.get(position);

                 holder.image.setImageResource(item.getFriImg());
//                 holder.num.setImageResource(item.getMesNum());
                 holder.name.setText(item.getFriName());
                 holder.mes.setText((item.getFriMes()));
                holder.time.setText(item.getTime());

               return convertView;

    }

    private class ViewHolder {
        ImageView image;
        ImageView num;
        TextView name;
        TextView mes;
        TextView time;
    }
}


