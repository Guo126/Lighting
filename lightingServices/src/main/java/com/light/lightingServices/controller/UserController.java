package com.light.lightingServices.controller;


import com.light.lightingServices.model.DTO.BaseMsg;
import com.light.lightingServices.model.DTO.FriendMsg;
import com.light.lightingServices.model.DTO.UserMsg;
import com.light.lightingServices.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;
import java.math.BigInteger;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private
    UserServices userServices;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }


    @PostMapping("/register")
    BaseMsg<UserMsg> register(@RequestParam("phone") BigInteger phone,
                              @RequestParam("psw") String psw,
                              @RequestParam("name") String name)
    {
        return userServices.register(phone,psw,name);
    }

    @PostMapping("/login")
    BaseMsg<UserMsg> login(@RequestParam("phone")BigInteger phone,
                           @RequestParam("psw") String psw)
    {
        return userServices.login(phone,psw);
    }


    @PostMapping("/friend")
    BaseMsg<FriendMsg> getAFriend(@RequestParam("id") BigInteger id)
    {
        return userServices.getFriend(id);
    }


    @PostMapping("/makeFriend")
    BaseMsg<FriendMsg> makeFriend(@RequestParam("uid")BigInteger uid,
                                  @RequestParam("fid")BigInteger fid)
    {
        return userServices.makeFriend(uid,fid);
    }


    @PostMapping("/upload")
    BaseMsg<Null> uploadIcon(@RequestParam("uid") BigInteger uid,
                             @RequestParam("img")MultipartFile img)
    {
        return userServices.uploadIcon(uid,img);
    }


}
