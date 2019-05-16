package com.dianmo.flash.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.dianmo.flash.Adapter.FriItemAdapter;
import com.dianmo.flash.EditActivity;
import com.dianmo.flash.Entity.FriItem;
import com.dianmo.flash.Entity.FriendLists;
import com.dianmo.flash.Entity.user.UserInner;
import com.dianmo.flash.MustActivity;
import com.dianmo.flash.R;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentA extends Fragment {
    public static final int take =1;
    private UserInner userInner = new UserInner();
    private ListView list;
    private ArrayList<FriItem> items;
    private FriItemAdapter adapter;

    public FragmentA() {
        // Required empty public constructor
        items = new ArrayList<FriItem>();
        items.add(new FriItem(R.drawable.man1,1,"小海","晚上一起去吃饭吧","10:45"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mes, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = (ListView)getActivity().findViewById(R.id.list);
        userInner = (UserInner) getActivity().getIntent().getSerializableExtra("userInner");
        adapter =  new FriItemAdapter(getContext(),items);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FriItem item =items.get(position);
                Intent intent =new Intent(getActivity(), EditActivity.class);
                intent.putExtra("name",item.getFriName());
                intent.putExtra("id",FriendLists.getInstance().GetFriId(item.getFriName()));
                startActivity(intent);
            }
        });

        ((MustActivity)getActivity()).setUpdateMsg(new MustActivity.IUpdateMsg() {
            @Override
            public void onUpdate(String id) {
                String friName = FriendLists.getInstance().GetFriName(id);

                for( FriItem item :items){
                    if(item.getFriName().equals(friName)){
                        item.setMesNum(1+ item.getMesNum());
                        item.setFriMes(((MustActivity)getActivity()).data);
                        Time time = new Time();
                        time.setToNow();
                        item.setTime(String.valueOf(time.hour)+":"+String.valueOf(time.minute));
                        adapter.notifyDataSetChanged();
                        return;
                    }
                }
                items.add(new FriItem(R.drawable.man1,1,friName,((MustActivity)getActivity()).data,String.valueOf(System.currentTimeMillis())));
                adapter.notifyDataSetChanged();
            }
        });
    }




}
