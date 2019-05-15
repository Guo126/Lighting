package com.dianmo.flash.Entity.user;

import com.dianmo.flash.Entity.NewFriend;

import java.math.BigInteger;
import java.util.List;

public class NewFriendFromServ {
    String alter;
    boolean success;
    List<NewFriend> newFriendFromServ;

    public NewFriendFromServ(String alter, boolean success, List<NewFriend> newFriendFromServ) {
        this.alter = alter;
        this.success = success;
        this.newFriendFromServ = newFriendFromServ;
    }

    public String getAlter() {
        return alter;
    }

    public void setAlter(String alter) {
        this.alter = alter;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<NewFriend> getNewFriendFromServ() {
        return newFriendFromServ;
    }

    public void setNewFriendFromServ(List<NewFriend> newFriendFromServ) {
        this.newFriendFromServ = newFriendFromServ;
    }
}
