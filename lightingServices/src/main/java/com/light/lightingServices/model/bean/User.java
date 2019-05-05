package com.light.lightingServices.model.bean;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "user")
public class User {


    @Id
    @Column(name = "phone")
    private
    BigInteger phone;

    @Column(name = "psw")
    private
    String psw;

    @Column(name = "name")
    private
    String name;

    @Column(name = "icon")
    private
    String icon;

    public User() {
    }

    public User(BigInteger phone, String psw, String name, String icon) {
        this.phone = phone;
        this.psw = psw;
        this.name = name;
        this.icon = icon;
    }

    public BigInteger getPhone() {
        return phone;
    }

    public void setPhone(BigInteger phone) {
        this.phone = phone;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
