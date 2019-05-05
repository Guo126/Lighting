package com.dianmo.flash.Entity.user;

import java.math.BigInteger;
import java.util.List;

public class UserInner {

    private String name;
    private List<BigInteger> friendIDList;

    public UserInner() {
    }

    public UserInner(String name, List<BigInteger> friendIDList) {
        this.name = name;
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
