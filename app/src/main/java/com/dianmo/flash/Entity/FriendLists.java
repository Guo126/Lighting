package com.dianmo.flash.Entity;

import android.util.Log;

import com.dianmo.flash.Entity.user.FriendFromServ;
import com.dianmo.flash.Entity.user.NewFriendFromServ;
import com.dianmo.flash.uitl.INetCallback;
import com.dianmo.flash.uitl.NetworkUtil;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FriendLists {
    private static final FriendLists INSTANCE = new FriendLists();

    private ArrayList<Friend> friends = new ArrayList<Friend>();
    private ArrayList<NewFriend> newFriends = new ArrayList<NewFriend>();

    private FriendLists(){

       /* newFriends.add(new NewFriend("wang",new BigInteger("17863108336"),String.valueOf(R.drawable.icon)));
        friends.add(new Friend(String.valueOf(R.drawable.icon),"零"));*/
    }
    public static FriendLists getInstance()
    {
        return INSTANCE;
    }

    public ArrayList<Friend> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Friend> friends) {
        this.friends = friends;
    }

    public ArrayList<NewFriend> getNewFriends() {
        return newFriends;
    }

    public void setNewFriends(ArrayList<NewFriend> newFriends) {
        this.newFriends = newFriends;
    }

    public String GetFriName(String id)
    {
        for(Friend friend:friends)
        {
            if(friend.getId().equals(id))
            {
                return friend.getFriendName();
            }
        }
        return null;
    }

    public void GetFriends(List<BigInteger> ui)
    {
        //FriendLists.getInstance().getFriends().add(new Friend(String.valueOf(R.drawable.icon),"零2"));
        List<BigInteger> friendIDs=ui;//记录好友id
        Log.i("好友ids",String.valueOf(friendIDs.size()));
        //查出对应id的好友的详细信息
        for(final BigInteger id:friendIDs)
        {
            Log.i("id",String.valueOf(id));
            NetworkUtil.postMethod("http://39.106.81.100:9999/firefly/user/friend",new HashMap<String, String>(){{
                put("id",id.toString());
            }}, FriendFromServ.class,new INetCallback<FriendFromServ>()
            {
                @Override
                public void onSuccess(FriendFromServ msg) {
                    if (msg != null) {
                        msg.getMsg().setId(id.toString());
                        FriendLists.getInstance().getFriends().add(msg.getMsg());
                    }

                }
            });
        }
    }

    public void GetNewFriendsList(final BigInteger m_id)
    {
        NetworkUtil.postMethod("http://39.106.81.100:9999/firefly/user/reqList", new HashMap<String, String>() {{
            put("uid", m_id.toString());
        }}, NewFriendFromServ.class, new INetCallback<NewFriendFromServ>() {
            @Override
            public void onSuccess(NewFriendFromServ msg) {
                Log.i("NewFirList:::", String.valueOf(msg.isSuccess())+"he");
                if (msg != null&&(msg.getMsg()!=null)) {
                    for(NewFriend n:msg.getMsg())
                    {
                        FriendLists.getInstance().getNewFriends().add(n);
                    }
                }
            }
        });
    }
}
