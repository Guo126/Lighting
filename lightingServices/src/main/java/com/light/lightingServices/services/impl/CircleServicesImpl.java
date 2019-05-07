package com.light.lightingServices.services.impl;

import com.light.lightingServices.model.DTO.BaseMsg;
import com.light.lightingServices.model.DTO.CircleMsg;
import com.light.lightingServices.model.bean.Circle;
import com.light.lightingServices.model.bean.CircleComment;
import com.light.lightingServices.model.bean.Friends;
import com.light.lightingServices.repository.CircleCommentRepository;
import com.light.lightingServices.repository.CircleRepository;
import com.light.lightingServices.repository.FriendRepository;
import com.light.lightingServices.services.CircleServices;
import com.light.lightingServices.uitl.FileUtil;
import javafx.scene.input.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CircleServicesImpl implements CircleServices {

    @Autowired
    private CircleRepository circleRepository;
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private CircleCommentRepository circleCommentRepository;

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
            Circle circle;
            CircleMsg circleMsg;
            if(img != null) {
                url += FileUtil.save(img, targetPath);
                circle = new Circle(uid, content, url, new Timestamp(System.currentTimeMillis()));
                circleMsg = new CircleMsg(circle.getCircle(), content, url, null);
            }
            else {
                circle = new Circle(uid, content, null, new Timestamp(System.currentTimeMillis()));
                circleMsg = new CircleMsg(circle.getCircle(), content, null, null);
            }
            circleRepository.save(circle);



            baseMsg.setSuccess(true);
            baseMsg.setMsg(circleMsg);

        } catch (IOException e) {
            e.printStackTrace();

            baseMsg.setSuccess(false);
            return baseMsg;
        }


        return baseMsg;
    }

    @Override
    public BaseMsg<List<CircleMsg>> getFriendsCircle(BigInteger uid) {

        BaseMsg<List<CircleMsg>> bcml = new BaseMsg<>();

        List<Circle> circleList = new ArrayList<>();
        circleList = circleRepository.getAllCircle(uid);

        List<CircleMsg> cml = new ArrayList<>();

        for (Circle c : circleList) {
            List<CircleComment> ccl = circleCommentRepository.getAllComment(c.getCircle());
            CircleMsg cm = new CircleMsg(c.getCircle(), c.getContent(), c.getTime(), c.getImg(), ccl);
            cml.add(cm);
        }

        bcml.setMsg(cml);
        bcml.setSuccess(true);

        return bcml;
    }

    @Override
    public BaseMsg<Null> deployComment(BigInteger uid, Integer cid, String content, MultipartFile file) {
        String targetPath = recoursePath + uid + "\\" + cid + "\\";
        String url = urlPath + uid + "/" + cid + "/";

        BaseMsg<Null> baseMsg = new BaseMsg<>();

        try {
            CircleComment circleComment;
            if (file != null) {
                url += FileUtil.save(file, targetPath);
                circleComment = new CircleComment(uid, cid, content, url, new Timestamp(System.currentTimeMillis()));
            } else {
                circleComment = new CircleComment(uid, cid, content, null, new Timestamp(System.currentTimeMillis()));
            }

            circleCommentRepository.save(circleComment);
            baseMsg.setSuccess(true);
        } catch (IOException e) {
            e.printStackTrace();

            baseMsg.setSuccess(false);
            return baseMsg;
        }


        return baseMsg;
    }


}
