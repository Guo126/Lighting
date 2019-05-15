package com.dianmo.flash.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.dianmo.flash.Adapter.FriItemAdapter;
import com.dianmo.flash.ChatActivity;
import com.dianmo.flash.EditActivity;
import com.dianmo.flash.Entity.FriItem;
import com.dianmo.flash.Entity.FriendLists;
import com.dianmo.flash.Entity.user.UserInner;
import com.dianmo.flash.R;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentA extends Fragment {
    public static final int take =1;
    private UserInner userInner = new UserInner();
    private ListView list;
    private ArrayList<FriItem> items;

    public FragmentA() {
        // Required empty public constructor
        items = new ArrayList<FriItem>();
        items.add(new FriItem(R.drawable.girl,2,"叶轻灵","在吗?","11:12"));
        items.add(new FriItem(R.drawable.man1,2,"小海","晚上一起去吃饭吧","10:45"));
        items.add(new FriItem(R.drawable.man2,2,"王晴","晚上一起","昨天"));
        items.add(new FriItem(R.drawable.man3,2,"吴瑶","去吃饭吧","3月1日"));

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
        list.setAdapter(new FriItemAdapter(getContext(),items));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FriItem item =items.get(position);
                Intent intent =new Intent(getActivity(), EditActivity.class);
                intent.putExtra("name",item.getFriName());
                startActivity(intent);
            }
        });
    }


}
