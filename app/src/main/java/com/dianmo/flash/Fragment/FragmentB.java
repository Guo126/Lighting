package com.dianmo.flash.Fragment;


import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dianmo.flash.Adapter.ListAdapter;
import com.dianmo.flash.Adapter.NewFriendAdapter;
import com.dianmo.flash.EditActivity;
import com.dianmo.flash.Entity.Friend;

import com.dianmo.flash.FriendMessage;
import com.dianmo.flash.Entity.user.UserInner;


import com.dianmo.flash.Entity.FriendLists;

import com.dianmo.flash.R;
import com.dianmo.flash.uitl.INetCallback;
import com.dianmo.flash.uitl.NetworkUtil;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentB extends Fragment {
    public static final int take =1;


    private ListView list;
    private ListView newfirList;
    private List<BigInteger> friIds;

    private ArrayList<Friend> findFri;

    public FragmentB() {
        findFri = new ArrayList<Friend>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        friIds = ((UserInner) getActivity().getIntent().getSerializableExtra("userInner")).getFriendIDList();
        GetFriends(friIds);
        ImageView add=(ImageView)getActivity().findViewById(R.id.imageView);
        final EditText find=(EditText)getActivity().findViewById(R.id.friendFind);
        list = (ListView)getActivity().findViewById(R.id.list);
        newfirList=(ListView)getActivity().findViewById(R.id.newfirList);

        newfirList.setAdapter(new NewFriendAdapter(getContext(),FriendLists.getInstance().getNewFriends(),this));
        list.setAdapter(new ListAdapter(getContext(),FriendLists.getInstance().getFriends()));
        newfirList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //oast.makeText(getActivity(),"转到添加好友界面",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), FriendMessage.class);
                startActivity(intent);
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Friend item =(Friend)parent.getAdapter().getItem(position);
                Intent intent =new Intent(getActivity(), EditActivity.class);
                intent.putExtra("name",item.getFriendName());
                startActivity(intent);
            }
        });
        //添加
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        find.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_SEARCH)
                {
                    String findName=textView.getText().toString();
                    if(!findName.equals(""))
                    {
                        boolean findable=false;
                        for(Friend friend:FriendLists.getInstance().getFriends())
                        {
                            if(friend.getFriendName().equals(findName))
                            {
                                findFri.add(friend);
                                findable=true;
                            }
                        }
                        if(!findable)
                        {
                            list.setAdapter(new ListAdapter(getContext(),FriendLists.getInstance().getFriends()));
                            findFri.clear();
                            findable=false;
                        }
                        if(!findFri.isEmpty())
                        {
                            list.setAdapter(new ListAdapter(getContext(),findFri));
                        }
                        else
                        {
                            list.setAdapter(new ListAdapter(getContext(),FriendLists.getInstance().getFriends()));
                            findFri.clear();
                        }
                    }
                    else
                    {
                        list.setAdapter(new ListAdapter(getContext(),FriendLists.getInstance().getFriends()));
                        findFri.clear();
                    }
                    return true;
                }
                return false;
            }
        });

    }

    public void NewFirCallback(int i,boolean agree)
    {
        if(agree)
        {
            //数据添加，自己添加，对方也添加
            /*
            *
            *
            *
             */
            FriendLists.getInstance().getNewFriends().remove(i);
            list.setAdapter(new ListAdapter(getContext(),FriendLists.getInstance().getFriends()));
        }
        else
        {
            FriendLists.getInstance().getNewFriends().remove(i);
        }

        newfirList.setAdapter(new NewFriendAdapter(getContext(),FriendLists.getInstance().getNewFriends(),this));
    }

    private void GetFriends(List<BigInteger> ui)
    {
        List<BigInteger> friendIDs=ui;//记录好友id
        //查出对应id的好友的详细信息
        for(final BigInteger id:friendIDs)
        {
            NetworkUtil.postMethod("",new HashMap<String, String>(){{
                put("uid",id.toString());
            }},Friend.class,new INetCallback<Friend>()
            {
                @Override
                public void onSuccess(Friend msg) {
                    if(msg!=null){
                        FriendLists.getInstance().getFriends().add(msg);
                    }
                }
            });
        }

    }
}
