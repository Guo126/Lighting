package com.light.lightingServices.model.DTO;

public class FriendMsg {

    private String imgUrl;
    private String name;

    public FriendMsg() {
    }

    public FriendMsg(String imgUrl, String name) {
        this.imgUrl = imgUrl;
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
