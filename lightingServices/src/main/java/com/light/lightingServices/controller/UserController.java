package com.light.lightingServices.controller;


import com.light.lightingServices.model.DTO.BaseMsg;
import com.light.lightingServices.model.DTO.FriendMsg;
import com.light.lightingServices.model.DTO.FriendReqMsg;
import com.light.lightingServices.model.DTO.UserMsg;
import com.light.lightingServices.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;
import java.math.BigInteger;
import java.util.List;

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

    @PostMapping("/rename")
    BaseMsg<Null> rename(@RequestParam("id") BigInteger uid,
                         @RequestParam("name") String name)
    {
        return userServices.rename(uid,name);
    }


    @PostMapping("/friend")
    BaseMsg<FriendMsg> getAFriend(@RequestParam("id") BigInteger id)
    {
        return userServices.getFriend(id);
    }


    @PostMapping("/req")
    BaseMsg<Null> requireFriend(@RequestParam("uid")BigInteger uid,
                                  @RequestParam("fid")BigInteger fid)
    {
        return userServices.reqFriend(uid,fid);
    }

    @PostMapping("/reqList")
    BaseMsg<List<FriendReqMsg>> getRequireFriendList(@RequestParam("uid") BigInteger uid)
    {
        return userServices.getRequireFriendList(uid);
    }

    @PostMapping("/agree")
    BaseMsg<Null> agree(@RequestParam("uid") BigInteger uid,
                        @RequestParam("fid") BigInteger fid,
                        @RequestParam("agree") Boolean agree)
    {
        return userServices.agree(uid,fid,agree);
    }


    @PostMapping("/upload")
    BaseMsg<String> uploadIcon(@RequestParam("uid") BigInteger uid,
                             @RequestParam("img")MultipartFile img)
    {
        return userServices.uploadIcon(uid,img);
    }


}
