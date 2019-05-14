package com.dianmo.flash.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dianmo.flash.Entity.Item;
import com.dianmo.flash.R;

import java.util.List;

public class TextAdapter extends BaseAdapter {

    private Context context;
    private List<String> mDatas ;
    private LayoutInflater inflater;
    public TextAdapter(Context context, List<String> mDatas) {
        this.mDatas = mDatas;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.edit_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.msg = convertView.findViewById(R.id.msg);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String item = mDatas.get(position);
        viewHolder.msg.setText(item);
        return convertView;
    }

    private class ViewHolder{
        TextView msg;
    }
}
