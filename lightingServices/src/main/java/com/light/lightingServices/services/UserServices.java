package com.light.lightingServices.services;

import com.light.lightingServices.model.DTO.BaseMsg;
import com.light.lightingServices.model.DTO.UserMsg;

import java.math.BigInteger;

public interface UserServices {
    BaseMsg<UserMsg> login(BigInteger phone,String psw);
    BaseMsg<UserMsg> register(BigInteger phone, String psw, String name);
}
