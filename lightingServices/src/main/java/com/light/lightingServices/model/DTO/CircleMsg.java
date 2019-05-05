package com.light.lightingServices.model.DTO;

import com.light.lightingServices.model.bean.CircleComment;

import java.util.List;

public class CircleMsg {

    private String content;
    private String img;

    private List<CircleComment> circleCommentList;

    public CircleMsg() {
    }

    public CircleMsg(String content, String img, List<CircleComment> circleCommentList) {
        this.content = content;
        this.img = img;
        this.circleCommentList = circleCommentList;
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
