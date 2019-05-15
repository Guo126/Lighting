package com.dianmo.flash.Entity;

import com.google.gson.annotations.Expose;

public class Friend {
    private String imgUrl;
    private String name;
    @Expose(serialize = false,deserialize = false)
    private String id;

    public Friend() {
    }

    public Friend(String friendImg, String friendName,String id) {
        this.imgUrl = friendImg;
        this.name = friendName;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFriendImg() {
        return imgUrl;
    }

    public void setFriendImg(String friendImg) {
        this.imgUrl = friendImg;
    }

    public String getFriendName() {
        return name;
    }

    public void setFriendName(String friendName) {
        this.name = friendName;
    }

}
