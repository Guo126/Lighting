package com.dianmo.flash.Entity;

import com.dianmo.flash.R;
import com.dianmo.flash.uitl.NetworkUtil;

import java.util.ArrayList;

public class FriendLists {
    private static final FriendLists INSTANCE = new FriendLists();

    private ArrayList<Friend> friends = new ArrayList<Friend>();
    private ArrayList<Friend> newFriends = new ArrayList<Friend>();

    private FriendLists(){

        //newFriends.add(new Friend(R.drawable.girl,"小ai","dd"));

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

    public ArrayList<Friend> getNewFriends() {
        return newFriends;
    }

    public void setNewFriends(ArrayList<Friend> newFriends) {
        this.newFriends = newFriends;
    }
}
