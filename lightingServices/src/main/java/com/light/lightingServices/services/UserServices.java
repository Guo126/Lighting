package com.light.lightingServices.services;

import com.light.lightingServices.model.DTO.BaseMsg;
import com.light.lightingServices.model.DTO.FriendMsg;
import com.light.lightingServices.model.DTO.FriendReqMsg;
import com.light.lightingServices.model.DTO.UserMsg;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;
import java.math.BigInteger;
import java.util.List;

public interface UserServices {
    BaseMsg<UserMsg> login(BigInteger phone,String psw);
    BaseMsg<UserMsg> register(BigInteger phone, String psw, String name);
    BaseMsg<FriendMsg> getFriend(BigInteger id);
    BaseMsg<Null> reqFriend(BigInteger uid,BigInteger fid);
    BaseMsg<String> uploadIcon(BigInteger id, MultipartFile file);
    BaseMsg<Null> rename(BigInteger id,String name);
    BaseMsg<List<FriendReqMsg>> getRequireFriendList(BigInteger uid);
    BaseMsg<Null> agree(BigInteger uid,BigInteger fid,Boolean agree);
}
