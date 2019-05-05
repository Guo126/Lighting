package com.dianmo.flash.Entity;

public class Friend {
    private int friendImg = 0;
    private String friendName = null;
    private String FriendSpeak = null;

    public Friend() {
    }

    public Friend(int friendImg, String friendName, String friendSpeak) {
        this.friendImg = friendImg;
        this.friendName = friendName;
        FriendSpeak = friendSpeak;
    }

    public int getFriendImg() {
        return friendImg;
    }

    public void setFriendImg(int friendImg) {
        this.friendImg = friendImg;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendSpeak() {
        return FriendSpeak;
    }

    public void setFriendSpeak(String friendSpeak) {
        FriendSpeak = friendSpeak;
    }
}
