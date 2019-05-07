package com.dianmo.flash.Entity.user;

public class BasMsg {
    String alter;
    boolean success;


    public BasMsg() {
    }

    public BasMsg(String alter, boolean success, UserInner userInner) {
        this.alter = alter;
        this.success = success;

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


}
