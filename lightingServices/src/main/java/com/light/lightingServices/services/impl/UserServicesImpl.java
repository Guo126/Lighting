package com.light.lightingServices.services.impl;

import com.light.lightingServices.model.DTO.BaseMsg;
import com.light.lightingServices.model.DTO.CircleMsg;
import com.light.lightingServices.model.DTO.FriendMsg;
import com.light.lightingServices.model.DTO.UserMsg;
import com.light.lightingServices.model.bean.Circle;
import com.light.lightingServices.model.bean.Friends;
import com.light.lightingServices.model.bean.User;
import com.light.lightingServices.repository.FriendRepository;
import com.light.lightingServices.repository.UserRepository;
import com.light.lightingServices.services.UserServices;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private
    UserRepository userRepository;
    @Autowired
    private
    FriendRepository friendRepository;


    @Value(value = "${ResourcePath}")
    private String recoursePath;
    @Value(value = "${getResourcePath}")
    private String urlPath;


    @Override
    public BaseMsg<UserMsg> login(BigInteger phone, String psw) {
        User master = userRepository.login(phone,psw);
        if(master == null)
        {
            BaseMsg<UserMsg> baseMsg = new BaseMsg<>();
            baseMsg.setSuccess(false);
            return baseMsg;
        }

        List<Friends> friendsList = friendRepository.getFriends(master.getPhone());
        BaseMsg<UserMsg> returnMsg = new BaseMsg<>();
        returnMsg.setSuccess(true);
        UserMsg userMsg = new UserMsg();
        userMsg.setId(master.getPhone());
        userMsg.setPhoto(master.getIcon());
        userMsg.setName(master.getName());
        List<BigInteger> friendsIdList = new ArrayList<>();
        for (Friends f : friendsList)
        {
            friendsIdList.add(f.getUser_b_id());
        }

        userMsg.setFriendIDList(friendsIdList);
        returnMsg.setMsg(userMsg);
        return returnMsg;
    }

    @Override
    public BaseMsg<UserMsg> register(BigInteger phone, String psw, String name) {
        User user = new User(phone,psw,name,null);

        if(userRepository.exist(phone) != null)
        {
            BaseMsg<UserMsg> baseMsg = new BaseMsg<>();
            baseMsg.setSuccess(false);
            baseMsg.setAlter("账号已存在");
            return baseMsg;
        }

        userRepository.save(user);

        BaseMsg<UserMsg> baseMsg = new BaseMsg<>();
        baseMsg.setSuccess(true);

        UserMsg userMsg = new UserMsg();
        userMsg.setId(user.getPhone());
        userMsg.setPhoto(user.getIcon());
        userMsg.setName(user.getName());

        baseMsg.setMsg(userMsg);

        return baseMsg;
    }

    @Override
    public BaseMsg<FriendMsg> getFriend(BigInteger id) {
        BaseMsg<FriendMsg> msg = new BaseMsg<>();
        User user= userRepository.getUser(id);

        FriendMsg friendMsg = new FriendMsg();
        friendMsg.setName(user.getName());
        friendMsg.setImgUrl(user.getIcon());

        msg.setMsg(friendMsg);
        msg.setSuccess(true);

        return msg;
    }

    @Override
    public BaseMsg<FriendMsg> makeFriend(BigInteger uid, BigInteger fid) {
        if(friendRepository.hasFriend(uid,fid) != null)
        {
            BaseMsg<FriendMsg> baseMsg = new BaseMsg<>();
            baseMsg.setSuccess(false);
            baseMsg.setAlter("好友已经存在");
            return baseMsg;
        }


        Friends friends = new Friends(uid,fid);
        friendRepository.save(friends);

        BaseMsg<FriendMsg> baseMsg = new BaseMsg<>();
        baseMsg.setSuccess(true);

        FriendMsg friendMsg = new FriendMsg();
        User f = userRepository.getUser(fid);
        friendMsg.setName(f.getName());
        friendMsg.setImgUrl(f.getIcon());

        baseMsg.setMsg(friendMsg);

        return baseMsg;
    }

    @Override
    public BaseMsg<Null> uploadIcon(BigInteger id, MultipartFile file) {
        String targetPath = recoursePath + id + "\\";
        String url = urlPath + id + "/";

        BaseMsg<Null> baseMsg = new BaseMsg<>();

        try {
            byte[] imgByte = file.getBytes();
            DateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = dataFormat.format(System.currentTimeMillis())+file.getOriginalFilename();
            File targetFile = new File(targetPath);
            if(!targetFile.exists())
            {
                targetFile.mkdirs();
            }

            Path path = Paths.get(targetPath,fileName);
            Files.write(path,imgByte);

            url += fileName;


            User user= userRepository.getUser(id);
            user.setIcon(url);

            userRepository.save(user);

            baseMsg.setSuccess(true);


        } catch (IOException e) {
            e.printStackTrace();

            baseMsg.setSuccess(false);
            return baseMsg;
        }


        return baseMsg;
    }
}
