package com.light.lightingServices.services;

import com.light.lightingServices.model.DTO.BaseMsg;
import com.light.lightingServices.model.DTO.CircleMsg;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;
import java.math.BigInteger;
import java.util.List;

public interface CircleServices {

    BaseMsg<CircleMsg> deployCircle(BigInteger uid,String content,MultipartFile img);
    BaseMsg<List<CircleMsg>> getFriendsCircle(BigInteger uid);
    BaseMsg<Null> deployComment(BigInteger uid,Integer cid,String content,MultipartFile file);
}
