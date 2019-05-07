package com.light.lightingServices.model.DTO;

import com.light.lightingServices.model.bean.CircleComment;

import java.sql.Timestamp;
import java.util.List;

public class CircleMsg {

    private Integer id;
    private String content;
    private Timestamp time;
    private String img;

    private List<CircleComment> circleCommentList;

    public CircleMsg() {
    }

    public CircleMsg(Integer id, String content, String img, List<CircleComment> circleCommentList) {
        this.id = id;
        this.content = content;
        this.img = img;
        this.circleCommentList = circleCommentList;
    }

    public CircleMsg(String content, String img, List<CircleComment> circleCommentList) {
        this.content = content;
        this.img = img;
        this.circleCommentList = circleCommentList;
    }

    public CircleMsg(Integer id, String content, Timestamp time, String img, List<CircleComment> circleCommentList) {
        this.id = id;
        this.content = content;
        this.time = time;
        this.img = img;
        this.circleCommentList = circleCommentList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
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

    public List<CircleComment> getCircleCommentList() {
        return circleCommentList;
    }

    public void setCircleCommentList(List<CircleComment> circleCommentList) {
        this.circleCommentList = circleCommentList;
    }
}
