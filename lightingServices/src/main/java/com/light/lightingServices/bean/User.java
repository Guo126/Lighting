package com.light.lightingServices.bean;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private
    Integer id;

    @Column(name = "phone")
    private
    String phone;

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

    public User(Integer id, String phone, String psw, String name, String icon) {
        this.id = id;
        this.phone = phone;
        this.psw = psw;
        this.name = name;
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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
