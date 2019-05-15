package com.dianmo.flash.Entity.user;

import com.dianmo.flash.Entity.Friend;

public class FriendFromServ {
    String alter;
    boolean success;
    Friend msg;

    public FriendFromServ() {
    }

    public FriendFromServ(String alter, boolean success, Friend msg) {
        this.alter = alter;
        this.success = success;
        this.msg = msg;
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

    public Friend getMsg() {
        return msg;
    }

    public void setMsg(Friend msg) {
        this.msg = msg;
    }
}
