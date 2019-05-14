package com.dianmo.flash.Entity;

public class Friend {
    private String imgUrl;
    private String name;
    private String FriendSpeak;

    public Friend() {
    }

    public Friend(String friendImg, String friendName, String friendSpeak) {
        this.imgUrl = friendImg;
        this.name = friendName;
        FriendSpeak = friendSpeak;
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

    public String getFriendSpeak() {
        return FriendSpeak;
    }

    public void setFriendSpeak(String friendSpeak) {
        FriendSpeak = friendSpeak;
    }
}
