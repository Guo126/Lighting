package com.dianmo.flash.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dianmo.flash.Entity.Friend;
import com.dianmo.flash.R;

import java.util.List;

public class NewFriendAdapter extends BaseAdapter {
    public NewFriendAdapter(Context context, List<Friend> fDatas) {
        this.fInflater = LayoutInflater.from(context);
        this.fDatas = fDatas;
    }

    private LayoutInflater fInflater;
    private List<Friend> fDatas;
    @Override
    public int getCount() {
        return fDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return fDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        NewFriendAdapter.ViewHolder holder = null;
        if (view == null) {
            view=fInflater.inflate(R.layout.activity_friendlist,viewGroup,false);
            holder=new NewFriendAdapter.ViewHolder();
            holder.image=(ImageView) view.findViewById(R.id.friendImg);
            holder.name=(TextView)view.findViewById(R.id.name);
            holder.speak=(TextView)view.findViewById(R.id.speak);
            //holder.ok=(Button)view.findViewById(R.id.)
            view.setTag(holder);
        } else {   //else里面说明，convertView已经被复用了，说明convertView中已经设置过tag了，即holder
            holder = (NewFriendAdapter.ViewHolder) view.getTag();
        }
        Friend item = fDatas.get(i);

        holder.image.setImageResource(item.getFriendImg());
        holder.name.setText(item.getFriendName());
        holder.speak.setText((item.getFriendSpeak()));

        return view;
    }

    private class ViewHolder {
        ImageView image;
        TextView name;
        TextView speak;
        Button ok;
        Button refuse;
    }
}
