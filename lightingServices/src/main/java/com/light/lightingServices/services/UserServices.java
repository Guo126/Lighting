package com.light.lightingServices.services;

import com.light.lightingServices.model.DTO.BaseMsg;
import com.light.lightingServices.model.DTO.FriendMsg;
import com.light.lightingServices.model.DTO.UserMsg;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;
import java.math.BigInteger;

public interface UserServices {
    BaseMsg<UserMsg> login(BigInteger phone,String psw);
    BaseMsg<UserMsg> register(BigInteger phone, String psw, String name);
    BaseMsg<FriendMsg> getFriend(BigInteger id);
    BaseMsg<FriendMsg> makeFriend(BigInteger uid,BigInteger fid);
    BaseMsg<Null> uploadIcon(BigInteger id, MultipartFile file);
}
