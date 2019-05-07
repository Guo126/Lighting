package com.light.lightingServices.model.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "circle_comment")
public class CircleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    @JsonIgnore
    private Integer commentId;

    @Column(name = "user_id")
    private BigInteger userId;

    @Column(name = "circle_id")
    @JsonIgnore
    private Integer circleId;

    @Column(name = "content")
    private String content;

    @Column(name = "img")
    private String img;

    @Column(name = "time")
    private Timestamp time;

    public CircleComment() {
    }

    public CircleComment(Integer commentId, BigInteger userId, Integer circleId, String content) {
        this.commentId = commentId;
        this.userId = userId;
        this.circleId = circleId;
        this.content = content;
    }

    public CircleComment(BigInteger userId, Integer circleId, String content, String img, Timestamp time) {
        this.userId = userId;
        this.circleId = circleId;
        this.content = content;
        this.img = img;
        this.time = time;
    }

    public CircleComment(Integer commentId, BigInteger userId, Integer circleId, String content, String img, Timestamp time) {
        this.commentId = commentId;
        this.userId = userId;
        this.circleId = circleId;
        this.content = content;
        this.img = img;
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public Integer getCircleId() {
        return circleId;
    }

    public void setCircleId(Integer circleId) {
        this.circleId = circleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
