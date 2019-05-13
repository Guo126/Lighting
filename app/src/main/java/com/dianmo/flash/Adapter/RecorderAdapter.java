package com.dianmo.flash.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dianmo.flash.ChatActivity;
import com.dianmo.flash.Entity.Friend;
import com.dianmo.flash.R;

import java.util.ArrayList;
import java.util.List;

public class RecorderAdapter extends ArrayAdapter<ChatActivity.Recorder> {
    private List<ChatActivity.Recorder> mDatas;
    private Context context;
    private int minLength;
    private int maxLength;
    private LayoutInflater inflater;
    public RecorderAdapter(Context context, List<ChatActivity.Recorder> mDatas) {
        super(context,-1,mDatas);
        this.mDatas = mDatas;
        this.context = context;
        inflater = LayoutInflater.from(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        minLength = (int) (dm.widthPixels*0.2f);
        maxLength = (int) (dm.heightPixels*0.15f);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.chat_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.seconds = convertView.findViewById(R.id.time);
            viewHolder.length = convertView.findViewById(R.id.recorder_length);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.seconds.setText(Math.round(getItem(position).getTime())+"\'");
        ViewGroup.LayoutParams lp = viewHolder.length.getLayoutParams();
       // lp.width = (int) minLength;
        lp.width = (int) (minLength + (maxLength/60f*getItem(position).getTime()));
        return convertView;
    }

    private class ViewHolder{
        TextView seconds;
        View length;
    }
}
