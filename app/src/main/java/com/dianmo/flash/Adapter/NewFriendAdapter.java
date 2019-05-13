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
import com.dianmo.flash.Fragment.FragmentB;
import com.dianmo.flash.R;

import java.util.List;

public class NewFriendAdapter extends BaseAdapter {
    public NewFriendAdapter(Context context, List<Friend> newfDatas, FragmentB fragmentB) {
        this.new_fInflater = LayoutInflater.from(context);
        this.new_fDatas = newfDatas;
        fb=fragmentB;
    }

    private LayoutInflater new_fInflater;
    private List<Friend> new_fDatas;
    private FragmentB fb;
    @Override
    public int getCount() {
        return new_fDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return new_fDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        NewFriendAdapter.ViewHolder holder = null;
        if (view == null) {
            view=new_fInflater.inflate(R.layout.newfriendlist,viewGroup,false);
            holder=new NewFriendAdapter.ViewHolder();
            holder.image=(ImageView) view.findViewById(R.id.newfriendImg);
            holder.name=(TextView)view.findViewById(R.id.newfir_name);
            holder.speak=(TextView)view.findViewById(R.id.newfir_speak);
            holder.ok=(Button)view.findViewById(R.id.button_ok);
            holder.refuse=(Button)view.findViewById(R.id.button_refuse);
            view.setTag(holder);
        } else {   //else里面说明，convertView已经被复用了，说明convertView中已经设置过tag了，即holder
            holder = (NewFriendAdapter.ViewHolder) view.getTag();
        }
        Friend item = new_fDatas.get(i);

        holder.image.setImageResource(Integer.parseInt(item.getFriendImg()));
        holder.name.setText(item.getFriendName());
        holder.speak.setText((item.getFriendSpeak()));

        //添加ok和refuse响应函数
        final View view1=view;
        holder.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view1.getContext(),"同意",Toast.LENGTH_SHORT).show();
                if(view1!=null)
                {
                    fb.NewFirCallback(i,true);
                }
            }
        });
        holder.refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view1.getContext(),"同意",Toast.LENGTH_SHORT).show();
                if(view1!=null)
                {
                    fb.NewFirCallback(i,false);
                }
            }
        });
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
