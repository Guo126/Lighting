package com.light.lightingServices.model.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "friend_require_que")
public class FriendReqQue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Integer id;

    @Column(name = "uid")
    private BigInteger uid;

    @Column(name = "fid")
    private BigInteger fid;

    public FriendReqQue() {
    }

    public FriendReqQue(Integer id, BigInteger uid, BigInteger fid) {
        this.id = id;
        this.uid = uid;
        this.fid = fid;
    }

    public FriendReqQue(BigInteger uid, BigInteger fid) {
        this.uid = uid;
        this.fid = fid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getUid() {
        return uid;
    }

    public void setUid(BigInteger uid) {
        this.uid = uid;
    }

    public BigInteger getFid() {
        return fid;
    }

    public void setFid(BigInteger fid) {
        this.fid = fid;
    }
}
