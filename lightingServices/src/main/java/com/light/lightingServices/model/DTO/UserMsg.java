package com.light.lightingServices.model.DTO;

import java.math.BigInteger;
import java.util.List;

public class UserMsg {

    private BigInteger id;
    private String name;
    private String photo;
    private List<BigInteger> friendIDList;

    public UserMsg() {
    }

    public UserMsg(String name, List<BigInteger> friendIDList) {
        this.name = name;
        this.friendIDList = friendIDList;
    }

    public UserMsg(BigInteger id, String name, String photo, List<BigInteger> friendIDList) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.friendIDList = friendIDList;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
