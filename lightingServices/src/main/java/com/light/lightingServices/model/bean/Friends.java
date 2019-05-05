package com.light.lightingServices.model.bean;

import com.light.lightingServices.model.bean.compositeId.FriendsID;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "friends")
@IdClass(FriendsID.class)
public class Friends {

    @Id
    @Column(name = "user_a_id")
    private BigInteger user_a_id;

    @Id
    @Column(name = "user_b_id")
    private BigInteger user_b_id;

    public Friends() {
    }

    public Friends(BigInteger user_a_id, BigInteger user_b_id) {
        this.user_a_id = user_a_id;
        this.user_b_id = user_b_id;
    }

    public BigInteger getUser_a_id() {
        return user_a_id;
    }

    public void setUser_a_id(BigInteger user_a_id) {
        this.user_a_id = user_a_id;
    }

    public BigInteger getUser_b_id() {
        return user_b_id;
    }

    public void setUser_b_id(BigInteger user_b_id) {
        this.user_b_id = user_b_id;
    }
}
