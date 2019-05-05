package com.light.lightingServices.controller;

import com.light.lightingServices.model.DTO.BaseMsg;
import com.light.lightingServices.model.DTO.CircleMsg;
import com.light.lightingServices.services.CircleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import java.math.BigInteger;

@RestController
@RequestMapping(value = "/circle")
public class CircleController {

    @Autowired
    private
    CircleServices circleServices;

    @PostMapping("/deploy")
    BaseMsg<CircleMsg> deployCircle(@RequestParam("id") BigInteger id,
                                    @RequestParam( "content") String content,
                                    @RequestParam("img") MultipartFile img)
    {
        return circleServices.deployCircle(id,content,img);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        factory.setMaxFileSize("1024MB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("1024MB");
        return factory.createMultipartConfig();
    }
}
