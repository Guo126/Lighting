package com.light.lightingServices.model.bean;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "circle")
public class Circle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "circle_id")
    private Integer circle;

    @Column(name = "user_id")
    private BigInteger userId;

    @Column(name = "content")
    private String content;

    @Column(name = "img")
    private String img;

    @Column(name = "time")
    private Timestamp time;

    public Circle() {
    }

    public Circle(BigInteger userId, String content, String img) {
        this.userId = userId;
        this.content = content;
        this.img = img;
    }

    public Circle(Integer circle, BigInteger userId, String content, String img) {
        this.circle = circle;
        this.userId = userId;
        this.content = content;
        this.img = img;
    }

    public Circle(BigInteger userId, String content, String img, Timestamp time) {
        this.userId = userId;
        this.content = content;
        this.img = img;
        this.time = time;
    }

    public Circle(Integer circle, BigInteger userId, String content, String img, Timestamp time) {
        this.circle = circle;
        this.userId = userId;
        this.content = content;
        this.img = img;
        this.time = time;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getCircle() {
        return circle;
    }

    public void setCircle(Integer circle) {
        this.circle = circle;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
