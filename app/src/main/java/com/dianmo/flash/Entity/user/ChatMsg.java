package com.dianmo.flash.Entity.user;



public class ChatMsg {
    private String friId;
    private Byte[] msg;


    public ChatMsg(String friId, Byte[] msg) {
        this.friId = friId;
        this.msg = msg;
    }

    public ChatMsg() {
    }

    public String getFriId() {
        return friId;
    }

    public void setFriId(String friId) {
        this.friId = friId;
    }

    public Byte[] getMsg() {
        return msg;
    }

    public void setMsg(Byte[] msg) {
        this.msg = msg;
    }
}
