package com.dianmo.flash.Entity.user;

public class UserMsg {
    String alter;
    boolean success;
    UserInner msg;

    public UserMsg() {
    }

    public UserMsg(String alter, boolean success, UserInner userInner) {
        this.alter = alter;
        this.success = success;
        this.msg = userInner;
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

    public UserInner getUserInner() {
        return msg;
    }

    public void setUserInner(UserInner userInner) {
        this.msg = userInner;
    }
}
