package com.light.lightingServices.services.impl;

import com.light.lightingServices.model.DTO.BaseMsg;
import com.light.lightingServices.model.DTO.CircleMsg;
import com.light.lightingServices.model.bean.Circle;
import com.light.lightingServices.repository.CircleRepository;
import com.light.lightingServices.services.CircleServices;
import javafx.scene.input.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class CircleServicesImpl implements CircleServices {

    @Autowired
    private CircleRepository circleRepository;

    @Value(value = "${ResourcePath}")
    private String recoursePath;
    @Value(value = "${getResourcePath}")
    private String urlPath;

    @Override
    public BaseMsg<CircleMsg> deployCircle(BigInteger uid, String content, MultipartFile img) {
        String targetPath = recoursePath + uid + "\\";
        String url = urlPath + uid + "/";

        BaseMsg<CircleMsg> baseMsg = new BaseMsg<>();

        try {
            byte[] imgByte = img.getBytes();
            DateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = dataFormat.format(System.currentTimeMillis())+img.getOriginalFilename();
            File targetFile = new File(targetPath);
            if(!targetFile.exists())
            {
                targetFile.mkdirs();
            }

            Path path = Paths.get(targetPath,fileName);
            Files.write(path,imgByte);

            url += fileName;

            Circle circle = new Circle(uid,content,url);
            circleRepository.save(circle);
            CircleMsg circleMsg = new CircleMsg(content,url,null);

            baseMsg.setSuccess(true);
            baseMsg.setMsg(circleMsg);

        } catch (IOException e) {
            e.printStackTrace();

            baseMsg.setSuccess(false);
            return baseMsg;
        }


        return baseMsg;
    }
}
