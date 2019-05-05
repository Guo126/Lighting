package com.light.lightingServices.model.DTO;

import java.math.BigInteger;
import java.util.List;

public class UserMsg {

    private String name;
    private List<BigInteger> friendIDList;

    public UserMsg() {
    }

    public UserMsg(String name, List<BigInteger> friendIDList) {
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
