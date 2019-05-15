package com.light.lightingServices.services.impl;

import com.google.gson.Gson;
import com.light.lightingServices.model.DTO.*;
import com.light.lightingServices.model.bean.FriendReqQue;
import com.light.lightingServices.model.bean.Friends;
import com.light.lightingServices.model.bean.User;
import com.light.lightingServices.repository.FriendRepository;
import com.light.lightingServices.repository.FriendReqQueRepository;
import com.light.lightingServices.repository.UserRepository;
import com.light.lightingServices.services.UserServices;
import com.light.lightingServices.services.WebSocketService;
import com.light.lightingServices.uitl.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.math.BigInteger;
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

    @Autowired
    private
    FriendReqQueRepository friendReqQueRepository;


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
    public BaseMsg<Null> reqFriend(BigInteger uid, BigInteger fid) {

        BaseMsg<Null> result = new BaseMsg<>();

        User friend = userRepository.getUser(fid);
        User user=  userRepository.getUser(uid);
        if(friend == null || user == null)
        {
            result.setSuccess(false);
            result.setAlter("用户不存在");
            return result;
        }

        FriendReqMsg friendReqMsg = new FriendReqMsg(user.getName(),user.getPhone(),user.getIcon());
        if(WebSocketService.hasExist(fid))
        {
            Gson gson = new Gson();
            WebSocketService.sendMessage(uid.toString(),fid.toString(),"rq"+ gson.toJson(friendReqMsg));
            FriendReqQue friendReqQue = new FriendReqQue(uid,fid);
            friendReqQueRepository.save(friendReqQue);
        }
        else{

            if(friendReqQueRepository.hasExist(uid,fid) != null)
            {
                result.setSuccess(false);
                result.setAlter("已经发送过申请了");
                return result;
            }
            else
            {
                FriendReqQue friendReqQue = new FriendReqQue(uid,fid);
                friendReqQueRepository.save(friendReqQue);
            }

        }

        result.setSuccess(true);
        return result;
    }


    @Override
    public BaseMsg<String> uploadIcon(BigInteger id, MultipartFile file) {
        String targetPath = recoursePath + id + "\\";
        String url = urlPath + id + "/";

        BaseMsg<String> baseMsg = new BaseMsg<>();

        try {
            url += FileUtil.save(file,targetPath);

            User user= userRepository.getUser(id);
            user.setIcon(url);

            userRepository.save(user);

            baseMsg.setSuccess(true);
            baseMsg.setMsg(url);

        } catch (IOException e) {
            e.printStackTrace();

            baseMsg.setSuccess(false);
            return baseMsg;
        }


        return baseMsg;
    }

    @Override
    public BaseMsg<Null> rename(BigInteger id, String name) {
        User user = userRepository.getUser(id);
        if(user == null)
        {
            BaseMsg<Null> baseMsg = new BaseMsg<>();
            baseMsg.setSuccess(false);
            baseMsg.setAlter("用户不存在");
            return baseMsg;
        }

        user.setName(name);
        userRepository.save(user);

        BaseMsg<Null> baseMsg = new BaseMsg<>();
        baseMsg.setSuccess(true);
        return baseMsg;
    }

    @Override
    public BaseMsg<List<FriendReqMsg>> getRequireFriendList(BigInteger uid) {

        List<FriendReqMsg> friendReqMsgList = friendReqQueRepository.getReqMsg(uid);
        BaseMsg<List<FriendReqMsg>> result = new BaseMsg<>();
        result.setSuccess(true);
        result.setMsg(friendReqMsgList);

        return result;
    }

    @Override
    @Transactional
    public BaseMsg<Null> agree(BigInteger uid, BigInteger fid,Boolean agree) {
        BaseMsg<Null> baseMsg = new BaseMsg<>();
        friendReqQueRepository.deleteOne(uid,fid);
        if (agree) {
            if (friendRepository.hasFriend(uid, fid) != null) {
                baseMsg.setSuccess(false);
                baseMsg.setAlter("好友已经存在");
                return baseMsg;
            }

            Friends friends = new Friends(uid, fid);
            friendRepository.save(friends);

            Friends friends1 = new Friends(fid, uid);
            friendRepository.save(friends1);
        }

        baseMsg.setSuccess(true);
        return baseMsg;
    }
}
