package com.dianmo.flash.Entity.user;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class UserInner implements Serializable {

    private String name;
    private BigInteger id;
    private String photo;
    private List<BigInteger> friendIDList;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }



    public UserInner() {
    }

    public UserInner(String name, BigInteger id,List<BigInteger> friendIDList) {
        this.name = name;
        this.id =id;
        this.friendIDList = friendIDList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BigInteger> getFriendIDList() {
        return friendIDList;
    }

    public void setFriendIDList(List<BigInteger> friendIDList) {
        this.friendIDList = friendIDList;
    }
}
