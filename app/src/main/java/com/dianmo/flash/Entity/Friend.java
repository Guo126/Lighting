package com.dianmo.flash.Entity;

public class Friend {
    private String imgUrl;
    private String name;

    public Friend() {
    }

    public Friend(String friendImg, String friendName) {
        this.imgUrl = friendImg;
        this.name = friendName;
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
