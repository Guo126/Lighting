package com.light.lightingServices.bean;

import com.light.lightingServices.bean.compositeId.FriendsID;

import javax.persistence.*;

@Entity
@Table(name = "friends")
@IdClass(FriendsID.class)
public class Friends {

    @Id
    @Column(name = "user_a_id")
    private Integer user_a_id;

    @Id
    @Column(name = "user_b_id")
    private Integer user_b_id;

    public Friends() {
    }

    public Friends(Integer userAID, Integer userBID) {
        this.user_a_id = userAID;
        this.user_b_id = userBID;
    }

    public Integer getUser_a_id() {
        return user_a_id;
    }

    public void setUser_a_id(Integer user_a_id) {
        this.user_a_id = user_a_id;
    }

    public Integer getUser_b_id() {
        return user_b_id;
    }

    public void setUser_b_id(Integer user_b_id) {
        this.user_b_id = user_b_id;
    }
}
