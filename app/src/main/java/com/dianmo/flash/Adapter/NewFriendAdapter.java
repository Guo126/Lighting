package com.dianmo.flash.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dianmo.flash.Entity.Friend;
import com.dianmo.flash.Entity.NewFriend;
import com.dianmo.flash.Fragment.FragmentB;
import com.dianmo.flash.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class NewFriendAdapter extends BaseAdapter {
    public NewFriendAdapter(Context context, List<NewFriend> newfDatas, FragmentB fragmentB) {
        this.new_fInflater = LayoutInflater.from(context);
        this.new_fDatas = newfDatas;
        fb=fragmentB;
    }

    private LayoutInflater new_fInflater;
    private List<NewFriend> new_fDatas;
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
            holder.image=(SimpleDraweeView) view.findViewById(R.id.newfriendImg);
            holder.name=(TextView)view.findViewById(R.id.newfir_name);
            holder.ok=(Button)view.findViewById(R.id.button_ok);
            holder.refuse=(Button)view.findViewById(R.id.button_refuse);
            view.setTag(holder);
        } else {   //else里面说明，convertView已经被复用了，说明convertView中已经设置过tag了，即holder
            holder = (NewFriendAdapter.ViewHolder) view.getTag();
        }
        NewFriend item = new_fDatas.get(i);

        if(item.getImg()==null)
        {
            item.setImg(String.valueOf(R.drawable.icon));
        }
        if(item.getName()==null)
        {
            item.setName("萤火");
        }
        holder.image.setImageURI(Uri.parse(item.getImg()));
        holder.name.setText(item.getName());

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
        SimpleDraweeView image;
        TextView name;
        Button ok;
        Button refuse;
    }
}
