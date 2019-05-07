package com.dianmo.flash.Entity.user;

public class BasMsg {
    String alter;
    String msg;
    boolean success;


    public BasMsg() {
    }

    public BasMsg(String alter, String msg,boolean success) {
        this.alter = alter;
        this.msg = msg;
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


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
