package com.light.lightingServices.controller;

import com.light.lightingServices.model.DTO.BaseMsg;
import com.light.lightingServices.services.WebSocketService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;
import java.io.IOException;

@RestController
@RequestMapping("/chat")
public class ChatController {


    @PostMapping("/p2p/str")
    public BaseMsg<Null> sendToOne(@RequestParam("uid") String uid,
                                   @RequestParam("target") String tid,
                                   @RequestParam("msg") String msg)
    {
        BaseMsg<Null> baseMsg = new BaseMsg<>();
        WebSocketService.sendMessage(uid,tid,"pp"+msg);
        baseMsg.setSuccess(true);
        return baseMsg;
    }

    @PostMapping("/p2p/byte")
    public BaseMsg<Null> sendToOne(@RequestParam("target") String tid,
                                    @RequestParam("msg") MultipartFile msg)
    {
        BaseMsg<Null> baseMsg = new BaseMsg<>();
        try {
            WebSocketService.sendMessage(tid,msg.getBytes());
            baseMsg.setSuccess(true);
            return baseMsg;
        } catch (IOException e) {
            e.printStackTrace();
            baseMsg.setAlter("电波在宇宙之外");
            baseMsg.setSuccess(false);
            return baseMsg;
        }
    }

}
