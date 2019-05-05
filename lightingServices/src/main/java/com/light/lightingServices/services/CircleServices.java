package com.light.lightingServices.services;

import com.light.lightingServices.model.DTO.BaseMsg;
import com.light.lightingServices.model.DTO.CircleMsg;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;

public interface CircleServices {

    BaseMsg<CircleMsg> deployCircle(BigInteger uid,String content,MultipartFile img);

}
