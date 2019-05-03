package com.dianmo.flash.Entity;

public class FriItem  {
    private int FriImg = 0;
    private int MesNum = 0;
    private String FriName = null;
    private String FriMes = null;
    private String Time =null;

    public FriItem(){

    }

    public FriItem(int friImg, int mesNum, String friName, String friMes,String time) {
        FriImg = friImg;
        MesNum = mesNum;
        FriName = friName;
        FriMes = friMes;
        Time =time;
    }

    public int getFriImg() {
        return FriImg;
    }

    public void setFriImg(int friImg) {
        FriImg = friImg;
    }

    public int getMesNum() {
        return MesNum;
    }

    public void setMesNum(int mesNum) {
        MesNum = mesNum;
    }

    public String getFriName() {
        return FriName;
    }

    public void setFriName(String friName) {
        FriName = friName;
    }

    public String getFriMes() {
        return FriMes;
    }

    public void setFriMes(String friMes) {
        FriMes = friMes;
    }


    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}

