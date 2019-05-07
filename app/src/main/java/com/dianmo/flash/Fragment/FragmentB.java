package com.dianmo.flash.Fragment;


import android.content.Intent;
import android.os.Bundle;

import android.os.Debug;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.util.Log;
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
import android.widget.Toast;

import com.dianmo.flash.Adapter.FriItemAdapter;
import com.dianmo.flash.Adapter.ListAdapter;
import com.dianmo.flash.Adapter.NewFriendAdapter;
import com.dianmo.flash.ChatActivity;
import com.dianmo.flash.Entity.Friend;
import com.dianmo.flash.FriendMessage;
import com.dianmo.flash.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentB extends Fragment {
    public static final int take =1;

    private ListView list;
    private ListView newfirList;
    private ArrayList<Friend> friends;
    private ArrayList<Friend> findFri;
    private ArrayList<Friend> newFriends;
    public FragmentB() {
        // Required empty public constructor
        //加载好友列表
        friends = new ArrayList<Friend>();
        findFri = new ArrayList<Friend>();
        newFriends=new ArrayList<Friend>();

        newFriends.add(new Friend(R.drawable.girl,"小ai","dd"));

        friends.add(new Friend(R.drawable.girl,"叶轻灵","sdasdasddd"));
        friends.add(new Friend(R.drawable.girl,"小琴","asddd"));
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

        ImageView add=(ImageView)getActivity().findViewById(R.id.imageView);
        final EditText find=(EditText)getActivity().findViewById(R.id.friendFind);
        list = (ListView)getActivity().findViewById(R.id.list);
        newfirList=(ListView)getActivity().findViewById(R.id.newfirList);

        newfirList.setAdapter(new NewFriendAdapter(getContext(),newFriends,this));
        list.setAdapter(new ListAdapter(getContext(),friends));
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
                Intent intent =new Intent(getActivity(), ChatActivity.class);
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
                        for(Friend friend:friends)
                        {
                            if(friend.getFriendName().equals(findName))
                            {
                                findFri.add(friend);
                                findable=true;
                            }
                        }
                        if(!findable)
                        {
                            list.setAdapter(new ListAdapter(getContext(),friends));
                            findFri.clear();
                            findable=false;
                        }
                        if(!findFri.isEmpty())
                        {
                            list.setAdapter(new ListAdapter(getContext(),findFri));
                        }
                        else
                        {
                            list.setAdapter(new ListAdapter(getContext(),friends));
                            findFri.clear();
                        }
                    }
                    else
                    {
                        list.setAdapter(new ListAdapter(getContext(),friends));
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
            //数据添加
            /*
            *
            *
            *
             */
            newFriends.remove(i);
            list.setAdapter(new ListAdapter(getContext(),friends));
        }
        else
        {
            newFriends.remove(i);
        }

        newfirList.setAdapter(new NewFriendAdapter(getContext(),newFriends,this));
    }

}
