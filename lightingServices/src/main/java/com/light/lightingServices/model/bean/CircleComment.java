package com.light.lightingServices.model.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "circle_comment")
public class CircleComment {

    @Id
    @GeneratedValue
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

    public CircleComment() {
    }

    public CircleComment(Integer commentId, BigInteger userId, Integer circleId, String content) {
        this.commentId = commentId;
        this.userId = userId;
        this.circleId = circleId;
        this.content = content;
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
