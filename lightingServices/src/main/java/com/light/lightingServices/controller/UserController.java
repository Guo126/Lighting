package com.light.lightingServices.controller;


import com.light.lightingServices.model.DTO.BaseMsg;
import com.light.lightingServices.model.DTO.UserMsg;
import com.light.lightingServices.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


}
