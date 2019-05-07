package com.light.lightingServices.controller;

import com.light.lightingServices.model.DTO.BaseMsg;
import com.light.lightingServices.model.DTO.CircleMsg;
import com.light.lightingServices.services.CircleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/circle")
public class CircleController {

    @Autowired
    private
    CircleServices circleServices;

    @PostMapping("/deploy")
    BaseMsg<CircleMsg> deployCircle(@RequestParam("id") BigInteger id,
                                    @RequestParam("content") String content,
                                    @RequestParam(value = "img",required = false) MultipartFile img)
    {
        return circleServices.deployCircle(id,content,img);
    }


    @PostMapping("/get")
    BaseMsg<List<CircleMsg>> getFriendsCircle(@RequestParam("id") BigInteger id)
    {
        return circleServices.getFriendsCircle(id);
    }


    @PostMapping("/speak")
    BaseMsg<Null> deployComment(@RequestParam("uid")BigInteger uid,
                                @RequestParam("cid")Integer cid,
                                @RequestParam("content") String content,
                                @RequestParam(value = "img",required = false) MultipartFile img)
    {
        return circleServices.deployComment(uid,cid,content,img);
    }

}
