package com.dianmo.flash.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dianmo.flash.Entity.FriItem;
import com.dianmo.flash.Entity.Friend;
import com.dianmo.flash.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    public ListAdapter(Context context, List<Friend> fDatas) {
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
        ViewHolder holder = null;
        if (view == null) {
            view=fInflater.inflate(R.layout.activity_friendlist,viewGroup,false);
            holder=new ViewHolder();
            holder.image=(SimpleDraweeView) view.findViewById(R.id.friendImg);
            holder.name=(TextView)view.findViewById(R.id.name);
            view.setTag(holder);
        } else {   //else里面说明，convertView已经被复用了，说明convertView中已经设置过tag了，即holder
            holder = (ViewHolder) view.getTag();
        }
        Friend item = fDatas.get(i);

        if(item.getFriendImg()==null)
        {
            item.setFriendImg(String.valueOf(R.drawable.icon));
        }
        holder.image.setImageURI(Uri.parse(item.getFriendImg()));
        if(item.getFriendName()==null)
        {
            item.setFriendName("萤火");
        }
        holder.name.setText(item.getFriendName());

        return view;
    }

    private class ViewHolder {
        SimpleDraweeView image;
        TextView name;
    }
}
