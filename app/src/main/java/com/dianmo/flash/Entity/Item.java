package com.dianmo.flash.Entity;



public class Item {
    private int src;
    private String text1;

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }



    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }



    public Item(int src, String text1) {
        this.src = src;
        this.text1 = text1;

    }


}
