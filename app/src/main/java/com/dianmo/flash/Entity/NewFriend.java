package com.dianmo.flash.Entity;

import java.math.BigInteger;

public class NewFriend {
    private String name;
    private BigInteger fid;
    private String img;
    NewFriend(){
    }

    public NewFriend(String name, BigInteger fid, String img) {
        this.name = name;
        this.fid = fid;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getFid() {
        return fid;
    }

    public void setFid(BigInteger fid) {
        this.fid = fid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
